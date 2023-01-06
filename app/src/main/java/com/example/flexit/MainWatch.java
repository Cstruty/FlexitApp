package com.example.flexit;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainWatch extends AppCompatActivity {

    Button Stop, Start;
    ImageView arrow;
    Chronometer timer;
    Animation round, stoparrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch);


        Start = findViewById(R.id.button_Start);
        Stop = findViewById(R.id.button_stop);
        arrow = findViewById(R.id.arrow_view);
        timer = findViewById(R.id.time);

        round = AnimationUtils.loadAnimation(this, R.anim.round_animate);
        stoparrow = AnimationUtils.loadAnimation(this, R.anim.stop_animate);
        Typeface CalibriFont = Typeface.createFromAsset(getAssets(), "fonts/CalibriFont.ttf");
        Start.setTypeface(CalibriFont);
        Stop.setTypeface(CalibriFont);

        Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrow.startAnimation(round);
                    // arrow should go arround
                timer.setBase(SystemClock.elapsedRealtime());
                timer.start();// once the timer starts
            }
        });

        Stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrow.setAnimation(stoparrow);

                timer.stop();// easy implementation if the user stops the timer stop the arrow
            }
        });




    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


}
