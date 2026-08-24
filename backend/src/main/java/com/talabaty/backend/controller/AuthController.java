package com.talabaty.backend.controller;

import com.talabaty.backend.dto.request.*;
import com.talabaty.backend.dto.request.VerifyEmailChangeRequest;
import com.talabaty.backend.dto.request.VerifyPasswordChangeRequest;
import com.talabaty.backend.dto.response.LoginResponse;
import com.talabaty.backend.dto.response.RegisterResponse;
import com.talabaty.backend.service.AuthService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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
@SecurityRequirement(name = "bearerAuth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup/customer")
    public ResponseEntity<RegisterResponse> registerCustomer(@Valid @RequestBody CustomerSignupRequest request) {
        RegisterResponse result = authService.registerCustomer(request);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/signup/driver")
    public ResponseEntity<RegisterResponse> registerDriver(@Valid @RequestBody DriverSignupRequest request) {
        RegisterResponse result = authService.registerDriver(request);
        return ResponseEntity.ok(result);
    }

    @Operation(
            summary = "Verify OTP",
            description = "Verifies the OTP sent to the user's email after signup and activates the account."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Account verified successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid or expired OTP"),
            @ApiResponse(responseCode = "404", description = "No OTP request found for this email")
    })
    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@Valid @RequestBody VerifyOtpRequest request) {
        authService.verifyOtp(request.getEmail(), request.getOtp());
        return ResponseEntity.ok("Account verified successfully");
    }

    @Operation(
            summary = "Resend OTP",
            description = "Resends a new OTP code to the user's email if the previous one expired."
    )
    @PostMapping("/resend-otp")
    public ResponseEntity<?> resendOtp(@RequestParam String email) {
        authService.resendOtp(email);
        return ResponseEntity.ok("OTP resent successfully");
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
    )

    {
        // Uses the direct caller IP for the in-memory rate-limit key.
        String clientIp = httpRequest.getRemoteAddr();

        return ResponseEntity.ok(authService.login(request, clientIp));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@Valid @RequestBody ForgotPasswordRequest request) {
        authService.forgotPassword(request.getEmail());
        return ResponseEntity.ok("Password reset OTP sent to email.");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@Valid @RequestBody ResetPasswordRequest request) {
        authService.resetPassword(request.getEmail(), request.getOtp(), request.getNewPassword());
        return ResponseEntity.ok("Password reset successfully.");
    }

    // --- Email Change with OTP ---
    @Operation(
            summary = "Request email change (sends OTP to new email)",
            description = "Authenticated user requests to change their email. OTP is sent to the NEW email address."
    )
    @PostMapping("/change-email")
    public ResponseEntity<?> requestEmailChange(
            Authentication authentication,
            @Valid @RequestBody ChangeEmailRequest request
    ) {
        Long userId = Long.valueOf(authentication.getName());
        authService.requestEmailChange(userId, request);
        return ResponseEntity.ok("OTP sent to new email for verification.");
    }

    @Operation(
            summary = "Verify email change OTP",
            description = "Verifies OTP sent to the new email and updates the user's email."
    )
    @PostMapping("/verify-email-change")
    public ResponseEntity<?> verifyEmailChange(
            @Valid @RequestBody VerifyEmailChangeRequest request
    ) {
        authService.verifyEmailChange(request);
        return ResponseEntity.ok("Email updated successfully.");
    }

    // --- Password Change with OTP ---
    @Operation(
            summary = "Request password change (sends OTP to current email)",
            description = "Authenticated user requests to change password. OTP is sent to current email."
    )
    @PostMapping("/change-password")
    public ResponseEntity<?> requestPasswordChange(
            Authentication authentication,
            @Valid @RequestBody ChangePasswordWithOtpRequest request
    ) {
        Long userId = Long.valueOf(authentication.getName());
        authService.requestPasswordChange(userId, request);
        return ResponseEntity.ok("OTP sent to email for password change verification.");
    }

    @Operation(
            summary = "Verify password change OTP",
            description = "Verifies OTP and updates the password."
    )
    @PostMapping("/verify-password-change")
    public ResponseEntity<?> verifyPasswordChange(
            @Valid @RequestBody VerifyPasswordChangeRequest request
    ) {
        authService.verifyPasswordChange(request);
        return ResponseEntity.ok("Password updated successfully.");
    }
}
