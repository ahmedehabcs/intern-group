BEGIN;

-- Verified customer account.
-- BCrypt hash generated for the plain-text password: Talabaty@123
INSERT INTO users (
    email,
    password,
    role,
    email_verified,
    otp,
    otp_expiration,
    password_reset_token,
    password_reset_token_expiration
)
VALUES (
           'customer@test.talabaty.local',
           '$2a$10$dXJ3SW6G7P50lGQH7ZJmLuD7iPj2Zr4p5A7s9B1c3E5f7G9h1J3K.',
           'CUSTOMER',
           TRUE,
           NULL,
           NULL,
           NULL,
           NULL
       )
    ON CONFLICT (email) DO UPDATE
                               SET password = EXCLUDED.password,
                               role = EXCLUDED.role,
                               email_verified = TRUE,
                               otp = NULL,
                               otp_expiration = NULL,
                               password_reset_token = NULL,
                               password_reset_token_expiration = NULL;

INSERT INTO customers (
    id,
    name,
    loyalty_points,
    phone_number
)
SELECT
    id,
    'Talabaty Test Customer',
    250,
    201001234567
FROM users
WHERE email = 'customer@test.talabaty.local'
    ON CONFLICT (id) DO UPDATE
                            SET name = EXCLUDED.name,
                            loyalty_points = EXCLUDED.loyalty_points,
                            phone_number = EXCLUDED.phone_number;

-- Governorates
INSERT INTO governorates (name)
VALUES
    ('Cairo'),
    ('Giza'),
    ('Alexandria')
    ON CONFLICT (name) DO NOTHING;

-- Global restaurant cuisine categories.
-- These remain completely separate from restaurant menu sections.
INSERT INTO categories (name, description, is_active)
VALUES
    ('Chinese',   'Traditional and modern Chinese cuisine.', TRUE),
    ('Indian',    'Indian curries, biryani, tandoori dishes, and breads.', TRUE),
    ('Italian',   'Italian pasta, pizza, and classic Mediterranean dishes.', TRUE),
    ('Egyptian',  'Traditional Egyptian comfort food and street food.', TRUE),
    ('Asian',     'A broad selection of dishes inspired by Asian cuisines.', TRUE),
    ('Fast Food', 'Burgers, fried chicken, sandwiches, and quick meals.', FALSE)
    ON CONFLICT (name) DO UPDATE
                              SET description = EXCLUDED.description,
                              is_active = EXCLUDED.is_active;

-- Restaurants
INSERT INTO restaurants (
    name,
    phone,
    email,
    address,
    governorate_id,
    description,
    logo_url,
    is_active,
    delivery_fee
)
SELECT
    'Nile Kitchen',
    '+20225550001',
    'nile-kitchen@restaurants.talabaty.local',
    '15 Tahrir Street, Downtown',
    id,
    'Egyptian classics prepared with fresh local ingredients.',
    'https://example.test/images/restaurants/nile-kitchen.png',
    TRUE,
    25.00
FROM governorates
WHERE name = 'Cairo'
    ON CONFLICT (email) DO UPDATE
                               SET name = EXCLUDED.name,
                               phone = EXCLUDED.phone,
                               address = EXCLUDED.address,
                               governorate_id = EXCLUDED.governorate_id,
                               description = EXCLUDED.description,
                               logo_url = EXCLUDED.logo_url,
                               is_active = EXCLUDED.is_active,
                               delivery_fee = EXCLUDED.delivery_fee;

INSERT INTO restaurants (
    name,
    phone,
    email,
    address,
    governorate_id,
    description,
    logo_url,
    is_active,
    delivery_fee
)
SELECT
    'Silk Road Bistro',
    '+20225550002',
    'silk-road@restaurants.talabaty.local',
    '42 El-Haram Road',
    id,
    'Chinese, Indian, and pan-Asian dishes made for sharing.',
    'https://example.test/images/restaurants/silk-road.png',
    TRUE,
    35.00
FROM governorates
WHERE name = 'Giza'
    ON CONFLICT (email) DO UPDATE
                               SET name = EXCLUDED.name,
                               phone = EXCLUDED.phone,
                               address = EXCLUDED.address,
                               governorate_id = EXCLUDED.governorate_id,
                               description = EXCLUDED.description,
                               logo_url = EXCLUDED.logo_url,
                               is_active = EXCLUDED.is_active,
                               delivery_fee = EXCLUDED.delivery_fee;

INSERT INTO restaurants (
    name,
    phone,
    email,
    address,
    governorate_id,
    description,
    logo_url,
    is_active,
    delivery_fee
)
SELECT
    'Roma Express',
    '+20345550003',
    'roma-express@restaurants.talabaty.local',
    '8 Corniche Road, Raml Station',
    id,
    'Italian favorites, pizzas, pasta, desserts, and fast meals.',
    'https://example.test/images/restaurants/roma-express.png',
    TRUE,
    30.00
FROM governorates
WHERE name = 'Alexandria'
    ON CONFLICT (email) DO UPDATE
                               SET name = EXCLUDED.name,
                               phone = EXCLUDED.phone,
                               address = EXCLUDED.address,
                               governorate_id = EXCLUDED.governorate_id,
                               description = EXCLUDED.description,
                               logo_url = EXCLUDED.logo_url,
                               is_active = EXCLUDED.is_active,
                               delivery_fee = EXCLUDED.delivery_fee;

INSERT INTO restaurants (
    name,
    phone,
    email,
    address,
    governorate_id,
    description,
    logo_url,
    is_active,
    delivery_fee
)
SELECT
    'Urban Bites',
    '+20225550004',
    'urban-bites@restaurants.talabaty.local',
    '21 Makram Ebeid Street, Nasr City',
    id,
    'A temporarily inactive fast-food restaurant for status filtering tests.',
    'https://example.test/images/restaurants/urban-bites.png',
    FALSE,
    20.00
FROM governorates
WHERE name = 'Cairo'
    ON CONFLICT (email) DO UPDATE
                               SET name = EXCLUDED.name,
                               phone = EXCLUDED.phone,
                               address = EXCLUDED.address,
                               governorate_id = EXCLUDED.governorate_id,
                               description = EXCLUDED.description,
                               logo_url = EXCLUDED.logo_url,
                               is_active = EXCLUDED.is_active,
                               delivery_fee = EXCLUDED.delivery_fee;

-- Many-to-many restaurant cuisine assignments.
INSERT INTO restaurant_categories (restaurant_id, category_id)
SELECT r.id, c.id
FROM (
         VALUES
             ('nile-kitchen@restaurants.talabaty.local', 'Egyptian'),
             ('nile-kitchen@restaurants.talabaty.local', 'Fast Food'),
             ('silk-road@restaurants.talabaty.local', 'Chinese'),
             ('silk-road@restaurants.talabaty.local', 'Indian'),
             ('silk-road@restaurants.talabaty.local', 'Asian'),
             ('roma-express@restaurants.talabaty.local', 'Italian'),
             ('roma-express@restaurants.talabaty.local', 'Fast Food'),
             ('urban-bites@restaurants.talabaty.local', 'Asian'),
             ('urban-bites@restaurants.talabaty.local', 'Fast Food')
     ) AS assignment(restaurant_email, category_name)
         JOIN restaurants r
              ON r.email = assignment.restaurant_email
         JOIN categories c
              ON c.name = assignment.category_name
    ON CONFLICT (restaurant_id, category_id) DO NOTHING;

-- Restaurant-specific menu sections.
INSERT INTO menu_sections (
    name,
    description,
    is_active,
    restaurant_id
)
SELECT
    seed.section_name,
    seed.description,
    seed.is_active,
    r.id
FROM (
         VALUES
             ('nile-kitchen@restaurants.talabaty.local', 'Appetizers', 'Small Egyptian dishes to begin the meal.', TRUE),
             ('nile-kitchen@restaurants.talabaty.local', 'Main Dishes', 'Traditional filling Egyptian meals.', TRUE),
             ('nile-kitchen@restaurants.talabaty.local', 'Side Dishes', 'Sides and accompaniments.', TRUE),
             ('nile-kitchen@restaurants.talabaty.local', 'Drinks', 'Cold and hot beverages.', TRUE),
             ('nile-kitchen@restaurants.talabaty.local', 'Desserts', 'Traditional Egyptian sweets.', TRUE),

             ('silk-road@restaurants.talabaty.local', 'Appetizers', 'Asian starters and sharing plates.', TRUE),
             ('silk-road@restaurants.talabaty.local', 'Main Dishes', 'Chinese and Indian specialties.', TRUE),
             ('silk-road@restaurants.talabaty.local', 'Side Dishes', 'Rice, breads, and vegetable sides.', TRUE),
             ('silk-road@restaurants.talabaty.local', 'Drinks', 'Refreshing house beverages.', TRUE),
             ('silk-road@restaurants.talabaty.local', 'Desserts', 'Asian-inspired desserts.', FALSE),

             ('roma-express@restaurants.talabaty.local', 'Appetizers', 'Classic Italian starters.', TRUE),
             ('roma-express@restaurants.talabaty.local', 'Main Dishes', 'Pizza and pasta dishes.', TRUE),
             ('roma-express@restaurants.talabaty.local', 'Side Dishes', 'Italian-style side dishes.', TRUE),
             ('roma-express@restaurants.talabaty.local', 'Drinks', 'Soft drinks and Italian beverages.', TRUE),
             ('roma-express@restaurants.talabaty.local', 'Desserts', 'Traditional Italian desserts.', TRUE),

             ('urban-bites@restaurants.talabaty.local', 'Main Dishes', 'Burgers, sandwiches, and fried chicken.', TRUE),
             ('urban-bites@restaurants.talabaty.local', 'Side Dishes', 'Fast-food sides.', TRUE),
             ('urban-bites@restaurants.talabaty.local', 'Drinks', 'Cold beverages.', TRUE)
     ) AS seed(restaurant_email, section_name, description, is_active)
         JOIN restaurants r
              ON r.email = seed.restaurant_email
WHERE NOT EXISTS (
    SELECT 1
    FROM menu_sections existing
    WHERE existing.restaurant_id = r.id
      AND existing.name = seed.section_name
);

-- Keep existing seed sections synchronized on reruns.
UPDATE menu_sections ms
SET description = seed.description,
    is_active = seed.is_active
    FROM restaurants r
JOIN (
    VALUES
        ('nile-kitchen@restaurants.talabaty.local', 'Appetizers', 'Small Egyptian dishes to begin the meal.', TRUE),
        ('nile-kitchen@restaurants.talabaty.local', 'Main Dishes', 'Traditional filling Egyptian meals.', TRUE),
        ('nile-kitchen@restaurants.talabaty.local', 'Side Dishes', 'Sides and accompaniments.', TRUE),
        ('nile-kitchen@restaurants.talabaty.local', 'Drinks', 'Cold and hot beverages.', TRUE),
        ('nile-kitchen@restaurants.talabaty.local', 'Desserts', 'Traditional Egyptian sweets.', TRUE),
        ('silk-road@restaurants.talabaty.local', 'Appetizers', 'Asian starters and sharing plates.', TRUE),
        ('silk-road@restaurants.talabaty.local', 'Main Dishes', 'Chinese and Indian specialties.', TRUE),
        ('silk-road@restaurants.talabaty.local', 'Side Dishes', 'Rice, breads, and vegetable sides.', TRUE),
        ('silk-road@restaurants.talabaty.local', 'Drinks', 'Refreshing house beverages.', TRUE),
        ('silk-road@restaurants.talabaty.local', 'Desserts', 'Asian-inspired desserts.', FALSE),
        ('roma-express@restaurants.talabaty.local', 'Appetizers', 'Classic Italian starters.', TRUE),
        ('roma-express@restaurants.talabaty.local', 'Main Dishes', 'Pizza and pasta dishes.', TRUE),
        ('roma-express@restaurants.talabaty.local', 'Side Dishes', 'Italian-style side dishes.', TRUE),
        ('roma-express@restaurants.talabaty.local', 'Drinks', 'Soft drinks and Italian beverages.', TRUE),
        ('roma-express@restaurants.talabaty.local', 'Desserts', 'Traditional Italian desserts.', TRUE),
        ('urban-bites@restaurants.talabaty.local', 'Main Dishes', 'Burgers, sandwiches, and fried chicken.', TRUE),
        ('urban-bites@restaurants.talabaty.local', 'Side Dishes', 'Fast-food sides.', TRUE),
        ('urban-bites@restaurants.talabaty.local', 'Drinks', 'Cold beverages.', TRUE)
) AS seed(restaurant_email, section_name, description, is_active)
ON r.email = seed.restaurant_email
WHERE ms.restaurant_id = r.id
  AND ms.name = seed.section_name;

-- Menu items belong to menu sections, not cuisine categories.
INSERT INTO menu_items (
    name,
    description,
    base_price,
    image_url,
    is_available,
    menu_section_id
)
SELECT
    seed.item_name,
    seed.description,
    seed.base_price,
    seed.image_url,
    seed.is_available,
    ms.id
FROM (
         VALUES
             ('nile-kitchen@restaurants.talabaty.local', 'Appetizers', 'Hummus Plate', 'Creamy chickpeas with tahini, lemon, and olive oil.', 55.00, 'https://example.test/images/items/hummus.png', TRUE),
             ('nile-kitchen@restaurants.talabaty.local', 'Appetizers', 'Stuffed Vine Leaves', 'Vine leaves filled with seasoned rice and herbs.', 70.00, 'https://example.test/images/items/vine-leaves.png', TRUE),
             ('nile-kitchen@restaurants.talabaty.local', 'Main Dishes', 'Koshari', 'Rice, pasta, lentils, chickpeas, tomato sauce, and crispy onions.', 95.00, 'https://example.test/images/items/koshari.png', TRUE),
             ('nile-kitchen@restaurants.talabaty.local', 'Main Dishes', 'Molokhia with Chicken', 'Molokhia served with roasted chicken and rice.', 185.00, 'https://example.test/images/items/molokhia.png', TRUE),
             ('nile-kitchen@restaurants.talabaty.local', 'Side Dishes', 'Baladi Bread', 'Fresh Egyptian flatbread.', 15.00, 'https://example.test/images/items/baladi-bread.png', TRUE),
             ('nile-kitchen@restaurants.talabaty.local', 'Drinks', 'Hibiscus Juice', 'Chilled karkadeh prepared in-house.', 35.00, 'https://example.test/images/items/hibiscus.png', TRUE),
             ('nile-kitchen@restaurants.talabaty.local', 'Desserts', 'Om Ali', 'Warm puff pastry with milk, nuts, and raisins.', 75.00, 'https://example.test/images/items/om-ali.png', TRUE),

             ('silk-road@restaurants.talabaty.local', 'Appetizers', 'Vegetable Spring Rolls', 'Crispy rolls with vegetables and sweet chili sauce.', 85.00, 'https://example.test/images/items/spring-rolls.png', TRUE),
             ('silk-road@restaurants.talabaty.local', 'Appetizers', 'Chicken Samosa', 'Crisp pastry filled with spiced chicken.', 90.00, 'https://example.test/images/items/chicken-samosa.png', TRUE),
             ('silk-road@restaurants.talabaty.local', 'Main Dishes', 'Kung Pao Chicken', 'Chicken, peanuts, peppers, and chili sauce.', 225.00, 'https://example.test/images/items/kung-pao.png', TRUE),
             ('silk-road@restaurants.talabaty.local', 'Main Dishes', 'Butter Chicken', 'Tandoori chicken in a creamy tomato and spice sauce.', 240.00, 'https://example.test/images/items/butter-chicken.png', TRUE),
             ('silk-road@restaurants.talabaty.local', 'Main Dishes', 'Vegetable Biryani', 'Basmati rice cooked with vegetables and aromatic spices.', 175.00, 'https://example.test/images/items/vegetable-biryani.png', TRUE),
             ('silk-road@restaurants.talabaty.local', 'Side Dishes', 'Garlic Naan', 'Fresh naan bread topped with garlic and butter.', 45.00, 'https://example.test/images/items/garlic-naan.png', TRUE),
             ('silk-road@restaurants.talabaty.local', 'Side Dishes', 'Egg Fried Rice', 'Wok-fried rice with egg and vegetables.', 80.00, 'https://example.test/images/items/egg-fried-rice.png', TRUE),
             ('silk-road@restaurants.talabaty.local', 'Drinks', 'Mango Lassi', 'Yogurt drink blended with ripe mango.', 65.00, 'https://example.test/images/items/mango-lassi.png', TRUE),
             ('silk-road@restaurants.talabaty.local', 'Desserts', 'Coconut Pudding', 'Chilled coconut pudding with toasted sesame.', 70.00, 'https://example.test/images/items/coconut-pudding.png', FALSE),

             ('roma-express@restaurants.talabaty.local', 'Appetizers', 'Bruschetta', 'Toasted bread with tomato, basil, garlic, and olive oil.', 90.00, 'https://example.test/images/items/bruschetta.png', TRUE),
             ('roma-express@restaurants.talabaty.local', 'Main Dishes', 'Margherita Pizza', 'Tomato sauce, mozzarella, basil, and olive oil.', 190.00, 'https://example.test/images/items/margherita.png', TRUE),
             ('roma-express@restaurants.talabaty.local', 'Main Dishes', 'Chicken Alfredo', 'Fettuccine with grilled chicken and creamy Parmesan sauce.', 235.00, 'https://example.test/images/items/chicken-alfredo.png', TRUE),
             ('roma-express@restaurants.talabaty.local', 'Main Dishes', 'Lasagna Bolognese', 'Layered pasta with beef ragù, béchamel, and cheese.', 245.00, 'https://example.test/images/items/lasagna.png', FALSE),
             ('roma-express@restaurants.talabaty.local', 'Side Dishes', 'Garlic Bread', 'Toasted bread with garlic butter and herbs.', 60.00, 'https://example.test/images/items/garlic-bread.png', TRUE),
             ('roma-express@restaurants.talabaty.local', 'Drinks', 'Sparkling Lemonade', 'Fresh lemon, soda water, and mint.', 55.00, 'https://example.test/images/items/lemonade.png', TRUE),
             ('roma-express@restaurants.talabaty.local', 'Desserts', 'Tiramisu', 'Coffee-soaked sponge layered with mascarpone cream.', 105.00, 'https://example.test/images/items/tiramisu.png', TRUE),

             ('urban-bites@restaurants.talabaty.local', 'Main Dishes', 'Classic Cheeseburger', 'Beef patty, cheddar, lettuce, tomato, pickles, and house sauce.', 160.00, 'https://example.test/images/items/cheeseburger.png', TRUE),
             ('urban-bites@restaurants.talabaty.local', 'Main Dishes', 'Crispy Chicken Sandwich', 'Crispy chicken breast with slaw and spicy mayonnaise.', 150.00, 'https://example.test/images/items/chicken-sandwich.png', FALSE),
             ('urban-bites@restaurants.talabaty.local', 'Side Dishes', 'Seasoned Fries', 'Crispy fries with house seasoning.', 55.00, 'https://example.test/images/items/fries.png', TRUE),
             ('urban-bites@restaurants.talabaty.local', 'Drinks', 'Cola', 'Chilled canned cola.', 30.00, 'https://example.test/images/items/cola.png', TRUE)
     ) AS seed(
               restaurant_email,
               section_name,
               item_name,
               description,
               base_price,
               image_url,
               is_available
    )
         JOIN restaurants r
              ON r.email = seed.restaurant_email
         JOIN menu_sections ms
              ON ms.restaurant_id = r.id
                  AND ms.name = seed.section_name
WHERE NOT EXISTS (
    SELECT 1
    FROM menu_items existing
    WHERE existing.menu_section_id = ms.id
      AND existing.name = seed.item_name
);

COMMIT;

