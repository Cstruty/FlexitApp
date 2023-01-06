
//Import necessary package and file
package com.example.flexit;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//Main activity class start here
public class BMIActivity extends Activity {


    TextView height, weight, result, status;
    Button run;
    DatabaseReference reff;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        status= (TextView) findViewById(R.id.status);

        height = (TextView) findViewById(R.id.heightview);
        weight = (TextView) findViewById(R.id.weighttview);
        run = (Button) findViewById(R.id.run_bmi);
        result = (TextView) findViewById(R.id.resultView);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        final String userID = user.getUid();

        run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reff = FirebaseDatabase.getInstance().getReference().child("Users").child(userID);
                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
// here we get the value of the bmi from the firebase database
                        String Height = dataSnapshot.child("Height").getValue().toString();
                        String Weight = dataSnapshot.child("Weight").getValue().toString();

                        height.setText(Height);
                        weight.setText(Weight);
                                // convert values to string
                        final int userweight = Integer.parseInt(Weight.toString());
                        final int userheight = Integer.parseInt(Height.toString());




//Calculate BMI value
                        final int bmiValue = getUserBMI(userweight, userheight);

                        String setBMI = showUserBMI(bmiValue);

                            //reun weight and height and get string value of it
                        status.setText(String.valueOf(getUserBMI(userweight,userheight)));
                        result.setText(String.valueOf(setBMI));



                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });


    }


// bmi formula to calculate bmi
    private int getUserBMI(final int userweight, final int userheight) {
        return (int) ((userweight * 703) / (userheight * userheight));
    }

    // bmi being called and evaluated to see what criteria the user fits in
    private String showUserBMI(float userBMI) {

        if (userBMI < 18.5) {
            return "underweight";
        } else if (userBMI < 25) {

            return "normal";
        } else if (userBMI < 30) {

            return "overweight";
        } else if (userBMI < 40) {

            return "Obese";
        } else {
            return "Morebidly Obese";
        }
    }

}