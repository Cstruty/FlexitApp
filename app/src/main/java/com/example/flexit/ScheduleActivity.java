package com.example.flexit;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class ScheduleActivity extends AppCompatActivity {

    String Sunday, Monday, Tuesday, Wednesday,
            Thursday, Friday, Saturday;

    Button Save;
    String sundayText, mondayText, tuesdayText, wednesdayText, thursdayText, fridayText, saturdayText;

    EditText SundayBox;
    EditText MondayBox;
    EditText TuesdayBox;
    EditText WednesdayBox;
    EditText ThursdayBox;
    EditText FridayBox;
    EditText SaturdayBox;

    @Override
    protected void onCreate(Bundle saveIntstantState) {
        super.onCreate(saveIntstantState);
        setContentView(R.layout.activity_schedule);


        SundayBox = (EditText) findViewById(R.id.SundayBox);
        MondayBox = (EditText) findViewById(R.id.MondayBox);
        TuesdayBox = (EditText) findViewById(R.id.TuesdayBox);
        WednesdayBox = (EditText) findViewById(R.id.WednesdayBox);
        ThursdayBox = (EditText) findViewById(R.id.ThursdayBox);
        FridayBox = (EditText) findViewById(R.id.FridayBox);
        SaturdayBox = (EditText) findViewById(R.id.SaturdayBox);
        Save = findViewById(R.id.BtnSave);


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        String storeSun = prefs.getString("sundayText","");
        SundayBox.setText(storeSun);

        String storeMon = prefs.getString("mondayText","");
        MondayBox.setText(storeMon);

        String storeTues= prefs.getString("tuesdayText","");
        TuesdayBox.setText(storeTues);

        String storeWed = prefs.getString("wednesdayText","");
        WednesdayBox.setText(storeWed);

        String storeThurs = prefs.getString("thursdayText","");
        ThursdayBox.setText(storeThurs);

        String storeFri = prefs.getString("fridayText","");
        FridayBox.setText(storeFri);

        String storeSat = prefs.getString("saturdayText","");
        SaturdayBox.setText(storeSat);


        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sundayText = SundayBox.getText().toString();
                mondayText = MondayBox.getText().toString();
                tuesdayText = TuesdayBox.getText().toString();
                wednesdayText = WednesdayBox.getText().toString();
                thursdayText = ThursdayBox.getText().toString();
                fridayText = FridayBox.getText().toString();
                saturdayText = SaturdayBox.getText().toString();

                //This line saves the Data
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences
                        (ScheduleActivity.this);
                SharedPreferences.Editor editor = prefs.edit();

                editor.putString("sundayText", sundayText);
                editor.putString("mondayText", mondayText);
                editor.putString("tuesdayText", tuesdayText);
                editor.putString("wednesdayText", wednesdayText);
                editor.putString("thursdayText", thursdayText);
                editor.putString("fridayText", fridayText);
                editor.putString("saturdayText", saturdayText);
                editor.apply();

            }
        });




    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
