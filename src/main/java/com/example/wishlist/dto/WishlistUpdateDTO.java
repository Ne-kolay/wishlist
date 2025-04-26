package com.example.wishlist.dto;

public class WishlistUpdateDTO {
    String name;
    String description;
    String privacyLevel;

    //get & set
    public String getName()                          { return name; }
    public void setName(String name)                 { this.name = name; }

    public String getDescription()                   { return description; }
    public void setDescription(String description)   { this.description = description; }

    public String getPrivacyLevel()                  { return privacyLevel; }
    public void setPrivacyLevel(String privacyLevel) { this.privacyLevel = privacyLevel; }
}
