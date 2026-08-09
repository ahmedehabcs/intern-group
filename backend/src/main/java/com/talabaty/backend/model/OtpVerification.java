package com.talabaty.backend.model;

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

        private String email;
        private String otpHash;       // never store raw OTP
        private LocalDateTime expiresAt;
        private int attemptCount;
        private boolean verified;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getOtpHash() {
            return otpHash;
        }

        public void setOtpHash(String otpHash) {
            this.otpHash = otpHash;
        }

        public LocalDateTime getExpiresAt() {
            return expiresAt;
        }

        public void setExpiresAt(LocalDateTime expiresAt) {
            this.expiresAt = expiresAt;
        }

        public int getAttemptCount() {
            return attemptCount;
        }

        public void setAttemptCount(int attemptCount) {
            this.attemptCount = attemptCount;
        }

        public boolean isVerified() {
            return verified;
        }

        public void setVerified(boolean verified) {
            this.verified = verified;
        }

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
