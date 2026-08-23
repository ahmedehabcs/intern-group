import { OrderStatus } from '../../orders/models/order.models';
export type ApprovalStatus='PENDING'|'APPROVED'|'REJECTED';
export interface CategoryRequest { name:string;description?:string; }
export interface AdminCategoryResponse { id:number;name:string;description:string;active:boolean; }
export interface CustomerAdminResponse { id:number;name:string;email:string;phoneNumber:string; }
export interface OrderAdminResponse { id:number;status:OrderStatus;restaurantName:string;customerName:string;riderName:string|null;totalPrice:number;deliveryFee:number;updatedAt:string; }
// TODO(api-contract): no governorate lookup endpoint is documented, so the UI accepts the required ID.
export interface CreateRestaurantRequest { name:string;phone:string;email:string;address:string;governorateId:number;description?:string;logoUrl?:string;deliveryFee:number;categoryIds?:number[]; }
export interface RestaurantAdminResponse { id:number;name:string;phone:string;email:string;address:string;governorateName:string;description:string;logoUrl:string;isActive:boolean;deliveryFee:number;categoryNames:string[]; }
export interface AssignKitchenManagerRequest { email:string;password:string;name:string;phoneNumber?:string; }
export interface RiderAdminResponse { id:number;name:string;phoneNumber:string;vehicleType:string;licenseNumber:string;nationalId:string;approvalStatus:ApprovalStatus;online:boolean;isActive:boolean; }
export interface DeliveryFeedbackResponse { orderId:number;rating:number;comment:string; }
