package com.example.flexit;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.like.LikeButton;

import java.util.List;

public class ModelAdapter extends PagerAdapter {

    private static final String TAG = "ModelAdapter";

    private List<Model> models;
    private LayoutInflater layoutInflater;
    private Context context;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private FirebaseDatabase myFireBaseDB;
    private DatabaseReference myRef;
    private DatabaseReference likeRef;
    private DatabaseReference disLikeRef;
    private boolean isLiked;




    public ModelAdapter(List<Model> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item, container, false);

        mAuth = FirebaseAuth.getInstance();
//        myFireBaseDB = FirebaseDatabase.getInstance();
//        user = mAuth.getCurrentUser();

//        myRef = myFireBaseDB.getReference("WorkoutRating");
//        final String userID = user.getUid();

//        likeRef = myRef.child(userID).child("Likes");
//        disLikeRef = myRef.child(userID).child("Dislikes");


        ImageView imageView;
        final TextView title;
        final TextView description;
        LikeButton likeButton;

        imageView = view.findViewById(R.id.image);
        title = view.findViewById(R.id.title);
        description = view.findViewById(R.id.desc);
        // makes the button
        final ImageButton btnTest =view.findViewById(R.id.heart_button);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref=models.get(0).getSharedPreferences();
                SharedPreferences.Editor editor = pref.edit();


                if (isLiked) {
                    editor.putString(models.get(position).getTitle(), "true");
                    editor.commit();
                    btnTest.setImageResource(R.drawable.heart_on);

                }
                else {
                    editor.putString(models.get(position).getTitle(), "false");
                    editor.commit();
                    btnTest.setImageResource(R.drawable.heart_off);

                }
                isLiked=!isLiked;
            }
        });
        // recals last state of button and sets the display accordingly
        if  (models.get(0).getSharedPreferences().getString(models.get(position).getTitle(), "false").equals("true")){
            btnTest.setImageResource(R.drawable.heart_on);
        } else {
            btnTest.setImageResource(R.drawable.heart_off);
        }


        /*https://github.com/jd-alexander/LikeButton#like-event-listener*/
//        likeButton = view.findViewById(R.id.heart_button);
//        likeButton.setOnLikeListener(new OnLikeListener() {
//            @Override
//            public void liked(LikeButton likeButton) {
//                Toast.makeText(context, "Liked!", Toast.LENGTH_SHORT).show();
//                disLikeRef.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        if (dataSnapshot.hasChild(title.getText().toString())){
//                            disLikeRef.child(title.getText().toString()).removeValue();
//                        }
//                        likeRef.child(title.getText().toString()).setValue(description.getText().toString());
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//                        Log.e(TAG, "onCancelled", databaseError.toException());
//                    }
//                });
//
//            }

//            @Override
//            public void unLiked(LikeButton likeButton) {
//                Toast.makeText(context, "Disliked!", Toast.LENGTH_SHORT).show();
//                likeRef.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        if (dataSnapshot.hasChild(title.getText().toString())){
//                            likeRef.child(title.getText().toString()).removeValue();
//                        }
//                        disLikeRef.child(title.getText().toString()).setValue(description.getText().toString());
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//                        Log.e(TAG, "onCancelled", databaseError.toException());
//                    }
//                });
//            }
//        });

        imageView.setImageResource(models.get(position).getImage());
        title.setText(models.get(position).getTitle());
        description.setText(models.get(position).getDescription());

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
