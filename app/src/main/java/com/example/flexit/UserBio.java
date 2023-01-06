package com.example.flexit;

public class UserBio {



    private String userID;
    private String fBio;
    private String fName;
    private String lName;
    private int age;
    private int height;
    private int weight;
    private String description;


    public UserBio(String uID, String fBio){
        this.userID = uID;
        this.fBio = fBio;

    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getfBio() {
        return fBio;
    }

    public void setfBio(String fBio) {
        this.fBio = fBio;
    }


}
