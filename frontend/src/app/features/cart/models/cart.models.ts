export interface CartItemAddonRequest { menuItemAddonId: number; quantity: number; }
// TODO(api-contract): generated addon properties are malformed; confirm this typed array mapping with the backend.
export interface AddCartItemRequest { menuItemId: number; quantity: number; specialInstructions?: string; addons?: CartItemAddonRequest[]; }
export interface ReplaceCartItemRequest { quantity: number; specialInstructions?: string; addons?: CartItemAddonRequest[]; }
export interface CartItemAddonResponse { menuItemAddonId: number; name: string; addonGroupId: number; addonGroupName: string; quantity: number; priceAtAddition: number; totalPrice: number; }
export interface CartItemResponse { id: number; menuItemId: number; menuItemName: string; imageUrl: string; basePrice: number; quantity: number; specialInstructions: string; itemTotalPrice: number; addons: CartItemAddonResponse[]; }
export interface CartResponse { id: number; restaurantId: number; restaurantName: string; subtotal: number; items: CartItemResponse[]; }
