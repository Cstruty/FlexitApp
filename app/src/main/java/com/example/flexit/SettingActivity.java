package com.example.flexit;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


//https://www.youtube.com/watch?v=hoF5A30fbu4
//https://www.youtube.com/watch?v=4DTgE7qbD_8&list=LLxw5sEAJDjSBQ3uBuqFI8XQ&index=2&t=105s
public class SettingActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private FirebaseDatabase myFireBaseDB;
    private DatabaseReference myRef;

    private static final String TAG = "FragmentActivity";

    private EditText fName;
    private EditText lName;
    private EditText user_age;
    private EditText user_height;
    private EditText user_weight;
    private EditText user_description;
    private Button next, deleteFbUser, save;

    String userId = FirebaseAuth.getInstance().getUid();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        mAuth = FirebaseAuth.getInstance();
        myFireBaseDB = FirebaseDatabase.getInstance();
        user = mAuth.getCurrentUser();



        // get reference to 'users' node
        myRef = myFireBaseDB.getReference("users");
        save = (Button) findViewById(R.id.updateinfo);
        fName = (EditText) findViewById(R.id.text_fName);
        lName = (EditText) findViewById(R.id.text_lName);
        user_age = (EditText) findViewById(R.id.text_age);
        user_weight = (EditText) findViewById(R.id.text_weight);
        user_height = (EditText) findViewById(R.id.text_height);
        user_description = (EditText) findViewById(R.id.text_description);
        deleteFbUser = (Button)findViewById(R.id.setting_del);

//        next = (Button) findViewById(R.id.button_next);

        myRef = myFireBaseDB.getReference("Users");



        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                final String userID = user.getUid();
                final String first = fName.getText().toString();
                final String last = lName.getText().toString();
                final int age = Integer.parseInt(user_age.getText().toString());
                final int height = Integer.parseInt(user_height.getText().toString());
                final int weight = Integer.parseInt(user_weight.getText().toString());
                final String description = user_description.getText().toString();// thiis stuff is the code from the profile activity


                User user = new User(userID,first,last,age,height,weight,description);

                if (TextUtils.isEmpty(userId)) {
                    getUserInfo(userID,first,last,age,height,weight,description);// if the user id is empty initialize the object with these parameters
                }
                else {
                    UpdateInfo(userID,first,last,age,height,weight,description);
                    Toast.makeText(getApplicationContext(),"Flexit Account Updated!",Toast.LENGTH_SHORT).show();// esle update the info

                }



            }
        });


        deleteFbUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {// delete was very easy, as we just use user. delete
                FirebaseUser User = FirebaseAuth.getInstance().getCurrentUser();

                User.delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Intent intent = new Intent(SettingActivity.this,MainActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                    finish();
                                    Toast.makeText(getApplicationContext(),"Flexit Account Deleted!",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });






    }

    private void getUserInfo(String uID, String fName, String lName, int age, int height, int weight, String description ) {
        // get the users info and push the key initializing a new object user

        if (TextUtils.isEmpty(userId)) {
            userId = myRef.push().getKey();
        }

        User user = new User(uID,fName,lName,age,height,weight,description);


        myRef.child(userId).setValue(user);

    }


    private void UpdateInfo(String uID, String fName, String lName, int age, int height, int weight, String description ) {
        if (!TextUtils.isEmpty(fName))// this is the magic, we update it via child nodes, set value allows users to update their info
            myRef.child(userId).child("First Name").setValue(fName);
        myRef.child(userId).child("Age").setValue(age);

        if (!TextUtils.isEmpty(lName))
            myRef.child(userId).child("Last Name").setValue(lName);
        myRef.child(userId).child("Height").setValue(height);


        if (!TextUtils.isEmpty(description))
            myRef.child(userId).child("Description").setValue(description);
        myRef.child(userId).child("Weight").setValue(weight);



    }

}

