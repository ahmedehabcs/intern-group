-- Add missing immutable order-history snapshot columns as nullable first.

ALTER TABLE order_items
    ADD COLUMN product_name VARCHAR(255);

ALTER TABLE historical_order_item_addons
    ADD COLUMN quantity INTEGER;

ALTER TABLE orders
    ADD COLUMN restaurant_name VARCHAR(255),
    ADD COLUMN delivery_address TEXT,
    ADD COLUMN subtotal NUMERIC,
    ADD COLUMN created_at TIMESTAMP WITHOUT TIME ZONE;


-- Backfill the product name from the currently linked menu item.

UPDATE order_items oi
SET product_name = mi.name
FROM menu_items mi
WHERE oi.menu_item_id = mi.id;


-- Backfill the restaurant name from the currently linked restaurant.

UPDATE orders o
SET restaurant_name = r.name
FROM restaurants r
WHERE o.restaurant_id = r.id;


-- Backfill a formatted delivery-address snapshot from the current address.
-- Null or blank optional components are omitted.

UPDATE orders o
SET delivery_address = COALESCE(
    NULLIF(
        CONCAT_WS(
            ', ',
            NULLIF(TRIM(a.street), ''),
            CASE
                WHEN NULLIF(TRIM(a.building), '') IS NOT NULL
                    THEN 'Building ' || TRIM(a.building)
            END,
            CASE
                WHEN NULLIF(TRIM(a.floor), '') IS NOT NULL
                    THEN 'Floor ' || TRIM(a.floor)
            END,
            CASE
                WHEN NULLIF(TRIM(a.apartment), '') IS NOT NULL
                    THEN 'Apartment ' || TRIM(a.apartment)
            END,
            NULLIF(TRIM(a.city), ''),
            NULLIF(TRIM(g.name), '')
        ),
        ''
    ),
    'Address unavailable'
)
FROM addresses a
LEFT JOIN governorates g
    ON g.id = a.governorate_id
WHERE o.address_id = a.id;


-- Backfill order subtotal from the existing total and delivery fee.

UPDATE orders
SET subtotal = total_price - delivery_fee;


-- Use the existing update timestamp as the best available creation
-- timestamp for historical rows.

UPDATE orders
SET created_at = updated_at;


-- The previous schema did not preserve historical addon quantity.

UPDATE historical_order_item_addons
SET quantity = 1;


-- Make all snapshot columns required after backfilling existing rows.

ALTER TABLE order_items
    ALTER COLUMN product_name SET NOT NULL;

ALTER TABLE historical_order_item_addons
    ALTER COLUMN quantity SET NOT NULL;

ALTER TABLE orders
    ALTER COLUMN restaurant_name SET NOT NULL,
    ALTER COLUMN delivery_address SET NOT NULL,
    ALTER COLUMN subtotal SET NOT NULL,
    ALTER COLUMN created_at SET NOT NULL;
