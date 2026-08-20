ALTER TABLE addresses
    ADD COLUMN is_default BOOLEAN NOT NULL DEFAULT FALSE;

UPDATE addresses address
SET is_default = TRUE
WHERE address.id = (
    SELECT first_address.id
    FROM addresses first_address
    WHERE first_address.customer_id = address.customer_id
    ORDER BY first_address.id
    LIMIT 1
);

CREATE UNIQUE INDEX ux_addresses_one_default_per_customer
    ON addresses (customer_id)
    WHERE is_default = TRUE;

ALTER TABLE orders
    DROP CONSTRAINT fk_order_address;

ALTER TABLE orders
    ALTER COLUMN address_id DROP NOT NULL;

ALTER TABLE orders
    ADD CONSTRAINT fk_order_address
        FOREIGN KEY (address_id)
        REFERENCES addresses(id)
        ON DELETE SET NULL;
