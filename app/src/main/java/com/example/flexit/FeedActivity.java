package com.example.flexit;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.andrognito.flashbar.Flashbar;
import com.example.flexit.quote_of_day.Quote;
import com.example.flexit.quote_of_day.QuoteOfDay;
import com.example.flexit.quote_of_day.QuoteOfDayClient;

import java.io.IOException;

public class FeedActivity extends AppCompatActivity {

    private Button help;
    private Button workout;
    private Button schedule;
    private Button upload;
    private  Button clock;
    private Button BMI;
    private Button music;
    private ImageView profile;
    private ImageView logout;
    private ImageView setting;
    private Flashbar flashbar = null;
    private QuoteOfDay quoteOfDay;
    private TextView quoteOfDayText;
    private boolean quoteIsReady = false;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        quoteOfDay = new QuoteOfDayClient(this);

        help = (Button) findViewById(R.id.nutrition_repo_button);
        workout = (Button) findViewById(R.id.button_workout);
        schedule = (Button) findViewById(R.id.button_schedule);
        clock = (Button) findViewById(R.id.button_clock);
//        BMI = (Button) findViewById(R.id.button_BMI);
        music =(Button)findViewById(R.id.button_music);
//        profile =(ImageView) findViewById(R.id.button_viewprofile);
//        logout =(ImageView)findViewById(R.id.logout_view);
        setting = (ImageView)findViewById(R.id.setting_view);

//        QuoteThread quoteThread = new QuoteThread();
//        quoteThread.run();

        new GetQuoteTask().execute();


        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*finish();*/
                startActivity(new Intent(getApplicationContext(), NutritionRepositoryActivity.class));
            }
        });
//        // scheduler below
        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*finish();*/
                startActivity(new Intent(getApplicationContext(), ScheduleHome.class));
            }
        });
//
        workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* finish();*/
                startActivity(new Intent(getApplicationContext(), WorkoutFeed.class));
            }
        });
//
        clock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*finish();*/
                startActivity(new Intent(getApplicationContext(), MainWatch.class));
            }
        });

//        BMI.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                /*finish();*/
//                startActivity(new Intent(getApplicationContext(), BMIActivity.class));
//            }
//        });
//
        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MusicActivity.class));
            }
        });

//        profile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(), ReadDBActivity.class));
//            }
//        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SettingActivity.class));
            }
        });


//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FirebaseAuth.getInstance().signOut();
//                Intent intent = new Intent(FeedActivity.this,MainActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                startActivity(intent);
//                finish();
//
//            }
//        });
    }


    private class GetQuoteTask extends AsyncTask<QuoteOfDayClient, Flashbar , Quote> {

        Quote quote;

        @Override
        protected Quote doInBackground(QuoteOfDayClient... quoteOfDayClients) {

            try {
                quote = quoteOfDay.getQuote();
                flashbar = displayQuote(quote);
            } catch (IOException e) {
                flashbar = displayError();
            }


            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    flashbar.show();
                }
            });


            return new Quote();
        }
    }

    @Override
    public void onBackPressed() {
        if(true){
            return;
        }
        else{
            super.onBackPressed();
        }
    }

    private Flashbar displayQuote(Quote quote) {
        return new Flashbar.Builder(this)
                .gravity(Flashbar.Gravity.BOTTOM)
                .title("Quote of the Day!")
                .message(quote.getBody() + "\n" + "-" + quote.getAuthor())
                .backgroundDrawable(R.drawable.bg_gradient)
                .enableSwipeToDismiss()
                .build();
    }

    private Flashbar displayError() {
        return new Flashbar.Builder(this)
                .gravity(Flashbar.Gravity.BOTTOM)
                .title("Quote of the Day : Problem")
                .message("Unable to get Quote")
                .backgroundDrawable(R.drawable.bg_gradient)
                .enableSwipeToDismiss()
                .build();
    }
}
