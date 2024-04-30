package com.app.z.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentID;
    private String commentBody;
    @ManyToOne
    private UserEntity commentCreator;
    @ManyToOne
    @JoinColumn(name = "postID") // Assuming the column name in Comment table
    @JsonIgnore
    private Post post;

    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public UserEntity getCommentCreator() {
        return commentCreator;
    }

    public void setCommentCreator(UserEntity commentCreator) {
        this.commentCreator = commentCreator;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
    // Getters and setters
}
