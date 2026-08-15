-- V11: Drop the 'Government' column from the 'addresses' table.

-- The 'order_status' column is a VARCHAR, so no database migration is needed
-- to add new enum values. The changes in the OrderStatus.java file are sufficient.
-- The previous ALTER TYPE commands have been removed as they caused an error.

ALTER TABLE addresses DROP COLUMN IF EXISTS Government;
