package com.example.wishlist.controllers;

import com.example.wishlist.dto.WishlistCreateDTO;
import com.example.wishlist.models.Wishlist;
import com.example.wishlist.services.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/wishlists")
public class WishlistController {

    private final WishlistService wishlistService;

    @Autowired
    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @PostMapping("/create")
    public ResponseEntity<Wishlist> createWishlist(@RequestBody WishlistCreateDTO wishlistCreateDTO) {
        Wishlist createdWishlist = wishlistService.createWishlist(wishlistCreateDTO);
        return ResponseEntity.ok(createdWishlist);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Wishlist>> getWishlistsByUser(@PathVariable Long userId) {
        List<Wishlist> wishlists = wishlistService.getWishlistsByUser(userId);
        return ResponseEntity.ok(wishlists);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Wishlist> getWishlistById(@PathVariable Long id) {
        Optional<Wishlist> wishlist = wishlistService.getWishlistById(id);
        return wishlist.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Wishlist> updateWishlist(@PathVariable Long id, @RequestBody Wishlist wishlistDetails) {
        Wishlist updatedWishlist = wishlistService.updateWishlist(id, wishlistDetails);
        return ResponseEntity.ok(updatedWishlist);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWishlist(@PathVariable Long id) {
        wishlistService.deleteWishlist(id);
        return ResponseEntity.noContent().build();
    }
}