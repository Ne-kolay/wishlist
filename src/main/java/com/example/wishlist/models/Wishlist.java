package com.example.wishlist.models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "wishlists")
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User owner;

    @OneToMany(mappedBy = "wishlist", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Gift> gifts = new ArrayList<>();

    @Lob
    private String description;

    public Wishlist() {}

    // Get-Set
    public Long getId()                            { return id; }
    public void setId(Long id)                     { this.id = id; }

    public String getName()                        { return name; }
    public void setName(String name)               { this.name = name; }

    public User getOwner()                         { return owner; }
    public void setOwner(User owner)               { this.owner = owner; }

    public List<Gift> getGifts()                   { return gifts; }
    public void setGifts(List<Gift> gifts)         { this.gifts = gifts; }

    public String getDescription()                 { return description; }
    public void setDescription(String description) { this.description = description; }
}