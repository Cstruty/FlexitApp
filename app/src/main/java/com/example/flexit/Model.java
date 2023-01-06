package com.example.flexit;
import android.content.SharedPreferences;


public class Model {

    private int image;
    private String title;
    private String description;
    private SharedPreferences sharedPreferences;

    public Model(int image, String title, String description ) {
        this.image = image;
        this.title = title;
        this.description = description;
    }
    public void setSharedPreferences(SharedPreferences sharedPreferences){
        this.sharedPreferences = sharedPreferences;
    }

    public SharedPreferences getSharedPreferences(){
        return this.sharedPreferences;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
