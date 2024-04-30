package com.app.z.Requests;

public class PostRequest {
    private String postBody;
    private int userID;

    // Getters and setters

    public int getUserID() {
        return userID;
    }

    public String getPostBody() {
        return postBody;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }
}
