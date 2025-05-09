package com.example.wishlist.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Wishlist> wishlists = new ArrayList<>();

    public User() {}

    //Get-Set
    public Long getId()                                { return id; }
    public void setId(Long id)                         { this.id = id; }

    public String getUsername()                        { return username; }
    public void setUsername(String username)           { this.username = username; }

    public String getEmail()                           { return email; }
    public void setEmail(String email)                 { this.email = email; }

    public String getPassword()                        { return password; }
    public void setPassword(String password)           { this.password = password;}

    public Role getRole()                              { return role; }
    public void setRole(Role role)                     { this.role = role; }

    public List<Wishlist> getWishlists()               { return wishlists; }
    public void setWishlists(List<Wishlist> wishlists) { this.wishlists = wishlists; }

    public enum Role { USER, ADMIN, MODERATOR }
}


