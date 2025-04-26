package com.example.wishlist.controllers;

import com.example.wishlist.dto.WishlistCreateDTO;
import com.example.wishlist.dto.WishlistResponseDTO;
import com.example.wishlist.dto.WishlistUpdateDTO;
import com.example.wishlist.models.Wishlist;
import com.example.wishlist.services.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/wishlists")
public class WishlistController {

    private final WishlistService wishlistService;

    @Autowired
    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @PostMapping("/create")
    public ResponseEntity<WishlistResponseDTO> createWishlist(@RequestBody WishlistCreateDTO wishlistCreateDTO) {
        Wishlist createdWishlist = wishlistService.createWishlist(wishlistCreateDTO);
        return ResponseEntity.ok(new WishlistResponseDTO(createdWishlist));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getWishlistsByUser(@PathVariable Long userId) {
        try {
            List<Wishlist> wishlists = wishlistService.getWishlistsByUser(userId);
            List<WishlistResponseDTO> dtoList = wishlists.stream()
                    .map(WishlistResponseDTO::new)
                    .toList();

            return ResponseEntity.ok(dtoList);
        } catch (Exception e) {
            e.printStackTrace(); // Печатаем ошибку в консоль
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ошибка при получении вишлистов пользователя");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Wishlist> getWishlistById(@PathVariable Long id) {
        Optional<Wishlist> wishlist = wishlistService.getWishlistById(id);
        return wishlist.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Wishlist> updateWishlist(@PathVariable Long id, @RequestBody WishlistUpdateDTO wishlistUpdateDTO) {
        Wishlist updatedWishlist = wishlistService.updateWishlist(id, wishlistUpdateDTO);
        return ResponseEntity.ok(updatedWishlist);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWishlist(@PathVariable Long id) {
        wishlistService.deleteWishlist(id);
        return ResponseEntity.noContent().build();
    }
}