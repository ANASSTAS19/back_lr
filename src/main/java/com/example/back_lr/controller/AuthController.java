package com.example.back_lr.controller;


import com.example.back_lr.config.JwtService;
import com.example.back_lr.dto.AuthRequest;
import com.example.back_lr.dto.AuthResponse;
import com.example.back_lr.entity.User;
import com.example.back_lr.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request) {
        User user = userService.authenticate(request.getUsername(), request.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        String token = jwtService.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(token, user.getUsername(), user.getRole()));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {

        return ResponseEntity.ok().body("Logged out successfully");
    }
}