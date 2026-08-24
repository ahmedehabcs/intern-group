import {
  CategoryResponse,
  MenuItemDetailsResponse,
  MenuItemResponse,
  RestaurantDetailsResponse,
  RestaurantResponse,
} from '../features/restaurants/models/restaurant.models';

export const MOCK_CATEGORIES = [
  { id: 1, name: 'Burgers', description: 'Smash burgers, sandwiches and fries' },
  { id: 2, name: 'Pizza', description: 'Stone-baked and classic pizzas' },
  { id: 3, name: 'Chicken', description: 'Crispy, grilled and roasted chicken' },
  { id: 4, name: 'Arabic', description: 'Levantine grills and traditional plates' },
  { id: 5, name: 'Desserts', description: 'Cakes, kunafa and sweet treats' },
  { id: 6, name: 'Drinks', description: 'Coffee, juices and cold drinks' },
  { id: 7, name: 'Breakfast', description: 'Morning plates and fresh bakery' },
  { id: 8, name: 'Healthy', description: 'Bowls, salads and balanced meals' },
] satisfies CategoryResponse[];

const restaurantSeeds = [
  [1, 'Burger House', 'Hand-smashed burgers, loaded fries and house sauces', ['Burgers']],
  [2, 'Firewood Pizza', 'Artisan pizzas baked in a traditional stone oven', ['Pizza']],
  [3, 'Golden Chicken', 'Crispy chicken meals and family-size platters', ['Chicken']],
  [4, 'Damascus Kitchen', 'Authentic shawarma, grills and mezze', ['Arabic']],
  [5, 'Sweet Corner', 'Fresh cakes, kunafa and handcrafted desserts', ['Desserts']],
  [6, 'Coffee District', 'Specialty coffee, iced drinks and light bites', ['Drinks', 'Breakfast']],
  [7, 'Morning Bites', 'Breakfast sandwiches, bakery and morning plates', ['Breakfast']],
  [8, 'Green Bowl', 'Wholesome salads, protein bowls and fresh juices', ['Healthy']],
  [9, 'Urban Grill', 'Charcoal grills, burgers and generous platters', ['Burgers', 'Arabic']],
  [10, 'Fresh Squeeze', 'Cold-pressed juices, smoothies and fruit cups', ['Drinks', 'Healthy']],
] as const;

const image = (id: string) =>
  'https://images.unsplash.com/' + id + '?auto=format&fit=crop&w=900&q=80';

const restaurantImages = [
  'photo-1568901346375-23c9450c58cd',
  'photo-1574071318508-1cdbab80d002',
  'photo-1598103442097-8b74394b95c6',
  'photo-1547592180-85f173990554',
  'photo-1578985545062-69928b1d9587',
  'photo-1509042239860-f550ce710b93',
  'photo-1533089860892-a7c6f0a88666',
  'photo-1540420773420-3366772f4999',
  'photo-1558030006-450675393462',
  'photo-1622597467836-f3285f2131b8',
] as const;

const menuImages = [
  'photo-1568901346375-23c9450c58cd',
  'photo-1550547660-d9450f859349',
  'photo-1572802419224-296b0aeee0d9',
  'photo-1594212699903-ec8a3eca50f5',
  'photo-1565299624946-b28f40a0ae38',
  'photo-1579751626657-72bc17010498',
  'photo-1562967914-608f82629710',
  'photo-1569058242253-92a9c755a0ec',
  'photo-1540189549336-e6e99c3679fe',
  'photo-1547592180-85f173990554',
  'photo-1578985545062-69928b1d9587',
  'photo-1509042239860-f550ce710b93',
] as const;

export const MOCK_RESTAURANTS = restaurantSeeds.map(
  ([id, name, description, categories], index) => ({
    id,
    name,
    description,
    logoUrl: image(restaurantImages[index]),
    categories: [...categories],
  }),
) satisfies RestaurantResponse[];

export const MOCK_RESTAURANT_CATEGORY_IDS: Readonly<Record<number, readonly number[]>> = {
  1: [1],
  2: [2],
  3: [3],
  4: [4],
  5: [5],
  6: [6, 7],
  7: [7],
  8: [8],
  9: [1, 4],
  10: [6, 8],
};

const menus: readonly (readonly string[])[] = [
  [
    'Classic Cheeseburger',
    'Double Smash Burger',
    'Spicy Chicken Burger',
    'Loaded House Fries',
    'Mushroom Swiss Burger',
    'Crispy Onion Rings',
  ],
  [
    'Margherita Pizza',
    'Pepperoni Pizza',
    'Four Cheese Pizza',
    'Chicken Ranch Pizza',
    'Truffle Mushroom Pizza',
    'Garlic Bread',
  ],
  [
    'Crispy Chicken Meal',
    'Spicy Chicken Strips',
    'Grilled Chicken Plate',
    'Family Chicken Bucket',
    'Chicken Wrap',
    'Coleslaw Bowl',
  ],
  [
    'Chicken Shawarma',
    'Mixed Grill Plate',
    'Hummus & Bread',
    'Fattoush Salad',
    'Beef Kofta',
    'Falafel Plate',
  ],
  [
    'Classic Kunafa',
    'Chocolate Cake',
    'Lotus Cheesecake',
    'Pistachio Baklava',
    'Milk Cake',
    'Brownie Sundae',
  ],
  [
    'Iced Latte',
    'Flat White',
    'Spanish Latte',
    'Cold Brew',
    'Turkey Croissant',
    'Blueberry Muffin',
  ],
  [
    'Egg & Cheese Sandwich',
    'Halloumi Croissant',
    'Shakshuka Plate',
    'Pancake Stack',
    'Granola Bowl',
  ],
  [
    'Chicken Protein Bowl',
    'Quinoa Garden Salad',
    'Salmon Avocado Bowl',
    'Greek Salad',
    'Green Detox Juice',
  ],
  [
    'Charcoal Burger',
    'Shish Tawook Plate',
    'Kofta Sandwich',
    'Mixed Grill Feast',
    'Seasoned Fries',
  ],
  [
    'Fresh Orange Juice',
    'Mango Smoothie',
    'Berry Blast',
    'Avocado Honey Shake',
    'Seasonal Fruit Cup',
  ],
] as const;

function menuItem(
  restaurantId: number,
  sectionId: number,
  index: number,
  name: string,
): MenuItemResponse {
  return {
    id: restaurantId * 100 + index + 1,
    name,
    description: `Freshly prepared ${name.toLowerCase()} made to order with quality ingredients.`,
    basePrice: 55 + restaurantId * 4 + index * 13,
    imageUrl: image(menuImages[(restaurantId + index) % menuImages.length]),
  };
}

export const MOCK_RESTAURANT_DETAILS = MOCK_RESTAURANTS.map((restaurant, restaurantIndex) => {
  const allItems = menus[restaurantIndex].map((name, index) =>
    menuItem(restaurant.id, restaurant.id * 10 + 1, index, name),
  );
  const splitAt = Math.ceil(allItems.length / 2);
  return {
    id: restaurant.id,
    name: restaurant.name,
    description: restaurant.description,
    logoUrl: restaurant.logoUrl,
    menuSections: [
      {
        id: restaurant.id * 10 + 1,
        name: 'Popular',
        description: 'Most ordered favorites',
        menuItems: allItems.slice(0, splitAt),
      },
      {
        id: restaurant.id * 10 + 2,
        name: 'More to explore',
        description: 'More from the menu',
        menuItems: allItems.slice(splitAt),
      },
    ],
  };
}) satisfies RestaurantDetailsResponse[];

export const MOCK_MENU_ITEM_DETAILS = MOCK_RESTAURANT_DETAILS.flatMap((restaurant) =>
  restaurant.menuSections.flatMap((section) =>
    section.menuItems.map((item) => ({
      ...item,
      restaurantId: restaurant.id,
      menuSectionId: section.id,
      addonGroups: [
        {
          id: item.id * 10 + 1,
          name: 'Choose a size',
          minSelections: 1,
          maxSelections: 1,
          addons: [
            { id: item.id * 100 + 1, name: 'Regular', additionalPrice: 0 },
            { id: item.id * 100 + 2, name: 'Large', additionalPrice: 25 },
          ],
        },
        {
          id: item.id * 10 + 2,
          name: 'Extras',
          minSelections: 0,
          maxSelections: 3,
          addons: [
            { id: item.id * 100 + 3, name: 'Extra cheese', additionalPrice: 12 },
            { id: item.id * 100 + 4, name: 'Jalapeños', additionalPrice: 8 },
            { id: item.id * 100 + 5, name: 'Garlic sauce', additionalPrice: 7 },
          ],
        },
      ],
    })),
  ),
) satisfies MenuItemDetailsResponse[];
