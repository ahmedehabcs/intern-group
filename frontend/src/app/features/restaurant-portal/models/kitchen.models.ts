import { OrderItemResponse, OrderStatus, PaymentMethod } from '../../orders/models/order.models';
export interface KitchenMenuItemResponse {
  id: number;
  name: string;
  basePrice: number;
  available: boolean;
  menuSectionId: number;
  menuSectionName: string;
}
export interface KitchenMenuSectionResponse {
  id: number;
  name: string;
  description: string;
  active: boolean;
  menuItems: KitchenMenuItemResponse[];
}
export interface KitchenAddonResponse {
  id: number;
  name: string;
  additionalPrice: number;
  available: boolean;
}
export interface KitchenAddonGroupResponse {
  id: number;
  name: string;
  minSelections: number;
  maxSelections: number;
  addons: KitchenAddonResponse[];
}
export interface KitchenOrderSummaryResponse {
  id: number;
  itemCount: number;
  totalPrice: number;
  status: OrderStatus;
  createdAt: string;
}
export interface KitchenOrderPageResponse {
  orders: KitchenOrderSummaryResponse[];
  page: number;
  size: number;
  totalElements: number;
  totalPages: number;
  first: boolean;
  last: boolean;
}
export interface KitchenOrderDetailsResponse {
  id: number;
  deliveryAddress: string;
  subtotal: number;
  deliveryFee: number;
  totalPrice: number;
  status: OrderStatus;
  paymentMethod: PaymentMethod;
  createdAt: string;
  updatedAt: string;
  orderItems: OrderItemResponse[];
}
export interface KitchenDashboardSummaryResponse {
  totalOrdersToday: number;
  pendingOrders: number;
  confirmedOrders: number;
  preparingOrders: number;
  readyOrders: number;
  acceptedOrders: number;
  pickedUpOrders: number;
  deliveredOrders: number;
  cancelledOrders: number;
  delayedOrders: number;
}
export interface CreateMenuItemRequest {
  name: string;
  description?: string;
  basePrice: number;
  imageUrl?: string;
  menuSectionId: number;
  available?: boolean;
  addonGroupIds?: number[];
}
export type UpdateMenuItemRequest = Partial<CreateMenuItemRequest>;
export interface CreateMenuSectionRequest {
  name: string;
  description?: string;
  active?: boolean;
}
export type UpdateMenuSectionRequest = Partial<CreateMenuSectionRequest>;
export interface CreateAddonGroupRequest {
  name: string;
  minSelections: number;
  maxSelections: number;
}
export type UpdateAddonGroupRequest = Partial<CreateAddonGroupRequest>;
export interface CreateAddonRequest {
  name: string;
  additionalPrice: number;
  available?: boolean;
}
export type UpdateAddonRequest = Partial<CreateAddonRequest>;
