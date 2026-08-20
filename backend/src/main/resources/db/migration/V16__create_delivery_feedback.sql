CREATE TABLE delivery_feedback (
                                   id BIGSERIAL PRIMARY KEY,
                                   order_id BIGINT NOT NULL UNIQUE,
                                   customer_id BIGINT NOT NULL,
                                   rider_id BIGINT NOT NULL,
                                   rating INTEGER NOT NULL CHECK (rating BETWEEN 1 AND 5),
                                   comment VARCHAR(500),
                                   created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

                                   CONSTRAINT fk_delivery_feedback_order
                                       FOREIGN KEY (order_id) REFERENCES orders(id),

                                   CONSTRAINT fk_delivery_feedback_customer
                                       FOREIGN KEY (customer_id) REFERENCES customers(id),

                                   CONSTRAINT fk_delivery_feedback_rider
                                       FOREIGN KEY (rider_id) REFERENCES drivers(id)
);

CREATE INDEX idx_delivery_feedback_rider_id
    ON delivery_feedback(rider_id);