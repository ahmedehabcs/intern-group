package com.talabaty.backend.dto.response;

import lombok.Getter;
import lombok.Setter;

import com.talabaty.backend.model.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
@Getter
@Setter
public class LoginResponse {
    private String message;
    @Schema(
            description = "JWT access token for authenticated requests",
            example = "eyJhbGciOiJIUzI1NiJ9..."
    )
    String accessToken;
    @Schema(
            description = "Authentication scheme",
            example = "Bearer"
    )
    String tokenType;
    @Schema(
            description = "Access-token validity in seconds",
            example = "900"
    )
    long expiresIn;
    @Schema(
            description = "Authenticated user ID",
            example = "1"
    )
    Long userId;
    @Schema(
            description = "Authenticated user email",
            example = "customer@example.com"
    )


    String email;
    @Schema(
            description = "Authenticated user role",
            example = "CUSTOMER"
    )
    Role role;


    public LoginResponse() {}

    public LoginResponse(String message) { this.message = message; }


    public LoginResponse(Role role, String email, Long userId, long expiresIn, String tokenType, String accessToken) {
        this.role = role;
        this.email = email;
        this.userId = userId;
        this.expiresIn = expiresIn;
        this.tokenType = tokenType;
        this.accessToken = accessToken;
    }











}
