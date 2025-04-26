package com.example.wishlist.dto;

import com.example.wishlist.models.Wishlist;

public class WishlistResponseDTO {
    private Long id;
    private String name;
    private String description;
    private String privacyLevel;

    public WishlistResponseDTO() {}

    public WishlistResponseDTO(Long id, String name, String description, String privacyLevel) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.privacyLevel = privacyLevel;
    }

    public WishlistResponseDTO(Wishlist wishlist) {
        this.id = wishlist.getId();
        this.name = wishlist.getName();
        this.description = wishlist.getDescription();
        this.privacyLevel = wishlist.getPrivacyLevel().name();
    }

    // геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getPrivacyLevel() { return privacyLevel; }
    public void setPrivacyLevel(String privacyLevel) { this.privacyLevel = privacyLevel; }
}