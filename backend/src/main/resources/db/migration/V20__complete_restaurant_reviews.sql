ALTER TABLE reviews
    ADD COLUMN IF NOT EXISTS created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP;

ALTER TABLE reviews
ALTER COLUMN comment TYPE VARCHAR(500);

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1 FROM information_schema.constraint_column_usage 
        WHERE table_name = 'reviews' AND constraint_name = 'chk_reviews_rating'
    ) THEN
        ALTER TABLE reviews
            ADD CONSTRAINT chk_reviews_rating
                CHECK (rating BETWEEN 1 AND 5);
    END IF;
END
$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1 FROM information_schema.constraint_column_usage 
        WHERE table_name = 'reviews' AND constraint_name = 'uq_reviews_order_id'
    ) THEN
        ALTER TABLE reviews
            ADD CONSTRAINT uq_reviews_order_id
                UNIQUE (order_id);
    END IF;
END
$$;

CREATE INDEX IF NOT EXISTS idx_reviews_restaurant_id
    ON reviews(restaurant_id);