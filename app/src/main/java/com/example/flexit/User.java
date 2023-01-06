package com.example.flexit;

public class User {
    private String userID;
    private String fName;
    private String lName;
    private int age;
    private int height;
    private int weight;
    private String description;


    public User(String uID, String fName, String lName, int age, int height, int weight, String description ){
        this.userID = uID;
        this.fName = fName;
        this.lName = lName;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.description = description;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
