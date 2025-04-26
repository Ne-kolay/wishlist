package com.example.wishlist.dto;

import com.example.wishlist.models.Gift;

import java.math.BigDecimal;

public class GiftDTO {

    private Long id;
    private String name;
    private BigDecimal estimatedPrice;
    private String description;
    private String link;

    // Конструктор для создания нового подарка
    public GiftDTO(String name, BigDecimal estimatedPrice, String description, String link) {
        this.name = name;
        this.estimatedPrice = estimatedPrice;
        this.description = description;
        this.link = link;
    }

    // Конструктор для конвертации сущности Gift в DTO
    public GiftDTO(Long id, String name, BigDecimal estimatedPrice, String description, String link) {
        this.id = id;
        this.name = name;
        this.estimatedPrice = estimatedPrice;
        this.description = description;
        this.link = link;
    }

    // Конструктор для преобразования модели в DTO (при получении данных с базы)
    public GiftDTO(Gift gift) {
        this.id = gift.getId();
        this.name = gift.getName();
        this.estimatedPrice = gift.getEstimatedPrice();
        this.description = gift.getDescription();
        this.link = gift.getLink();
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getEstimatedPrice() {
        return estimatedPrice;
    }

    public void setEstimatedPrice(BigDecimal estimatedPrice) {
        this.estimatedPrice = estimatedPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}