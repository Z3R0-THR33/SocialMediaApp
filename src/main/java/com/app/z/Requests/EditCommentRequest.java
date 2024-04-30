package com.app.z.Requests;

public class EditCommentRequest {
    private int commentID;
    private String commentBody;

    // Constructor
    public EditCommentRequest(int commentID, String commentBody) {
        this.commentID = commentID;
        this.commentBody = commentBody;
    }

    // Getters and setters
    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }
}

