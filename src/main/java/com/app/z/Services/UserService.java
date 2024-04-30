package com.app.z.Services;

import com.app.z.Repos.CommentRepository;
import com.app.z.Repos.PostRepository;
import com.app.z.Repos.UserRepository;
import com.app.z.Requests.*;
import com.app.z.entity.Comment;
import com.app.z.entity.Post;
import com.app.z.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private PostRepository postRepo;
    @Autowired
    private CommentRepository commentRepo;

    public boolean isValidCredentials(String email, String password) {
        UserEntity user = userRepo.findByEmail(email);
        return user!=null && user.getPassword().equals(password);
    }
    public void createUser(SignupRequest signupRequest) {
        UserEntity user = new UserEntity();
        user.setEmail(signupRequest.getEmail());
        user.setName(signupRequest.getName());
        user.setPassword(signupRequest.getPassword());
        userRepo.save(user);
    }
    public List<Comment> getCommentsForPost(int postId) {
        // Retrieve the post by its ID
        Post post= postRepo.findById(postId).orElse(null);
        return post==null?null:post.getComments();
    }
    public UserDetails getUserDetails(int userID) {
        UserEntity user = userRepo.findById(userID).orElse(null);
        if (user == null) {
            return null;
        }
        UserDetails userDetails = new UserDetails();
        userDetails.setUserID(user.getUserID());
        userDetails.setName(user.getName());
        userDetails.setEmail(user.getEmail());
        return userDetails;
    }
//    public List<Post> getUserFeed() {
//        List<Post> userFeed = postRepo.findAll();
//        Collections.reverse(userFeed); // Reverse the order of posts in-place
//        return userFeed;
//    }
public List<Post> getUserFeed() {
    List<Post> userFeed = postRepo.findAll();

    // Sort the list of posts by time
    Collections.sort(userFeed, new Comparator<Post>() {
        @Override
        public int compare(Post post1, Post post2) {
            // Compare by time
            int dateCompare = post2.getDate().compareTo(post1.getDate()); // Reverse order for descending sorting
            if (dateCompare != 0)return dateCompare;
            //If times are equal, compare by postID
            //higher postID implies later post
            return Integer.compare(post2.getPostID(), post1.getPostID());
        }
    });
    return userFeed;
}
    public void createPost(PostRequest postRequest) {
        Post post = new Post();
        post.setPostBody(postRequest.getPostBody());
        LocalDate currentDate = LocalDate.now();
        String formattedDate = DateUtils.formatDate(currentDate);
        post.setDate(formattedDate);
        postRepo.save(post);
    }
    public ResponseEntity<String> editPost(EditPostRequest postRequest) {
        Optional<Post> optionalPost = postRepo.findById(postRequest.getPostID());
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.setPostBody(postRequest.getPostBody());
            postRepo.save(post);
            return ResponseEntity.ok("Post edited successfully");
        }
        return ResponseEntity.badRequest().body("Post does not exist");
    }
    public ResponseEntity<String> deletePost(int postID) {
        Optional<Post> optionalPost = postRepo.findById(postID);
        if (optionalPost.isPresent()) {
            postRepo.deleteById(postID);
            return ResponseEntity.ok("Post deleted");
        }
        return ResponseEntity.badRequest().body("Post does not exist");
    }
    public void createComment(CommentRequest commentRequest) {
        Post post = postRepo.findById(commentRequest.getPostID()).orElse(null);
        if(post==null)return;
        Comment comment = new Comment();
        comment.setCommentBody(commentRequest.getCommentBody());
        comment.setCommentCreator(userRepo.getOne(commentRequest.getUserID()));
        comment.setPost(post);
        commentRepo.save(comment);
    }
    public void editComment(EditCommentRequest editComment) {
        Comment comment = commentRepo.findById(editComment.getCommentID()).orElse(null);
        if (comment!=null) {
            comment.setCommentBody(editComment.getCommentBody());
            commentRepo.save(comment);
        }
    }
    public List<UserDetails> getAllUsers() {
        List<UserEntity> users = userRepo.findAll();
        List<UserDetails> userDetailsList = new ArrayList<>();
        for (UserEntity user : users) {
            UserDetails userDetails = new UserDetails();
            userDetails.setUserID(user.getUserID());
            userDetails.setName(user.getName());
            userDetails.setEmail(user.getEmail());
            userDetailsList.add(userDetails);
        }
        return userDetailsList;
    }
    public Post getPost(int postID) {return postRepo.findById(postID).orElse(null);}
    public void deleteComment(int commentID){commentRepo.deleteById(commentID);}
    public Comment getComment(int commentID) {return commentRepo.findById(commentID).orElse(null);}

    public boolean isUserExists(String email) {
        return email==null || userRepo.findByEmail(email)!=null;
    }
    public boolean postExists(int postID) {return postRepo.existsById(postID);}
    public boolean commentExists(int commentID){return commentRepo.existsById(commentID);}
}
class DateUtils {
    public static String formatDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date.format(formatter);
    }
}