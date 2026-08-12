ALTER TABLE users
    ADD COLUMN otp_attempt_count INTEGER NOT NULL DEFAULT 0,
ADD COLUMN password_reset_attempt_count INTEGER NOT NULL DEFAULT 0;