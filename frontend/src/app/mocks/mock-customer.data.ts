import {
  AddressResponse,
  CustomerProfileResponse,
} from '../features/account/models/account.models';
import { CartResponse } from '../features/cart/models/cart.models';
import { CustomerOrderDetailsResponse } from '../features/orders/models/order.models';

export const MOCK_ADDRESSES = [
  {
    id: 1,
    street: '12 Nile Street',
    building: 'A',
    floor: '3',
    apartment: '8',
    city: 'Giza',
    governorateId: 1,
    governorateName: 'Giza',
    isDefault: true,
  },
  {
    id: 2,
    street: '45 Tahrir Street',
    building: '7',
    floor: '2',
    apartment: '5',
    city: 'Cairo',
    governorateId: 2,
    governorateName: 'Cairo',
    isDefault: false,
  },
  {
    id: 3,
    street: '9 Corniche Road',
    building: 'C',
    floor: '5',
    apartment: '12',
    city: 'Alexandria',
    governorateId: 3,
    governorateName: 'Alexandria',
    isDefault: false,
  },
] satisfies AddressResponse[];

export const MOCK_CUSTOMER_PROFILE = {
  email: 'customer@talabaty.local',
  name: 'Omar Hassan',
  phoneNumber: '+20 100 555 0123',
} satisfies CustomerProfileResponse;

export const INITIAL_MOCK_CART = {
  id: 1,
  restaurantId: 1,
  restaurantName: 'Burger House',
  subtotal: 400,
  items: [
    {
      id: 1,
      menuItemId: 101,
      menuItemName: 'Classic Cheeseburger',
      imageUrl: '',
      basePrice: 59,
      quantity: 2,
      specialInstructions: 'No onions',
      itemTotalPrice: 168,
      addons: [
        {
          menuItemAddonId: 10102,
          name: 'Large',
          addonGroupId: 1011,
          addonGroupName: 'Choose a size',
          quantity: 1,
          priceAtAddition: 25,
          totalPrice: 25,
        },
      ],
    },
    {
      id: 2,
      menuItemId: 102,
      menuItemName: 'Double Smash Burger',
      imageUrl: '',
      basePrice: 72,
      quantity: 2,
      specialInstructions: '',
      itemTotalPrice: 168,
      addons: [
        {
          menuItemAddonId: 10203,
          name: 'Extra cheese',
          addonGroupId: 1022,
          addonGroupName: 'Extras',
          quantity: 1,
          priceAtAddition: 12,
          totalPrice: 12,
        },
      ],
    },
    {
      id: 3,
      menuItemId: 104,
      menuItemName: 'Loaded House Fries',
      imageUrl: '',
      basePrice: 64,
      quantity: 1,
      specialInstructions: 'Sauce on the side',
      itemTotalPrice: 64,
      addons: [],
    },
  ],
} satisfies CartResponse;

const orderSeed = [
  [1008, 'Burger House', 'PENDING', '2026-08-23T10:15:00Z'],
  [1007, 'Firewood Pizza', 'CONFIRMED', '2026-08-22T18:30:00Z'],
  [1006, 'Golden Chicken', 'PREPARING', '2026-08-21T15:05:00Z'],
  [1005, 'Damascus Kitchen', 'READY', '2026-08-20T20:40:00Z'],
  [1004, 'Green Bowl', 'ACCEPTED', '2026-08-18T12:10:00Z'],
  [1003, 'Urban Grill', 'PICKED_UP', '2026-08-17T19:20:00Z'],
  [1002, 'Sweet Corner', 'DELIVERED', '2026-08-15T16:45:00Z'],
  [1001, 'Coffee District', 'CANCELLED', '2026-08-12T08:15:00Z'],
] as const;

export const MOCK_CUSTOMER_ORDERS = orderSeed.map(
  ([id, restaurantName, status, createdAt], index) => ({
    id,
    restaurantName,
    deliveryAddress: '12 Nile Street, Giza',
    subtotal: 180 + index * 15,
    deliveryFee: 20,
    totalPrice: 200 + index * 15,
    status,
    paymentMethod: index % 2 === 0 ? 'CASH' : 'CREDIT_CARD',
    createdAt,
    orderItems: [
      {
        productName: index % 2 === 0 ? 'Classic Cheeseburger' : 'Margherita Pizza',
        unitPrice: 90 + index * 5,
        quantity: 2,
        notes: '',
        addons: [],
      },
    ],
  }),
) satisfies CustomerOrderDetailsResponse[];
