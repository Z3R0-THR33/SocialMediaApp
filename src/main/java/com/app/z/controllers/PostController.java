package com.app.z.controllers;

import com.app.z.JSONConvertor;
import com.app.z.Repos.UserRepository;
import com.app.z.Requests.EditPostRequest;
import com.app.z.Requests.PostRequest;
import com.app.z.Services.UserService;
import com.app.z.entity.Comment;
import com.app.z.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepo;
    JSONConvertor postDoesNotExist =new JSONConvertor("Post does not exist");
    @GetMapping("/")
    // User feed endpoint
    public ResponseEntity<?> getUserFeed() {
        List<Post> userFeed = userService.getUserFeed();
        return ResponseEntity.ok(userFeed);
    }
    // Post related endpoints
    @PostMapping("/post")
    public ResponseEntity<?> createPost(@RequestBody PostRequest postRequest) {
        String ErrorMessage="User does not exist";
        String isError="";//easier to debug as it is easier to read than if-else
        if(userRepo.findById(postRequest.getUserID()).isEmpty()) {
            isError="error";
        }
        switch(isError){
            case "error":
                return ResponseEntity.badRequest().body(new JSONConvertor(ErrorMessage));
            default: userService.createPost(postRequest);
                return ResponseEntity.ok("Post created successfully");
        }
    }
    @GetMapping("/post")
    public ResponseEntity<?> getPost(@RequestParam int postID) {
        Post post=userService.getPost(postID);
        if (post==null)//return ResponseEntity.badRequest().body("Post does not exist");
            return ResponseEntity.badRequest().body(postDoesNotExist);
        List<Comment> comments = userService.getCommentsForPost(postID);
        post.setComments(comments);
        return ResponseEntity.ok(post);
    }
    @PatchMapping("/post")
    public ResponseEntity<?> editPost(@RequestBody EditPostRequest editReq){
        if (!userService.postExists(editReq.getPostID()))//return ResponseEntity.badRequest().body("Post does not exist");
            return ResponseEntity.badRequest().body(postDoesNotExist);
        userService.editPost(editReq);
        return ResponseEntity.ok("Post edited successfully");
    }
    @DeleteMapping("/post")
    public ResponseEntity<?> deletePost(@RequestParam int postID) {
        if(!userService.postExists(postID)) //return ResponseEntity.badRequest().body("Post does not exist");
            return ResponseEntity.badRequest().body(postDoesNotExist);
        userService.deletePost(postID);
        return ResponseEntity.ok("Post deleted");
    }
}
