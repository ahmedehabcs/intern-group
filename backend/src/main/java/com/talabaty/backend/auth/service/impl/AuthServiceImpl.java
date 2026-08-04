package com.talabaty.backend.auth.service.impl;

import com.talabaty.backend.auth.dto.request.LoginRequest;
import com.talabaty.backend.auth.dto.request.RegisterRequest;
import com.talabaty.backend.auth.dto.response.LoginResponse;
import com.talabaty.backend.auth.dto.response.RegisterResponse;
import com.talabaty.backend.auth.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public RegisterResponse register(RegisterRequest request) {
        // TODO: implement registration logic
        throw new UnsupportedOperationException("register not implemented");
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        // TODO: implement login logic
        throw new UnsupportedOperationException("login not implemented");
    }
}
