package com.talabaty.backend.controller;

import tools.jackson.databind.JsonNode;
import com.talabaty.backend.dto.request.CustomerProfileUpdateRequest;
import com.talabaty.backend.dto.request.DriverProfileUpdateRequest;
import com.talabaty.backend.service.ProfileService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
@RestController
@RequestMapping("/api/profile")
@SecurityRequirement(name = "bearerAuth")
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

//        Object profileResponse = profileService.getProfile(email);
        Long userId = Long.valueOf(authentication.getName());
        Object profileResponse = profileService.getProfile(userId);
        return ResponseEntity.ok(profileResponse);
    }

    @PutMapping
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(oneOf = {
                            CustomerProfileUpdateRequest.class,
                            DriverProfileUpdateRequest.class
                    }),
                    examples = {
                            @ExampleObject(
                                    name = "Customer profile",
                                    value = """
                                            {
                                              "name": "Thoraya",
                                              "phoneNumber": "201001234567"
                                            }
                                            """
                            ),
                            @ExampleObject(
                                    name = "Driver profile",
                                    value = """
                                            {
                                              "name": "Thoraya",
                                              "phoneNumber": "201001234567",
                                              "vehicleType": "Motorcycle",
                                              "licenseNumber": "ABC123",
                                              "nationalId": "29801011234567"
                                            }
                                            """
                            )
                    }
            )
    )
    public ResponseEntity<?> updateProfile(
            Authentication authentication,
            @RequestBody JsonNode requestBody) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(401).body("User not authenticated");
        }

       // profileService.updateProfile(authentication.getName(), requestBody);
        Long userId = Long.valueOf(authentication.getName());
        profileService.updateProfile(userId, requestBody);
        return ResponseEntity.ok().build();
    }
}
