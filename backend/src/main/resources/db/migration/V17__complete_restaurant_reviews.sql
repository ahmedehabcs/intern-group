ALTER TABLE reviews
    ADD COLUMN created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP;

ALTER TABLE reviews
ALTER COLUMN comment TYPE VARCHAR(500);

ALTER TABLE reviews
    ADD CONSTRAINT chk_reviews_rating
        CHECK (rating BETWEEN 1 AND 5);

ALTER TABLE reviews
    ADD CONSTRAINT uq_reviews_order_id
        UNIQUE (order_id);

CREATE INDEX idx_reviews_restaurant_id
    ON reviews(restaurant_id);