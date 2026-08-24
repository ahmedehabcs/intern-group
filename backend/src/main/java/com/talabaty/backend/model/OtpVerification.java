package com.talabaty.backend.model;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;


    @Entity
    public class OtpVerification {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Getter
        @Setter
        private String email;

        @Getter
        @Setter
        private String otpHash;       // never store raw OTP

        @Getter
        @Setter
        private LocalDateTime expiresAt;

        @Getter
        @Setter
        private int attemptCount;

        @Getter
        @Setter
        private boolean verified;

        @Override
        public String toString() {
            return "OtpVerification{" +
                    "id=" + id +
                    ", email='" + email + '\'' +
                    ", otpHash='" + otpHash + '\'' +
                    ", expiresAt=" + expiresAt +
                    ", attemptCount=" + attemptCount +
                    ", verified=" + verified +
                    '}';
        }
    }
