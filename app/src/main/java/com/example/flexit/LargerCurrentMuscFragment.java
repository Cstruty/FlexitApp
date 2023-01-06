package com.example.flexit;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CurrentMuscFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LargerCurrentMuscFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private final ArrayList<getUserSong> getUserSongArrList;
    //    private final Object musicActivity;
// TODO: Rename and change types of parameters
    private int currentPos;
    private final Context MusicContent;
    private MediaPlayer MP;
    private Boolean playing=true;
    private Boolean musicFreed=false;
    private     TextView song;
    private ImageView play;
    private TextView artist;
    private UserSongAdapter userSongAdapter;
    private MusicActivity musicActivity;
    private CurrentMuscFragment smallFragment;

    public LargerCurrentMuscFragment(ArrayList<getUserSong> getUserSongArrList, int currentPos, Context MusicContent,MediaPlayer MP,UserSongAdapter userSongAdapter,MusicActivity musicActivity,CurrentMuscFragment smallFragment) {
        // Required empty public constructor
        this.MusicContent = MusicContent;
        this.getUserSongArrList=getUserSongArrList;
        this.currentPos=currentPos;
        this.MP=MP;
        this.userSongAdapter=userSongAdapter;
        this.musicActivity=musicActivity;
        this.smallFragment=smallFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_larger_current_musc, container, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                musicActivity.LargerCurrentSongFragment(getUserSongArrList,currentPos,MusicContent,MP);
            }
        });

        return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){

        final getUserSong currentSong = getUserSongArrList.get(currentPos);

        song =(TextView) view.findViewById(R.id.NameMusictext);
        song.setText(currentSong.getName());
        artist=(TextView)view.findViewById(R.id.Artisttext);
        artist.setText(currentSong.getArtist());
        play=(ImageView) view.findViewById(R.id.play);
        if(MP.isPlaying()) {
            play.setImageResource(R.drawable.pauselogo);
        }else{
            play.setImageResource(R.drawable.playlogo);
        }
        //on click the music is played
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(musicFreed){
                    MP = MediaPlayer.create(MusicContent, currentSong.getCurrentSong());
                    musicFreed=false;
                }
                if(MP.isPlaying()) {
                    MP.pause();// pauseing the music
                    play.setImageResource(R.drawable.playlogo);
                    userSongAdapter.updatepause(currentPos);
                    playing=false;
                    smallFragment.update(currentPos,playing,false,MP);
                } else {
                    MP.start();// start the music
                    play.setImageResource(R.drawable.pauselogo);
                    userSongAdapter.updateplay(currentPos);
                    playing=true;
                    smallFragment.update(currentPos,playing,false,MP);
                }
            }
        });
        //this pauses and frees the previous song, then gets the next song, then play it if the play button is on
        ImageView skipFoward=(ImageView) view.findViewById(R.id.foward);
        skipFoward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUserSong currentSong;
                int prevPos=currentPos;
                currentPos++;
                if(currentPos>=getUserSongArrList.size()){
                    currentSong=getUserSongArrList.get(0);
                    currentPos=0;
                }else{
                    currentSong=getUserSongArrList.get(currentPos);

                }
                MP.stop();
                MP.release();
                userSongAdapter.update();
                userSongAdapter.updatepause(prevPos);
                userSongAdapter.updateplay(currentPos);
                smallFragment.update(currentPos,playing,true,MP);
                MP = MediaPlayer.create(MusicContent, currentSong.getCurrentSong());
                if(playing){
                    MP.start();
                }

                song.setText(currentSong.getName());
                artist.setText(currentSong.getArtist());
            }
        });
        //this pauses and frees the previous song, then gets the song before it, then play it if the play button is on
        ImageView skipBackwards=(ImageView) view.findViewById(R.id.back);
        skipBackwards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUserSong currentSong;
                int prevPos=currentPos;
                currentPos--;
                if(currentPos<0){
                    currentSong=getUserSongArrList.get(getUserSongArrList.size()-1);
                    currentPos=getUserSongArrList.size()-1;
                }else{
                    currentSong=getUserSongArrList.get(currentPos);

                }

                MP.stop();
                MP.release();
                userSongAdapter.update();
                userSongAdapter.updatepause(prevPos);
                userSongAdapter.updateplay(currentPos);
                smallFragment.update(currentPos,playing,true,MP);
                MP = MediaPlayer.create(MusicContent, currentSong.getCurrentSong());
                if(playing){
                    MP.start();
                }

                song.setText(currentSong.getName());
                artist.setText(currentSong.getArtist());

            }
        });
        ImageView close=(ImageView) view.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                musicActivity.closeLargerFragment();
            }
        });

    }
    //this is currently not needed but updates the larger fragment, can be for furture implementation if needed
    public void update(int currentPosition,boolean playingmusic,Boolean musicfreed){
        final getUserSong currentSong = getUserSongArrList.get(currentPosition);
        song.setText(currentSong.getName());
        if(playingmusic){
            play.setImageResource(R.drawable.pauselogo);
        }else{
            play.setImageResource(R.drawable.playlogo);
        }
        if(musicfreed){
            musicFreed=true;
        }


    }


    @Override
    public void onClick(View view) {

    }
}