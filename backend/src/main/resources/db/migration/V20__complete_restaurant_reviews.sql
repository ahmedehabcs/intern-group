ALTER TABLE reviews
    ADD COLUMN IF NOT EXISTS created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP;

ALTER TABLE reviews
ALTER COLUMN comment TYPE VARCHAR(500);

ALTER TABLE reviews
    ADD CONSTRAINT IF NOT EXISTS chk_reviews_rating
        CHECK (rating BETWEEN 1 AND 5);

ALTER TABLE reviews
    ADD CONSTRAINT IF NOT EXISTS uq_reviews_order_id
        UNIQUE (order_id);

CREATE INDEX IF NOT EXISTS idx_reviews_restaurant_id
    ON reviews(restaurant_id);