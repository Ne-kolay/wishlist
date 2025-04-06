package com.example.wishlist;

import com.example.wishlist.models.User;
import com.example.wishlist.repositories.UserRepository;
import com.example.wishlist.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setUsername("testuser");
        user.setEmail("testuser@example.com");
        user.setPassword("password123");
    }

    @Test
    void testRegisterUser_shouldThrowException_whenEmailAlreadyExists() {
        when(userRepository.existsByEmail(user.getEmail())).thenReturn(true);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.registerUser(user.getUsername(), user.getEmail(), user.getPassword());
        });

        assertEquals("Email is already in use", exception.getMessage());
    }

    @Test
    void testRegisterUser_shouldThrowException_whenUsernameAlreadyExists() {
        when(userRepository.existsByUsername(user.getUsername())).thenReturn(true);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.registerUser(user.getUsername(), user.getEmail(), user.getPassword());
        });

        assertEquals("Username is already taken", exception.getMessage());
    }

    @Test
    void testRegisterUser_shouldSaveUser_whenDataIsValid() {
        when(userRepository.existsByEmail(user.getEmail())).thenReturn(false);
        when(userRepository.existsByUsername(user.getUsername())).thenReturn(false);
        when(passwordEncoder.encode(user.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        User savedUser = userService.registerUser(user.getUsername(), user.getEmail(), user.getPassword());

        assertNotNull(savedUser);
        assertEquals(user.getUsername(), savedUser.getUsername());
        assertEquals(user.getEmail(), savedUser.getEmail());
    }

    @Test
    void testFindByEmail_shouldReturnUser_whenEmailExists() {
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        Optional<User> foundUser = userService.findByEmail(user.getEmail());

        assertTrue(foundUser.isPresent());
        assertEquals(user.getEmail(), foundUser.get().getEmail());
    }

    @Test
    void testFindByEmail_shouldReturnEmpty_whenEmailDoesNotExist() {
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());

        Optional<User> foundUser = userService.findByEmail(user.getEmail());

        assertFalse(foundUser.isPresent());
    }

    @Test
    void testFindByUsername_shouldReturnUser_whenUsernameExists() {
        when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));

        Optional<User> foundUser = userService.findByUsername(user.getUsername());

        assertTrue(foundUser.isPresent());
        assertEquals(user.getUsername(), foundUser.get().getUsername());
    }

    @Test
    void testFindByUsername_shouldReturnEmpty_whenUsernameDoesNotExist() {
        when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.empty());

        Optional<User> foundUser = userService.findByUsername(user.getUsername());

        assertFalse(foundUser.isPresent());
    }

    @Test
    void testCheckPassword_shouldReturnTrue_whenPasswordMatches() {
        when(passwordEncoder.matches(user.getPassword(), user.getPassword())).thenReturn(true);

        boolean isPasswordCorrect = userService.checkPassword(user, user.getPassword());

        assertTrue(isPasswordCorrect);
    }

    @Test
    void testCheckPassword_shouldReturnFalse_whenPasswordDoesNotMatch() {
        // Здесь порядок аргументов должен быть такой же, как в реальном вызове в сервисе
        when(passwordEncoder.matches("wrongPassword", user.getPassword())).thenReturn(false);

        boolean isPasswordCorrect = userService.checkPassword(user, "wrongPassword");

        assertFalse(isPasswordCorrect);
    }
}