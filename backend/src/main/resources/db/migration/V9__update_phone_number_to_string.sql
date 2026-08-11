-- BUG 3 FIX: Change phone_number column from a numeric type to VARCHAR
-- to preserve leading zeros and support more flexible formats.

ALTER TABLE customers ALTER COLUMN phone_number TYPE VARCHAR(255);
ALTER TABLE drivers ALTER COLUMN phone_number TYPE VARCHAR(255);
