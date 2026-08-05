package com.talabaty.backend.auth.service;

import com.talabaty.backend.auth.dto.request.LoginRequest;
import com.talabaty.backend.auth.dto.request.SignupRequest;
import com.talabaty.backend.auth.dto.response.LoginResponse;
import com.talabaty.backend.auth.dto.response.RegisterResponse;

public interface AuthService {
    RegisterResponse register(SignupRequest request);
    LoginResponse login(LoginRequest request);

}
