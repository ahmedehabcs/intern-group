package com.talabaty.backend.controller;

import com.talabaty.backend.dto.request.LoginRequest;
import com.talabaty.backend.dto.response.LoginResponse;
import com.talabaty.backend.dto.response.RegisterResponse;
import com.talabaty.backend.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.talabaty.backend.dto.request.SignupRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.servlet.http.HttpServletRequest;
@Tag(
        name = "Authentication",
        description = "User sign-up and access-token login"
)
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody SignupRequest request) {
        RegisterResponse result = authService.registerUser(request);
        return ResponseEntity.ok(result);
    }

    @Operation(
            summary = "Log in",
            description = "Authenticates with email and password. "
                    + "Returns an access JWT only when the email is verified."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login successful"),
            @ApiResponse(responseCode = "400", description = "Email or password is missing"),
            @ApiResponse(responseCode = "401", description = "Invalid email or password"),
            @ApiResponse(responseCode = "403", description = "Email is not verified"),
            @ApiResponse(responseCode = "500", description = "Unexpected authentication error")
    })
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request,
            HttpServletRequest httpRequest
    ) {
        // Uses the direct caller IP for the in-memory rate-limit key.
        String clientIp = httpRequest.getRemoteAddr();

        return ResponseEntity.ok(authService.login(request, clientIp));
    }
}