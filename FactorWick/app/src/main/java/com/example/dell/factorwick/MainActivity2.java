package com.example.dell.factorwick;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.View;
import java.util.*;
import java.util.logging.Handler;
import android.media.MediaPlayer;


public class MainActivity2 extends AppCompatActivity {
    TextView textmark;
    Button option1;
    Button option2;
    Button option3;
    Vibrator vibration;
    int activebutton=0;
    TextView timer;
    ImageButton timeup;
    TextView correctanswer;
    int answer;
    ImageButton correctdisplay;
    ImageButton wrongdisplay;
    SharedPreferences varun;
    int score=0;
    TextView scorecard;
    SharedPreferences.Editor asus;
    MediaPlayer soundtimeout;
    MediaPlayer wrongmsgsong;
    MediaPlayer correctmsgsong;
    ImageButton endgame;
    int mani=0;
    CountDownTimer countDownTimer1;
    int usernumber1;
    MediaPlayer countdown1;
    MediaPlayer countdown2;






    //Changed Here
    int y = 0,z = 0,k = 0,x = -1;

    int millisinfuture = 11;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        option1=(Button) findViewById(R.id.button2);
        option2=(Button) findViewById(R.id.button3);
        option3=(Button) findViewById(R.id.button4);
        timer=(TextView)findViewById(R.id.textView2);
        timeup=(ImageButton)findViewById(R.id.imageButton);
        correctanswer=(TextView) findViewById(R.id.correctanswer);
        correctdisplay=(ImageButton) findViewById(R.id.correctdisplay);
        wrongdisplay=(ImageButton)findViewById(R.id.wrongdisplay);
        scorecard=(TextView) findViewById(R.id.textView3);
        soundtimeout=MediaPlayer.create(this,R.raw.soundtimeout);
        wrongmsgsong=MediaPlayer.create(this,R.raw.wronngmsgsong);
        correctmsgsong=MediaPlayer.create(this,R.raw.correctanswersound);
        endgame=(ImageButton)findViewById(R.id.endgame);
        countdown1=MediaPlayer.create(this,R.raw.countdownsound1);
        countdown2=MediaPlayer.create(this,R.raw.countdownsound2);

        //to set scores
        varun=getSharedPreferences("kola",MODE_PRIVATE);
        score=varun.getInt("hema",0);
        //score edit
        asus=varun.edit();








        //setting up time up button behind
        timeup.setTranslationY(-3000f);
        //setting up correctdisplay behind
        correctdisplay.setTranslationY(-3000f);
        //setting up wrongdisplay behind
        wrongdisplay.setTranslationY(-3000f);
        //setting up scorecard








        final RelativeLayout rightwrong = (RelativeLayout) findViewById(R.id.background);

        vibration=(Vibrator)getSystemService(MainActivity2.VIBRATOR_SERVICE);

        // Changed in the millis in Future in CountDownTimer
        millisinfuture = varun.getInt("millisinfuture",11);



          countDownTimer1 = new CountDownTimer(millisinfuture * 1000, 1000) {
            @Override
            public void onTick(long millis) {
                countdown1.start();
                timer.setText("" + (int) (millis / 1000));
                asus = varun.edit();
                asus.putInt("millisinfuture", (int)(millis/1000));
                asus.commit();

            }

            @Override
            public void onFinish() {
                countdown2.start();
                mani=1;
                if (activebutton == 0) {
                    timeup.animate().translationYBy(3000f).rotation(720).setDuration(2000);
                    activebutton = 1;
                    correctanswer.setText("Correct answer = " + answer);
                    soundtimeout.start();


                    //Changed Here
                    asus = varun.edit();
                    asus.putInt("Answer",0);
                    asus.putInt("AnswerTag",-1);
                    asus.putInt("Option1Random",0);
                    asus.putInt("Option2Random",0);
                    asus.putInt("Option3Random",0);
                    asus.putInt("millisinfuture",11);
                    asus.commit();
                    //Changed Here

                     countDownTimer1=new CountDownTimer(5000,5000) {
                        @Override
                        public void onTick(long millisUntilFinished) {

                        }

                        @Override
                        public void onFinish() {
                            Intent maimenu= new Intent(getApplicationContext(),Mainmenu.class);
                            startActivity(maimenu);
                            finish();

                        }
                    }.start();

                }

            }
        }.start();







        Intent intent = getIntent();
        usernumber1 = getIntent().getExtras().getInt("Value");
        textmark = (TextView) findViewById(R.id.textView);
        textmark.setText("Factor of " + usernumber1 + " is:");

         //Factor finder

        int number = usernumber1;
        List factors = new ArrayList();

        for (int i = 1; i <= number; ++i) {
            if (number % i == 0) {
                factors.add(i);}}

        // Changed here
            answer = varun.getInt("Answer",0);

        // Random answer generator from the array
        if (answer == 0)
        {
            int randomposition = new Random().nextInt(factors.size());
            answer = (int) factors.get(randomposition);
            asus = varun.edit();
            asus.putInt("Answer",answer);
            asus.commit();
            asus = varun.edit();
        }

        //Change Ends Here


        // Random number generator for option selection
        Random ran = new Random();

        //Changed Here
        x = varun.getInt("AnswerTag",-1);

        if(x == -1)
        {
            x = ran.nextInt(3);
            asus = varun.edit();
            asus.putInt("AnswerTag",x);
            asus.commit();
            asus = varun.edit();
        }
        //Change Ends Here


        //Declaration of button tags
        final int button1tag= Integer.parseInt(option1.getTag().toString());
        final int button2tag= Integer.parseInt(option2.getTag().toString());
        final int button3tag= Integer.parseInt(option3.getTag().toString());



        if(button1tag==x){
            option1.setText(""+ answer);
        }
        else{
            Random car= new Random();

            //Changed Here
            y = varun.getInt("Option1Random",0);
            if (y==0)
            {
                y = car.nextInt(100);
                while (usernumber1 % y == 0) {
                    y = car.nextInt(100);
                }

                asus = varun.edit();
                asus.putInt("Option1Random",y);
                asus.commit();
                asus = varun.edit();
            }
            //Change Ends Here


            option1.setText(""+ y);
        }

        if (button2tag==x){
            option2.setText("" + answer);
        }
        else {
            //Changed Here
            Random bike = new Random();

            k = varun.getInt("Option2Random",0);
            if (k==0)
            {
                k = bike.nextInt(100);
                while (usernumber1 % k== 0) {
                    k = bike.nextInt(100);
                }

                asus = varun.edit();
                asus.putInt("Option2Random",k);
                asus.commit();
                asus = varun.edit();
            }
            //Change Ends Here

            option2.setText(""+ k);
        }

        if (button3tag==x){
            option3.setText("" + answer);
        }

        else{
            Random train=new Random();

            //Changed Here
            z = varun.getInt("Option3Random",0);
            if (z==0)
            {
                z = train.nextInt(100);
                while (usernumber1 % z == 0) {
                    z = train.nextInt(100);
                }

                asus = varun.edit();
                asus.putInt("Option3Random",z);
                asus.commit();
                asus = varun.edit();
            }
            //Change Ends Here
            option3.setText(""+ z);
        }

        option1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (activebutton==0){
                if (button1tag==x) {
                    rightwrong.setBackgroundColor(Color.GREEN);
                    correctdisplay.animate().translationYBy(3000f).rotation(720).setDuration(2000);
                    countDownTimer1.cancel();
                    score+=5;
                    asus.putInt("hema",score);
                    asus.commit();
                    scorecard.setText("Score: "+score);
                    correctmsgsong.start();
                    mani=2;
                    countDownTimer1 =new CountDownTimer(3000,3000) {
                        @Override
                        public void onTick(long millis) {


                        }

                        @Override
                        public void onFinish() {

                            Intent inputmenu = new Intent(getApplicationContext(),MainActivity.class);

                            //Changed Here
                            asus = varun.edit();
                            asus.putInt("Answer",0);
                            asus.putInt("AnswerTag",-1);
                            asus.putInt("Option1Random",0);
                            asus.putInt("Option2Random",0);
                            asus.putInt("Option3Random",0);
                            asus.putInt("millisinfuture",11);
                            asus.commit();
                            //Changed Here

                            startActivity(inputmenu);
                            finish();
                        }
                    }.start();



                }
                else{
                    rightwrong.setBackgroundColor(Color.RED);
                    vibration.vibrate(3000);
                    wrongdisplay.animate().translationYBy(3000f).rotation(720).setDuration(2000);
                    correctanswer.setText("Correct answer = " +  answer);
                    countDownTimer1.cancel();
                    score-=2;
                    asus.putInt("hema",score);
                    asus.commit();
                    scorecard.setText("Score: "+ score);
                    wrongmsgsong.start();
                    mani=3;
                     countDownTimer1 =new CountDownTimer(3000,3000) {
                        @Override
                        public void onTick(long millis) {


                        }

                        @Override
                        public void onFinish() {

                            Intent inputmenu=new Intent(getApplicationContext(),MainActivity.class);

                            //Changed Here
                            asus = varun.edit();
                            asus.putInt("Answer",0);
                            asus.putInt("AnswerTag",-1);
                            asus.putInt("Option1Random",0);
                            asus.putInt("Option2Random",0);
                            asus.putInt("Option3Random",0);
                            asus.putInt("millisinfuture",11);
                            asus.commit();
                            //Changed Here

                            startActivity(inputmenu);
                            finish();
                        }
                    }.start();
                }
                    activebutton=1;}


            }
        });


        option2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if (activebutton==0){
                if (button2tag==x) {
                    rightwrong.setBackgroundColor(Color.GREEN);
                    correctdisplay.animate().translationYBy(3000f).rotation(720).setDuration(2000);
                    countDownTimer1.cancel();
                    score+=5;
                    asus.putInt("hema",score);
                    asus.commit();
                    scorecard.setText("Score: "+score);
                    correctmsgsong.start();
                    mani=4;
                     countDownTimer1 =new CountDownTimer(3000,3000) {
                        @Override
                        public void onTick(long millis) {

                        }

                        @Override
                        public void onFinish() {

                            Intent inputmenu=new Intent(getApplicationContext(),MainActivity.class);

                            //Changed Here
                            asus = varun.edit();
                            asus.putInt("Answer",0);
                            asus.putInt("AnswerTag",-1);
                            asus.putInt("Option1Random",0);
                            asus.putInt("Option2Random",0);
                            asus.putInt("Option3Random",0);
                            asus.putInt("millisinfuture",11);
                            asus.commit();
                            //Changed Here

                            startActivity(inputmenu);
                            finish();
                        }
                    }.start();


                }
                else {
                    rightwrong.setBackgroundColor(Color.RED);
                    vibration.vibrate(3000);
                    wrongdisplay.animate().translationYBy(3000f).rotation(720).setDuration(2000);
                    correctanswer.setText("Correct answer = " +  answer);
                    countDownTimer1.cancel();
                    score-=2;
                    asus.putInt("hema",score);
                    asus.commit();
                    scorecard.setText("Score: "+score);
                    wrongmsgsong.start();
                    mani=5;
                     countDownTimer1 =new CountDownTimer(3000,3000) {
                        @Override
                        public void onTick(long millis) {


                        }

                        @Override
                        public void onFinish() {

                            Intent inputmenu=new Intent(getApplicationContext(),MainActivity.class);


                            //Changed Here
                            asus = varun.edit();
                            asus.putInt("Answer",0);
                            asus.putInt("AnswerTag",-1);
                            asus.putInt("Option1Random",0);
                            asus.putInt("Option2Random",0);
                            asus.putInt("Option3Random",0);
                            asus.putInt("millisinfuture",11);
                            asus.commit();
                            //Changed Here

                            startActivity(inputmenu);
                            finish();
                        }
                    }.start();






                }
                    activebutton=1;}

            }
        });


        option3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if (activebutton==0){
                if (button3tag==x){
                    rightwrong.setBackgroundColor(Color.GREEN);
                    correctdisplay.animate().translationYBy(3000f).rotation(720).setDuration(2000);
                    countDownTimer1.cancel();
                    score+=5;
                    asus.putInt("hema",score);
                    asus.commit();
                    scorecard.setText("Score: "+score);
                    correctmsgsong.start();
                    mani=6;
                     countDownTimer1 =new CountDownTimer(3000,3000) {
                        @Override
                        public void onTick(long millis) {


                        }

                        @Override
                        public void onFinish() {

                            Intent inputmenu=new Intent(getApplicationContext(),MainActivity.class);


                            //Changed Here
                            asus = varun.edit();
                            asus.putInt("Answer",0);
                            asus.putInt("AnswerTag",-1);
                            asus.putInt("Option1Random",0);
                            asus.putInt("Option2Random",0);
                            asus.putInt("Option3Random",0);
                            asus.putInt("millisinfuture",11);
                            asus.commit();
                            //Changed Here

                            startActivity(inputmenu);
                            finish();
                        }
                    }.start();


                }
                else {
                    rightwrong.setBackgroundColor(Color.RED);
                    vibration.vibrate(3000);
                    wrongdisplay.animate().translationYBy(3000f).rotation(720).setDuration(2000);
                    correctanswer.setText("Correct answer = " +  answer);
                    countDownTimer1.cancel();
                    score-=2;
                    asus.putInt("hema",score);
                    asus.commit();
                    scorecard.setText("Score: "+score);
                    wrongmsgsong.start();
                    mani=7;
                     countDownTimer1 =new CountDownTimer(3000,3000) {
                        @Override
                        public void onTick(long millis) {


                        }

                        @Override
                        public void onFinish() {

                            Intent inputmenu=new Intent(getApplicationContext(),MainActivity.class);


                            //Changed Here
                            asus = varun.edit();
                            asus.putInt("Answer",0);
                            asus.putInt("AnswerTag",-1);
                            asus.putInt("Option1Random",0);
                            asus.putInt("Option2Random",0);
                            asus.putInt("Option3Random",0);
                            asus.putInt("millisinfuture",11);
                            asus.commit();
                            //Changed Here


                            startActivity(inputmenu);
                            finish();
                        }
                    }.start();





                }
                    activebutton=1;}

            }
        });
        endgame.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainmenu=new Intent(getApplicationContext(),Mainmenu.class);


                //Changed Here
                asus = varun.edit();
                asus.putInt("Answer",0);
                asus.putInt("AnswerTag",-1);
                asus.putInt("Option1Random",0);
                asus.putInt("Option2Random",0);
                asus.putInt("Option3Random",0);
                asus.putInt("millisinfuture",11);
                asus.commit();
                //Changed Here

                startActivity(mainmenu);
                finish();
                countDownTimer1.cancel();


            }

        });}
         @Override
        public void onConfigurationChanged(Configuration newConfig){
             countDownTimer1.cancel();
             super.onConfigurationChanged(newConfig);

             if(mani==1)
             {
                 Intent maimenu= new Intent(MainActivity2.this,Mainmenu.class);
                 startActivity(maimenu);
                 finish();
             }
            else if(mani==2){
                 Intent inputmenu = new Intent(MainActivity2.this,MainActivity.class);

                 //Changed Here
                 asus = varun.edit();
                 asus.putInt("Answer",0);
                 asus.putInt("AnswerTag",-1);
                 asus.putInt("Option1Random",0);
                 asus.putInt("Option2Random",0);
                 asus.putInt("Option3Random",0);
                 asus.putInt("millisinfuture",11);
                 asus.commit();
                 //Changed Here

                 startActivity(inputmenu);
                 finish();
             }
            else if(mani==3){
                 Intent inputmenu=new Intent(MainActivity2.this,MainActivity.class);

                 //Changed Here
                 asus = varun.edit();
                 asus.putInt("Answer",0);
                 asus.putInt("AnswerTag",-1);
                 asus.putInt("Option1Random",0);
                 asus.putInt("Option2Random",0);
                 asus.putInt("Option3Random",0);
                 asus.putInt("millisinfuture",11);
                 asus.commit();
                 //Changed Here

                 startActivity(inputmenu);
                 finish();

             }
            else if(mani==4){
                 Intent inputmenu=new Intent(MainActivity2.this,MainActivity.class);

                 //Changed Here
                 asus = varun.edit();
                 asus.putInt("Answer",0);
                 asus.putInt("AnswerTag",-1);
                 asus.putInt("Option1Random",0);
                 asus.putInt("Option2Random",0);
                 asus.putInt("Option3Random",0);
                 asus.putInt("millisinfuture",11);
                 asus.commit();


                 startActivity(inputmenu);
                 finish();

             }
            else if(mani==5){
                 Intent inputmenu=new Intent(MainActivity2.this,MainActivity.class);


                 //Changed Here
                 asus = varun.edit();
                 asus.putInt("Answer",0);
                 asus.putInt("AnswerTag",-1);
                 asus.putInt("Option1Random",0);
                 asus.putInt("Option2Random",0);
                 asus.putInt("Option3Random",0);
                 asus.putInt("millisinfuture",11);
                 asus.commit();


                 startActivity(inputmenu);
                 finish();

             }
           else if(mani==6){
                 Intent inputmenu=new Intent(MainActivity2.this,MainActivity.class);


                 //Changed Here
                 asus = varun.edit();
                 asus.putInt("Answer",0);
                 asus.putInt("AnswerTag",-1);
                 asus.putInt("Option1Random",0);
                 asus.putInt("Option2Random",0);
                 asus.putInt("Option3Random",0);
                 asus.putInt("millisinfuture",11);
                 asus.commit();
                 //Changed Here

                 startActivity(inputmenu);
                 finish();
             }
            else if(mani==7){
                 Intent inputmenu=new Intent(MainActivity2.this,MainActivity.class);


                 //Changed Here
                 asus = varun.edit();
                 asus.putInt("Answer",0);
                 asus.putInt("AnswerTag",-1);
                 asus.putInt("Option1Random",0);
                 asus.putInt("Option2Random",0);
                 asus.putInt("Option3Random",0);
                 asus.putInt("millisinfuture",11);
                 asus.commit();
                 //Changed Here


                 startActivity(inputmenu);
                 finish();
             }
             else{

                 Intent m=new Intent(MainActivity2.this,MainActivity2.class);
                 m.putExtra("Value",usernumber1);
                 startActivity(m);
                 finish();
             }


         }




            }



















