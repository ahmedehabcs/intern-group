export type OrderStatus='PENDING'|'CONFIRMED'|'PREPARING'|'READY'|'ACCEPTED'|'PICKED_UP'|'DELIVERED'|'CANCELLED';
export type PaymentMethod='CASH'|'CREDIT_CARD'|'WALLET';
export interface OrderAddonResponse { addonName:string;addonPrice:number;quantity:number; }
export interface OrderItemResponse { productName:string;unitPrice:number;quantity:number;notes:string;addons:OrderAddonResponse[]; }
export interface CustomerOrderSummaryResponse { id:number;restaurantName:string;itemCount:number;totalPrice:number;status:OrderStatus;createdAt:string; }
export interface CustomerOrderPageResponse { orders:CustomerOrderSummaryResponse[];page:number;size:number;totalElements:number;totalPages:number;first:boolean;last:boolean; }
export interface CustomerOrderDetailsResponse { id:number;restaurantName:string;deliveryAddress:string;subtotal:number;deliveryFee:number;totalPrice:number;status:OrderStatus;paymentMethod:PaymentMethod;createdAt:string;orderItems:OrderItemResponse[]; }
export interface PlaceOrderRequest { addressId:number;paymentMethod:PaymentMethod; }
export type PlaceOrderResponse=CustomerOrderDetailsResponse;
export interface DeliveryFeedbackResponse { orderId:number;rating:number;comment:string; }
