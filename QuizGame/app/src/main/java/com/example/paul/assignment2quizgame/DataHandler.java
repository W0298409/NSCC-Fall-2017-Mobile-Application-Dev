package com.example.paul.assignment2quizgame;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;



/**
 * Created by paul on 10/22/2017.
 */

public class DataHandler extends QuizActivity{
    Context myContext;

    Random random = new Random();

    private static final String TAG = "DataHandler";

    private ArrayList<String> questions = new ArrayList<>();

    private HashMap<String,String> hash = new HashMap();

    private String[] split;

    private String tempLine;
    private String delimiter = ":";

    private int[] wrongAns = new int[3];

    private int questionIncrement =0;
    private int wrongAnsIncr = -1;

    public DataHandler(Context context)
    {
        this.myContext=context;
    }

    public void readFile(){
        //uses context from UI so it can access resource folder
        InputStream is = myContext.getResources().openRawResource(R.raw.qa);
        //Buffered reader using created input stream
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        try {
            //while there is a line to read in reader
            while ((tempLine=reader.readLine()) !=null)
            {
                //split the line by delimiter
                split = tempLine.split(delimiter);
                //first half of string is the question and it is added to the questions list
                questions.add(split[0]);
                //adds the question and answer to hashmap
                hash.put(split[0],split[1]);
            }
        } catch (IOException e) {
            Log.e(TAG, "Exception: "+Log.getStackTraceString(e));
            e.printStackTrace();
        }
        //shuffles the list of questions
        Collections.shuffle(questions);
    }

    public String getQuestion()
    {
        //gets the next question in the list
        return questions.get(questionIncrement);
    }


    public String getCorrectAnswer(){
        //gets the correct answer
        return hash.get(questions.get(questionIncrement));
    }

    public String getWrongAnswer(){
        //increments the wrong answer number
        wrongAnsIncr++;
        //using the hasmap it finds the answer by using the random index number made by newWrongAnswers
        return hash.get(questions.get(wrongAns[wrongAnsIncr]));
    }

    public int getQuizLength(){
        return questions.size();
    }

    public void setNextQuestion()
    {
        //increment index used to get questions
        questionIncrement++;
        //resets the wrong answers increment to -1
        wrongAnsIncr=-1;
        //generates a new int array of random wrong answers
        newWrongAnswers();

    }

    public void newWrongAnswers()
    {
        int tempRandom = random.nextInt(questions.size()-1);
        int newRandom1 = tempRandom;
        int newRandom2;
        int newRandom3;
        //makes sure that the random wrong answer does not match the correct answer
        while (tempRandom==wrongAnsIncr) {tempRandom = random.nextInt(questions.size()-1);}
        wrongAns[0]=newRandom1;
        tempRandom = random.nextInt(questions.size()-1);
        //makes sure answer and first wrong answer do not match the new random number
        while(tempRandom==newRandom1&&tempRandom!=wrongAnsIncr) {tempRandom = random.nextInt(questions.size()-1);}
        newRandom2 = tempRandom = random.nextInt(questions.size()-1);
        wrongAns[1] = newRandom2;
        tempRandom = random.nextInt(questions.size()-1);
        //same as above with first and second wrong answers
        while((tempRandom==newRandom1 || tempRandom==newRandom2)&&tempRandom!=wrongAnsIncr) {tempRandom = random.nextInt(questions.size()-1);}
        newRandom3 = tempRandom;
        wrongAns[2] = newRandom3;
    }
}


