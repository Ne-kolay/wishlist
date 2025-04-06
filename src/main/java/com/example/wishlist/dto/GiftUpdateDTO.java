package com.example.wishlist.dto;

import java.math.BigDecimal;

public class GiftUpdateDTO {

    private String name;
    private BigDecimal estimatedPrice;
    private String description;
    private String link;  // Новое поле для ссылки

    public GiftUpdateDTO(String name, String link, String description, BigDecimal estimatedPrice) {
        this.name = name;
        this.link = link;
        this.description = description;
        this.estimatedPrice = estimatedPrice;
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