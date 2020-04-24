package com.example.dell.factorwick;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.media.MediaPlayer;

public class MainActivity extends AppCompatActivity {
   Button backtomainmenu;
    TextView score2;
    SharedPreferences akila;
    MediaPlayer buttonsong;


    public void hunt(View view) {
        EditText inputNumber = (EditText) findViewById(R.id.editText);
        String inputstring=inputNumber.getText().toString();
        if (inputstring.matches("")){
            Toast.makeText(MainActivity.this, "Enter a number", Toast.LENGTH_SHORT).show();

        }
        else {
            int usernumber = Integer.parseInt(inputNumber.getText().toString());
            if (usernumber != 0) {
            Intent i = new Intent(getApplicationContext(), MainActivity2.class);
            i.putExtra("Value", usernumber);
            startActivity(i);
            finish();
            buttonsong.start();
                MySingleton.getInstance(getApplicationContext()).stop();
                MySingleton.setInstance();

        }

        else {
            Toast.makeText(MainActivity.this, "Enter a non zero digit", Toast.LENGTH_SHORT).show();
        }
    }}



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            backtomainmenu=(Button)findViewById(R.id.backtomainmenu);
            score2=(TextView) findViewById(R.id.score2);
            buttonsong=MediaPlayer.create(this,R.raw.button);
            EditText inputNumber = (EditText) findViewById(R.id.editText);
            if (!MySingleton.getInstance(this).isPlaying())
            { MySingleton.getInstance(this).start();}



            backtomainmenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent k=new Intent(getApplicationContext(),Mainmenu.class);
                    startActivity(k);
                    finish();
                    buttonsong.start();
                    MySingleton.getInstance(getApplicationContext()).stop();
                    MySingleton.setInstance();

                }
            });

            akila=getSharedPreferences("kola",MODE_PRIVATE);
            akila.getInt("hema",0);

            score2.setText("Score:"+ akila.getInt("hema",0) );












        }
    }
