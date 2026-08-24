export interface OrderSummaryResponse {
  id: number;
  restaurantName: string;
  deliveryAddress: string;
  itemCount: number;
  totalPrice: number;
  deliveryFee: number;
}
export interface OrderHistoryResponse {
  id: number;
  restaurantName: string;
  deliveredAt: string;
  earnings: number;
  orderTotal: number;
}
export interface DeliveryFeedbackResponse {
  orderId: number;
  rating: number;
  comment: string;
}
export interface DriverProfileResponse {
  email: string;
  name: string;
  phoneNumber: string;
  vehicleType: string;
  licenseNumber: string;
  nationalId: string;
}
export interface DriverProfileUpdateRequest {
  name?: string;
  phoneNumber?: string;
  vehicleType?: string;
  licenseNumber?: string;
  nationalId?: string;
}
