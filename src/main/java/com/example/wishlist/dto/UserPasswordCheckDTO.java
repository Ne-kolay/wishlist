package com.example.wishlist.dto;

public class UserPasswordCheckDTO {

    private String email;
    private String password;

    //Get-Set
    public String getEmail()                 { return email; }
    public void setEmail(String email)       { this.email = email; }

    public String getPassword()              { return password; }
    public void setPassword(String password) { this.password = password; }
}