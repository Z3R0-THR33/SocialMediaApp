package com.app.z.Requests;

public class CommentRequest {
    private String commentBody;
    private int postID;
    private int userID;

    // Getters and setters

    public int getUserID() {
        return userID;
    }

    public int getPostID() {
        return postID;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }
}
