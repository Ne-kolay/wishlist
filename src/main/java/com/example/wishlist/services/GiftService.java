package com.example.wishlist.services;

import com.example.wishlist.dto.GiftCreateDTO;
import com.example.wishlist.dto.GiftUpdateDTO;
import com.example.wishlist.models.Gift;
import com.example.wishlist.models.Wishlist;
import com.example.wishlist.repositories.GiftRepository;
import com.example.wishlist.repositories.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GiftService {

    private final GiftRepository giftRepository;
    private final WishlistRepository wishlistRepository;

    @Autowired
    public GiftService(GiftRepository giftRepository, WishlistRepository wishlistRepository) {
        this.giftRepository = giftRepository;
        this.wishlistRepository = wishlistRepository;
    }

    public Gift createGift(GiftCreateDTO giftCreateDTO, Long wishlistId) {
        Wishlist wishlist = wishlistRepository.findById(wishlistId)
                .orElseThrow(() -> new RuntimeException("Wishlist not found"));

        Gift gift = new Gift();
        gift.setName(giftCreateDTO.getName());
        gift.setDescription(giftCreateDTO.getDescription());
        gift.setEstimatedPrice(giftCreateDTO.getEstimatedPrice());
        gift.setLink(giftCreateDTO.getLink());
        gift.setWishlist(wishlist);

        return giftRepository.save(gift);
    }

    public List<Gift> getGiftsByWishlistId(Long wishlistId) {
        return giftRepository.findByWishlistId(wishlistId);
    }

    public Optional<Gift> getGiftById(Long id) {
        return giftRepository.findById(id);
    }

    public Gift updateGift(Long id, GiftUpdateDTO giftUpdateDTO) {
        Gift gift = giftRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gift not found"));

        gift.setName(giftUpdateDTO.getName());
        gift.setDescription(giftUpdateDTO.getDescription());
        gift.setEstimatedPrice(giftUpdateDTO.getEstimatedPrice());
        gift.setLink(giftUpdateDTO.getLink());

        return giftRepository.save(gift);
    }

    public void deleteGift(Long id) {
        giftRepository.deleteById(id);
    }
}