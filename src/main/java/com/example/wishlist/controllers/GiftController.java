package com.example.wishlist.controllers;

import com.example.wishlist.dto.GiftCreateDTO;
import com.example.wishlist.dto.GiftUpdateDTO;
import com.example.wishlist.models.Gift;
import com.example.wishlist.services.GiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/gifts")
public class GiftController {

    private final GiftService giftService;

    @Autowired
    public GiftController(GiftService giftService) {
        this.giftService = giftService;
    }

    @PostMapping("/create")
    public ResponseEntity<Gift> createGift(@RequestBody GiftCreateDTO giftCreateDTO, @RequestParam Long wishlistId) {
        Gift createdGift = giftService.createGift(giftCreateDTO, wishlistId);
        return ResponseEntity.ok(createdGift);
    }

    @GetMapping
    public ResponseEntity<List<Gift>> getGiftsByWishlistId(@RequestParam Long wishlistId) {
        List<Gift> gifts = giftService.getGiftsByWishlistId(wishlistId);
        return ResponseEntity.ok(gifts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gift> getGiftById(@PathVariable Long id) {
        Optional<Gift> gift = giftService.getGiftById(id);
        return gift.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Gift> updateGift(@PathVariable Long id, @RequestBody GiftUpdateDTO giftUpdateDTO) {
        Gift updatedGift = giftService.updateGift(id, giftUpdateDTO);
        return ResponseEntity.ok(updatedGift);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGift(@PathVariable Long id) {
        giftService.deleteGift(id);
        return ResponseEntity.noContent().build();
    }
}
