package com.example.flexit;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import java.util.ArrayList;
import android.view.LayoutInflater;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class UserSongAdapter extends BaseAdapter {

    private Context MusicContent;
    private int Usermusic;
    private ArrayList<getUserSong> getUserSongArrList;
    private MediaPlayer MP;
    private Boolean validate = true;
    private MusicActivity musicActivity;
    private CurrentMuscFragment fragment=null;
    private boolean SongupdatefromFragment=false;
    private boolean fragmentMade=false;
    private boolean playing=false;
    private ArrayList<getView> Views=new ArrayList<>();
    private String lastsong="nada";
    private int lastsongindex=0;
    private boolean firstrun=true;

    public UserSongAdapter(Context MusicContent, int Usermusic, ArrayList<getUserSong> getUserSongArrList,MusicActivity MusicActivity) {
        this.MusicContent = MusicContent;
        this.Usermusic = Usermusic;
        this.getUserSongArrList = getUserSongArrList;
        this.musicActivity=MusicActivity;
    }


    @Override// tehse are built in from array list , baseadapter is a built in adapter in android studio, everything is already pre initialized
    public int getCount() {
        return getUserSongArrList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class getView {
        ImageView playView, stopView;

        TextView nameView, songView;
    }


    @Override
    public View getView(int currentpos, View getUserView, ViewGroup view) {
        final getView getView;
        if(getUserView == null){
            getView = new getView();
            LayoutInflater layoutInflater = (LayoutInflater) MusicContent.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            getUserView = layoutInflater.inflate(Usermusic, null);
            getView.nameView = (TextView) getUserView.findViewById(R.id.NameMusictext);
            getView.songView = (TextView) getUserView.findViewById(R.id.Songtext);
            getView.playView = (ImageView) getUserView.findViewById(R.id.play);
//            getView.stopView = (ImageView) getUserView.findViewById(R.id.stop);
            getUserView.setTag(getView);
        } else {
            getView = (getView) getUserView.getTag();
        }

        final getUserSong currentSong = getUserSongArrList.get(currentpos);

        getView.nameView.setText(currentSong.getName());
        getView.songView.setText(currentSong.getArtist());

        // play the music for the user
        getView.playView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final getView lastsongview;
                lastsongview=Views.get(currentpos);
                final getUserSong song=getUserSongArrList.get(currentpos);
              //this checks if the song is switching, if it is then the prev song music is pause and freed
                //then the prev song logo is placed to "play" to show it's no longer playing
                if(lastsongview.nameView.getText().toString().equals(lastsong)==true||((lastsong.equals("nada")||firstrun==true))){
                }else{
                        if (MP.isPlaying()) {
                            MP.pause();
                        }
                    MP.release();
                    validate=true;

                    updatepause(lastsongindex);


                }


                if(validate){
                    MP = MediaPlayer.create(MusicContent, song.getCurrentSong());// setting the music player up
                    firstrun=false;
                    validate = false;
                }
                if(MP.isPlaying()) {
                    MP.pause();// pauseing the music
                    playing=false;
                    getView.playView.setImageResource(R.drawable.playlogo);
                } else {
                    MP.start();// start the music
                    playing=true;
                    getView.playView.setImageResource(R.drawable.pauselogo);
                }
                //if the first time through, the fragment is made
            if(fragmentMade==false) {
                fragmentMade=true;
                fragment = musicActivity.CurrentSongFragment(getUserSongArrList, currentpos, MusicContent, MP);
            }else{
                //all the other times it's just updated
                fragment.update(currentpos,playing,false,MP);
            }
                lastsongindex=currentpos;
                lastsong=currentSong.getName();
            }
        });

        // stop
//        getView.stopView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(!validate) {
//                    MP.stop();
//                    MP.release();
//                    validate = true;
//                }
//                playing=false;
//                if(fragmentMade==false) {
//                }else{
//
//                    fragment.update(currentpos,playing,true);
//                }
//                getView.playView.setImageResource(R.drawable.playlogo);
//            }
//        });
        Views.add(getView);
        return getUserView;
    }
    public void update(){
        if(!validate){
            validate=true;
        }
    }
    public void updatepause(int currentPosition){
        final getView getView;

        getView=Views.get(currentPosition);
        getView.playView.setImageResource(R.drawable.playlogo);
        lastsong="nada";
        SongupdatefromFragment=true;
        lastsongindex=currentPosition;
    }
    public void updateplay(int nextPosition){
        final getView getView;
        getView=Views.get(nextPosition);
        getView.playView.setImageResource(R.drawable.pauselogo);
        lastsong="nada";
        lastsongindex=nextPosition;
    }
}
