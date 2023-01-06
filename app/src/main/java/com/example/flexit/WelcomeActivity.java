package com.example.flexit;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


//https://www.youtube.com/watch?v=hoF5A30fbu4
//https://www.youtube.com/watch?v=4DTgE7qbD_8&list=LLxw5sEAJDjSBQ3uBuqFI8XQ&index=2&t=105s
public class WelcomeActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private FirebaseDatabase myFireBaseDB;
    private DatabaseReference myRef;


    private EditText fBio;
    private Button nextbio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        mAuth = FirebaseAuth.getInstance();
        myFireBaseDB = FirebaseDatabase.getInstance();
        user = mAuth.getCurrentUser();

        fBio = (EditText) findViewById(R.id.text_bio);
        nextbio = (Button) findViewById(R.id.button_nextbio);
        myRef = myFireBaseDB.getReference("UserBios");// making a firebase table for user bio



        nextbio.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                final String userID = user.getUid();
                if(userID == null)return;
                final String Bio = fBio.getText().toString();


                UserBio user = new UserBio(userID,Bio);

                if (TextUtils.isEmpty(Bio)){// if user does not enter anything
                    Toast.makeText(WelcomeActivity.this, "Invalid, enter a valid BIO", Toast.LENGTH_SHORT).show();
                    return;
                }



                //Put user profile information into database
                myRef.child(user.getUserID()).child("User Bio").setValue(user.getfBio());
                finish();
                startActivity(new Intent(getApplicationContext(),
                        FeedActivity.class));
            }
        });


    }

}

