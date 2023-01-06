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
public class CurrentMuscFragment extends Fragment implements View.OnClickListener {

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
    private UserSongAdapter userSongAdapter;
    private MusicActivity musicActivity;

    public CurrentMuscFragment(ArrayList<getUserSong> getUserSongArrList, int currentPos, Context MusicContent,MediaPlayer MP,UserSongAdapter userSongAdapter,MusicActivity musicActivity) {
    // Required empty public constructor
    this.MusicContent = MusicContent;
    this.getUserSongArrList=getUserSongArrList;
    this.currentPos=currentPos;
    this.MP=MP;
    this.userSongAdapter=userSongAdapter;
    this.musicActivity=musicActivity;
}
/**
 * Use this factory method to create a new instance of
 * this fragment using the provided parameters.
 *
 * @param param1 Parameter 1.
 * @param param2 Parameter 2.
 * @return A new instance of fragment CurrentMuscFragment.
 */
// TODO: Rename and change types and number of parameters
//    public static CurrentMuscFragment newInstance(String param1, String param2) {
//        CurrentMuscFragment fragment = new CurrentMuscFragment(musicContent);
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
}

@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view=inflater.inflate(R.layout.fragment_current_musc, container, false);
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

    getUserSong currentSong = getUserSongArrList.get(currentPos);

    song =(TextView) view.findViewById(R.id.NameMusictext);
    song.setText(currentSong.getName());
    play=(ImageView) view.findViewById(R.id.play);
    //on click the music is played
    play.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            getUserSong music=getUserSongArrList.get(currentPos);
            if(musicFreed){
                MP = MediaPlayer.create(MusicContent, music.getCurrentSong());
                musicFreed=false;
            }
            if(MP.isPlaying()) {
                MP.pause();// pauseing the music
                play.setImageResource(R.drawable.playlogo);
                userSongAdapter.updatepause(currentPos);
                playing=false;
            } else {
                MP.start();// start the music
                play.setImageResource(R.drawable.pauselogo);
                userSongAdapter.updateplay(currentPos);
                playing=true;
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

            MP = MediaPlayer.create(MusicContent, currentSong.getCurrentSong());
            Log.e("tag","playing:"+playing);
            if(playing){
                MP.start();
                userSongAdapter.updateplay(currentPos);
            }

            song.setText(currentSong.getName());

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

            MP = MediaPlayer.create(MusicContent, currentSong.getCurrentSong());
            if(playing){
                MP.start();
                userSongAdapter.updateplay(currentPos);
            }

            song.setText(currentSong.getName());

        }
    });


}
//this function is for if the larger fragment is use and it'll update the values for the smaller fragment or if the list is used
public void update(int currentPosition,boolean playingmusic,Boolean musicfreed,MediaPlayer MediaPlayer){
    final getUserSong currentSong = getUserSongArrList.get(currentPosition);

    currentPos=currentPosition;
    song.setText(currentSong.getName());
    if(playingmusic){
        play.setImageResource(R.drawable.pauselogo);
    }else{
        play.setImageResource(R.drawable.playlogo);
    }
    MP=MediaPlayer;


}


    @Override
    public void onClick(View view) {

    }
}