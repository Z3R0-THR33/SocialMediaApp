package com.app.z.Requests;

public class LoginRequest {
    private String email;
    private String password;

    // Getters and setters

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
