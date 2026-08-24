# Talabaty API Documentation for Frontend

> Generated from the backend controllers and DTOs on 2026-08-23. The Java implementation is the source of truth.

## Base URL and conventions

- Local API: `http://localhost:8080`
- Content type: `application/json`
- Protected endpoints require `Authorization: Bearer <accessToken>`.
- JWT lifetime defaults to 900 seconds (15 minutes). There is no refresh-token endpoint; log in again after expiry.
- Dates use `YYYY-MM-DD`; timestamps use ISO-8601, e.g. `2026-08-23T12:00:00`.
- Monetary JSON values are numbers.
- Empty successful bodies are shown as `—` and normally use HTTP 204 for deletes or HTTP 200 for commands.

## Shared status and error behavior

| Status | Meaning | Typical body |
|---|---|---|
| 200 | Successful read/update/action | Endpoint response below |
| 201 | Created | Created resource |
| 204 | Successful delete | No body |
| 400 | Validation or business-rule failure | Usually `{"error":"..."}` or a validation field map |
| 401 | Missing, invalid, or expired JWT | `{"error":"Unauthorized","message":"Authentication required"}` |
| 403 | Authenticated but wrong role/account state | Error message/body from Spring or auth handler |
| 404 | Resource not found | `{"error":"..."}` |
| 409 | State conflict (for example, order cannot be cancelled) | `{"error":"..."}` |
| 429 | Login rate limit exceeded | Error response |
| 500 | Unexpected server failure | Error response |

## Authentication flow

1. Sign up a customer or driver.
2. Verify the emailed OTP with `/api/auth/verify-otp`.
3. Log in and store `accessToken` plus `role` from `LoginResponse`.
4. Send the token on protected calls. Route UI by `CUSTOMER`, `DRIVER`, `ADMIN`, or `KITCHEN_MANAGER`.

## Endpoint reference

### Addresses

#### `GET /api/addresses`

Get addresses.

- Access: `Authenticated`
- Request body: none
- Success response: `List<AddressResponse>`

Response example:

```json
[
  {
    "id": 1,
    "street": "string",
    "building": "string",
    "floor": "string",
    "apartment": "string",
    "city": "string",
    "governorateId": 1,
    "governorateName": "string",
    "isDefault": true
  }
]
```

#### `GET /api/addresses/{addressId}`

Get address.

- Access: `Authenticated`
- Path parameters: `addressId` (Long)
- Request body: none
- Success response: `AddressResponse`

Response example:

```json
{
  "id": 1,
  "street": "string",
  "building": "string",
  "floor": "string",
  "apartment": "string",
  "city": "string",
  "governorateId": 1,
  "governorateName": "string",
  "isDefault": true
}
```

#### `POST /api/addresses`

Create address.

- Access: `Authenticated`
- Request body: `AddressRequest`
- Success response: `AddressResponse`

Request example:

```json
{
  "street": "string",
  "building": "string",
  "floor": "string",
  "apartment": "string",
  "city": "string",
  "governorateId": 1
}
```

Response example:

```json
{
  "id": 1,
  "street": "string",
  "building": "string",
  "floor": "string",
  "apartment": "string",
  "city": "string",
  "governorateId": 1,
  "governorateName": "string",
  "isDefault": true
}
```

#### `PUT /api/addresses/{addressId}`

Update address.

- Access: `Authenticated`
- Path parameters: `addressId` (Long)
- Request body: `AddressRequest`
- Success response: `AddressResponse`

Request example:

```json
{
  "street": "string",
  "building": "string",
  "floor": "string",
  "apartment": "string",
  "city": "string",
  "governorateId": 1
}
```

Response example:

```json
{
  "id": 1,
  "street": "string",
  "building": "string",
  "floor": "string",
  "apartment": "string",
  "city": "string",
  "governorateId": 1,
  "governorateName": "string",
  "isDefault": true
}
```

#### `PATCH /api/addresses/{addressId}/default`

Set default address.

- Access: `Authenticated`
- Path parameters: `addressId` (Long)
- Request body: none
- Success response: `AddressResponse`

Response example:

```json
{
  "id": 1,
  "street": "string",
  "building": "string",
  "floor": "string",
  "apartment": "string",
  "city": "string",
  "governorateId": 1,
  "governorateName": "string",
  "isDefault": true
}
```

#### `DELETE /api/addresses/{addressId}`

Delete address.

- Access: `Authenticated`
- Path parameters: `addressId` (Long)
- Request body: none
- Success response: `Void`

Response body: none.

### Admin Categories

#### `GET /api/admin/categories`

Get all categories.

- Access: `ADMIN`
- Request body: none
- Success response: `List<AdminCategoryResponse>`

Response example:

```json
[
  {
    "id": 1,
    "name": "string",
    "description": "string",
    "active": true
  }
]
```

#### `POST /api/admin/categories`

Create category.

- Access: `ADMIN`
- Request body: `CategoryRequest`
- Success response: `CategoryResponse`

Request example:

```json
{
  "name": "string",
  "description": "string"
}
```

Response example:

```json
{
  "id": 1,
  "name": "string",
  "description": "string"
}
```

#### `PUT /api/admin/categories/{id}`

Update category.

- Access: `ADMIN`
- Path parameters: `id` (Long)
- Request body: `CategoryRequest`
- Success response: `CategoryResponse`

Request example:

```json
{
  "name": "string",
  "description": "string"
}
```

Response example:

```json
{
  "id": 1,
  "name": "string",
  "description": "string"
}
```

#### `DELETE /api/admin/categories/{id}`

Delete category.

- Access: `ADMIN`
- Path parameters: `id` (Long)
- Request body: none
- Success response: `Void`

Response body: none.

### AdminCustomer

#### `GET /api/admin/customers`

Search customers.

- Access: `ADMIN`
- Query parameters: `search` (String, optional)
- Request body: none
- Success response: `List<CustomerAdminResponse>`

Response example:

```json
[
  {
    "id": 1,
    "name": "string",
    "email": "user@example.com",
    "phoneNumber": "string"
  }
]
```

### AdminOrder

#### `GET /api/admin/orders`

Get orders.

- Access: `ADMIN`
- Query parameters: `status` (OrderStatus, optional), `restaurantId` (Long, optional), `from` (LocalDate, optional), `to` (LocalDate, optional)
- Request body: none
- Success response: `List<OrderAdminResponse>`

Response example:

```json
[
  {
    "id": 1,
    "status": "PENDING",
    "restaurantName": "string",
    "customerName": "string",
    "riderName": "string",
    "totalPrice": 12.5,
    "deliveryFee": 12.5,
    "updatedAt": "2026-08-23T12:00:00"
  }
]
```

#### `PUT /api/admin/orders/{orderId}/cancel`

Cancel order.

- Access: `ADMIN`
- Path parameters: `orderId` (Long)
- Request body: none
- Success response: `OrderAdminResponse`

Response example:

```json
{
  "id": 1,
  "status": "PENDING",
  "restaurantName": "string",
  "customerName": "string",
  "riderName": "string",
  "totalPrice": 12.5,
  "deliveryFee": 12.5,
  "updatedAt": "2026-08-23T12:00:00"
}
```

### AdminRestaurant

#### `POST /api/admin/restaurants/add`

Create restaurant.

- Access: `ADMIN`
- Request body: `CreateRestaurantRequest`
- Success response: `RestaurantAdminResponse`

Request example:

```json
{
  "name": "string",
  "phone": "string",
  "email": "user@example.com",
  "address": "string",
  "governorateId": 1,
  "description": "string",
  "logoUrl": "https://example.com/image.jpg",
  "deliveryFee": 12.5,
  "categoryIds": [
    1
  ]
}
```

Response example:

```json
{
  "id": 1,
  "name": "string",
  "phone": "string",
  "email": "user@example.com",
  "address": "string",
  "governorateName": "string",
  "description": "string",
  "logoUrl": "https://example.com/image.jpg",
  "isActive": true,
  "deliveryFee": 12.5,
  "categoryNames": [
    "string"
  ]
}
```

#### `PUT /api/admin/restaurants/{id}/edit`

Update restaurant.

- Access: `ADMIN`
- Path parameters: `id` (Long)
- Request body: none
- Success response: `RestaurantAdminResponse`

Response example:

```json
{
  "id": 1,
  "name": "string",
  "phone": "string",
  "email": "user@example.com",
  "address": "string",
  "governorateName": "string",
  "description": "string",
  "logoUrl": "https://example.com/image.jpg",
  "isActive": true,
  "deliveryFee": 12.5,
  "categoryNames": [
    "string"
  ]
}
```

#### `PUT /api/admin/restaurants/{id}/deactivate`

Deactivate restaurant.

- Access: `ADMIN`
- Path parameters: `id` (Long)
- Request body: none
- Success response: `RestaurantAdminResponse`

Response example:

```json
{
  "id": 1,
  "name": "string",
  "phone": "string",
  "email": "user@example.com",
  "address": "string",
  "governorateName": "string",
  "description": "string",
  "logoUrl": "https://example.com/image.jpg",
  "isActive": true,
  "deliveryFee": 12.5,
  "categoryNames": [
    "string"
  ]
}
```

#### `POST /api/admin/restaurants/{restaurantId}/kitchen-manager`

Assign kitchen manager.

- Access: `ADMIN`
- Path parameters: `restaurantId` (Long)
- Request body: `AssignKitchenManagerRequest`
- Success response: `Void`

Request example:

```json
{
  "email": "user@example.com",
  "password": "StrongPass123",
  "name": "string",
  "phoneNumber": "string"
}
```

Response body: none.

#### `GET /api/admin/restaurants`

Search restaurants.

- Access: `ADMIN`
- Query parameters: `search` (String, optional)
- Request body: none
- Success response: `List<RestaurantAdminResponse>`

Response example:

```json
[
  {
    "id": 1,
    "name": "string",
    "phone": "string",
    "email": "user@example.com",
    "address": "string",
    "governorateName": "string",
    "description": "string",
    "logoUrl": "https://example.com/image.jpg",
    "isActive": true,
    "deliveryFee": 12.5,
    "categoryNames": [
      "string"
    ]
  }
]
```

### AdminRider

#### `GET /api/admin/riders`

Search riders.

- Access: `ADMIN`
- Query parameters: `search` (String, optional)
- Request body: none
- Success response: `List<RiderAdminResponse>`

Response example:

```json
[
  {
    "id": 1,
    "name": "string",
    "phoneNumber": "string",
    "vehicleType": "string",
    "licenseNumber": "string",
    "nationalId": "string",
    "approvalStatus": "PENDING",
    "online": true,
    "isActive": true
  }
]
```

#### `GET /api/admin/riders/pending`

Get pending riders.

- Access: `ADMIN`
- Request body: none
- Success response: `List<RiderAdminResponse>`

Response example:

```json
[
  {
    "id": 1,
    "name": "string",
    "phoneNumber": "string",
    "vehicleType": "string",
    "licenseNumber": "string",
    "nationalId": "string",
    "approvalStatus": "PENDING",
    "online": true,
    "isActive": true
  }
]
```

#### `PUT /api/admin/riders/{riderId}/approve`

Approve rider.

- Access: `ADMIN`
- Path parameters: `riderId` (Long)
- Request body: none
- Success response: `RiderAdminResponse`

Response example:

```json
{
  "id": 1,
  "name": "string",
  "phoneNumber": "string",
  "vehicleType": "string",
  "licenseNumber": "string",
  "nationalId": "string",
  "approvalStatus": "PENDING",
  "online": true,
  "isActive": true
}
```

#### `PUT /api/admin/riders/{riderId}/reject`

Reject rider.

- Access: `ADMIN`
- Path parameters: `riderId` (Long)
- Request body: none
- Success response: `RiderAdminResponse`

Response example:

```json
{
  "id": 1,
  "name": "string",
  "phoneNumber": "string",
  "vehicleType": "string",
  "licenseNumber": "string",
  "nationalId": "string",
  "approvalStatus": "PENDING",
  "online": true,
  "isActive": true
}
```

#### `PUT /api/admin/riders/{riderId}/deactivate`

Deactivate rider.

- Access: `ADMIN`
- Path parameters: `riderId` (Long)
- Request body: none
- Success response: `RiderAdminResponse`

Response example:

```json
{
  "id": 1,
  "name": "string",
  "phoneNumber": "string",
  "vehicleType": "string",
  "licenseNumber": "string",
  "nationalId": "string",
  "approvalStatus": "PENDING",
  "online": true,
  "isActive": true
}
```

### Authentication

#### `POST /api/auth/signup/customer`

Register customer.

- Access: `Public`
- Request body: `CustomerSignupRequest`
- Success response: `RegisterResponse`

Request example:

```json
{
  "regexp": "string",
  "message": "string",
  "name": "string"
}
```

Response example:

```json
{
  "message": "string"
}
```

#### `POST /api/auth/signup/driver`

Register driver.

- Access: `Public`
- Request body: `DriverSignupRequest`
- Success response: `RegisterResponse`

Request example:

```json
{
  "regexp": "string",
  "message": "string",
  "name": "string",
  "phoneNumber": "string",
  "vehicleType": "string",
  "licenseNumber": "string",
  "nationalId": "string"
}
```

Response example:

```json
{
  "message": "string"
}
```

#### `POST /api/auth/verify-otp`

Verify otp.

- Access: `Public`
- Request body: `VerifyOtpRequest`
- Success response: `String`

Request example:

```json
{
  "email": "user@example.com",
  "otp": "123456"
}
```

Response example:

```json
"Account verified successfully"
```

#### `POST /api/auth/resend-otp`

Resend otp.

- Access: `Public`
- Query parameters: `email` (String, required)
- Request body: none
- Success response: `String`

Response example:

```json
"OTP resent successfully"
```

#### `POST /api/auth/login`

Login.

- Access: `Public`
- Request body: `LoginRequest`
- Success response: `LoginResponse`

Request example:

```json
{
  "email": "user@example.com",
  "password": "StrongPass123"
}
```

Response example:

```json
{
  "message": "string",
  "accessToken": "string",
  "tokenType": "string",
  "expiresIn": 1,
  "userId": 1,
  "email": "user@example.com",
  "role": "string"
}
```

#### `POST /api/auth/forgot-password`

Forgot password.

- Access: `Public`
- Request body: `ForgotPasswordRequest`
- Success response: `String`

Request example:

```json
{
  "email": "user@example.com"
}
```

Response example:

```json
"Password reset OTP sent to email."
```

#### `POST /api/auth/reset-password`

Reset password.

- Access: `Public`
- Request body: `ResetPasswordRequest`
- Success response: `String`

Request example:

```json
{
  "email": "user@example.com",
  "otp": "123456",
  "newPassword": "StrongPass123"
}
```

Response example:

```json
"Password reset successfully."
```

### Cart

#### `GET /api/cart`

Get cart.

- Access: `Authenticated`
- Request body: none
- Success response: `CartResponse`

Response example:

```json
{
  "id": 1,
  "restaurantId": 1,
  "restaurantName": "string",
  "subtotal": 12.5,
  "items": [
    {
      "id": 1,
      "menuItemId": 1,
      "menuItemName": "string",
      "imageUrl": "https://example.com/image.jpg",
      "basePrice": 12.5,
      "quantity": 1,
      "specialInstructions": "string",
      "itemTotalPrice": 12.5,
      "addons": [
        {}
      ]
    }
  ]
}
```

#### `POST /api/cart/items`

Add item.

- Access: `Authenticated`
- Request body: `AddCartItemRequest`
- Success response: `CartResponse`

Request example:

```json
{
  "menuItemId": 1,
  "quantity": 1,
  "specialInstructions": "string",
  "addons": "string"
}
```

Response example:

```json
{
  "id": 1,
  "restaurantId": 1,
  "restaurantName": "string",
  "subtotal": 12.5,
  "items": [
    {
      "id": 1,
      "menuItemId": 1,
      "menuItemName": "string",
      "imageUrl": "https://example.com/image.jpg",
      "basePrice": 12.5,
      "quantity": 1,
      "specialInstructions": "string",
      "itemTotalPrice": 12.5,
      "addons": [
        {}
      ]
    }
  ]
}
```

#### `PATCH /api/cart/items/{cartItemId}/quantity`

Update item quantity.

- Access: `Authenticated`
- Path parameters: `cartItemId` (Long)
- Request body: `UpdateCartItemQuantityRequest`
- Success response: `CartResponse`

Request example:

```json
{
  "quantity": 1
}
```

Response example:

```json
{
  "id": 1,
  "restaurantId": 1,
  "restaurantName": "string",
  "subtotal": 12.5,
  "items": [
    {
      "id": 1,
      "menuItemId": 1,
      "menuItemName": "string",
      "imageUrl": "https://example.com/image.jpg",
      "basePrice": 12.5,
      "quantity": 1,
      "specialInstructions": "string",
      "itemTotalPrice": 12.5,
      "addons": [
        {}
      ]
    }
  ]
}
```

#### `PUT /api/cart/items/{cartItemId}`

Replace item configuration.

- Access: `Authenticated`
- Path parameters: `cartItemId` (Long)
- Request body: `ReplaceCartItemRequest`
- Success response: `CartResponse`

Request example:

```json
{
  "quantity": 1,
  "specialInstructions": "string",
  "addons": "string"
}
```

Response example:

```json
{
  "id": 1,
  "restaurantId": 1,
  "restaurantName": "string",
  "subtotal": 12.5,
  "items": [
    {
      "id": 1,
      "menuItemId": 1,
      "menuItemName": "string",
      "imageUrl": "https://example.com/image.jpg",
      "basePrice": 12.5,
      "quantity": 1,
      "specialInstructions": "string",
      "itemTotalPrice": 12.5,
      "addons": [
        {}
      ]
    }
  ]
}
```

#### `DELETE /api/cart/items/{cartItemId}`

Remove item.

- Access: `Authenticated`
- Path parameters: `cartItemId` (Long)
- Request body: none
- Success response: `CartResponse`

Response example:

```json
{
  "id": 1,
  "restaurantId": 1,
  "restaurantName": "string",
  "subtotal": 12.5,
  "items": [
    {
      "id": 1,
      "menuItemId": 1,
      "menuItemName": "string",
      "imageUrl": "https://example.com/image.jpg",
      "basePrice": 12.5,
      "quantity": 1,
      "specialInstructions": "string",
      "itemTotalPrice": 12.5,
      "addons": [
        {}
      ]
    }
  ]
}
```

#### `DELETE /api/cart/items`

Clear cart.

- Access: `Authenticated`
- Request body: none
- Success response: `Void`

Response body: none.

### Categories

#### `GET /api/categories`

Browse categories.

- Access: `Public`
- Request body: none
- Success response: `List<CategoryResponse>`

Response example:

```json
[
  {
    "id": 1,
    "name": "string",
    "description": "string"
  }
]
```

### Delivery

#### `GET /api/delivery/orders/available`

Get available orders.

- Access: `DRIVER`
- Request body: none
- Success response: `List<OrderSummaryresponse>`

Response example:

```json
[
  {
    "id": 1,
    "restaurantName": "string",
    "deliveryAddress": "string",
    "itemCount": 1,
    "totalPrice": 12.5,
    "deliveryFee": 12.5
  }
]
```

#### `PUT /api/delivery/orders/{orderId}/accept`

Accept order.

- Access: `DRIVER`
- Path parameters: `orderId` (Long)
- Request body: none
- Success response: `OrderSummaryresponse`

Response example:

```json
{
  "id": 1,
  "restaurantName": "string",
  "deliveryAddress": "string",
  "itemCount": 1,
  "totalPrice": 12.5,
  "deliveryFee": 12.5
}
```

#### `PUT /api/delivery/orders/{orderId}/pickup`

Mark picked up.

- Access: `DRIVER`
- Path parameters: `orderId` (Long)
- Request body: none
- Success response: `OrderSummaryresponse`

Response example:

```json
{
  "id": 1,
  "restaurantName": "string",
  "deliveryAddress": "string",
  "itemCount": 1,
  "totalPrice": 12.5,
  "deliveryFee": 12.5
}
```

#### `PUT /api/delivery/orders/{orderId}/deliver`

Mark delivered.

- Access: `DRIVER`
- Path parameters: `orderId` (Long)
- Request body: none
- Success response: `OrderSummaryresponse`

Response example:

```json
{
  "id": 1,
  "restaurantName": "string",
  "deliveryAddress": "string",
  "itemCount": 1,
  "totalPrice": 12.5,
  "deliveryFee": 12.5
}
```

#### `PUT /api/delivery/orders/{orderId}/cancel`

Cancel order.

- Access: `DRIVER`
- Path parameters: `orderId` (Long)
- Request body: none
- Success response: `Void`

Response body: none.

#### `GET /api/delivery/orders/active`

Get active order.

- Access: `DRIVER`
- Request body: none
- Success response: `OrderSummaryresponse`

Response example:

```json
{
  "id": 1,
  "restaurantName": "string",
  "deliveryAddress": "string",
  "itemCount": 1,
  "totalPrice": 12.5,
  "deliveryFee": 12.5
}
```

#### `GET /api/delivery/orders/history`

Get delivery history.

- Access: `DRIVER`
- Request body: none
- Success response: `List<OrderHistoryResponse>`

Response example:

```json
[
  {
    "id": 1,
    "restaurantName": "string",
    "deliveredAt": "2026-08-23T12:00:00",
    "earnings": 12.5,
    "orderTotal": 12.5
  }
]
```

### DeliveryFeedback

#### `POST /api/delivery-feedback/orders/{orderId}`

Create feedback.

- Access: `CUSTOMER`
- Path parameters: `orderId` (Long)
- Request body: `CreateDeliveryFeedbackRequest`
- Success response: `DeliveryFeedbackResponse`

Request example:

```json
{
  "rating": 1
}
```

Response example:

```json
{
  "orderId": 1,
  "rating": 1,
  "comment": "string"
}
```

#### `GET /api/delivery-feedback/me`

Get my feedback.

- Access: `DRIVER`
- Request body: none
- Success response: `List<DeliveryFeedbackResponse>`

Response example:

```json
[
  {
    "orderId": 1,
    "rating": 1,
    "comment": "string"
  }
]
```

#### `GET /api/delivery-feedback/admin/all`

Get all feedback.

- Access: `ADMIN`
- Request body: none
- Success response: `List<DeliveryFeedbackResponse>`

Response example:

```json
[
  {
    "orderId": 1,
    "rating": 1,
    "comment": "string"
  }
]
```

### DeliveryProfile

#### `PUT /api/delivery/profile/status`

Update status.

- Access: `DRIVER`
- Request body: none
- Success response: `Void`

Response body: none.

### Kitchen Dashboard

#### `GET /api/kitchen/dashboard/summary`

Get today summary.

- Access: `KITCHEN_MANAGER`
- Request body: none
- Success response: `KitchenDashboardSummaryResponse`

Response example:

```json
{
  "totalOrdersToday": 1,
  "pendingOrders": 1,
  "confirmedOrders": 1,
  "preparingOrders": 1,
  "readyOrders": 1,
  "acceptedOrders": 1,
  "pickedUpOrders": 1,
  "deliveredOrders": 1,
  "cancelledOrders": 1,
  "delayedOrders": 1
}
```

### Kitchen Menu Items

#### `GET /api/kitchen/menu-items`

Get menu items.

- Access: `KITCHEN_MANAGER`
- Request body: none
- Success response: `List<KitchenMenuItemResponse>`

Response example:

```json
[
  {
    "id": 1,
    "name": "string",
    "basePrice": 12.5,
    "available": true,
    "menuSectionId": 1,
    "menuSectionName": "string"
  }
]
```

#### `POST /api/kitchen/menu-items`

Create menu item.

- Access: `KITCHEN_MANAGER`
- Request body: `CreateMenuItemRequest`
- Success response: `KitchenMenuItemResponse`

Request example:

```json
{
  "name": "string",
  "description": "string",
  "basePrice": 12.5,
  "imageUrl": "https://example.com/image.jpg",
  "menuSectionId": 1,
  "available": true,
  "addonGroupIds": [
    1
  ]
}
```

Response example:

```json
{
  "id": 1,
  "name": "string",
  "basePrice": 12.5,
  "available": true,
  "menuSectionId": 1,
  "menuSectionName": "string"
}
```

#### `PATCH /api/kitchen/menu-items/{menuItemId}`

Update menu item.

- Access: `KITCHEN_MANAGER`
- Path parameters: `menuItemId` (Long)
- Request body: `UpdateMenuItemRequest`
- Success response: `KitchenMenuItemResponse`

Request example:

```json
{
  "name": "string",
  "description": "string",
  "basePrice": 12.5,
  "imageUrl": "https://example.com/image.jpg",
  "menuSectionId": 1,
  "available": true,
  "addonGroupIds": [
    1
  ]
}
```

Response example:

```json
{
  "id": 1,
  "name": "string",
  "basePrice": 12.5,
  "available": true,
  "menuSectionId": 1,
  "menuSectionName": "string"
}
```

#### `PATCH /api/kitchen/menu-items/{menuItemId}/availability`

Update availability.

- Access: `KITCHEN_MANAGER`
- Path parameters: `menuItemId` (Long)
- Request body: `UpdateMenuItemAvailabilityRequest`
- Success response: `KitchenMenuItemResponse`

Request example:

```json
{
  "available": true
}
```

Response example:

```json
{
  "id": 1,
  "name": "string",
  "basePrice": 12.5,
  "available": true,
  "menuSectionId": 1,
  "menuSectionName": "string"
}
```

#### `DELETE /api/kitchen/menu-items/{menuItemId}`

Delete menu item.

- Access: `KITCHEN_MANAGER`
- Path parameters: `menuItemId` (Long)
- Request body: none
- Success response: `Void`

Response body: none.

#### `GET /api/kitchen/menu-items/sections`

Get menu sections.

- Access: `KITCHEN_MANAGER`
- Request body: none
- Success response: `List<KitchenMenuSectionResponse>`

Response example:

```json
[
  {
    "id": 1,
    "name": "string",
    "description": "string",
    "active": true,
    "menuItems": [
      {
        "id": 1,
        "name": "string",
        "basePrice": 12.5,
        "available": true,
        "menuSectionId": 1,
        "menuSectionName": "string"
      }
    ]
  }
]
```

#### `POST /api/kitchen/menu-items/sections`

Create menu section.

- Access: `KITCHEN_MANAGER`
- Request body: `CreateMenuSectionRequest`
- Success response: `KitchenMenuSectionResponse`

Request example:

```json
{
  "name": "string",
  "description": "string",
  "active": true
}
```

Response example:

```json
{
  "id": 1,
  "name": "string",
  "description": "string",
  "active": true,
  "menuItems": [
    {
      "id": 1,
      "name": "string",
      "basePrice": 12.5,
      "available": true,
      "menuSectionId": 1,
      "menuSectionName": "string"
    }
  ]
}
```

#### `PATCH /api/kitchen/menu-items/sections/{menuSectionId}`

Update menu section.

- Access: `KITCHEN_MANAGER`
- Path parameters: `menuSectionId` (Long)
- Request body: `UpdateMenuSectionRequest`
- Success response: `KitchenMenuSectionResponse`

Request example:

```json
{
  "name": "string",
  "description": "string",
  "active": true
}
```

Response example:

```json
{
  "id": 1,
  "name": "string",
  "description": "string",
  "active": true,
  "menuItems": [
    {
      "id": 1,
      "name": "string",
      "basePrice": 12.5,
      "available": true,
      "menuSectionId": 1,
      "menuSectionName": "string"
    }
  ]
}
```

#### `GET /api/kitchen/menu-items/addon-groups`

Get addon groups.

- Access: `KITCHEN_MANAGER`
- Request body: none
- Success response: `List<KitchenAddonGroupResponse>`

Response example:

```json
[
  {
    "id": 1,
    "name": "string",
    "minSelections": 1,
    "maxSelections": 1,
    "addons": [
      {
        "id": 1,
        "name": "string",
        "additionalPrice": 12.5,
        "available": true
      }
    ]
  }
]
```

#### `POST /api/kitchen/menu-items/addon-groups`

Create addon group.

- Access: `KITCHEN_MANAGER`
- Request body: `CreateAddonGroupRequest`
- Success response: `KitchenAddonGroupResponse`

Request example:

```json
{
  "name": "string",
  "minSelections": 1,
  "maxSelections": 1
}
```

Response example:

```json
{
  "id": 1,
  "name": "string",
  "minSelections": 1,
  "maxSelections": 1,
  "addons": [
    {
      "id": 1,
      "name": "string",
      "additionalPrice": 12.5,
      "available": true
    }
  ]
}
```

#### `PATCH /api/kitchen/menu-items/addon-groups/{addonGroupId}`

Update addon group.

- Access: `KITCHEN_MANAGER`
- Path parameters: `addonGroupId` (Long)
- Request body: `UpdateAddonGroupRequest`
- Success response: `KitchenAddonGroupResponse`

Request example:

```json
{
  "name": "string",
  "minSelections": 1,
  "maxSelections": 1
}
```

Response example:

```json
{
  "id": 1,
  "name": "string",
  "minSelections": 1,
  "maxSelections": 1,
  "addons": [
    {
      "id": 1,
      "name": "string",
      "additionalPrice": 12.5,
      "available": true
    }
  ]
}
```

#### `DELETE /api/kitchen/menu-items/addon-groups/{addonGroupId}`

Delete addon group.

- Access: `KITCHEN_MANAGER`
- Path parameters: `addonGroupId` (Long)
- Request body: none
- Success response: `Void`

Response body: none.

#### `GET /api/kitchen/menu-items/addon-groups/{addonGroupId}/addons`

Get addons by group.

- Access: `KITCHEN_MANAGER`
- Path parameters: `addonGroupId` (Long)
- Request body: none
- Success response: `List<KitchenAddonResponse>`

Response example:

```json
[
  {
    "id": 1,
    "name": "string",
    "additionalPrice": 12.5,
    "available": true
  }
]
```

#### `POST /api/kitchen/menu-items/addon-groups/{addonGroupId}/addons`

Create addon.

- Access: `KITCHEN_MANAGER`
- Path parameters: `addonGroupId` (Long)
- Request body: `CreateAddonRequest`
- Success response: `KitchenAddonResponse`

Request example:

```json
{
  "name": "string",
  "additionalPrice": 12.5,
  "available": true
}
```

Response example:

```json
{
  "id": 1,
  "name": "string",
  "additionalPrice": 12.5,
  "available": true
}
```

#### `PATCH /api/kitchen/menu-items/addons/{addonId}`

Update addon.

- Access: `KITCHEN_MANAGER`
- Path parameters: `addonId` (Long)
- Request body: `UpdateAddonRequest`
- Success response: `KitchenAddonResponse`

Request example:

```json
{
  "name": "string",
  "additionalPrice": 12.5,
  "available": true
}
```

Response example:

```json
{
  "id": 1,
  "name": "string",
  "additionalPrice": 12.5,
  "available": true
}
```

#### `DELETE /api/kitchen/menu-items/addons/{addonId}`

Delete addon.

- Access: `KITCHEN_MANAGER`
- Path parameters: `addonId` (Long)
- Request body: none
- Success response: `Void`

Response body: none.

### Kitchen Orders

#### `GET /api/kitchen/orders`

Get active orders.

- Access: `KITCHEN_MANAGER`
- Request body: none
- Success response: `List<KitchenOrderSummaryResponse>`

Response example:

```json
[
  {
    "id": 1,
    "itemCount": 1,
    "totalPrice": 12.5,
    "status": "PENDING",
    "createdAt": "2026-08-23T12:00:00"
  }
]
```

#### `GET /api/kitchen/orders/history`

Get order history.

- Access: `KITCHEN_MANAGER`
- Query parameters: `status` (OrderStatus, optional), `from` (LocalDate, optional), `to` (LocalDate, optional), `page` (int, optional, default `0`), `size` (int, optional, default `10`), `direction` (String, optional, default `desc`)
- Request body: none
- Success response: `KitchenOrderPageResponse`

Response example:

```json
{
  "orders": [
    {
      "id": 1,
      "itemCount": 1,
      "totalPrice": 12.5,
      "status": "PENDING",
      "createdAt": "2026-08-23T12:00:00"
    }
  ],
  "page": 1,
  "size": 1,
  "totalElements": 1,
  "totalPages": 1,
  "first": true,
  "last": true
}
```

#### `GET /api/kitchen/orders/{orderId}`

Get order details.

- Access: `KITCHEN_MANAGER`
- Path parameters: `orderId` (Long)
- Request body: none
- Success response: `KitchenOrderDetailsResponse`

Response example:

```json
{
  "id": 1,
  "deliveryAddress": "string",
  "subtotal": 12.5,
  "deliveryFee": 12.5,
  "totalPrice": 12.5,
  "status": "PENDING",
  "paymentMethod": "CASH",
  "createdAt": "2026-08-23T12:00:00",
  "updatedAt": "2026-08-23T12:00:00",
  "orderItems": [
    {
      "productName": "string",
      "unitPrice": 12.5,
      "quantity": 1,
      "notes": "string",
      "addons": [
        {}
      ]
    }
  ]
}
```

#### `PATCH /api/kitchen/orders/{orderId}/status`

Update order status.

- Access: `KITCHEN_MANAGER`
- Path parameters: `orderId` (Long)
- Request body: `UpdateKitchenOrderStatusRequest`
- Success response: `KitchenOrderDetailsResponse`

Request example:

```json
{
  "status": "PENDING"
}
```

Response example:

```json
{
  "id": 1,
  "deliveryAddress": "string",
  "subtotal": 12.5,
  "deliveryFee": 12.5,
  "totalPrice": 12.5,
  "status": "PENDING",
  "paymentMethod": "CASH",
  "createdAt": "2026-08-23T12:00:00",
  "updatedAt": "2026-08-23T12:00:00",
  "orderItems": [
    {
      "productName": "string",
      "unitPrice": 12.5,
      "quantity": 1,
      "notes": "string",
      "addons": [
        {}
      ]
    }
  ]
}
```

#### `PATCH /api/kitchen/orders/{orderId}/cancel`

Cancel order.

- Access: `KITCHEN_MANAGER`
- Path parameters: `orderId` (Long)
- Request body: `CancelKitchenOrderRequest`
- Success response: `KitchenOrderDetailsResponse`

Request example:

```json
{
  "reason": "string"
}
```

Response example:

```json
{
  "id": 1,
  "deliveryAddress": "string",
  "subtotal": 12.5,
  "deliveryFee": 12.5,
  "totalPrice": 12.5,
  "status": "PENDING",
  "paymentMethod": "CASH",
  "createdAt": "2026-08-23T12:00:00",
  "updatedAt": "2026-08-23T12:00:00",
  "orderItems": [
    {
      "productName": "string",
      "unitPrice": 12.5,
      "quantity": 1,
      "notes": "string",
      "addons": [
        {}
      ]
    }
  ]
}
```

### Menu Items

#### `GET /api/menu-items/{menuItemId}`

Get menu item details.

- Access: `Public`
- Path parameters: `menuItemId` (Long)
- Request body: none
- Success response: `MenuItemDetailsResponse`

Response example:

```json
{
  "id": 1,
  "name": "string",
  "description": "string",
  "basePrice": 12.5,
  "imageUrl": "https://example.com/image.jpg",
  "restaurantId": 1,
  "menuSectionId": 1,
  "addonGroups": [
    {
      "id": 1,
      "name": "string",
      "minSelections": 1,
      "maxSelections": 1,
      "addons": [
        {}
      ]
    }
  ]
}
```

### Orders

#### `POST /api/orders`

Place order.

- Access: `Authenticated`
- Request body: `PlaceOrderRequest`
- Success response: `PlaceOrderResponse`

Request example:

```json
{
  "addressId": 1,
  "paymentMethod": "CASH"
}
```

Response example:

```json
{
  "id": 1,
  "restaurantName": "string",
  "deliveryAddress": "string",
  "subtotal": 12.5,
  "deliveryFee": 12.5,
  "totalPrice": 12.5,
  "createdAt": "2026-08-23T12:00:00",
  "status": "PENDING",
  "paymentMethod": "CASH",
  "orderItems": [
    {
      "productName": "string",
      "unitPrice": 12.5,
      "quantity": 1,
      "notes": "string",
      "addons": [
        {}
      ]
    }
  ]
}
```

#### `GET /api/orders`

Get customer orders.

- Access: `Authenticated`
- Query parameters: `page` (int, optional, default `0`), `size` (int, optional, default `10`)
- Request body: none
- Success response: `CustomerOrderPageResponse`

Response example:

```json
{
  "orders": [
    {
      "id": 1,
      "restaurantName": "string",
      "itemCount": 1,
      "totalPrice": 12.5,
      "status": "PENDING",
      "createdAt": "2026-08-23T12:00:00"
    }
  ],
  "page": 1,
  "size": 1,
  "totalElements": 1,
  "totalPages": 1,
  "first": true,
  "last": true
}
```

#### `GET /api/orders/{orderId}`

Get customer order details.

- Access: `Authenticated`
- Path parameters: `orderId` (Long)
- Request body: none
- Success response: `CustomerOrderDetailsResponse`

Response example:

```json
{
  "id": 1,
  "restaurantName": "string",
  "deliveryAddress": "string",
  "subtotal": 12.5,
  "deliveryFee": 12.5,
  "totalPrice": 12.5,
  "status": "PENDING",
  "paymentMethod": "CASH",
  "createdAt": "2026-08-23T12:00:00",
  "orderItems": [
    {
      "productName": "string",
      "unitPrice": 12.5,
      "quantity": 1,
      "notes": "string",
      "addons": [
        {}
      ]
    }
  ]
}
```

#### `PUT /api/orders/{orderId}/cancel`

Cancel customer order.

- Access: `Authenticated`
- Path parameters: `orderId` (Long)
- Request body: `CancelOrderRequest`
- Success response: `CustomerOrderDetailsResponse`

Request example:

```json
{
  "reason": "string"
}
```

Response example:

```json
{
  "id": 1,
  "restaurantName": "string",
  "deliveryAddress": "string",
  "subtotal": 12.5,
  "deliveryFee": 12.5,
  "totalPrice": 12.5,
  "status": "PENDING",
  "paymentMethod": "CASH",
  "createdAt": "2026-08-23T12:00:00",
  "orderItems": [
    {
      "productName": "string",
      "unitPrice": 12.5,
      "quantity": 1,
      "notes": "string",
      "addons": [
        {}
      ]
    }
  ]
}
```

### Profile

#### `GET /api/profile`

Get profile.

- Access: `Authenticated`
- Request body: none
- Success response: `CustomerProfileResponse | DriverProfileResponse`

Response is one of the listed role-specific schemas; see the schema reference below.

#### `PUT /api/profile`

Update profile.

- Access: `Authenticated`
- Request body: `CustomerProfileUpdateRequest | DriverProfileUpdateRequest`
- Success response: `Void`

Response body: none.

### Restaurants

#### `GET /api/restaurants`

Browse restaurants.

- Access: `Public`
- Query parameters: `categoryId` (Long, optional)
- Request body: none
- Success response: `List<RestaurantResponse>`

Response example:

```json
[
  {
    "id": 1,
    "name": "string",
    "description": "string",
    "logoUrl": "https://example.com/image.jpg",
    "categories": [
      "string"
    ]
  }
]
```

#### `GET /api/restaurants/{restaurantId}`

Get restaurant details.

- Access: `Public`
- Path parameters: `restaurantId` (Long)
- Request body: none
- Success response: `RestaurantDetailsResponse`

Response example:

```json
{
  "id": 1,
  "name": "string",
  "description": "string",
  "logoUrl": "https://example.com/image.jpg",
  "menuSections": [
    {
      "id": 1,
      "name": "string",
      "description": "string",
      "menuItems": [
        {}
      ]
    }
  ]
}
```

### Search

#### `GET /api/search`

Search.

- Access: `Public`
- Query parameters: `search` (String, required)
- Request body: none
- Success response: `SearchResponse`

Response example:

```json
{
  "restaurants": [
    {
      "id": 1,
      "name": "string",
      "description": "string",
      "logoUrl": "https://example.com/image.jpg",
      "categories": [
        {}
      ]
    }
  ],
  "menuItems": [
    {
      "id": 1,
      "name": "string",
      "description": "string",
      "price": 12.5,
      "imageUrl": "https://example.com/image.jpg",
      "restaurantId": 1,
      "restaurantName": "string",
      "menuSectionId": 1,
      "menuSectionName": "string"
    }
  ]
}
```

## Schema reference

Required means the backend DTO declares `@NotNull`, `@NotBlank`, or `@NotEmpty`. Other fields may still be conditionally required by business rules.

### `AddCartItemRequest`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `menuItemId` | `Long` | Yes | > 0 (message = "Menu item ID must be positive") |
| `quantity` | `Integer` | Yes | > 0 (message = "Item quantity must be greater than zero") |
| `specialInstructions` | `String` | No | size (max = 500, message = "Special instructions must not exceed 500 characters") |
| `addons` | `>` | No | — |

```json
{
  "menuItemId": 1,
  "quantity": 1,
  "specialInstructions": "string",
  "addons": "string"
}
```

### `AddonGroupResponse`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `id` | `Long` | No | — |
| `name` | `String` | No | — |
| `minSelections` | `Integer` | No | — |
| `maxSelections` | `Integer` | No | — |
| `addons` | `List<MenuItemAddonResponse>` | No | — |

```json
{
  "id": 1,
  "name": "string",
  "minSelections": 1,
  "maxSelections": 1,
  "addons": [
    {
      "id": 1,
      "name": "string",
      "additionalPrice": 12.5
    }
  ]
}
```

### `AddressRequest`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `street` | `String` | Yes | size (max = 255, message = "Street must not exceed 255 characters") |
| `building` | `String` | No | size (max = 255, message = "Building must not exceed 255 characters") |
| `floor` | `String` | No | size (max = 255, message = "Floor must not exceed 255 characters") |
| `apartment` | `String` | No | size (max = 255, message = "Apartment must not exceed 255 characters") |
| `city` | `String` | Yes | size (max = 255, message = "City must not exceed 255 characters") |
| `governorateId` | `Long` | Yes | > 0 (message = "Governorate ID must be positive") |

```json
{
  "street": "string",
  "building": "string",
  "floor": "string",
  "apartment": "string",
  "city": "string",
  "governorateId": 1
}
```

### `AddressResponse`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `id` | `Long` | No | — |
| `street` | `String` | No | — |
| `building` | `String` | No | — |
| `floor` | `String` | No | — |
| `apartment` | `String` | No | — |
| `city` | `String` | No | — |
| `governorateId` | `Long` | No | — |
| `governorateName` | `String` | No | — |
| `isDefault` | `boolean` | No | — |

```json
{
  "id": 1,
  "street": "string",
  "building": "string",
  "floor": "string",
  "apartment": "string",
  "city": "string",
  "governorateId": 1,
  "governorateName": "string",
  "isDefault": true
}
```

### `AdminCategoryResponse`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `id` | `Long` | No | — |
| `name` | `String` | No | — |
| `description` | `String` | No | — |
| `active` | `Boolean` | No | — |

```json
{
  "id": 1,
  "name": "string",
  "description": "string",
  "active": true
}
```

### `AssignKitchenManagerRequest`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `email` | `String` | Yes | email (message = "Invalid email format") |
| `password` | `String` | Yes | size (min = 8, max = 100, message = "Password must be between 8 and 100 characters") |
| `name` | `String` | Yes | size (max = 100, message = "Name must not exceed 100 characters") |
| `phoneNumber` | `String` | No | size (max = 20, message = "Phone must not exceed 20 characters") |

```json
{
  "email": "user@example.com",
  "password": "StrongPass123",
  "name": "string",
  "phoneNumber": "string"
}
```

### `CancelKitchenOrderRequest`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `reason` | `String` | Yes | size (max = 255, message = "Cancellation reason must not exceed 255 characters") |

```json
{
  "reason": "string"
}
```

### `CancelOrderRequest`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `reason` | `String` | No | size (max = 255, message = "Cancellation reason must not exceed 255 characters") |

```json
{
  "reason": "string"
}
```

### `CartItemAddonRequest`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `menuItemAddonId` | `Long` | Yes | > 0 (message = "Add-on ID must be positive") |
| `quantity` | `Integer` | Yes | > 0 (message = "Add-on quantity must be greater than zero") |

```json
{
  "menuItemAddonId": 1,
  "quantity": 1
}
```

### `CartItemAddonResponse`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `menuItemAddonId` | `Long` | No | — |
| `name` | `String` | No | — |
| `addonGroupId` | `Long` | No | — |
| `addonGroupName` | `String` | No | — |
| `quantity` | `Integer` | No | — |
| `priceAtAddition` | `Double` | No | — |
| `totalPrice` | `Double` | No | — |

```json
{
  "menuItemAddonId": 1,
  "name": "string",
  "addonGroupId": 1,
  "addonGroupName": "string",
  "quantity": 1,
  "priceAtAddition": 12.5,
  "totalPrice": 12.5
}
```

### `CartItemResponse`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `id` | `Long` | No | — |
| `menuItemId` | `Long` | No | — |
| `menuItemName` | `String` | No | — |
| `imageUrl` | `String` | No | — |
| `basePrice` | `Double` | No | — |
| `quantity` | `Integer` | No | — |
| `specialInstructions` | `String` | No | — |
| `itemTotalPrice` | `Double` | No | — |
| `addons` | `List<CartItemAddonResponse>` | No | — |

```json
{
  "id": 1,
  "menuItemId": 1,
  "menuItemName": "string",
  "imageUrl": "https://example.com/image.jpg",
  "basePrice": 12.5,
  "quantity": 1,
  "specialInstructions": "string",
  "itemTotalPrice": 12.5,
  "addons": [
    {
      "menuItemAddonId": 1,
      "name": "string",
      "addonGroupId": 1,
      "addonGroupName": "string",
      "quantity": 1,
      "priceAtAddition": 12.5,
      "totalPrice": 12.5
    }
  ]
}
```

### `CartResponse`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `id` | `Long` | No | — |
| `restaurantId` | `Long` | No | — |
| `restaurantName` | `String` | No | — |
| `subtotal` | `Double` | No | — |
| `items` | `List<CartItemResponse>` | No | — |

```json
{
  "id": 1,
  "restaurantId": 1,
  "restaurantName": "string",
  "subtotal": 12.5,
  "items": [
    {
      "id": 1,
      "menuItemId": 1,
      "menuItemName": "string",
      "imageUrl": "https://example.com/image.jpg",
      "basePrice": 12.5,
      "quantity": 1,
      "specialInstructions": "string",
      "itemTotalPrice": 12.5,
      "addons": [
        {}
      ]
    }
  ]
}
```

### `CategoryRequest`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `name` | `String` | Yes | size (max = 100, message = "Category name must not exceed 100 characters") |
| `description` | `String` | No | size (max = 1000, message = "Category description must not exceed 1000 characters") |

```json
{
  "name": "string",
  "description": "string"
}
```

### `CategoryResponse`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `id` | `Long` | No | — |
| `name` | `String` | No | — |
| `description` | `String` | No | — |

```json
{
  "id": 1,
  "name": "string",
  "description": "string"
}
```

### `CreateAddonGroupRequest`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `name` | `String` | Yes | size (max = 100, message = "Name must not exceed 100 characters") |
| `minSelections` | `Integer` | Yes | min (value = 0, message = "Minimum selections must be at least 0") |
| `maxSelections` | `Integer` | Yes | min (value = 1, message = "Maximum selections must be at least 1"); max (value = 20, message = "Maximum selections must not exceed 20") |

```json
{
  "name": "string",
  "minSelections": 1,
  "maxSelections": 1
}
```

### `CreateAddonRequest`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `name` | `String` | Yes | size (max = 100, message = "Name must not exceed 100 characters") |
| `additionalPrice` | `Double` | Yes | min (value = 0, message = "Additional price must be non-negative"); max (value = 999999, message = "Additional price must not exceed 999999") |
| `available` | `Boolean` | No | — |

```json
{
  "name": "string",
  "additionalPrice": 12.5,
  "available": true
}
```

### `CreateDeliveryFeedbackRequest`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `rating` | `Integer` | Yes | min (value = 1, message = "Rating must be between 1 and 5"); max (value = 5, message = "Rating must be between 1 and 5") |

```json
{
  "rating": 1
}
```

### `CreateMenuItemRequest`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `name` | `String` | Yes | size (max = 100, message = "Name must not exceed 100 characters") |
| `description` | `String` | No | size (max = 500, message = "Description must not exceed 500 characters") |
| `basePrice` | `Double` | Yes | min (value = 0, message = "Base price must be non-negative"); max (value = 999999, message = "Base price must not exceed 999999") |
| `imageUrl` | `String` | No | size (max = 255, message = "Image URL must not exceed 255 characters") |
| `menuSectionId` | `Long` | Yes | — |
| `available` | `Boolean` | No | — |
| `addonGroupIds` | `Long[]` | No | — |

```json
{
  "name": "string",
  "description": "string",
  "basePrice": 12.5,
  "imageUrl": "https://example.com/image.jpg",
  "menuSectionId": 1,
  "available": true,
  "addonGroupIds": [
    1
  ]
}
```

### `CreateMenuSectionRequest`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `name` | `String` | Yes | size (max = 100, message = "Name must not exceed 100 characters") |
| `description` | `String` | No | size (max = 500, message = "Description must not exceed 500 characters") |
| `active` | `Boolean` | No | — |

```json
{
  "name": "string",
  "description": "string",
  "active": true
}
```

### `CreateRestaurantRequest`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `name` | `String` | Yes | — |
| `phone` | `String` | Yes | — |
| `email` | `String` | Yes | — |
| `address` | `String` | Yes | — |
| `governorateId` | `Long` | Yes | — |
| `description` | `String` | No | — |
| `logoUrl` | `String` | No | — |
| `deliveryFee` | `BigDecimal` | Yes | — |
| `categoryIds` | `List<Long>` | No | — |

```json
{
  "name": "string",
  "phone": "string",
  "email": "user@example.com",
  "address": "string",
  "governorateId": 1,
  "description": "string",
  "logoUrl": "https://example.com/image.jpg",
  "deliveryFee": 12.5,
  "categoryIds": [
    1
  ]
}
```

### `CustomerAdminResponse`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `id` | `Long` | No | — |
| `name` | `String` | No | — |
| `email` | `String` | No | — |
| `phoneNumber` | `String` | No | — |

```json
{
  "id": 1,
  "name": "string",
  "email": "user@example.com",
  "phoneNumber": "string"
}
```

### `CustomerOrderDetailsResponse`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `id` | `Long` | No | — |
| `restaurantName` | `String` | No | — |
| `deliveryAddress` | `String` | No | — |
| `subtotal` | `BigDecimal` | No | — |
| `deliveryFee` | `BigDecimal` | No | — |
| `totalPrice` | `BigDecimal` | No | — |
| `status` | `OrderStatus` | No | — |
| `paymentMethod` | `PaymentMethod` | No | — |
| `createdAt` | `LocalDateTime` | No | — |
| `orderItems` | `List<OrderItemResponse>` | No | — |

```json
{
  "id": 1,
  "restaurantName": "string",
  "deliveryAddress": "string",
  "subtotal": 12.5,
  "deliveryFee": 12.5,
  "totalPrice": 12.5,
  "status": "PENDING",
  "paymentMethod": "CASH",
  "createdAt": "2026-08-23T12:00:00",
  "orderItems": [
    {
      "productName": "string",
      "unitPrice": 12.5,
      "quantity": 1,
      "notes": "string",
      "addons": [
        {}
      ]
    }
  ]
}
```

### `CustomerOrderPageResponse`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `orders` | `List<CustomerOrderSummaryResponse>` | No | — |
| `page` | `int` | No | — |
| `size` | `int` | No | — |
| `totalElements` | `long` | No | — |
| `totalPages` | `int` | No | — |
| `first` | `boolean` | No | — |
| `last` | `boolean` | No | — |

```json
{
  "orders": [
    {
      "id": 1,
      "restaurantName": "string",
      "itemCount": 1,
      "totalPrice": 12.5,
      "status": "PENDING",
      "createdAt": "2026-08-23T12:00:00"
    }
  ],
  "page": 1,
  "size": 1,
  "totalElements": 1,
  "totalPages": 1,
  "first": true,
  "last": true
}
```

### `CustomerOrderSummaryResponse`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `id` | `Long` | No | — |
| `restaurantName` | `String` | No | — |
| `itemCount` | `Integer` | No | — |
| `totalPrice` | `BigDecimal` | No | — |
| `status` | `OrderStatus` | No | — |
| `createdAt` | `LocalDateTime` | No | — |

```json
{
  "id": 1,
  "restaurantName": "string",
  "itemCount": 1,
  "totalPrice": 12.5,
  "status": "PENDING",
  "createdAt": "2026-08-23T12:00:00"
}
```

### `CustomerProfileResponse`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `email` | `String` | No | — |
| `name` | `String` | No | — |
| `phoneNumber` | `String` | No | — |

```json
{
  "email": "user@example.com",
  "name": "string",
  "phoneNumber": "string"
}
```

### `CustomerProfileUpdateRequest`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `name` | `String` | No | size (min = 2, max = 50, message = "Name must be between 2 and 50 characters") |
| `phoneNumber` | `String` | No | — |

```json
{
  "name": "string",
  "phoneNumber": "string"
}
```

### `CustomerSignupRequest`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `regexp` | `` | No | — |
| `message` | `8,` | No | — |
| `name` | `String` | Yes | — |

```json
{
  "regexp": "string",
  "message": "string",
  "name": "string"
}
```

### `DeliveryFeedbackResponse`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `orderId` | `Long` | No | — |
| `rating` | `Integer` | No | — |
| `comment` | `String` | No | — |

```json
{
  "orderId": 1,
  "rating": 1,
  "comment": "string"
}
```

### `DriverProfileResponse`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `email` | `String` | No | — |
| `name` | `String` | No | — |
| `phoneNumber` | `String` | No | — |
| `vehicleType` | `String` | No | — |
| `licenseNumber` | `String` | No | — |
| `nationalId` | `String` | No | — |

```json
{
  "email": "user@example.com",
  "name": "string",
  "phoneNumber": "string",
  "vehicleType": "string",
  "licenseNumber": "string",
  "nationalId": "string"
}
```

### `DriverProfileUpdateRequest`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `name` | `String` | No | size (min = 2, max = 50, message = "Name must be between 2 and 50 characters") |
| `phoneNumber` | `String` | No | — |
| `vehicleType` | `String` | No | size (min = 2, max = 50, message = "Vehicle type must be between 2 and 50 characters") |
| `licenseNumber` | `String` | No | size (min = 2, max = 50, message = "License number must be between 2 and 50 characters") |
| `nationalId` | `String` | No | size (min = 10, max = 20, message = "National ID must be between 10 and 20 characters") |

```json
{
  "name": "string",
  "phoneNumber": "string",
  "vehicleType": "string",
  "licenseNumber": "string",
  "nationalId": "string"
}
```

### `DriverSignupRequest`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `regexp` | `` | No | — |
| `message` | `8,` | No | — |
| `name` | `String` | Yes | — |
| `phoneNumber` | `String` | Yes | — |
| `vehicleType` | `String` | Yes | — |
| `licenseNumber` | `String` | Yes | — |
| `nationalId` | `String` | Yes | — |

```json
{
  "regexp": "string",
  "message": "string",
  "name": "string",
  "phoneNumber": "string",
  "vehicleType": "string",
  "licenseNumber": "string",
  "nationalId": "string"
}
```

### `ForgotPasswordRequest`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `email` | `String` | Yes | email |

```json
{
  "email": "user@example.com"
}
```

### `KitchenAddonGroupResponse`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `id` | `Long` | No | — |
| `name` | `String` | No | — |
| `minSelections` | `Integer` | No | — |
| `maxSelections` | `Integer` | No | — |
| `addons` | `List<KitchenAddonResponse>` | No | — |

```json
{
  "id": 1,
  "name": "string",
  "minSelections": 1,
  "maxSelections": 1,
  "addons": [
    {
      "id": 1,
      "name": "string",
      "additionalPrice": 12.5,
      "available": true
    }
  ]
}
```

### `KitchenAddonResponse`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `id` | `Long` | No | — |
| `name` | `String` | No | — |
| `additionalPrice` | `Double` | No | — |
| `available` | `Boolean` | No | — |

```json
{
  "id": 1,
  "name": "string",
  "additionalPrice": 12.5,
  "available": true
}
```

### `KitchenDashboardSummaryResponse`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `totalOrdersToday` | `long` | No | — |
| `pendingOrders` | `long` | No | — |
| `confirmedOrders` | `long` | No | — |
| `preparingOrders` | `long` | No | — |
| `readyOrders` | `long` | No | — |
| `acceptedOrders` | `long` | No | — |
| `pickedUpOrders` | `long` | No | — |
| `deliveredOrders` | `long` | No | — |
| `cancelledOrders` | `long` | No | — |
| `delayedOrders` | `long` | No | — |

```json
{
  "totalOrdersToday": 1,
  "pendingOrders": 1,
  "confirmedOrders": 1,
  "preparingOrders": 1,
  "readyOrders": 1,
  "acceptedOrders": 1,
  "pickedUpOrders": 1,
  "deliveredOrders": 1,
  "cancelledOrders": 1,
  "delayedOrders": 1
}
```

### `KitchenMenuItemResponse`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `id` | `Long` | No | — |
| `name` | `String` | No | — |
| `basePrice` | `Double` | No | — |
| `available` | `Boolean` | No | — |
| `menuSectionId` | `Long` | No | — |
| `menuSectionName` | `String` | No | — |

```json
{
  "id": 1,
  "name": "string",
  "basePrice": 12.5,
  "available": true,
  "menuSectionId": 1,
  "menuSectionName": "string"
}
```

### `KitchenMenuSectionResponse`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `id` | `Long` | No | — |
| `name` | `String` | No | — |
| `description` | `String` | No | — |
| `active` | `Boolean` | No | — |
| `menuItems` | `List<KitchenMenuItemResponse>` | No | — |

```json
{
  "id": 1,
  "name": "string",
  "description": "string",
  "active": true,
  "menuItems": [
    {
      "id": 1,
      "name": "string",
      "basePrice": 12.5,
      "available": true,
      "menuSectionId": 1,
      "menuSectionName": "string"
    }
  ]
}
```

### `KitchenOrderDetailsResponse`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `id` | `Long` | No | — |
| `deliveryAddress` | `String` | No | — |
| `subtotal` | `BigDecimal` | No | — |
| `deliveryFee` | `BigDecimal` | No | — |
| `totalPrice` | `BigDecimal` | No | — |
| `status` | `OrderStatus` | No | — |
| `paymentMethod` | `PaymentMethod` | No | — |
| `createdAt` | `LocalDateTime` | No | — |
| `updatedAt` | `LocalDateTime` | No | — |
| `orderItems` | `List<OrderItemResponse>` | No | — |

```json
{
  "id": 1,
  "deliveryAddress": "string",
  "subtotal": 12.5,
  "deliveryFee": 12.5,
  "totalPrice": 12.5,
  "status": "PENDING",
  "paymentMethod": "CASH",
  "createdAt": "2026-08-23T12:00:00",
  "updatedAt": "2026-08-23T12:00:00",
  "orderItems": [
    {
      "productName": "string",
      "unitPrice": 12.5,
      "quantity": 1,
      "notes": "string",
      "addons": [
        {}
      ]
    }
  ]
}
```

### `KitchenOrderPageResponse`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `orders` | `List<KitchenOrderSummaryResponse>` | No | — |
| `page` | `int` | No | — |
| `size` | `int` | No | — |
| `totalElements` | `long` | No | — |
| `totalPages` | `int` | No | — |
| `first` | `boolean` | No | — |
| `last` | `boolean` | No | — |

```json
{
  "orders": [
    {
      "id": 1,
      "itemCount": 1,
      "totalPrice": 12.5,
      "status": "PENDING",
      "createdAt": "2026-08-23T12:00:00"
    }
  ],
  "page": 1,
  "size": 1,
  "totalElements": 1,
  "totalPages": 1,
  "first": true,
  "last": true
}
```

### `KitchenOrderSummaryResponse`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `id` | `Long` | No | — |
| `itemCount` | `Integer` | No | — |
| `totalPrice` | `BigDecimal` | No | — |
| `status` | `OrderStatus` | No | — |
| `createdAt` | `LocalDateTime` | No | — |

```json
{
  "id": 1,
  "itemCount": 1,
  "totalPrice": 12.5,
  "status": "PENDING",
  "createdAt": "2026-08-23T12:00:00"
}
```

### `LoginRequest`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `email` | `String` | Yes | email (message = "Email must be valid") |
| `password` | `String` | Yes | — |

```json
{
  "email": "user@example.com",
  "password": "StrongPass123"
}
```

### `LoginResponse`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `message` | `String` | No | — |
| `accessToken` | `String` | No | — |
| `tokenType` | `String` | No | — |
| `expiresIn` | `long` | No | — |
| `userId` | `Long` | No | — |
| `email` | `String` | No | — |
| `role` | `Role` | No | — |

```json
{
  "message": "string",
  "accessToken": "string",
  "tokenType": "string",
  "expiresIn": 1,
  "userId": 1,
  "email": "user@example.com",
  "role": "string"
}
```

### `MenuItemAddonResponse`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `id` | `Long` | No | — |
| `name` | `String` | No | — |
| `additionalPrice` | `Double` | No | — |

```json
{
  "id": 1,
  "name": "string",
  "additionalPrice": 12.5
}
```

### `MenuItemDetailsResponse`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `id` | `Long` | No | — |
| `name` | `String` | No | — |
| `description` | `String` | No | — |
| `basePrice` | `Double` | No | — |
| `imageUrl` | `String` | No | — |
| `restaurantId` | `Long` | No | — |
| `menuSectionId` | `Long` | No | — |
| `addonGroups` | `List<AddonGroupResponse>` | No | — |

```json
{
  "id": 1,
  "name": "string",
  "description": "string",
  "basePrice": 12.5,
  "imageUrl": "https://example.com/image.jpg",
  "restaurantId": 1,
  "menuSectionId": 1,
  "addonGroups": [
    {
      "id": 1,
      "name": "string",
      "minSelections": 1,
      "maxSelections": 1,
      "addons": [
        {}
      ]
    }
  ]
}
```

### `MenuItemResponse`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `id` | `Long` | No | — |
| `name` | `String` | No | — |
| `description` | `String` | No | — |
| `basePrice` | `Double` | No | — |
| `imageUrl` | `String` | No | — |

```json
{
  "id": 1,
  "name": "string",
  "description": "string",
  "basePrice": 12.5,
  "imageUrl": "https://example.com/image.jpg"
}
```

### `MenuItemSearchResponse`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `id` | `Long` | No | — |
| `name` | `String` | No | — |
| `description` | `String` | No | — |
| `price` | `Double` | No | — |
| `imageUrl` | `String` | No | — |
| `restaurantId` | `Long` | No | — |
| `restaurantName` | `String` | No | — |
| `menuSectionId` | `Long` | No | — |
| `menuSectionName` | `String` | No | — |

```json
{
  "id": 1,
  "name": "string",
  "description": "string",
  "price": 12.5,
  "imageUrl": "https://example.com/image.jpg",
  "restaurantId": 1,
  "restaurantName": "string",
  "menuSectionId": 1,
  "menuSectionName": "string"
}
```

### `MenuSectionResponse`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `id` | `Long` | No | — |
| `name` | `String` | No | — |
| `description` | `String` | No | — |
| `menuItems` | `List<MenuItemResponse>` | No | — |

```json
{
  "id": 1,
  "name": "string",
  "description": "string",
  "menuItems": [
    {
      "id": 1,
      "name": "string",
      "description": "string",
      "basePrice": 12.5,
      "imageUrl": "https://example.com/image.jpg"
    }
  ]
}
```

### `OrderAddonResponse`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `addonName` | `String` | No | — |
| `addonPrice` | `BigDecimal` | No | — |
| `quantity` | `Integer` | No | — |

```json
{
  "addonName": "string",
  "addonPrice": 12.5,
  "quantity": 1
}
```

### `OrderAdminResponse`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `id` | `Long` | No | — |
| `status` | `String` | No | — |
| `restaurantName` | `String` | No | — |
| `customerName` | `String` | No | — |
| `riderName` | `String` | No | — |
| `totalPrice` | `BigDecimal` | No | — |
| `deliveryFee` | `BigDecimal` | No | — |
| `updatedAt` | `LocalDateTime` | No | — |

```json
{
  "id": 1,
  "status": "PENDING",
  "restaurantName": "string",
  "customerName": "string",
  "riderName": "string",
  "totalPrice": 12.5,
  "deliveryFee": 12.5,
  "updatedAt": "2026-08-23T12:00:00"
}
```

### `OrderHistoryResponse`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `id` | `Long` | No | — |
| `restaurantName` | `String` | No | — |
| `deliveredAt` | `LocalDateTime` | No | — |
| `earnings` | `BigDecimal` | No | — |
| `orderTotal` | `BigDecimal` | No | — |

```json
{
  "id": 1,
  "restaurantName": "string",
  "deliveredAt": "2026-08-23T12:00:00",
  "earnings": 12.5,
  "orderTotal": 12.5
}
```

### `OrderItemResponse`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `productName` | `String` | No | — |
| `unitPrice` | `BigDecimal` | No | — |
| `quantity` | `Integer` | No | — |
| `notes` | `String` | No | — |
| `addons` | `List<OrderAddonResponse>` | No | — |

```json
{
  "productName": "string",
  "unitPrice": 12.5,
  "quantity": 1,
  "notes": "string",
  "addons": [
    {
      "addonName": "string",
      "addonPrice": 12.5,
      "quantity": 1
    }
  ]
}
```

### `OrderSummaryresponse`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `id` | `Long` | No | — |
| `restaurantName` | `String` | No | — |
| `deliveryAddress` | `String` | No | — |
| `itemCount` | `int` | No | — |
| `totalPrice` | `BigDecimal` | No | — |
| `deliveryFee` | `BigDecimal` | No | — |

```json
{
  "id": 1,
  "restaurantName": "string",
  "deliveryAddress": "string",
  "itemCount": 1,
  "totalPrice": 12.5,
  "deliveryFee": 12.5
}
```

### `PlaceOrderRequest`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `addressId` | `Long` | Yes | > 0 (message = "Delivery address ID must be positive") |
| `paymentMethod` | `PaymentMethod` | Yes | — |

```json
{
  "addressId": 1,
  "paymentMethod": "CASH"
}
```

### `PlaceOrderResponse`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `id` | `Long` | No | — |
| `restaurantName` | `String` | No | — |
| `deliveryAddress` | `String` | No | — |
| `subtotal` | `BigDecimal` | No | — |
| `deliveryFee` | `BigDecimal` | No | — |
| `totalPrice` | `BigDecimal` | No | — |
| `createdAt` | `LocalDateTime` | No | — |
| `status` | `OrderStatus` | No | — |
| `paymentMethod` | `PaymentMethod` | No | — |
| `orderItems` | `List<OrderItemResponse>` | No | — |

```json
{
  "id": 1,
  "restaurantName": "string",
  "deliveryAddress": "string",
  "subtotal": 12.5,
  "deliveryFee": 12.5,
  "totalPrice": 12.5,
  "createdAt": "2026-08-23T12:00:00",
  "status": "PENDING",
  "paymentMethod": "CASH",
  "orderItems": [
    {
      "productName": "string",
      "unitPrice": 12.5,
      "quantity": 1,
      "notes": "string",
      "addons": [
        {}
      ]
    }
  ]
}
```

### `ProfileResponse`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `userId` | `Long` | No | — |
| `email` | `String` | No | — |
| `role` | `Role` | No | — |
| `name` | `String` | No | — |
| `phoneNumber` | `Long` | No | — |
| `loyaltyPoints` | `Integer` | No | — |
| `vehicleType` | `String` | No | — |
| `licenseNumber` | `String` | No | — |
| `nationalId` | `String` | No | — |
| `online` | `Boolean` | No | — |

```json
{
  "userId": 1,
  "email": "user@example.com",
  "role": "string",
  "name": "string",
  "phoneNumber": 1,
  "loyaltyPoints": 1,
  "vehicleType": "string",
  "licenseNumber": "string",
  "nationalId": "string",
  "online": true
}
```

### `RegisterResponse`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `message` | `String` | No | — |

```json
{
  "message": "string"
}
```

### `ReplaceCartItemRequest`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `quantity` | `Integer` | Yes | > 0 (message = "Item quantity must be greater than zero") |
| `specialInstructions` | `String` | No | size (max = 500, message = "Special instructions must not exceed 500 characters") |
| `addons` | `>` | No | — |

```json
{
  "quantity": 1,
  "specialInstructions": "string",
  "addons": "string"
}
```

### `ResetPasswordRequest`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `email` | `String` | Yes | email |
| `otp` | `String` | Yes | — |
| `newPassword` | `String` | Yes | size (min = 8) |

```json
{
  "email": "user@example.com",
  "otp": "123456",
  "newPassword": "StrongPass123"
}
```

### `RestaurantAdminResponse`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `id` | `Long` | No | — |
| `name` | `String` | No | — |
| `phone` | `String` | No | — |
| `email` | `String` | No | — |
| `address` | `String` | No | — |
| `governorateName` | `String` | No | — |
| `description` | `String` | No | — |
| `logoUrl` | `String` | No | — |
| `isActive` | `Boolean` | No | — |
| `deliveryFee` | `BigDecimal` | No | — |
| `categoryNames` | `List<String>` | No | — |

```json
{
  "id": 1,
  "name": "string",
  "phone": "string",
  "email": "user@example.com",
  "address": "string",
  "governorateName": "string",
  "description": "string",
  "logoUrl": "https://example.com/image.jpg",
  "isActive": true,
  "deliveryFee": 12.5,
  "categoryNames": [
    "string"
  ]
}
```

### `RestaurantDetailsResponse`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `id` | `Long` | No | — |
| `name` | `String` | No | — |
| `description` | `String` | No | — |
| `logoUrl` | `String` | No | — |
| `menuSections` | `List<MenuSectionResponse>` | No | — |

```json
{
  "id": 1,
  "name": "string",
  "description": "string",
  "logoUrl": "https://example.com/image.jpg",
  "menuSections": [
    {
      "id": 1,
      "name": "string",
      "description": "string",
      "menuItems": [
        {}
      ]
    }
  ]
}
```

### `RestaurantResponse`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `id` | `Long` | No | — |
| `name` | `String` | No | — |
| `description` | `String` | No | — |
| `logoUrl` | `String` | No | — |
| `categories` | `List<String>` | No | — |

```json
{
  "id": 1,
  "name": "string",
  "description": "string",
  "logoUrl": "https://example.com/image.jpg",
  "categories": [
    "string"
  ]
}
```

### `RiderAdminResponse`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `id` | `Long` | No | — |
| `name` | `String` | No | — |
| `phoneNumber` | `String` | No | — |
| `vehicleType` | `String` | No | — |
| `licenseNumber` | `String` | No | — |
| `nationalId` | `String` | No | — |
| `approvalStatus` | `String` | No | — |
| `online` | `Boolean` | No | — |
| `isActive` | `Boolean` | No | — |

```json
{
  "id": 1,
  "name": "string",
  "phoneNumber": "string",
  "vehicleType": "string",
  "licenseNumber": "string",
  "nationalId": "string",
  "approvalStatus": "PENDING",
  "online": true,
  "isActive": true
}
```

### `SearchResponse`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `restaurants` | `List<RestaurantResponse>` | No | — |
| `menuItems` | `List<MenuItemSearchResponse>` | No | — |

```json
{
  "restaurants": [
    {
      "id": 1,
      "name": "string",
      "description": "string",
      "logoUrl": "https://example.com/image.jpg",
      "categories": [
        {}
      ]
    }
  ],
  "menuItems": [
    {
      "id": 1,
      "name": "string",
      "description": "string",
      "price": 12.5,
      "imageUrl": "https://example.com/image.jpg",
      "restaurantId": 1,
      "restaurantName": "string",
      "menuSectionId": 1,
      "menuSectionName": "string"
    }
  ]
}
```

### `UpdateAddonGroupRequest`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `name` | `String` | No | size (max = 100, message = "Name must not exceed 100 characters") |
| `minSelections` | `Integer` | No | min (value = 0, message = "Minimum selections must be at least 0") |
| `maxSelections` | `Integer` | No | min (value = 1, message = "Maximum selections must be at least 1"); max (value = 20, message = "Maximum selections must not exceed 20") |

```json
{
  "name": "string",
  "minSelections": 1,
  "maxSelections": 1
}
```

### `UpdateAddonRequest`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `name` | `String` | No | size (max = 100, message = "Name must not exceed 100 characters") |
| `additionalPrice` | `Double` | No | min (value = 0, message = "Additional price must be non-negative"); max (value = 999999, message = "Additional price must not exceed 999999") |
| `available` | `Boolean` | No | — |

```json
{
  "name": "string",
  "additionalPrice": 12.5,
  "available": true
}
```

### `UpdateCartItemQuantityRequest`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `quantity` | `Integer` | Yes | > 0 (message = "Item quantity must be greater than zero") |

```json
{
  "quantity": 1
}
```

### `UpdateKitchenOrderStatusRequest`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `status` | `OrderStatus` | Yes | — |

```json
{
  "status": "PENDING"
}
```

### `UpdateMenuItemAvailabilityRequest`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `available` | `Boolean` | Yes | — |

```json
{
  "available": true
}
```

### `UpdateMenuItemRequest`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `name` | `String` | No | size (max = 100, message = "Name must not exceed 100 characters") |
| `description` | `String` | No | size (max = 500, message = "Description must not exceed 500 characters") |
| `basePrice` | `Double` | No | min (value = 0, message = "Base price must be non-negative"); max (value = 999999, message = "Base price must not exceed 999999") |
| `imageUrl` | `String` | No | size (max = 255, message = "Image URL must not exceed 255 characters") |
| `menuSectionId` | `Long` | No | — |
| `available` | `Boolean` | No | — |
| `addonGroupIds` | `Long[]` | No | — |

```json
{
  "name": "string",
  "description": "string",
  "basePrice": 12.5,
  "imageUrl": "https://example.com/image.jpg",
  "menuSectionId": 1,
  "available": true,
  "addonGroupIds": [
    1
  ]
}
```

### `UpdateMenuSectionRequest`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `name` | `String` | No | size (max = 100, message = "Name must not exceed 100 characters") |
| `description` | `String` | No | size (max = 500, message = "Description must not exceed 500 characters") |
| `active` | `Boolean` | No | — |

```json
{
  "name": "string",
  "description": "string",
  "active": true
}
```

### `UpdateProfileRequest`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| — | — | — | No serializable fields detected |

```json
{}
```

### `UpdateRestaurantRequest`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `name` | `String` | No | — |
| `phone` | `String` | No | — |
| `email` | `String` | No | — |
| `address` | `String` | No | — |
| `governorateId` | `Long` | No | — |
| `description` | `String` | No | — |
| `logoUrl` | `String` | No | — |
| `deliveryFee` | `BigDecimal` | No | — |
| `categoryIds` | `List<Long>` | No | — |

```json
{
  "name": "string",
  "phone": "string",
  "email": "user@example.com",
  "address": "string",
  "governorateId": 1,
  "description": "string",
  "logoUrl": "https://example.com/image.jpg",
  "deliveryFee": 12.5,
  "categoryIds": [
    1
  ]
}
```

### `UpdateStatusRequest`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `online` | `boolean` | No | — |

```json
{
  "online": true
}
```

### `VerifyOtpRequest`

| Field | JSON type / Java type | Required | Validation |
|---|---|---:|---|
| `email` | `String` | Yes | email (message = "Invalid email format") |
| `otp` | `String` | Yes | — |

```json
{
  "email": "user@example.com",
  "otp": "123456"
}
```

## Enums

- `Role`: `CUSTOMER`, `DRIVER`, `ADMIN`, `KITCHEN_MANAGER`
- `OrderStatus`: `PENDING`, `CONFIRMED`, `PREPARING`, `READY`, `ACCEPTED`, `PICKED_UP`, `DELIVERED`, `CANCELLED`
- `PaymentMethod`: `CASH`, `CREDIT_CARD`, `WALLET`
- `ApprovalStatus`: `PENDING`, `APPROVED`, `REJECTED`

## Live Swagger

When the backend is running: Swagger UI is at `/swagger-ui/index.html` and raw OpenAPI JSON is at `/v3/api-docs`.
