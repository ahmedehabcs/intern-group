-- This is a corrected version of the initial schema for a clean database.
-- It includes ON DELETE CASCADE and ON UPDATE CASCADE for all direct user-related profiles.

CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL,
    email_verified BOOLEAN NOT NULL DEFAULT FALSE,
    otp VARCHAR(255),
    otp_expiration TIMESTAMP,
    password_reset_token VARCHAR(255),
    password_reset_token_expiration TIMESTAMP
);

CREATE TABLE customers (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255),
    loyalty_points INT,
    phone_number BIGINT,
    CONSTRAINT fk_customer_user FOREIGN KEY (id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE drivers (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255),
    vehicle_type VARCHAR(255),
    license_number VARCHAR(255),
    is_online BOOLEAN,
    phone_number BIGINT,
    national_id VARCHAR(255),
    CONSTRAINT fk_driver_user FOREIGN KEY (id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE admins (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255),
    phone_number VARCHAR(255),
    CONSTRAINT fk_admin_user FOREIGN KEY (id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE wallets (
    id BIGSERIAL PRIMARY KEY,
    balance DOUBLE PRECISION,
    user_id BIGINT UNIQUE,
    CONSTRAINT fk_wallet_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Other tables from your original V1 script...

CREATE TABLE governorates (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE addresses (
    id BIGSERIAL PRIMARY KEY,
    street VARCHAR(255),
    building VARCHAR(255),
    floor VARCHAR(255),
    apartment VARCHAR(255),
    customer_id BIGINT NOT NULL,
    governorate_id BIGINT,
    CONSTRAINT fk_address_customer FOREIGN KEY (customer_id) REFERENCES customers(id),
    CONSTRAINT fk_address_governorate FOREIGN KEY (governorate_id) REFERENCES governorates(id)
);

CREATE TABLE categories (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE restaurants (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    phone VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    address VARCHAR(255) NOT NULL,
    governorate_id BIGINT NOT NULL,
    description TEXT,
    logo_url VARCHAR(255),
    is_active BOOLEAN DEFAULT TRUE,
    CONSTRAINT fk_restaurant_governorate FOREIGN KEY (governorate_id) REFERENCES governorates(id)
);

CREATE TABLE restaurant_categories (
    restaurant_id BIGINT NOT NULL,
    category_id BIGINT NOT NULL,
    PRIMARY KEY (restaurant_id, category_id),
    CONSTRAINT fk_rc_restaurant FOREIGN KEY (restaurant_id) REFERENCES restaurants(id),
    CONSTRAINT fk_rc_category FOREIGN KEY (category_id) REFERENCES categories(id)
);

CREATE TABLE menu_sections (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    is_active BOOLEAN DEFAULT TRUE,
    restaurant_id BIGINT NOT NULL,
    CONSTRAINT fk_ms_restaurant FOREIGN KEY (restaurant_id) REFERENCES restaurants(id)
);

CREATE TABLE menu_items (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    base_price DOUBLE PRECISION NOT NULL,
    image_url VARCHAR(255),
    is_available BOOLEAN DEFAULT TRUE,
    category_id BIGINT NOT NULL,
    CONSTRAINT fk_mi_category FOREIGN KEY (category_id) REFERENCES menu_sections(id)
);

CREATE TABLE addon_groups (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    min_selections INT NOT NULL,
    max_selections INT NOT NULL
);

CREATE TABLE menu_item_addons (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    additional_price DOUBLE PRECISION DEFAULT 0.0,
    is_available BOOLEAN DEFAULT TRUE,
    addon_group_id BIGINT NOT NULL,
    CONSTRAINT fk_mia_addon_group FOREIGN KEY (addon_group_id) REFERENCES addon_groups(id)
);

CREATE TABLE menu_item_addon_groups (
    menu_item_id BIGINT NOT NULL,
    addon_group_id BIGINT NOT NULL,
    PRIMARY KEY (menu_item_id, addon_group_id),
    CONSTRAINT fk_miag_menu_item FOREIGN KEY (menu_item_id) REFERENCES menu_items(id),
    CONSTRAINT fk_miag_addon_group FOREIGN KEY (addon_group_id) REFERENCES addon_groups(id)
);

CREATE TABLE orders (
    id BIGSERIAL PRIMARY KEY,
    customer_id BIGINT NOT NULL,
    restaurant_id BIGINT NOT NULL,
    rider_id BIGINT,
    address_id BIGINT NOT NULL,
    status VARCHAR(255) NOT NULL,
    payment_method VARCHAR(255) NOT NULL,
    delivery_fee DECIMAL NOT NULL,
    total_price DECIMAL NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_order_customer FOREIGN KEY (customer_id) REFERENCES customers(id),
    CONSTRAINT fk_order_restaurant FOREIGN KEY (restaurant_id) REFERENCES restaurants(id),
    CONSTRAINT fk_order_rider FOREIGN KEY (rider_id) REFERENCES drivers(id),
    CONSTRAINT fk_order_address FOREIGN KEY (address_id) REFERENCES addresses(id)
);

CREATE TABLE order_items (
    id BIGSERIAL PRIMARY KEY,
    order_id BIGINT NOT NULL,
    menu_item_id BIGINT NOT NULL,
    quantity INT NOT NULL,
    unit_price DOUBLE PRECISION NOT NULL,
    notes TEXT,
    CONSTRAINT fk_oi_order FOREIGN KEY (order_id) REFERENCES orders(id),
    CONSTRAINT fk_oi_menu_item FOREIGN KEY (menu_item_id) REFERENCES menu_items(id)
);

CREATE TABLE historical_order_item_addons (
    id BIGSERIAL PRIMARY KEY,
    order_item_id BIGINT NOT NULL,
    addon_name VARCHAR(255) NOT NULL,
    addon_price DOUBLE PRECISION NOT NULL,
    CONSTRAINT fk_hoia_order_item FOREIGN KEY (order_item_id) REFERENCES order_items(id)
);

CREATE TABLE carts (
    id BIGSERIAL PRIMARY KEY,
    customer_id BIGINT NOT NULL UNIQUE,
    restaurant_id BIGINT,
    subtotal DOUBLE PRECISION DEFAULT 0.0,
    CONSTRAINT fk_cart_customer FOREIGN KEY (customer_id) REFERENCES customers(id),
    CONSTRAINT fk_cart_restaurant FOREIGN KEY (restaurant_id) REFERENCES restaurants(id)
);

CREATE TABLE cart_items (
    id BIGSERIAL PRIMARY KEY,
    quantity INT,
    special_instructions TEXT,
    item_total_price DOUBLE PRECISION DEFAULT 0.0,
    cart_id BIGINT NOT NULL,
    menu_item_id BIGINT NOT NULL,
    CONSTRAINT fk_ci_cart FOREIGN KEY (cart_id) REFERENCES carts(id),
    CONSTRAINT fk_ci_menu_item FOREIGN KEY (menu_item_id) REFERENCES menu_items(id)
);

CREATE TABLE cart_addons (
    id BIGSERIAL PRIMARY KEY,
    quantity INT DEFAULT 1,
    price_at_addition DOUBLE PRECISION DEFAULT 0.0,
    cart_item_id BIGINT NOT NULL,
    menu_item_addon_id BIGINT NOT NULL,
    CONSTRAINT fk_ca_cart_item FOREIGN KEY (cart_item_id) REFERENCES cart_items(id),
    CONSTRAINT fk_ca_menu_item_addon FOREIGN KEY (menu_item_addon_id) REFERENCES menu_item_addons(id)
);

CREATE TABLE payments (
    id BIGSERIAL PRIMARY KEY,
    amount DOUBLE PRECISION,
    payment_method VARCHAR(255),
    payment_status VARCHAR(255),
    payment_date TIMESTAMP,
    order_id BIGINT,
    CONSTRAINT fk_payment_order FOREIGN KEY (order_id) REFERENCES orders(id)
);

CREATE TABLE promotions (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255),
    description VARCHAR(255),
    discount_value DOUBLE PRECISION,
    start_date TIMESTAMP,
    end_date TIMESTAMP,
    active BOOLEAN,
    restaurant_id BIGINT,
    CONSTRAINT fk_promo_restaurant FOREIGN KEY (restaurant_id) REFERENCES restaurants(id)
);

CREATE TABLE coupons (
    id BIGSERIAL PRIMARY KEY,
    code VARCHAR(255) UNIQUE,
    discount_value DOUBLE PRECISION,
    discount_type VARCHAR(255),
    expiry_date TIMESTAMP,
    active BOOLEAN
);

CREATE TABLE banners (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255),
    image_url VARCHAR(255),
    redirect_url VARCHAR(255),
    active BOOLEAN
);

CREATE TABLE reviews (
    id BIGSERIAL PRIMARY KEY,
    rating INT,
    comment VARCHAR(255),
    customer_id BIGINT,
    restaurant_id BIGINT,
    order_id BIGINT,
    CONSTRAINT fk_review_customer FOREIGN KEY (customer_id) REFERENCES customers(id),
    CONSTRAINT fk_review_restaurant FOREIGN KEY (restaurant_id) REFERENCES restaurants(id),
    CONSTRAINT fk_review_order FOREIGN KEY (order_id) REFERENCES orders(id)
);
