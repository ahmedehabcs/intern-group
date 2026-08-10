-- V6 has already been applied and must remain immutable for Flyway checksum validation.
-- This is a verified BCrypt hash for the plain-text password: Talabaty@123
UPDATE users
SET password = '$2a$10$b0W/CWxtQ9vqlGYnPK8Xmut/UoRV2fns2EkUId6Pw7weAr4vx3Cu2',
    email_verified = TRUE
WHERE email = 'customer@test.talabaty.local';
