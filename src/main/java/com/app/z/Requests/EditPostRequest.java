package com.app.z.Requests;

public class EditPostRequest {
    private int postID;
    private String postBody;

    // Constructor
    public EditPostRequest(int postID, String postBody) {
        this.postID = postID;
        this.postBody = postBody;
    }

    // Getters and setters
    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }
}
