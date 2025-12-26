package com.example.demo.dto;

public class AuthRequest {

    private String email;
    private String password;
    private String name; // used during registration

    // No-args constructor
    public AuthRequest() {
    }

    // All-args constructor
    public AuthRequest(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}
