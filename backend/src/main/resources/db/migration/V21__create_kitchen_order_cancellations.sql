CREATE TABLE kitchen_order_cancellations (
    id BIGSERIAL PRIMARY KEY,
    order_id BIGINT NOT NULL UNIQUE,
    kitchen_manager_id BIGINT NOT NULL,
    reason VARCHAR(255) NOT NULL,
    cancelled_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,

    CONSTRAINT fk_kitchen_order_cancellation_order
        FOREIGN KEY (order_id) REFERENCES orders(id),

    CONSTRAINT fk_kitchen_order_cancellation_manager
        FOREIGN KEY (kitchen_manager_id) REFERENCES kitchen_managers(id)
);
