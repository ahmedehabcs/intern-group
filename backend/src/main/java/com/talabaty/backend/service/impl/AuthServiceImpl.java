package com.talabaty.backend.service.impl;

import com.talabaty.backend.dto.request.CustomerSignupRequest;
import com.talabaty.backend.dto.request.DriverSignupRequest;
import com.talabaty.backend.service.LoginRateLimitService;
import com.talabaty.backend.dto.request.LoginRequest;
import com.talabaty.backend.dto.response.LoginResponse;
import com.talabaty.backend.dto.response.RegisterResponse;
import com.talabaty.backend.service.AuthService;
import com.talabaty.backend.service.EmailService;
import com.talabaty.backend.security.JwtService;
import com.talabaty.backend.model.*;
import com.talabaty.backend.repository.AdminRepository;
import com.talabaty.backend.repository.CustomerProfileRepository;
import com.talabaty.backend.repository.DeliveryProfileRepository;
import com.talabaty.backend.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final CustomerProfileRepository customerProfileRepository;
    private final DeliveryProfileRepository deliveryProfileRepository;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final EmailService emailService;
    private final LoginRateLimitService loginRateLimitService;

    public AuthServiceImpl(
            UserRepository userRepository,
            CustomerProfileRepository customerProfileRepository,
            DeliveryProfileRepository deliveryProfileRepository,
            AdminRepository adminRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            EmailService emailService,
            LoginRateLimitService loginRateLimitService
    ) {
        this.userRepository = userRepository;
        this.customerProfileRepository = customerProfileRepository;
        this.deliveryProfileRepository = deliveryProfileRepository;
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.emailService = emailService;
        this.loginRateLimitService = loginRateLimitService;
    }

    @Override
    @Transactional
    public RegisterResponse registerCustomer(CustomerSignupRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already in use");
        }

        try {
            User user = new User(request.getEmail(), passwordEncoder.encode(request.getPassword()), Role.CUSTOMER);
            user.setEmailVerified(false);
            generateAndSetOtp(user);

            User savedUser = userRepository.save(user);

            CustomerProfile customerProfile = new CustomerProfile();
            customerProfile.setUser(savedUser);
            customerProfile.setName(request.getName());
            // The new spec for CustomerSignupRequest doesn't include a phone number.
            customerProfile.setPhoneNumber(null); 
            customerProfileRepository.save(customerProfile);

            emailService.sendOtpEmail(savedUser.getEmail(), savedUser.getOtp());

            return new RegisterResponse("OTP sent to email for verification.");
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already in use");
        }
    }

    @Override
    @Transactional
    public RegisterResponse registerDriver(DriverSignupRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already in use");
        }

        try {
            User user = new User(request.getEmail(), passwordEncoder.encode(request.getPassword()), Role.DRIVER);
            user.setEmailVerified(false);
            generateAndSetOtp(user);

            User savedUser = userRepository.save(user);

            DeliveryProfile deliveryProfile = new DeliveryProfile();
            deliveryProfile.setUser(savedUser);
            deliveryProfile.setName(request.getName());
            deliveryProfile.setPhoneNumber(request.getPhoneNumber());
            deliveryProfile.setVehicleType(request.getVehicleType());
            deliveryProfile.setLicenseNumber(request.getLicenseNumber());
            deliveryProfile.setNationalId(request.getNationalId());
            deliveryProfileRepository.save(deliveryProfile);

            emailService.sendOtpEmail(savedUser.getEmail(), savedUser.getOtp());

            return new RegisterResponse("OTP sent to email for verification.");
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already in use");
        }
    }


    @Override
    public void verifyOtp(String email, String otp) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        if (user.getOtp() == null || !user.getOtp().equals(otp)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid OTP");
        }

        if (user.getOtpExpiration().isBefore(LocalDateTime.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This OTP has expired. Please request a new one.");
        }

        user.setEmailVerified(true);
        user.setOtp(null);
        user.setOtpExpiration(null);
        userRepository.save(user);
    }

    @Override
    public void resendOtp(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        if (user.isEmailVerified()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is already verified");
        }

        generateAndSetOtp(user);
        userRepository.save(user);
        emailService.sendOtpEmail(user.getEmail(), user.getOtp());
    }

    @Override
    public void forgotPassword(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        String otp = generateNumericOtp();
        user.setPasswordResetToken(otp);
        user.setPasswordResetTokenExpiration(LocalDateTime.now().plusMinutes(15));
        userRepository.save(user);

        emailService.sendPasswordResetEmail(user.getEmail(), otp);
    }

    @Override
    public void resetPassword(String email, String otp, String newPassword) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        if (user.getPasswordResetToken() == null || !user.getPasswordResetToken().equals(otp)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid OTP");
        }

        if (user.getPasswordResetTokenExpiration().isBefore(LocalDateTime.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This password reset OTP has expired. Please request a new one.");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        user.setPasswordResetToken(null);
        user.setPasswordResetTokenExpiration(null);
        userRepository.save(user);
    }

    private void generateAndSetOtp(User user) {
        String otp = generateNumericOtp();
        user.setOtp(otp);
        user.setOtpExpiration(LocalDateTime.now().plusMinutes(15));
    }
    
    private String generateNumericOtp() {
        return new Random().ints(6, 0, 10)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining());
    }

    @Override
    public LoginResponse login(LoginRequest request, String clientIp) {
        if (request == null
                || request.getEmail() == null
                || request.getEmail().isBlank()
                || request.getPassword() == null
                || request.getPassword().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Email and password are required"
            );
        }

        // Blocks the request if this email and IP exceeded the configured limit.
        loginRateLimitService.checkLoginAllowed(request.getEmail(), clientIp);

        User user = userRepository.findByEmail(request.getEmail())
                .orElse(null);

        if (user == null) {
            loginRateLimitService.recordFailedAttempt(request.getEmail(), clientIp);

            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Invalid email or password"
            );
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            loginRateLimitService.recordFailedAttempt(request.getEmail(), clientIp);

            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Invalid email or password"
            );
        }

        if (!user.isEmailVerified()) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Email is not verified"
            );
        }

        // A successful login clears earlier failures for this email and IP.
        loginRateLimitService.clearFailedAttempts(request.getEmail(), clientIp);

        String accessToken = jwtService.generateAccessToken(user);

        return new LoginResponse(
                user.getRole(),
                user.getEmail(),
                user.getId(),
                jwtService.getAccessTokenExpirationSeconds(),
                "Bearer",
                accessToken
        );
    }
}
