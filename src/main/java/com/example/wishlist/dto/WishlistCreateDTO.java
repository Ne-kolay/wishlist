package com.example.wishlist.dto;

public class WishlistCreateDTO {

    private String name;
    private String description;
    private Long userId;

    public WishlistCreateDTO(String name, String description, Long userId) {
        this.name = name;
        this.description = description;
        this.userId = userId;
    }

    // Get-Set
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}