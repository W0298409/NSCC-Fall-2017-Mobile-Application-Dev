package com.example.paul.assignment2quizgame;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.design.widget.Snackbar;
import java.util.ArrayList;
import java.util.Random;


public class QuizActivity extends AppCompatActivity {

    Random random = new Random();
    DataHandler dh;

    private TextView tvQuestion;
    private TextView tvShowName;
    private TextView tvScore;

    private Button btnReturnMain;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private ArrayList<Button> answerButtons = new ArrayList<>();

    private Integer quizLenght;
    private Integer score = 0;

    private int questionsAsked = 0;
    private int correctAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        //data from main activity
        Bundle userName = getIntent().getExtras();
        //setting up textviews with Res
        tvShowName = (TextView)findViewById(R.id.tvShowName);
        tvQuestion = (TextView)findViewById(R.id.tvQuestion);
        tvScore = (TextView)findViewById(R.id.tvScore);
        //setting up buttons and adding to button list
        btnReturnMain = (Button)findViewById(R.id.btnHome);
        btn1 = (Button)findViewById(R.id.btnAns1);
        btn2 = (Button)findViewById(R.id.btnAns2);
        btn3 = (Button)findViewById(R.id.btnAns3);
        btn4 = (Button)findViewById(R.id.btnAns4);
        answerButtons.add(btn1);
        answerButtons.add(btn2);
        answerButtons.add(btn3);
        answerButtons.add(btn4);
        //instancing datahandles
        dh  = new DataHandler(this.getApplicationContext());
        //read questions and answers from txt file
        dh.readFile();
        //get the length of the quiz
        quizLenght=dh.getQuizLength();
        //Set player name
        tvShowName.setText(userName.getString("name"));
        //setup a round of questions
        setNewRound();
        //setting up on click listeners from answer buttons
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(correctAnswer==0)
                {
                    score++;

                    if(questionsAsked==quizLenght)
                    {
                        gameOver();
                    }
                    questionsAsked++;
                    setNewRound();

                    Snackbar.make(view,"Good guess!",Snackbar.LENGTH_SHORT).show();
                } else {
                    Snackbar.make(view, "Bad guess!", Snackbar.LENGTH_SHORT).show();
                    if(questionsAsked==quizLenght)
                    {
                        gameOver();
                    }
                    questionsAsked++;
                    setNewRound();
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(correctAnswer==1)
                {
                    score++;

                    if(questionsAsked==quizLenght)
                    {
                        gameOver();
                    }
                    questionsAsked++;
                    setNewRound();

                    Snackbar.make(view,"Good guess!",Snackbar.LENGTH_SHORT).show();
                } else {

                    Snackbar.make(view, "Bad guess!", Snackbar.LENGTH_SHORT).show();
                    if(questionsAsked==quizLenght)
                    {
                        gameOver();
                    }
                    questionsAsked++;
                    setNewRound();
                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(correctAnswer==2)
                {
                    score++;

                    if(questionsAsked==quizLenght)
                    {
                        gameOver();
                    }
                    questionsAsked++;
                    setNewRound();

                    Snackbar.make(view,"Good guess!",Snackbar.LENGTH_SHORT).show();
                } else {
                    Snackbar.make(view, "Bad guess!", Snackbar.LENGTH_SHORT).show();
                    if(questionsAsked==quizLenght)
                    {
                        gameOver();
                    }
                    questionsAsked++;
                    setNewRound();
                }
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(correctAnswer==3)
                {
                    score++;

                    if(questionsAsked==quizLenght)
                    {
                        gameOver();
                    }
                    questionsAsked++;
                    setNewRound();
                    Snackbar.make(view,"Good guess!",Snackbar.LENGTH_SHORT).show();

                } else {
                    Snackbar.make(view, "Bad guess!", Snackbar.LENGTH_SHORT).show();
                    if(questionsAsked==quizLenght)
                    {
                        gameOver();
                    }
                    questionsAsked++;
                    setNewRound();
                }
            }
        });

        btnReturnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //On click return home
                Intent goHome = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(goHome);
            }
        });

    }

    public void setNewRound() {
        //Sets up the next question
        dh.setNextQuestion();
        //randomly selects position for correct answer
        correctAnswer = (random.nextInt(3));
        //using random number, the correct answer button is selected by arrany index
        //gets correct answer from data handler
        answerButtons.get(correctAnswer).setText(dh.getCorrectAnswer());
        //sets up the wrong answers
        dh.newWrongAnswers();
        //gets current question
        tvQuestion.setText(dh.getQuestion());
        //updates the score
        tvScore.setText(getScore());
        //loops through answerButtons list and sets up wrong answers
        for (int x = 0; x < answerButtons.size(); x++) {
            if (correctAnswer != x) {
                answerButtons.get(x).setText(dh.getWrongAnswer());
            }
        }
    }

    public void gameOver()
    {
        //incrments quiz length because index starts a 0
        quizLenght++;
        //setups up intent and bundles data for game over activity
        Intent gameOver = new Intent(getApplicationContext(),GameOverActivity.class);
        gameOver.putExtra("name",tvShowName.getText().toString());
        gameOver.putExtra("score",score.toString());
        gameOver.putExtra("quesAsked",quizLenght.toString());
        startActivity(gameOver);

    }

    private String getScore()
    {
        return "Score: "+score.toString();
    }
}
