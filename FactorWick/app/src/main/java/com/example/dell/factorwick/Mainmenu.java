package com.example.dell.factorwick;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.media.MediaPlayer;

public class Mainmenu extends AppCompatActivity {
    Button startgame;
    int score;
    int highscore;
    SharedPreferences rigved;
    TextView mainmenuscore;
    SharedPreferences.Editor pri;
    MediaPlayer mainmenu;
    MediaPlayer buttonsong;
    Button exitbutton;
    TextView currentscore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);
        startgame=(Button) findViewById(R.id.startgame);
        mainmenuscore=(TextView) findViewById(R.id.highestscore);
        rigved=getSharedPreferences("kola",MODE_PRIVATE);
        buttonsong=MediaPlayer.create(this,R.raw.button);
        exitbutton=(Button) findViewById(R.id.button6);
        currentscore=(TextView) findViewById(R.id.textView4);






        //setting new high score button


        startgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k=new Intent(getApplicationContext(),MainActivity.class);
                pri=rigved.edit();
                pri.putInt("hema",0);
                pri.commit();
                startActivity(k);
                finish();
                buttonsong.start();


            }
        });



        score=rigved.getInt("hema",0);
        highscore=rigved.getInt("arunesh",0);

        if (highscore>=score){
            mainmenuscore.setText("High Score: " + highscore);

        }
        else {
            highscore = score;
            pri= rigved.edit();
            pri.putInt("arunesh", highscore);
            pri.commit();
            mainmenuscore.setText("High Score: " + highscore);

        }
        // setting currentscore
        currentscore.setText("Current Score: "+score);
        exitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
                buttonsong.start();
            }
        });


    }
    @Override
    public  void onStop(){
        Mysingleton1.getInstance(getApplicationContext()).pause();
        super.onStop();
    }
    @Override
    public void onStart()
    {
        Mysingleton1.getInstance(getApplicationContext()).start();
        Mysingleton1.getInstance(getApplicationContext()).setLooping(true);
        super.onStart();
    }
}
