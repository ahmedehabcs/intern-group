-- V22: Add password change and email change attempt tracking fields
-- Separate password change (authenticated) from forgot-password flow

-- Add columns as nullable first
ALTER TABLE users
    ADD COLUMN IF NOT EXISTS password_change_token VARCHAR(255),
    ADD COLUMN IF NOT EXISTS password_change_token_expiration TIMESTAMP,
    ADD COLUMN IF NOT EXISTS password_change_attempt_count INTEGER DEFAULT 0,
    ADD COLUMN IF NOT EXISTS email_change_token VARCHAR(255),
    ADD COLUMN IF NOT EXISTS email_change_token_expiration TIMESTAMP,
    ADD COLUMN IF NOT EXISTS email_change_attempt_count INTEGER DEFAULT 0,
    ADD COLUMN IF NOT EXISTS pending_email VARCHAR(255);

-- Update existing rows with default values
UPDATE users SET password_change_attempt_count = 0 WHERE password_change_attempt_count IS NULL;
UPDATE users SET email_change_attempt_count = 0 WHERE email_change_attempt_count IS NULL;

-- Now set NOT NULL constraints
ALTER TABLE users
    ALTER COLUMN password_change_attempt_count SET NOT NULL,
    ALTER COLUMN email_change_attempt_count SET NOT NULL;