package com.example.flexit;

import android.animation.ArgbEvaluator;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

public class WorkoutFeed extends AppCompatActivity {

    //private Button back;
    private static final String TAG = "WorkoutFeed";

    ViewPager viewPager;
    ModelAdapter adapter;
    List<Model> models;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_feed);
        RequestQueue queue = Volley.newRequestQueue(this);
        /* put images in the drawable folder */
        /* parameters to pass in (image, title, description) */

       // back = (Button)findViewById(R.id.button_back);
        SharedPreferences sharedPref;
        sharedPref = this.getSharedPreferences("myPref", MODE_PRIVATE);

        models = new ArrayList<>();
        models.add(new Model(R.drawable.weighted1, "Dumbell Bench Press", "3 Sets 10 Reps Rest for 60sec: " +
                "Lie on a bench with your feet on the floor directly underneath your knees, " +
                "holding the dumbbells above your chest. Lower them to your chest, then drive your feet hard into the floor and push the dumbbells back strongly to the start position."));
        models.add(new Model(R.drawable.weighted2, "Back Squat", "Sets 5 Time 5 Rest 90sec" +
                "Rest the bar on your back with your feet roughly shoulder-width apart, toes pointing out slightly. " +
                "Keep your spine in alignment by looking at a spot on the floor about two metres in front of you, then sit back and down as if you were aiming for a chair. " +
                "Lower until your hip crease is below your knee. As you drive back up, keep your weight on your heels."));
        models.add(new Model(R.drawable.unweighted1, "Wall Sit", "30 secs per set." +
                "Slowly slide your back down a wall until your thighs are parallel to the floor. " +
                "Make sure knees are directly above ankles and keep back straight."));
        models.add(new Model(R.drawable.unweighted2, "Lunge Jump", " 10 reps Rest for 60 sec: " +
                "Stand with feet together and lunge forward with right foot. " +
                "Jump straight up, propelling arms forward while keeping elbows bent." +
                "While in the air, switch legs and land in a lunge with the opposite leg forward. Repeat and continue switching legs."));
        for (int i =0; i<models.size(); i++){
            models.get(i).setSharedPreferences(sharedPref);
        }

        adapter = new ModelAdapter(models, this);

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(140,0,140,0);

        Integer[] colors_temp = {
                getResources().getColor(R.color.color1),
                getResources().getColor(R.color.color2),
                getResources().getColor(R.color.color3),
                getResources().getColor(R.color.color4)};

        colors = colors_temp;


        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position < (adapter.getCount() - 1) && position < (colors.length - 1)) {
                    viewPager.setBackgroundColor(
                            (Integer) argbEvaluator.evaluate(
                                    positionOffset,
                                    colors[position],
                                    colors[position + 1]));
                }
                else{
                    viewPager.setBackgroundColor(colors[colors.length - 1]);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    public boolean testLikedMemory(String Title,List<Model> models){
        try {
            for (int i = 0; i < models.size(); i++) {
                models.get(0).getSharedPreferences().getString(models.get(i).getTitle(), "false");
            }
            return true;
        }catch (Exception err){
            return false;
        }

    }


}
