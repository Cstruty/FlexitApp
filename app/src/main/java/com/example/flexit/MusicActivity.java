package com.example.flexit;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

public class MusicActivity extends AppCompatActivity {

    private ArrayList<getUserSong> insertGetUserSong;
    private ListView songList;
    private UserSongAdapter adapter;
    private CurrentMuscFragment currentSongSmall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        songList = (ListView) findViewById(R.id.songList);
        insertGetUserSong = new ArrayList<>();      //manually adding songs, these represents, everyones favorite song
        insertGetUserSong.add(new getUserSong("A Change Is Gonna Come", "reta van fleet", R.raw.gretavan));
        insertGetUserSong.add(new getUserSong("Over The Hills And Far Away", "Night Wish", R.raw.nightwish));
        insertGetUserSong.add(new getUserSong("Ayushmann Khurrana ", "Tequila ", R.raw.teq));
        insertGetUserSong.add(new getUserSong("Lost", "France Ocean", R.raw.lost));
        insertGetUserSong.add(new getUserSong("Feelin you ", "Abe ", R.raw.feelingyou));
        insertGetUserSong.add(new getUserSong("Farewell ", "J cole ", R.raw.farewell));
        insertGetUserSong.add(new getUserSong("None Shall Pass ", "Aesop Rock ", R.raw.aesopr));
        insertGetUserSong.add(new getUserSong("Lost You  ", "Zeds dead ", R.raw.zedsdead));
        insertGetUserSong.add(new getUserSong("Rivers of Babylon ", "Bony M ", R.raw.babylon));
        insertGetUserSong.add(new getUserSong("Cotton Eye Joe ", "Rednex", R.raw.conttoneye));
        insertGetUserSong.add(new getUserSong("Believer ", "Imagine Dragon", R.raw.believer));



        adapter = new UserSongAdapter(this, R.layout.content_musc, insertGetUserSong,this);
        songList.setAdapter(adapter);// call out adapter class

    }
    //This function is called from inside UserSongAdapter whenever a song is selected and creates the first fragment
    public CurrentMuscFragment CurrentSongFragment(ArrayList<getUserSong> getUserSongArrList, int currentPos, Context MusicContent, MediaPlayer MP){
        FragmentManager fm=getSupportFragmentManager();
        currentSongSmall=new CurrentMuscFragment(getUserSongArrList,currentPos,MusicContent,MP,adapter,this);
        Fragment currentSong=currentSongSmall;
        FragmentTransaction transaction= fm.beginTransaction();
        transaction.replace(R.id.container,currentSong);
        transaction.addToBackStack(null);
        transaction.commit();
        return (CurrentMuscFragment) currentSong;
    }
    //This function is called when the user clicks on the smaller fragment to expand it, this makes the larger fragment
    public LargerCurrentMuscFragment LargerCurrentSongFragment(ArrayList<getUserSong> getUserSongArrList, int currentPos, Context MusicContent, MediaPlayer MP){
        FragmentManager fm=getSupportFragmentManager();
        Fragment currentSong =new LargerCurrentMuscFragment(getUserSongArrList,currentPos,MusicContent,MP,adapter,this,currentSongSmall);
        FragmentTransaction transaction= fm.beginTransaction();
        transaction.replace(R.id.containerlarger,currentSong);
        transaction.addToBackStack(null);
        transaction.commit();
        return (LargerCurrentMuscFragment) currentSong;
    }
    //this function closes the larger fragment
    public void closeLargerFragment(){
        FragmentManager fm=getSupportFragmentManager();
        fm.popBackStack();
    }

}
