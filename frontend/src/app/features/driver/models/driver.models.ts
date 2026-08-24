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
  // Persisted server-side and toggled via PUT /api/delivery/profile/status.
  // Read on load so a refresh restores the driver's actual state instead of
  // defaulting them to offline.
  online: boolean;
}
export interface DriverProfileUpdateRequest {
  name?: string;
  phoneNumber?: string;
  vehicleType?: string;
  licenseNumber?: string;
  nationalId?: string;
}
