package com.example.wishlist;

import com.example.wishlist.dto.GiftCreateDTO;
import com.example.wishlist.dto.GiftUpdateDTO;
import com.example.wishlist.models.Gift;
import com.example.wishlist.models.Wishlist;
import com.example.wishlist.repositories.GiftRepository;
import com.example.wishlist.repositories.WishlistRepository;
import com.example.wishlist.services.GiftService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GiftServiceTest {

    @Mock
    private GiftRepository giftRepository;

    @Mock
    private WishlistRepository wishlistRepository;

    @InjectMocks
    private GiftService giftService;

    private Wishlist wishlist;
    private Gift gift;

    @BeforeEach
    void setUp() {
        wishlist = new Wishlist();
        wishlist.setId(1L);
        wishlist.setName("Christmas Wishlist");

        gift = new Gift();
        gift.setId(1L);
        gift.setName("Gift 1");
        gift.setWishlist(wishlist);
    }

    @Test
    void testCreateGift() {
        GiftCreateDTO giftCreateDTO = new GiftCreateDTO(
                "Gift 1",
                new BigDecimal("100.0"), // Используем BigDecimal
                "Description of gift 1",
                "http://linktothegift.com"
        );
        when(wishlistRepository.findById(1L)).thenReturn(Optional.of(wishlist));
        when(giftRepository.save(any(Gift.class))).thenReturn(gift);

        Gift createdGift = giftService.createGift(giftCreateDTO, 1L);

        assertNotNull(createdGift);
        assertEquals("Gift 1", createdGift.getName());
        assertEquals(wishlist, createdGift.getWishlist());
        verify(giftRepository, times(1)).save(any(Gift.class));
    }

    @Test
    void testGetGiftsByWishlistId() {
        when(giftRepository.findByWishlistId(1L)).thenReturn(List.of(gift));

        List<Gift> gifts = giftService.getGiftsByWishlistId(1L);

        assertNotNull(gifts);
        assertFalse(gifts.isEmpty());
        assertEquals(1, gifts.size());
        assertEquals(gift, gifts.get(0));
        verify(giftRepository, times(1)).findByWishlistId(1L);
    }

    @Test
    void testGetGiftById() {
        when(giftRepository.findById(1L)).thenReturn(Optional.of(gift));

        Optional<Gift> foundGift = giftService.getGiftById(1L);

        assertTrue(foundGift.isPresent());
        assertEquals(gift, foundGift.get());
        verify(giftRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateGift() {
        GiftUpdateDTO giftUpdateDTO = new GiftUpdateDTO(
                "Updated Gift",
                "http://updatedlink.com", // Правильное значение для поля link
                "Updated description",
                new BigDecimal("120.0") // Используем BigDecimal для estimatedPrice
        );
        when(giftRepository.findById(1L)).thenReturn(Optional.of(gift));
        when(giftRepository.save(any(Gift.class))).thenReturn(gift);

        Gift updatedGift = giftService.updateGift(1L, giftUpdateDTO);

        assertNotNull(updatedGift);
        assertEquals("Updated Gift", updatedGift.getName());
        assertEquals("Updated description", updatedGift.getDescription());
        // Используем compareTo для сравнения BigDecimal с Double
        assertEquals(0, updatedGift.getEstimatedPrice().compareTo(new BigDecimal("120.0")));
        assertEquals("http://updatedlink.com", updatedGift.getLink());
        verify(giftRepository, times(1)).save(any(Gift.class));
    }
    @Test
    void testDeleteGift() {
        // Заглушка для findById уже не требуется, если метод deleteById вызывается без проверки результата findById

        giftService.deleteGift(1L);

        verify(giftRepository, times(1)).deleteById(1L);
    }
}