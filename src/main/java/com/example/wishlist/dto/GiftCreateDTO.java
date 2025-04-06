package com.example.wishlist.dto;

import java.math.BigDecimal;

public class GiftCreateDTO {

    private String name;
    private BigDecimal estimatedPrice;
    private String description;
    private String link;

    public GiftCreateDTO(String name, BigDecimal estimatedPrice, String description, String link) {
        this.name = name;
        this.estimatedPrice = estimatedPrice;
        this.description = description;
        this.link = link;
    }

    // Get-Set
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public BigDecimal getEstimatedPrice() { return estimatedPrice; }
    public void setEstimatedPrice(BigDecimal estimatedPrice) { this.estimatedPrice = estimatedPrice; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLink() { return link; }
    public void setLink(String link) { this.link = link; }
}