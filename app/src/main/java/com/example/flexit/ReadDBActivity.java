package com.example.flexit;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;




//https://www.youtube.com/watch?v=hoF5A30fbu4
//https://www.youtube.com/watch?v=4DTgE7qbD_8&list=LLxw5sEAJDjSBQ3uBuqFI8XQ&index=2&t=105s
public class ReadDBActivity extends AppCompatActivity {

   TextView height, weight, age, name;
    Button info;
    DatabaseReference reff;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_db);

        height = (TextView)findViewById(R.id.heightview);
        weight = (TextView)findViewById(R.id.weighttview);
        age = (TextView)findViewById(R.id.AgeView);
        name = (TextView)findViewById(R.id.NameView);
        info = (Button)findViewById(R.id.button_viewinfo);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        final String userID = user.getUid();

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reff = FirebaseDatabase.getInstance().getReference().child("Users").child(userID);
                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        // read these values from the database
                        String Age=dataSnapshot.child("Age").getValue().toString();
                        String Height=dataSnapshot.child("Height").getValue().toString();
                        String Weight=dataSnapshot.child("Weight").getValue().toString();
                        String FirstName = dataSnapshot.child("First Name").getValue().toString();
                        age.setText(Age);// show these values to the user
                        height.setText(Height);
                        weight.setText(Weight);
                        name.setText(FirstName);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });



            }
        });


    }

}

