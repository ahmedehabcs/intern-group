package com.talabaty.backend.auth.service;

import com.talabaty.backend.auth.dto.request.LoginRequest;
import com.talabaty.backend.auth.dto.request.RegisterRequest;
import com.talabaty.backend.auth.dto.response.LoginResponse;
import com.talabaty.backend.auth.dto.response.RegisterResponse;

public interface AuthService {
    RegisterResponse register(RegisterRequest request);
    LoginResponse login(LoginRequest request);
}
