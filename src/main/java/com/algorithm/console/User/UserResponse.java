package com.algorithm.console.User;

public class UserResponse {

    private String userId;
    private String username;

    private String email;

    private String token;

    public UserResponse(String userId, String username, String email, String token) {
        this.username = username;
        this.token = token;
        this.email = email;
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
