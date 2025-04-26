package com.example.wishlist.dto;

public class WishlistCreateDTO {

    private String name;
    private String description;
    private Long userId;
    private String privacyLevel;

    public WishlistCreateDTO(String name, String description, Long userId, String privacyLevel) {
        this.name = name;
        this.description = description;
        this.userId = userId;
        this.privacyLevel = privacyLevel;
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

    public String getPrivacyLevel() { return privacyLevel; }
    public void setPrivacyLevel(String privacyLevel) { this.privacyLevel = privacyLevel; }
}