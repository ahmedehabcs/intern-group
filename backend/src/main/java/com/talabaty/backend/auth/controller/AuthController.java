package com.talabaty.backend.auth.controller;

import com.talabaty.backend.auth.dto.request.LoginRequest;
import com.talabaty.backend.auth.dto.request.RegisterRequest;
import com.talabaty.backend.auth.dto.response.LoginResponse;
import com.talabaty.backend.auth.dto.response.RegisterResponse;
import com.talabaty.backend.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<RegisterResponse> signup(@RequestBody RegisterRequest request) {
        // Temporary placeholder: implementation pending
        throw new UnsupportedOperationException("signup not implemented");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        // Temporary placeholder: implementation pending
        throw new UnsupportedOperationException("login not implemented");
    }
}
