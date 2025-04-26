package com.example.wishlist.models;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "gifts")
public class Gift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, precision = 10, scale = 3)
    private BigDecimal estimatedPrice;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "wishlist_id", nullable = false)
    private Wishlist wishlist;

    @Column
    private String link;

    public Gift() {}

    // Get-Set
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public BigDecimal getEstimatedPrice() { return estimatedPrice; }
    public void setEstimatedPrice(BigDecimal estimatedPrice) { this.estimatedPrice = estimatedPrice; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Wishlist getWishlist() { return wishlist; }
    public void setWishlist(Wishlist wishlist) { this.wishlist = wishlist; }

    public String getLink() { return link; }
    public void setLink(String link) { this.link = link; }
}