package com.talabaty.backend.service;

import com.talabaty.backend.dto.request.CustomerSignupRequest;
import com.talabaty.backend.dto.request.DriverSignupRequest;
import com.talabaty.backend.dto.request.LoginRequest;
import com.talabaty.backend.dto.response.LoginResponse;
import com.talabaty.backend.dto.response.RegisterResponse;

public interface AuthService {
    RegisterResponse registerCustomer(CustomerSignupRequest request);
    RegisterResponse registerDriver(DriverSignupRequest request);
    LoginResponse login(LoginRequest request, String clientIp);
    void verifyOtp(String email, String otp);
    void resendOtp(String email);
    void forgotPassword(String email);
    void resetPassword(String email, String otp, String newPassword);
}
