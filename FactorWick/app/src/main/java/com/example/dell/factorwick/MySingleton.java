package com.example.dell.factorwick;

import android.app.Application;
import android.content.Context;
import android.media.MediaPlayer;

public final class MySingleton extends Application {
   static MediaPlayer instance;
   public static MediaPlayer getInstance(Context song) {
       if (instance == null) {
           instance = MediaPlayer.create(song, R.raw.inputmenusong);
       }

       return instance;
   }
    public static void setInstance(){
        instance=null;
    };
}
