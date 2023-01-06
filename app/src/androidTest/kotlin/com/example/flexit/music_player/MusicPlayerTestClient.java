package com.example.flexit.music_player;

import  org.junit.Test;
import static org.junit.Assert.assertTrue;

import android.content.Context;
import android.media.MediaPlayer;

public class MusicPlayerTestClient {

    private MediaPlayer MP;
    private boolean sucess = false;
    private Context context;

    public void setupTester(Context context){
        MP = new MediaPlayer();
        this.context = context;
    }

    // to use this function you must pass the correct context to this class from the applicaiton
    public boolean testUpdateFragment(){
        MP = MediaPlayer.create(context, 0);// setting the music player up
       MP.start();
       if (MP.isPlaying()){
           sucess = true;
       }
       MP.stop();
        if (!MP.isPlaying()){
            sucess = true;
        }
        assertTrue(sucess);
        return sucess;
    }
}
