package com.talabaty.backend.controller;

import com.talabaty.backend.dto.request.ForgotPasswordRequest;
import com.talabaty.backend.dto.request.LoginRequest;
import com.talabaty.backend.dto.request.ResetPasswordRequest;
import com.talabaty.backend.dto.request.VerifyOtpRequest;
import com.talabaty.backend.dto.response.LoginResponse;
import com.talabaty.backend.dto.response.RegisterResponse;
import com.talabaty.backend.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
