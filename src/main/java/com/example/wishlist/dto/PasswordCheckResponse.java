package com.example.wishlist.dto;

public class PasswordCheckResponse {

    private boolean valid;

    public PasswordCheckResponse(boolean valid) { this.valid = valid; }

    //Get-Set
    public boolean isValid()            { return valid; }
    public void setValid(boolean valid) { this.valid = valid; }
}