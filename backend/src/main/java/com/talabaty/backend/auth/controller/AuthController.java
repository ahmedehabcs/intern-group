package com.talabaty.backend.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.talabaty.backend.user.service.AuthService;
import com.talabaty.backend.user.dto.request.SignupRequest;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*") // عشان الفرونت إند يعرف يكلم الباك إند
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody SignupRequest request) {
        String result = authService.registerUser(request);

        if (result.startsWith("Error")) {
            return ResponseEntity.badRequest().body(result);
        }
        return ResponseEntity.ok(result);
    }
}
