package com.example.wishlist.controllers;

import com.example.wishlist.dto.UserRegisterDTO;
import com.example.wishlist.dto.UserPasswordCheckDTO;
import com.example.wishlist.dto.PasswordCheckResponse;
import com.example.wishlist.models.User;
import com.example.wishlist.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegisterDTO userDTO) {
        try {
            User newUser = userService.registerUser(
                    userDTO.getUsername(),
                    userDTO.getEmail(),
                    userDTO.getPassword()
            );
            return ResponseEntity.ok(newUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        return userService.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        return userService.findByUsername(username)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/check-password")
    public ResponseEntity<PasswordCheckResponse> checkPassword(@RequestBody UserPasswordCheckDTO passwordCheckDTO) {
        Optional<User> userOpt = userService.findByEmail(passwordCheckDTO.getEmail());
        boolean isValid = userOpt.map(user -> userService.checkPassword(user, passwordCheckDTO.getPassword()))
                .orElse(false);

        return ResponseEntity.ok(new PasswordCheckResponse(isValid));
    }

    @GetMapping("/id/{email}")
    public ResponseEntity<?> getUserIdByEmail(@PathVariable String email) {
        return userService.findByEmail(email)
                .map(user -> ResponseEntity.ok(user.getId())) // Возвращаем userId
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}