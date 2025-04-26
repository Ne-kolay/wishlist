package com.example.wishlist.services;

import com.example.wishlist.dto.WishlistCreateDTO;
import com.example.wishlist.dto.WishlistUpdateDTO;
import com.example.wishlist.models.PrivacyLevel;
import com.example.wishlist.models.User;
import com.example.wishlist.models.Wishlist;
import com.example.wishlist.repositories.UserRepository;
import com.example.wishlist.repositories.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishlistService {

    private final WishlistRepository wishlistRepository;
    private final UserRepository userRepository;

    @Autowired
    public WishlistService(WishlistRepository wishlistRepository, UserRepository userRepository) {
        this.wishlistRepository = wishlistRepository;
        this.userRepository = userRepository;
    }

    public Wishlist createWishlist(WishlistCreateDTO wishlistCreateDTO) {
        System.out.println("📥 Пришёл DTO: " + wishlistCreateDTO);
        System.out.println("🔍 Название: " + wishlistCreateDTO.getName());
        System.out.println("📝 Описание: " + wishlistCreateDTO.getDescription());
        System.out.println("🔐 Приватность: " + wishlistCreateDTO.getPrivacyLevel());
        System.out.println("👤 userId: " + wishlistCreateDTO.getUserId());

        if (wishlistCreateDTO.getUserId() == null) {
            throw new RuntimeException("❌ userId is null! Проверь поле в JSON (должно быть 'userId')");
        }

        User owner = userRepository.findById(wishlistCreateDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("❌ User not found with id: " + wishlistCreateDTO.getUserId()));

        Wishlist wishlist = new Wishlist();
        wishlist.setName(wishlistCreateDTO.getName());
        wishlist.setDescription(wishlistCreateDTO.getDescription());
        wishlist.setOwner(owner);

        try {
            wishlist.setPrivacyLevel(PrivacyLevel.valueOf(wishlistCreateDTO.getPrivacyLevel().toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("❌ Invalid privacy level: " + wishlistCreateDTO.getPrivacyLevel());
        }

        Wishlist saved = wishlistRepository.save(wishlist);
        System.out.println("✅ Вишлист сохранён с id: " + saved.getId());
        return saved;
    }

    public List<Wishlist> getAllWishlists() {
        return wishlistRepository.findAll();
    }

    public List<Wishlist> getWishlistsByUser(Long userId) {
        return wishlistRepository.findByOwnerId(userId);
    }

    public Optional<Wishlist> getWishlistById(Long id) {
        return wishlistRepository.findById(id);
    }

    public void deleteWishlist(Long id) {
        wishlistRepository.deleteById(id);
    }

    public Wishlist updateWishlist(Long id, WishlistUpdateDTO wishlistUpdateDTO) {
        Wishlist wishlist = wishlistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Wishlist not found"));
        if (wishlistUpdateDTO.getName() != null) {
            wishlist.setName(wishlistUpdateDTO.getName());
        }
        if (wishlistUpdateDTO.getDescription() != null) {
            wishlist.setDescription(wishlistUpdateDTO.getDescription());
        }
        if (wishlistUpdateDTO.getPrivacyLevel() != null) {
            try {
                wishlist.setPrivacyLevel(
                        PrivacyLevel.valueOf(wishlistUpdateDTO.getPrivacyLevel().toUpperCase())
                );
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Invalid privacy level: " + wishlistUpdateDTO.getPrivacyLevel());
            }
        }

        return wishlistRepository.save(wishlist);
    }
}