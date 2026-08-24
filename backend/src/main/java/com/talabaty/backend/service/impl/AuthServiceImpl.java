package com.talabaty.backend.service.impl;

import com.talabaty.backend.dto.request.ChangeEmailRequest;
import com.talabaty.backend.dto.request.ChangePasswordWithOtpRequest;
import com.talabaty.backend.dto.request.CustomerSignupRequest;
import com.talabaty.backend.dto.request.DriverSignupRequest;
import com.talabaty.backend.dto.request.LoginRequest;
import com.talabaty.backend.dto.request.VerifyEmailChangeRequest;
import com.talabaty.backend.dto.request.VerifyPasswordChangeRequest;
import com.talabaty.backend.service.LoginRateLimitService;
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
            // Null when the customer skipped the field - it is optional here,
            // unlike driver signup. Previously hardcoded to null because the
            // request had no such field, which silently discarded whatever the
            // signup form collected.
            customerProfile.setPhoneNumber(request.getPhoneNumber());
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

        if (user.getOtpAttemptCount() >= MAX_OTP_ATTEMPTS) {
            throw new ResponseStatusException(HttpStatus.TOO_MANY_REQUESTS, "Too many failed attempts. Please request a new OTP.");
        }

        if (user.getOtp() == null || !user.getOtp().equals(otp)) {
            user.setOtpAttemptCount(user.getOtpAttemptCount() + 1);
            userRepository.save(user);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid OTP");
        }

        if (user.getOtpExpiration() == null ||
                user.getOtpExpiration().isBefore(LocalDateTime.now())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "This OTP has expired. Please request a new one."
            );
        }

        user.setEmailVerified(true);
        user.setOtp(null);
        user.setOtpExpiration(null);
        user.setOtpAttemptCount(0); // Clear on success
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
        user.setOtpAttemptCount(0); // Reset attempt count on new OTP
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
        user.setPasswordResetAttemptCount(0); // Reset attempt count on new OTP
        userRepository.save(user);

        emailService.sendPasswordResetEmail(user.getEmail(), otp);
    }

    @Override
    public void resetPassword(String email, String otp, String newPassword) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        // Check attempt count before validating OTP
        if (user.getPasswordResetAttemptCount() >= MAX_OTP_ATTEMPTS) {
            throw new ResponseStatusException(HttpStatus.TOO_MANY_REQUESTS, "Too many failed attempts. Please request a new OTP.");
        }

        if (user.getPasswordResetToken() == null || !user.getPasswordResetToken().equals(otp)) {
            user.setPasswordResetAttemptCount(user.getPasswordResetAttemptCount() + 1);
            userRepository.save(user);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid OTP");
        }

        if (user.getPasswordResetTokenExpiration().isBefore(LocalDateTime.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This password reset OTP has expired. Please request a new one.");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        user.setPasswordResetToken(null);
        user.setPasswordResetTokenExpiration(null);
        user.setPasswordResetAttemptCount(0); // Clear on success
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

    private static final int MAX_OTP_ATTEMPTS = 5;

    @Override
    @Transactional
    public void requestEmailChange(Long userId, ChangeEmailRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Current password is incorrect");
        }

        if (userRepository.findByEmail(request.getNewEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already in use");
        }

        // Generate OTP for the NEW email
        String otp = generateNumericOtp();
        user.setEmailChangeToken(otp);
        user.setEmailChangeTokenExpiration(LocalDateTime.now().plusMinutes(15));
        user.setPendingEmail(request.getNewEmail());
        user.setEmailChangeAttemptCount(0); // Reset attempt count on new OTP
        userRepository.save(user);

        // Send OTP to the NEW email
        emailService.sendOtpEmail(request.getNewEmail(), otp);
    }

    @Override
    @Transactional
    public void verifyEmailChange(VerifyEmailChangeRequest request) {
        // Find user by PENDING email (the new email they want to change to)
        User user = userRepository.findByPendingEmail(request.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No pending email change for this email"));

        // Check attempt count before validating OTP
        if (user.getEmailChangeAttemptCount() >= MAX_OTP_ATTEMPTS) {
            throw new ResponseStatusException(HttpStatus.TOO_MANY_REQUESTS, "Too many failed attempts. Please request a new OTP.");
        }

        if (user.getEmailChangeToken() == null || !user.getEmailChangeToken().equals(request.getOtp())) {
            // Increment attempt count on failed verification
            user.setEmailChangeAttemptCount(user.getEmailChangeAttemptCount() + 1);
            userRepository.save(user);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid OTP");
        }

        if (user.getEmailChangeTokenExpiration() == null ||
                user.getEmailChangeTokenExpiration().isBefore(LocalDateTime.now())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "This OTP has expired. Please request a new one."
            );
        }

        // Update email - success, clear all tokens and attempt count
        user.setEmail(user.getPendingEmail());
        user.setEmailVerified(true);
        user.setPendingEmail(null);
        user.setEmailChangeToken(null);
        user.setEmailChangeTokenExpiration(null);
        user.setEmailChangeAttemptCount(0);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void requestPasswordChange(Long userId, ChangePasswordWithOtpRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Current password is incorrect");
        }

        // Generate OTP for password change - use DEDICATED fields separate from forgot-password
        String otp = generateNumericOtp();
        user.setPasswordChangeToken(otp);
        user.setPasswordChangeTokenExpiration(LocalDateTime.now().plusMinutes(15));
        user.setPasswordChangeAttemptCount(0); // Reset attempt count on new OTP
        userRepository.save(user);

        emailService.sendPasswordResetEmail(user.getEmail(), otp);
    }

    @Override
    @Transactional
    public void verifyPasswordChange(VerifyPasswordChangeRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        // Check attempt count before validating OTP
        if (user.getPasswordChangeAttemptCount() >= MAX_OTP_ATTEMPTS) {
            throw new ResponseStatusException(HttpStatus.TOO_MANY_REQUESTS, "Too many failed attempts. Please request a new OTP.");
        }

        // Use DEDICATED password change fields (separate from forgot-password)
        if (user.getPasswordChangeToken() == null || !user.getPasswordChangeToken().equals(request.getOtp())) {
            // Increment attempt count on failed verification
            user.setPasswordChangeAttemptCount(user.getPasswordChangeAttemptCount() + 1);
            userRepository.save(user);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid OTP");
        }

        if (user.getPasswordChangeTokenExpiration() == null ||
                user.getPasswordChangeTokenExpiration().isBefore(LocalDateTime.now())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "This password change OTP has expired. Please request a new one."
            );
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        user.setPasswordChangeToken(null);
        user.setPasswordChangeTokenExpiration(null);
        user.setPasswordChangeAttemptCount(0);
        userRepository.save(user);
    }
}
