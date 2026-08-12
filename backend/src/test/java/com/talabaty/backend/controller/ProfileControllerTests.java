package com.talabaty.backend.controller;

import com.talabaty.backend.config.OpenApiConfig;
import com.talabaty.backend.dto.request.UpdateProfileRequest;
import com.talabaty.backend.dto.response.ProfileResponse;
import com.talabaty.backend.service.ProfileService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ProfileControllerTests {

    @Test
    void getProfileUsesAuthenticatedPrincipalEmail() {
        ProfileService profileService = mock(ProfileService.class);
        ProfileController controller = new ProfileController(profileService);
        UserDetails principal = principal();
        ProfileResponse expected = new ProfileResponse();
        when(profileService.getProfile(principal.getUsername())).thenReturn(expected);

        assertEquals(expected, controller.getProfile(principal).getBody());
        verify(profileService).getProfile("customer@example.com");
    }

    @Test
    void updateProfileReturnsUpdatedProfile() {
        ProfileService profileService = mock(ProfileService.class);
        ProfileController controller = new ProfileController(profileService);
        UserDetails principal = principal();
        UpdateProfileRequest request = new UpdateProfileRequest();
        ProfileResponse expected = new ProfileResponse();
        when(profileService.updateProfile(principal.getUsername(), request)).thenReturn(expected);

        assertEquals(expected, controller.updateProfile(principal, request).getBody());
        verify(profileService).updateProfile("customer@example.com", request);
    }

    @Test
    void declaresSwaggerBearerSecurityRequirement() {
        SecurityRequirement requirement = ProfileController.class.getAnnotation(SecurityRequirement.class);

        assertNotNull(requirement);
        assertEquals(OpenApiConfig.SECURITY_SCHEME_NAME, requirement.name());
    }

    private UserDetails principal() {
        return User.withUsername("customer@example.com")
                .password("")
                .authorities("ROLE_CUSTOMER")
                .build();
    }
}
