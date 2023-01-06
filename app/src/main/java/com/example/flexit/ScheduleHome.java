package com.example.flexit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class ScheduleHome extends AppCompatActivity {
    private ImageButton MonthlyS, WeeklyS;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_home);

        MonthlyS = (ImageButton) findViewById(R.id.MonthlyS);
        MonthlyS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMonthlySchedule();
            }
        });
        WeeklyS = (ImageButton) findViewById(R.id.WeeklyS);
        WeeklyS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWeeklySchedule();
            }
        });
    }
    public void openMonthlySchedule() {
        Intent intent = new Intent(this, MonthlySchedule.class);
        startActivity(intent);
    }
    public void openWeeklySchedule() {
        Intent intent = new Intent(this, ScheduleActivity.class);
        startActivity(intent);
    }
}
