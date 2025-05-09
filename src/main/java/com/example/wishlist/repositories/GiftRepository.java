package com.example.wishlist.repositories;

import com.example.wishlist.models.Gift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GiftRepository extends JpaRepository<Gift, Long> {
    List<Gift> findByWishlistId(Long wishlistId);

}