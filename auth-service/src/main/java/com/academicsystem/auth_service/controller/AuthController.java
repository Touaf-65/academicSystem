package com.academicsystem.auth_service.controller;

import com.academicsystem.auth_service.dto.*;
import com.academicsystem.auth_service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(
            @RequestBody RegisterRequest request
    ) {

        return ResponseEntity.ok(
                authService.signup(request)
        );
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody LoginRequest request
    ) {

        return ResponseEntity.ok(
                authService.login(request)
        );
    }

    @GetMapping("/me")
    public ResponseEntity<ProfileResponse> me(
            @RequestHeader(HttpHeaders.AUTHORIZATION)
            String authHeader
    ) {

        String token =
                authHeader.substring(7);

        return ResponseEntity.ok(
                authService.getProfile(token)
        );
    }

    @PutMapping("/me")
    public ResponseEntity<ProfileResponse> updateProfile(
            @RequestHeader(HttpHeaders.AUTHORIZATION)
            String authHeader,

            @RequestBody
            UpdateProfileRequest request
    ) {

        String token =
                authHeader.substring(7);

        return ResponseEntity.ok(
                authService.updateProfile(token, request)
        );
    }

    @GetMapping("/headers")
    public Map<String, String> getHeaders(
            @RequestHeader(value = "X-User-Id", required = false) String userId,
            @RequestHeader(value = "X-User-Email", required = false) String email,
            @RequestHeader(value = "X-User-Role", required = false) String role
    ) {

        System.out.println("HEADERS ENDPOINT HIT");

        Map<String, String> response = new HashMap<>();

        response.put("userId", userId);
        response.put("email", email);
        response.put("role", role);

        return response;
    }
}