package com.example.wishlist.services;

import com.example.wishlist.dto.WishlistCreateDTO;
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
        User owner = userRepository.findById(wishlistCreateDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Wishlist wishlist = new Wishlist();
        wishlist.setName(wishlistCreateDTO.getName());
        wishlist.setDescription(wishlistCreateDTO.getDescription());
        wishlist.setOwner(owner);

        return wishlistRepository.save(wishlist);
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

    public Wishlist updateWishlist(Long id, Wishlist wishlistDetails) {
        Wishlist wishlist = wishlistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Wishlist not found"));
        wishlist.setName(wishlistDetails.getName());
        wishlist.setDescription(wishlistDetails.getDescription());

        return wishlistRepository.save(wishlist);
    }
}