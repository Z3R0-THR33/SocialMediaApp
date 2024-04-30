package com.app.z.controllers;

import com.app.z.JSONConvertor;
import com.app.z.Requests.*;
import com.app.z.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    // Login endpoint
    @PostMapping(value="/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String errorlevel="None";//so that it becomes easier to debug
        if (!userService.isUserExists(loginRequest.getEmail())){
            errorlevel="UserExistError";
            //return ResponseEntity.badRequest().body(new JSONConvertor("User does not exist"));
        }
        else if (!userService.isValidCredentials(loginRequest.getEmail(), loginRequest.getPassword())) {
            errorlevel="PasswordError";
            //return ResponseEntity.badRequest().body(new JSONConvertor("Username/Password Incorrect"));
        }
        switch (errorlevel) {
            case "UserExistError":return ResponseEntity.badRequest().body(new JSONConvertor("User does not exist"));
            case "PasswordError":return ResponseEntity.badRequest().body(new JSONConvertor("Username/Password Incorrect"));
            case "None": return ResponseEntity.ok("Login Successful");
            default:return ResponseEntity.ok("Login Successful");
        }
    }
    // Signup endpoint
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest){
        int isError=0;//so that it becomes easier to debug
        if (userService.isUserExists(signupRequest.getEmail())) {
            isError=1;
            //return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JSONConvertor("Forbidden, Account already exists"));
        }
        switch(isError){
            case 1:return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JSONConvertor("Forbidden, Account already exists"));
            default:userService.createUser(signupRequest);
            return ResponseEntity.ok("Account Creation Successful");
        }
    }
    // User detail endpoint
    @GetMapping("/user")
    public ResponseEntity<?> getUserDetails(@RequestParam int userID) {
        UserDetails userDetails=userService.getUserDetails(userID);
        if(userDetails==null)return ResponseEntity.badRequest().body(new JSONConvertor("User does not exist"));
        return ResponseEntity.ok(userDetails);
    }
    @GetMapping("/users")
    public ResponseEntity<List<UserDetails>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}

