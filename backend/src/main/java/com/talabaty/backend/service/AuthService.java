package com.talabaty.backend.service;

import com.talabaty.backend.dto.request.LoginRequest;
import com.talabaty.backend.dto.request.SignupRequest;
import com.talabaty.backend.dto.response.LoginResponse;
import com.talabaty.backend.dto.response.RegisterResponse;

public interface AuthService {
    RegisterResponse registerUser(SignupRequest request);
    LoginResponse login(LoginRequest request, String clientIp);
    void verifyOtp(String email, String otp);
    void resendOtp(String email);
    void forgotPassword(String email);
    void resetPassword(String email, String otp, String newPassword);
}
