package com.example.paul.assignment2quizgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {

    private TextView tvPlayerName;
    private TextView tvCongrats;
    private TextView tvMessage;

    private Button btnHome;

    String message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        //sets up text views by resource id
        tvPlayerName = (TextView)findViewById(R.id.tvPlayerName);
        tvCongrats = (TextView)findViewById(R.id.tvCongrats);
        tvMessage = (TextView)findViewById(R.id.tvMessage);
        //sets up button by resource id
        btnHome = (Button)findViewById(R.id.btnHome);
        //creates bundle object for passed in data
        Bundle bundle = getIntent().getExtras();
        //makes score message
        message = "You scored "+bundle.getString("score")+" out of "+bundle.getString("quesAsked");
        //sets text to UI
        tvPlayerName.setText(bundle.getString("name"));
        tvCongrats.setText(R.string.Congrats);
        tvMessage.setText(message);
        //on click listener for returning to main menu
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goHome = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(goHome);
            }
        });
    }
}
