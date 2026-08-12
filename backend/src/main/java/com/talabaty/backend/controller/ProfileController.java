package com.talabaty.backend.controller;

import com.talabaty.backend.config.OpenApiConfig;
import com.talabaty.backend.dto.request.UpdateProfileRequest;
import com.talabaty.backend.dto.response.ProfileResponse;
import com.talabaty.backend.service.ProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
@Tag(name = "Profile", description = "View and update the authenticated user's profile")
@SecurityRequirement(name = OpenApiConfig.SECURITY_SCHEME_NAME)
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @Operation(summary = "Get the authenticated user's profile")
    @GetMapping
    public ResponseEntity<ProfileResponse> getProfile(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return ResponseEntity.ok(profileService.getProfile(userDetails.getUsername()));
    }

    @Operation(summary = "Update the authenticated user's profile")
    @PutMapping
    public ResponseEntity<ProfileResponse> updateProfile(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody UpdateProfileRequest request
    ) {
        return ResponseEntity.ok(
                profileService.updateProfile(userDetails.getUsername(), request)
        );
    }
}
