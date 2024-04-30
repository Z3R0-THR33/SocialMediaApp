package com.app.z.controllers;

import com.app.z.JSONConvertor;
import com.app.z.Repos.CommentRepository;
import com.app.z.Repos.UserRepository;
import com.app.z.Requests.CommentRequest;
import com.app.z.Requests.EditCommentRequest;
import com.app.z.Services.UserService;
import com.app.z.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {
    // Comment related endpoints
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private UserService userService;
    JSONConvertor commentDoesNotExist =new JSONConvertor("Comment does not exist");
    @PostMapping("/comment")
    public ResponseEntity<?> createComment(@RequestBody CommentRequest createReq){
        String error="None";
        if(userRepo.findById(createReq.getUserID()).isEmpty())
            error="UserExistError";
        else if(!userService.postExists(createReq.getPostID()))
            error="PostExistError";
            //return ResponseEntity.badRequest().body(new JSONConvertor("Post does not exist"));
        switch (error) {
            case "None": userService.createComment(createReq);
                return ResponseEntity.ok("Comment created successfully");
            case "UserExistError":
                return ResponseEntity.badRequest().body(new JSONConvertor("User does not exist"));
            case "PostExistError":
                return ResponseEntity.badRequest().body(new JSONConvertor("Post does not exist"));
            default:
                userService.createComment(createReq);
                return ResponseEntity.ok("Comment created successfully");
        }
    }
    @GetMapping("/comment")
    public ResponseEntity<?> getComment(@RequestParam int commentID) {
        Comment comment=userService.getComment(commentID);
        if (comment==null)return ResponseEntity.badRequest().body(commentDoesNotExist);
        //return JSONConvertor.JSONObject(comment);
        return ResponseEntity.ok(comment);
    }
    @PatchMapping("/comment")
    public ResponseEntity<?> editComment(@RequestBody EditCommentRequest editReq) {
        int commentID = editReq.getCommentID();
        if (!userService.commentExists(commentID)) {
            return ResponseEntity.badRequest().body(commentDoesNotExist);
        }
        userService.editComment(editReq);
        return ResponseEntity.ok("Comment edited successfully");
    }
    @DeleteMapping("/comment")
    public ResponseEntity<?> deleteComment(@RequestParam int commentID) {
        if (!userService.commentExists(commentID))return ResponseEntity.badRequest().body(commentDoesNotExist);
        userService.deleteComment(commentID);
        return ResponseEntity.ok("Comment deleted");
    }
}
