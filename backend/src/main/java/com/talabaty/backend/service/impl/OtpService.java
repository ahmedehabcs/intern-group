package com.talabaty.backend.service.impl;

import com.talabaty.backend.model.OtpVerification;
import com.talabaty.backend.repository.OtpVerificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@Service
public class OtpService {

    @Value("${otp.enabled:true}")
    private boolean otpEnabled;

    @Autowired
    private OtpVerificationRepository otpRepo;

    @Autowired
    private PasswordEncoder passwordEncoder; // reuse your existing BCrypt bean

    private static final int OTP_LENGTH = 6;
    private static final int EXPIRY_MINUTES = 5;
    private static final int MAX_ATTEMPTS = 5;

    public String generateAndStoreOtp(String email) {
        String otp = generateNumericOtp(OTP_LENGTH);

        OtpVerification record = new OtpVerification();
        record.setEmail(email);
        record.setOtpHash(passwordEncoder.encode(otp));
        record.setExpiresAt(LocalDateTime.now().plusMinutes(EXPIRY_MINUTES));
        record.setAttemptCount(0);
        record.setVerified(false);

        otpRepo.save(record);
        return otp; // return raw OTP only so you can send it — never persist it raw
    }

    public boolean verifyOtp(String email, String submittedOtp) {
        OtpVerification record = otpRepo.findTopByEmailOrderByIdDesc(email)
                .orElseThrow(() -> new RuntimeException("No OTP requested for this email"));

        if (record.isVerified()) return true;

        if (record.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("OTP expired");
        }

        if (record.getAttemptCount() >= MAX_ATTEMPTS) {
            throw new RuntimeException("Too many attempts, request a new OTP");
        }

        record.setAttemptCount(record.getAttemptCount() + 1);

        boolean match = passwordEncoder.matches(submittedOtp, record.getOtpHash());
        if (match) {
            record.setVerified(true);
        }
        otpRepo.save(record);
        return match;
    }

    private String generateNumericOtp(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) sb.append(random.nextInt(10));
        return sb.toString();
    }
}
