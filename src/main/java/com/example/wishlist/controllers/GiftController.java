package com.example.wishlist.controllers;

import com.example.wishlist.dto.GiftDTO;
import com.example.wishlist.dto.GiftCreateDTO;
import com.example.wishlist.dto.GiftUpdateDTO;
import com.example.wishlist.models.Gift;
import com.example.wishlist.services.GiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/wishlists")
public class GiftController {

    private final GiftService giftService;

    @Autowired
    public GiftController(GiftService giftService) {
        this.giftService = giftService;
    }

    @PostMapping("/{wishlistId}/gifts")
    public ResponseEntity<GiftDTO> createGift(@RequestBody GiftCreateDTO giftCreateDTO, @PathVariable Long wishlistId) {
        Gift createdGift = giftService.createGift(giftCreateDTO, wishlistId);
        GiftDTO giftDTO = new GiftDTO(createdGift); // Используем GiftDTO вместо GiftCreateDTO
        return ResponseEntity.ok(giftDTO);
    }

    @GetMapping("/{wishlistId}/gifts")
    public ResponseEntity<List<GiftDTO>> getGiftsByWishlistId(@PathVariable Long wishlistId) {
        List<Gift> gifts = giftService.getGiftsByWishlistId(wishlistId);
        List<GiftDTO> giftDTOs = gifts.stream()
                .map(GiftDTO::new) // Используем GiftDTO вместо GiftCreateDTO
                .collect(Collectors.toList());
        return ResponseEntity.ok(giftDTOs);
    }

    @GetMapping("/{wishlistId}/gifts/{id}")
    public ResponseEntity<GiftDTO> getGiftById(@PathVariable Long wishlistId, @PathVariable Long id) {
        Optional<Gift> gift = giftService.getGiftById(id);
        return gift.map(g -> ResponseEntity.ok(new GiftDTO(g))) // Возвращаем GiftDTO
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{wishlistId}/gifts/{id}")
    public ResponseEntity<GiftDTO> updateGift(@PathVariable Long wishlistId, @PathVariable Long id, @RequestBody GiftUpdateDTO giftUpdateDTO) {
        Gift updatedGift = giftService.updateGift(id, giftUpdateDTO);
        GiftDTO giftDTO = new GiftDTO(updatedGift); // Конвертируем обновленный подарок в GiftDTO
        return ResponseEntity.ok(giftDTO);
    }

    @DeleteMapping("/{wishlistId}/gifts/{id}")
    public ResponseEntity<Void> deleteGift(@PathVariable Long wishlistId, @PathVariable Long id) {
        giftService.deleteGift(id);
        return ResponseEntity.noContent().build();
    }
}