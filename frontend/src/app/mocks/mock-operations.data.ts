import { AdminCategoryResponse, CustomerAdminResponse, DeliveryFeedbackResponse as AdminFeedback, OrderAdminResponse, RestaurantAdminResponse, RiderAdminResponse } from '../features/admin/models/admin.models';
import { DeliveryFeedbackResponse, DriverProfileResponse, OrderHistoryResponse, OrderSummaryResponse } from '../features/driver/models/driver.models';
import { KitchenAddonGroupResponse, KitchenDashboardSummaryResponse, KitchenMenuItemResponse, KitchenMenuSectionResponse, KitchenOrderDetailsResponse } from '../features/restaurant-portal/models/kitchen.models';
import { MOCK_CATEGORIES, MOCK_RESTAURANTS, MOCK_RESTAURANT_DETAILS } from './mock-catalog.data';
import { MOCK_CUSTOMER_ORDERS } from './mock-customer.data';

export const MOCK_DRIVER_PROFILE = { email: 'driver@talabaty.local', name: 'Youssef Ali', phoneNumber: '+20 111 400 2200', vehicleType: 'Motorcycle', licenseNumber: 'DL-48291', nationalId: '29801011234567' } satisfies DriverProfileResponse;
export const MOCK_AVAILABLE_DELIVERIES = [
  { id: 2101, restaurantName: 'Firewood Pizza', deliveryAddress: '18 Dokki Street, Giza', itemCount: 3, totalPrice: 310, deliveryFee: 24 },
  { id: 2102, restaurantName: 'Golden Chicken', deliveryAddress: '7 Abbas El Akkad, Cairo', itemCount: 2, totalPrice: 245, deliveryFee: 22 },
  { id: 2103, restaurantName: 'Green Bowl', deliveryAddress: '31 Lebanon Square, Giza', itemCount: 4, totalPrice: 380, deliveryFee: 28 },
] satisfies OrderSummaryResponse[];
export const MOCK_ACTIVE_DELIVERY = { id: 2100, restaurantName: 'Burger House', deliveryAddress: '12 Nile Street, Giza', itemCount: 2, totalPrice: 220, deliveryFee: 25 } satisfies OrderSummaryResponse;
export const MOCK_DELIVERY_HISTORY = [
  { id: 2099, restaurantName: 'Damascus Kitchen', deliveredAt: '2026-08-22T20:10:00Z', earnings: 38, orderTotal: 295 },
  { id: 2098, restaurantName: 'Sweet Corner', deliveredAt: '2026-08-21T17:30:00Z', earnings: 31, orderTotal: 185 },
  { id: 2097, restaurantName: 'Urban Grill', deliveredAt: '2026-08-20T22:05:00Z', earnings: 42, orderTotal: 410 },
] satisfies OrderHistoryResponse[];
export const MOCK_DRIVER_FEEDBACK = [
  { orderId: 2099, rating: 5, comment: 'Friendly and quick delivery.' },
  { orderId: 2098, rating: 4, comment: 'Order arrived in good condition.' },
  { orderId: 2097, rating: 5, comment: 'Excellent service.' },
] satisfies DeliveryFeedbackResponse[];

export const MOCK_KITCHEN_SECTIONS = MOCK_RESTAURANT_DETAILS[0].menuSections.map((section) => ({
  id: section.id, name: section.name, description: section.description, active: true,
  menuItems: section.menuItems.map((item) => ({ id: item.id, name: item.name, basePrice: item.basePrice, available: true, menuSectionId: section.id, menuSectionName: section.name })),
})) satisfies KitchenMenuSectionResponse[];
export const MOCK_KITCHEN_ITEMS = MOCK_KITCHEN_SECTIONS.flatMap((section) => section.menuItems) satisfies KitchenMenuItemResponse[];
export const MOCK_KITCHEN_GROUPS = [
  { id: 1, name: 'Choose a size', minSelections: 1, maxSelections: 1, addons: [{ id: 1, name: 'Regular', additionalPrice: 0, available: true }, { id: 2, name: 'Large', additionalPrice: 25, available: true }] },
  { id: 2, name: 'Extra toppings', minSelections: 0, maxSelections: 3, addons: [{ id: 3, name: 'Extra cheese', additionalPrice: 12, available: true }, { id: 4, name: 'Jalapeños', additionalPrice: 8, available: true }, { id: 5, name: 'Crispy onions', additionalPrice: 10, available: true }] },
] satisfies KitchenAddonGroupResponse[];
export const MOCK_KITCHEN_ORDERS = MOCK_CUSTOMER_ORDERS.slice(0, 7).map((order) => ({
  id: order.id, deliveryAddress: order.deliveryAddress, subtotal: order.subtotal, deliveryFee: order.deliveryFee, totalPrice: order.totalPrice, status: order.status, paymentMethod: order.paymentMethod, createdAt: order.createdAt, updatedAt: order.createdAt, orderItems: order.orderItems,
})) satisfies KitchenOrderDetailsResponse[];
export const MOCK_KITCHEN_DASHBOARD = { totalOrdersToday: 18, pendingOrders: 3, confirmedOrders: 2, preparingOrders: 4, readyOrders: 2, acceptedOrders: 2, pickedUpOrders: 1, deliveredOrders: 3, cancelledOrders: 1, delayedOrders: 1 } satisfies KitchenDashboardSummaryResponse;

export const MOCK_ADMIN_CATEGORIES = MOCK_CATEGORIES.map((category) => ({ ...category, active: true })) satisfies AdminCategoryResponse[];
export const MOCK_ADMIN_CUSTOMERS = [
  { id: 1, name: 'Omar Hassan', email: 'customer@talabaty.local', phoneNumber: '+20 100 555 0123' },
  { id: 2, name: 'Mariam Adel', email: 'mariam@example.local', phoneNumber: '+20 101 223 3400' },
  { id: 3, name: 'Karim Nabil', email: 'karim@example.local', phoneNumber: '+20 122 888 7100' },
  { id: 4, name: 'Salma Tarek', email: 'salma@example.local', phoneNumber: '+20 109 310 8800' },
] satisfies CustomerAdminResponse[];
export const MOCK_ADMIN_RESTAURANTS = MOCK_RESTAURANTS.map((restaurant) => ({ id: restaurant.id, name: restaurant.name, phone: `+20 100 100 10${restaurant.id.toString().padStart(2, '0')}`, email: `contact${restaurant.id}@talabaty.local`, address: `${10 + restaurant.id} Marketplace Street`, governorateName: restaurant.id % 2 ? 'Cairo' : 'Giza', description: restaurant.description, logoUrl: restaurant.logoUrl, isActive: true, deliveryFee: 18 + restaurant.id, categoryNames: restaurant.categories })) satisfies RestaurantAdminResponse[];
export const MOCK_ADMIN_ORDERS = MOCK_CUSTOMER_ORDERS.map((order, index) => ({ id: order.id, status: order.status, restaurantName: order.restaurantName, customerName: MOCK_ADMIN_CUSTOMERS[index % MOCK_ADMIN_CUSTOMERS.length].name, riderName: order.status === 'PENDING' ? null : 'Youssef Ali', totalPrice: order.totalPrice, deliveryFee: order.deliveryFee, updatedAt: order.createdAt })) satisfies OrderAdminResponse[];
export const MOCK_RIDERS = [
  { id: 1, name: 'Youssef Ali', phoneNumber: '+20 111 400 2200', vehicleType: 'Motorcycle', licenseNumber: 'DL-48291', nationalId: '29801011234567', approvalStatus: 'APPROVED', online: true, isActive: true },
  { id: 2, name: 'Ahmed Samir', phoneNumber: '+20 106 222 9911', vehicleType: 'Bicycle', licenseNumber: 'BI-11820', nationalId: '30002021234568', approvalStatus: 'APPROVED', online: false, isActive: true },
  { id: 3, name: 'Mahmoud Fathy', phoneNumber: '+20 115 555 8140', vehicleType: 'Motorcycle', licenseNumber: 'DL-90012', nationalId: '29709091234561', approvalStatus: 'PENDING', online: false, isActive: true },
  { id: 4, name: 'Hany Mostafa', phoneNumber: '+20 120 321 9010', vehicleType: 'Car', licenseNumber: 'CR-33510', nationalId: '29503111234566', approvalStatus: 'PENDING', online: false, isActive: true },
  { id: 5, name: 'Tamer Eid', phoneNumber: '+20 102 909 1200', vehicleType: 'Motorcycle', licenseNumber: 'DL-74321', nationalId: '29207071234562', approvalStatus: 'REJECTED', online: false, isActive: false },
] satisfies RiderAdminResponse[];
export const MOCK_ADMIN_FEEDBACK = MOCK_DRIVER_FEEDBACK.map((feedback) => ({ ...feedback })) satisfies AdminFeedback[];
