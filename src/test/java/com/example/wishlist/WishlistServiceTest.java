package com.example.wishlist;

import com.example.wishlist.dto.WishlistCreateDTO;
import com.example.wishlist.models.User;
import com.example.wishlist.models.Wishlist;
import com.example.wishlist.repositories.UserRepository;
import com.example.wishlist.repositories.WishlistRepository;
import com.example.wishlist.services.WishlistService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WishlistServiceTest {

    @Mock
    private WishlistRepository wishlistRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private WishlistService wishlistService;

    private User user;
    private Wishlist wishlist;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setEmail("testuser@example.com");

        wishlist = new Wishlist();
        wishlist.setId(1L);
        wishlist.setName("Christmas Wishlist");
        wishlist.setOwner(user);
    }

    @Test
    void testCreateWishlist() {
        WishlistCreateDTO createDTO = new WishlistCreateDTO("Christmas Wishlist", "Wishlist for Christmas", user.getId());

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(wishlistRepository.save(any(Wishlist.class))).thenReturn(wishlist);

        Wishlist createdWishlist = wishlistService.createWishlist(createDTO);

        assertNotNull(createdWishlist);
        assertEquals("Christmas Wishlist", createdWishlist.getName());
        verify(wishlistRepository, times(1)).save(any(Wishlist.class));
    }

    @Test
    void testGetAllWishlists() {
        when(wishlistRepository.findAll()).thenReturn(Arrays.asList(wishlist));

        List<Wishlist> wishlists = wishlistService.getAllWishlists();

        assertNotNull(wishlists);
        assertFalse(wishlists.isEmpty());
        assertEquals(1, wishlists.size());
        assertEquals("Christmas Wishlist", wishlists.get(0).getName());
    }

    @Test
    void testGetWishlistsByUser() {
        when(wishlistRepository.findByOwnerId(user.getId())).thenReturn(Arrays.asList(wishlist));

        List<Wishlist> wishlists = wishlistService.getWishlistsByUser(user.getId());

        assertNotNull(wishlists);
        assertFalse(wishlists.isEmpty());
        assertEquals(1, wishlists.size());
        assertEquals(user, wishlists.get(0).getOwner());
    }

    @Test
    void testGetWishlistById() {
        when(wishlistRepository.findById(wishlist.getId())).thenReturn(Optional.of(wishlist));

        Optional<Wishlist> foundWishlist = wishlistService.getWishlistById(wishlist.getId());

        assertTrue(foundWishlist.isPresent());
        assertEquals("Christmas Wishlist", foundWishlist.get().getName());
    }

    @Test
    void testDeleteWishlist() {
        doNothing().when(wishlistRepository).deleteById(wishlist.getId());

        wishlistService.deleteWishlist(wishlist.getId());

        verify(wishlistRepository, times(1)).deleteById(wishlist.getId());
    }

    @Test
    void testUpdateWishlist() {
        Wishlist updatedWishlist = new Wishlist();
        updatedWishlist.setId(wishlist.getId());
        updatedWishlist.setName("Updated Wishlist");
        updatedWishlist.setOwner(user);

        when(wishlistRepository.findById(wishlist.getId())).thenReturn(Optional.of(wishlist));
        when(wishlistRepository.save(any(Wishlist.class))).thenReturn(updatedWishlist);

        Wishlist result = wishlistService.updateWishlist(wishlist.getId(), updatedWishlist);

        assertNotNull(result);
        assertEquals("Updated Wishlist", result.getName());
        verify(wishlistRepository, times(1)).save(any(Wishlist.class));
    }
}