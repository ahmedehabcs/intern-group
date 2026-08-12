package com.talabaty.backend.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.talabaty.backend.service.ProfileService;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    public ResponseEntity<?> getProfile(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(401).body("User not authenticated");
        }
        String email = authentication.getName();
        Object profileResponse = profileService.getProfile(email);
        return ResponseEntity.ok(profileResponse);
    }

    @PutMapping
    public ResponseEntity<?> updateProfile(
            Authentication authentication,
            @RequestBody JsonNode requestBody) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(401).body("User not authenticated");
        }

        profileService.updateProfile(authentication.getName(), requestBody);
        return ResponseEntity.ok().build();
    }
}
