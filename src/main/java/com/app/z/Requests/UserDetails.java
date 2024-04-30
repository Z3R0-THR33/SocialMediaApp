package com.app.z.Requests;

public class UserDetails {
    private String name;
    private int userID;
    private String email;

    public String getEmail() {
        return email;
    }

    public int getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
    // Getters and setters
}
