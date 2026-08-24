import { Injectable, signal } from '@angular/core';
import { Observable } from 'rxjs';
import {
  AddressRequest,
  AddressResponse,
  CustomerProfileResponse,
  CustomerProfileUpdateRequest,
} from '../features/account/models/account.models';
import {
  AdminCategoryResponse,
  AssignKitchenManagerRequest,
  CategoryRequest,
  CreateRestaurantRequest,
  CustomerAdminResponse,
  DeliveryFeedbackResponse as AdminFeedback,
  OrderAdminResponse,
  RestaurantAdminResponse,
  RiderAdminResponse,
} from '../features/admin/models/admin.models';
import { LoginRequest, LoginResponse } from '../features/auth/models/login.model';
import { RegisterResponse, VerifyOtpRequest } from '../features/auth/models/otp.model';
import {
  AddCartItemRequest,
  CartItemAddonResponse,
  CartItemResponse,
  CartResponse,
  ReplaceCartItemRequest,
} from '../features/cart/models/cart.models';
import {
  DeliveryFeedbackResponse,
  DriverProfileResponse,
  DriverProfileUpdateRequest,
  OrderHistoryResponse,
  OrderSummaryResponse,
} from '../features/driver/models/driver.models';
import {
  CustomerOrderDetailsResponse,
  CustomerOrderPageResponse,
  DeliveryFeedbackResponse as CustomerFeedback,
  OrderStatus,
  PlaceOrderRequest,
  PlaceOrderResponse,
} from '../features/orders/models/order.models';
import {
  CreateAddonGroupRequest,
  CreateAddonRequest,
  CreateMenuItemRequest,
  CreateMenuSectionRequest,
  KitchenAddonGroupResponse,
  KitchenAddonResponse,
  KitchenDashboardSummaryResponse,
  KitchenMenuItemResponse,
  KitchenMenuSectionResponse,
  KitchenOrderDetailsResponse,
  KitchenOrderPageResponse,
  KitchenOrderSummaryResponse,
  UpdateAddonGroupRequest,
  UpdateAddonRequest,
  UpdateMenuItemRequest,
  UpdateMenuSectionRequest,
} from '../features/restaurant-portal/models/kitchen.models';
import {
  CategoryResponse,
  MenuItemDetailsResponse,
  RestaurantDetailsResponse,
  RestaurantResponse,
} from '../features/restaurants/models/restaurant.models';
import { SearchResponse } from '../features/search/models/search.models';
import { MOCK_ACCOUNTS, MOCK_OTP, MOCK_PASSWORD } from './mock-auth.data';
import {
  MOCK_CATEGORIES,
  MOCK_MENU_ITEM_DETAILS,
  MOCK_RESTAURANTS,
  MOCK_RESTAURANT_CATEGORY_IDS,
  MOCK_RESTAURANT_DETAILS,
} from './mock-catalog.data';
import {
  INITIAL_MOCK_CART,
  MOCK_ADDRESSES,
  MOCK_CUSTOMER_ORDERS,
  MOCK_CUSTOMER_PROFILE,
} from './mock-customer.data';
import {
  MOCK_ACTIVE_DELIVERY,
  MOCK_ADMIN_CATEGORIES,
  MOCK_ADMIN_CUSTOMERS,
  MOCK_ADMIN_FEEDBACK,
  MOCK_ADMIN_ORDERS,
  MOCK_ADMIN_RESTAURANTS,
  MOCK_AVAILABLE_DELIVERIES,
  MOCK_DELIVERY_HISTORY,
  MOCK_DRIVER_FEEDBACK,
  MOCK_DRIVER_PROFILE,
  MOCK_KITCHEN_GROUPS,
  MOCK_KITCHEN_ITEMS,
  MOCK_KITCHEN_ORDERS,
  MOCK_KITCHEN_SECTIONS,
  MOCK_RIDERS,
} from './mock-operations.data';
import { cloneMock, mockHttpError, mockResponse } from './mock-response.util';

@Injectable({ providedIn: 'root' })
export class MockDataStore {
  private categoriesState = signal<CategoryResponse[]>(cloneMock(MOCK_CATEGORIES));
  private restaurantsState = signal<RestaurantResponse[]>(cloneMock(MOCK_RESTAURANTS));
  private restaurantDetailsState = signal<RestaurantDetailsResponse[]>(
    cloneMock(MOCK_RESTAURANT_DETAILS),
  );
  private menuDetailsState = signal<MenuItemDetailsResponse[]>(cloneMock(MOCK_MENU_ITEM_DETAILS));
  private restaurantCategoryIdsState = signal<Record<number, number[]>>(
    Object.fromEntries(
      Object.entries(MOCK_RESTAURANT_CATEGORY_IDS).map(([id, categoryIds]) => [
        Number(id),
        [...categoryIds],
      ]),
    ),
  );
  private cartState = signal<CartResponse | null>(cloneMock(INITIAL_MOCK_CART));
  private addressesState = signal<AddressResponse[]>(cloneMock(MOCK_ADDRESSES));
  private profileState = signal<CustomerProfileResponse>(cloneMock(MOCK_CUSTOMER_PROFILE));
  private ordersState = signal<CustomerOrderDetailsResponse[]>(cloneMock(MOCK_CUSTOMER_ORDERS));
  private customerFeedbackState = signal<CustomerFeedback[]>([]);
  private availableState = signal<OrderSummaryResponse[]>(cloneMock(MOCK_AVAILABLE_DELIVERIES));
  private activeState = signal<OrderSummaryResponse | null>(cloneMock(MOCK_ACTIVE_DELIVERY));
  private deliveryHistoryState = signal<OrderHistoryResponse[]>(cloneMock(MOCK_DELIVERY_HISTORY));
  private driverFeedbackState = signal<DeliveryFeedbackResponse[]>(cloneMock(MOCK_DRIVER_FEEDBACK));
  private driverProfileState = signal<DriverProfileResponse>(cloneMock(MOCK_DRIVER_PROFILE));
  private driverOnlineState = signal(true);
  private kitchenOrdersState = signal<KitchenOrderDetailsResponse[]>(
    cloneMock(MOCK_KITCHEN_ORDERS),
  );
  private kitchenItemsState = signal<KitchenMenuItemResponse[]>(cloneMock(MOCK_KITCHEN_ITEMS));
  private kitchenSectionsState = signal<KitchenMenuSectionResponse[]>(
    cloneMock(MOCK_KITCHEN_SECTIONS),
  );
  private kitchenGroupsState = signal<KitchenAddonGroupResponse[]>(cloneMock(MOCK_KITCHEN_GROUPS));
  private adminCategoriesState = signal<AdminCategoryResponse[]>(cloneMock(MOCK_ADMIN_CATEGORIES));
  private adminCustomersState = signal<CustomerAdminResponse[]>(cloneMock(MOCK_ADMIN_CUSTOMERS));
  private adminRestaurantsState = signal<RestaurantAdminResponse[]>(
    cloneMock(MOCK_ADMIN_RESTAURANTS),
  );
  private adminOrdersState = signal<OrderAdminResponse[]>(cloneMock(MOCK_ADMIN_ORDERS));
  private ridersState = signal<RiderAdminResponse[]>(cloneMock(MOCK_RIDERS));
  private adminFeedbackState = signal<AdminFeedback[]>(cloneMock(MOCK_ADMIN_FEEDBACK));
  private nextCartItemId = 10;
  private nextAddressId = 10;
  private nextOrderId = 1100;
  private nextMenuItemId = 1000;
  private nextSectionId = 100;
  private nextGroupId = 100;
  private nextAddonId = 1000;
  private nextCategoryId = 100;
  private nextRestaurantId = 100;

  login(request: LoginRequest): Observable<LoginResponse> {
    return mockResponse(() => {
      const account = MOCK_ACCOUNTS.find(
        (entry) => entry.email.toLowerCase() === request.email.trim().toLowerCase(),
      );
      if (!account || request.password !== MOCK_PASSWORD)
        throw mockHttpError(401, 'Invalid email or password.');
      return {
        message: 'Login successful',
        accessToken: `mock-access-token-${account.role.toLowerCase()}`,
        tokenType: 'Bearer',
        expiresIn: 3600,
        userId: account.userId,
        email: account.email,
        role: account.role,
      } satisfies LoginResponse;
    });
  }
  register(): Observable<RegisterResponse> {
    return mockResponse(() => ({ message: 'Registration successful. Use OTP 123456.' }));
  }
  verifyOtp(request: VerifyOtpRequest): Observable<string> {
    return mockResponse(() => {
      if (request.otp !== MOCK_OTP) throw mockHttpError(400, 'Invalid mock OTP.');
      return 'OTP verified successfully';
    });
  }
  authMessage(message: string): Observable<string> {
    return mockResponse(() => message);
  }

  categories(): Observable<CategoryResponse[]> {
    return mockResponse(() => this.categoriesState());
  }
  restaurants(categoryId?: number): Observable<RestaurantResponse[]> {
    return mockResponse(() =>
      categoryId
        ? this.restaurantsState().filter((item) =>
            this.restaurantCategoryIdsState()[item.id]?.includes(categoryId),
          )
        : this.restaurantsState(),
    );
  }
  restaurant(id: number): Observable<RestaurantDetailsResponse> {
    return mockResponse(() =>
      this.require(
        this.restaurantDetailsState().find((item) => item.id === id),
        'Restaurant',
      ),
    );
  }
  menuItem(id: number): Observable<MenuItemDetailsResponse> {
    return mockResponse(() =>
      this.require(
        this.menuDetailsState().find((item) => item.id === id),
        'Menu item',
      ),
    );
  }
  search(query: string): Observable<SearchResponse> {
    return mockResponse(() => {
      const term = query.trim().toLowerCase();
      if (!term) return { restaurants: this.restaurantsState(), menuItems: [] };
      const categoriesByRestaurant = this.restaurantsState();
      const restaurants = categoriesByRestaurant.filter((item) =>
        [item.name, item.description, ...item.categories].some((value) =>
          value.toLowerCase().includes(term),
        ),
      );
      const menuItems = this.menuDetailsState()
        .filter((item) => `${item.name} ${item.description}`.toLowerCase().includes(term))
        .map((item) => {
          const restaurant = this.require(
            this.restaurantsState().find((entry) => entry.id === item.restaurantId),
            'Restaurant',
          );
          const section = this.require(
            this.restaurantDetailsState()
              .find((entry) => entry.id === item.restaurantId)
              ?.menuSections.find((entry) => entry.id === item.menuSectionId),
            'Menu section',
          );
          return {
            id: item.id,
            name: item.name,
            description: item.description,
            price: item.basePrice,
            imageUrl: item.imageUrl,
            restaurantId: item.restaurantId,
            restaurantName: restaurant.name,
            menuSectionId: item.menuSectionId,
            menuSectionName: section.name,
          };
        });
      return { restaurants, menuItems };
    });
  }

  cart(): Observable<CartResponse> {
    return mockResponse(() => this.emptyCartIfNeeded());
  }
  addCartItem(request: AddCartItemRequest): Observable<CartResponse> {
    return mockResponse(() => {
      const item = this.require(
        this.menuDetailsState().find((entry) => entry.id === request.menuItemId),
        'Menu item',
      );
      const restaurant = this.require(
        this.restaurantsState().find((entry) => entry.id === item.restaurantId),
        'Restaurant',
      );
      let cart = this.cartState();
      if (!cart || cart.restaurantId !== item.restaurantId)
        cart = {
          id: 1,
          restaurantId: item.restaurantId,
          restaurantName: restaurant.name,
          subtotal: 0,
          items: [],
        };
      const cartItem = this.makeCartItem(
        this.nextCartItemId++,
        item,
        request.quantity,
        request.specialInstructions,
        request.addons,
      );
      const updated = this.repriceCart({ ...cart, items: [...cart.items, cartItem] });
      this.cartState.set(updated);
      return updated;
    });
  }
  cartQuantity(id: number, quantity: number): Observable<CartResponse> {
    return mockResponse(() => this.updateCartItem(id, { quantity }));
  }
  replaceCartItem(id: number, request: ReplaceCartItemRequest): Observable<CartResponse> {
    return mockResponse(() => this.updateCartItem(id, request));
  }
  removeCartItem(id: number): Observable<CartResponse> {
    return mockResponse(() => {
      const cart = this.emptyCartIfNeeded();
      this.require(
        cart.items.find((item) => item.id === id),
        'Cart item',
      );
      const updated = this.repriceCart({
        ...cart,
        items: cart.items.filter((item) => item.id !== id),
      });
      this.cartState.set(updated);
      return updated;
    });
  }
  clearCart(): Observable<void> {
    return mockResponse(() => {
      this.cartState.set(null);
    });
  }

  addresses(): Observable<AddressResponse[]> {
    return mockResponse(() => this.addressesState());
  }
  address(id: number): Observable<AddressResponse> {
    return mockResponse(() =>
      this.require(
        this.addressesState().find((item) => item.id === id),
        'Address',
      ),
    );
  }
  createAddress(request: AddressRequest): Observable<AddressResponse> {
    return mockResponse(() => {
      const created = {
        ...request,
        id: this.nextAddressId++,
        governorateName: this.governorateName(request.governorateId),
        isDefault: this.addressesState().length === 0,
      } satisfies AddressResponse;
      this.addressesState.update((items) => [...items, created]);
      return created;
    });
  }
  updateAddress(id: number, request: AddressRequest): Observable<AddressResponse> {
    return mockResponse(() => {
      const current = this.require(
        this.addressesState().find((item) => item.id === id),
        'Address',
      );
      const updated = {
        ...request,
        id,
        governorateName: this.governorateName(request.governorateId),
        isDefault: current.isDefault,
      } satisfies AddressResponse;
      this.addressesState.update((items) => items.map((item) => (item.id === id ? updated : item)));
      return updated;
    });
  }
  setDefaultAddress(id: number): Observable<AddressResponse> {
    return mockResponse(() => {
      this.require(
        this.addressesState().find((item) => item.id === id),
        'Address',
      );
      this.addressesState.update((items) =>
        items.map((item) => ({ ...item, isDefault: item.id === id })),
      );
      return this.require(
        this.addressesState().find((item) => item.id === id),
        'Address',
      );
    });
  }
  deleteAddress(id: number): Observable<void> {
    return mockResponse(() => {
      const target = this.require(
        this.addressesState().find((item) => item.id === id),
        'Address',
      );
      const remaining = this.addressesState().filter((item) => item.id !== id);
      if (target.isDefault && remaining.length) remaining[0] = { ...remaining[0], isDefault: true };
      this.addressesState.set(remaining);
    });
  }
  profile(): Observable<CustomerProfileResponse> {
    return mockResponse(() => this.profileState());
  }
  updateProfile(request: CustomerProfileUpdateRequest): Observable<void> {
    return mockResponse(() => {
      this.profileState.update((profile) => ({ ...profile, ...request }));
    });
  }

  orders(page = 0, size = 10): Observable<CustomerOrderPageResponse> {
    return mockResponse(() => {
      const all = this.ordersState();
      const start = page * size;
      const orders = all.slice(start, start + size).map((order) => ({
        id: order.id,
        restaurantName: order.restaurantName,
        itemCount: order.orderItems.reduce((sum, item) => sum + item.quantity, 0),
        totalPrice: order.totalPrice,
        status: order.status,
        createdAt: order.createdAt,
      }));
      const totalPages = Math.ceil(all.length / size);
      return {
        orders,
        page,
        size,
        totalElements: all.length,
        totalPages,
        first: page === 0,
        last: totalPages === 0 || page >= totalPages - 1,
      };
    });
  }
  order(id: number): Observable<CustomerOrderDetailsResponse> {
    return mockResponse(() =>
      this.require(
        this.ordersState().find((item) => item.id === id),
        'Order',
      ),
    );
  }
  placeOrder(request: PlaceOrderRequest): Observable<PlaceOrderResponse> {
    return mockResponse(() => {
      const cart = this.cartState();
      if (!cart?.items.length) throw mockHttpError(409, 'Your cart is empty.');
      const address = this.require(
        this.addressesState().find((item) => item.id === request.addressId),
        'Address',
      );
      const created: CustomerOrderDetailsResponse = {
        id: this.nextOrderId++,
        restaurantName: cart.restaurantName,
        deliveryAddress: `${address.street}, ${address.city}`,
        subtotal: cart.subtotal,
        deliveryFee: 20,
        totalPrice: cart.subtotal + 20,
        status: 'PENDING',
        paymentMethod: request.paymentMethod,
        createdAt: new Date().toISOString(),
        orderItems: cart.items.map((item) => ({
          productName: item.menuItemName,
          unitPrice: item.basePrice,
          quantity: item.quantity,
          notes: item.specialInstructions,
          addons: item.addons.map((addon) => ({
            addonName: addon.name,
            addonPrice: addon.priceAtAddition,
            quantity: addon.quantity,
          })),
        })),
      };
      this.ordersState.update((items) => [created, ...items]);
      this.adminOrdersState.update((items) => [
        {
          id: created.id,
          status: created.status,
          restaurantName: created.restaurantName,
          customerName: this.profileState().name,
          riderName: null,
          totalPrice: created.totalPrice,
          deliveryFee: created.deliveryFee,
          updatedAt: created.createdAt,
        },
        ...items,
      ]);
      this.cartState.set(null);
      return created;
    });
  }
  cancelOrder(id: number): Observable<CustomerOrderDetailsResponse> {
    return mockResponse(() => {
      const order = this.require(
        this.ordersState().find((item) => item.id === id),
        'Order',
      );
      if (!['PENDING', 'CONFIRMED'].includes(order.status))
        throw mockHttpError(409, 'This order can no longer be cancelled.');
      const updated = { ...order, status: 'CANCELLED' as const };
      this.ordersState.update((items) => items.map((item) => (item.id === id ? updated : item)));
      this.adminOrdersState.update((items) =>
        items.map((item) => (item.id === id ? { ...item, status: 'CANCELLED' } : item)),
      );
      return updated;
    });
  }
  createCustomerFeedback(orderId: number, rating: number): Observable<CustomerFeedback> {
    return mockResponse(() => {
      this.require(
        this.ordersState().find((item) => item.id === orderId && item.status === 'DELIVERED'),
        'Delivered order',
      );
      const created = { orderId, rating, comment: '' };
      this.customerFeedbackState.update((items) => [...items, created]);
      return created;
    });
  }

  availableDeliveries(): Observable<OrderSummaryResponse[]> {
    return mockResponse(() => (this.driverOnlineState() ? this.availableState() : []));
  }
  activeDelivery(): Observable<OrderSummaryResponse | null> {
    return mockResponse(() => this.activeState());
  }
  deliveryHistory(): Observable<OrderHistoryResponse[]> {
    return mockResponse(() => this.deliveryHistoryState());
  }
  deliveryAction(
    id: number,
    action: 'accept' | 'pickup' | 'deliver' | 'cancel',
  ): Observable<OrderSummaryResponse> {
    return mockResponse(() => {
      if (action === 'accept') {
        if (this.activeState()) throw mockHttpError(409, 'Finish the active delivery first.');
        const selected = this.require(
          this.availableState().find((item) => item.id === id),
          'Available delivery',
        );
        this.availableState.update((items) => items.filter((item) => item.id !== id));
        this.activeState.set(selected);
        return selected;
      }
      const active = this.require(
        this.activeState()?.id === id ? this.activeState() : undefined,
        'Active delivery',
      );
      if (action === 'deliver') {
        this.deliveryHistoryState.update((items) => [
          {
            id: active.id,
            restaurantName: active.restaurantName,
            deliveredAt: new Date().toISOString(),
            earnings: active.deliveryFee,
            orderTotal: active.totalPrice,
          },
          ...items,
        ]);
        this.activeState.set(null);
      } else if (action === 'cancel') {
        this.availableState.update((items) => [...items, active]);
        this.activeState.set(null);
      }
      return active;
    });
  }
  setDriverStatus(online: boolean): Observable<void> {
    return mockResponse(() => {
      this.driverOnlineState.set(online);
    });
  }
  driverFeedback(): Observable<DeliveryFeedbackResponse[]> {
    return mockResponse(() => this.driverFeedbackState());
  }
  createDriverFeedback(orderId: number, rating: number): Observable<DeliveryFeedbackResponse> {
    return mockResponse(() => {
      const created = { orderId, rating, comment: '' };
      this.driverFeedbackState.update((items) => [...items, created]);
      return created;
    });
  }
  driverProfile(): Observable<DriverProfileResponse> {
    return mockResponse(() => this.driverProfileState());
  }
  updateDriverProfile(request: DriverProfileUpdateRequest): Observable<void> {
    return mockResponse(() => {
      this.driverProfileState.update((profile) => ({ ...profile, ...request }));
    });
  }

  kitchenDashboard(): Observable<KitchenDashboardSummaryResponse> {
    return mockResponse(() => {
      const counts = (status: OrderStatus) =>
        this.kitchenOrdersState().filter((item) => item.status === status).length;
      return {
        totalOrdersToday: this.kitchenOrdersState().length,
        pendingOrders: counts('PENDING'),
        confirmedOrders: counts('CONFIRMED'),
        preparingOrders: counts('PREPARING'),
        readyOrders: counts('READY'),
        acceptedOrders: counts('ACCEPTED'),
        pickedUpOrders: counts('PICKED_UP'),
        deliveredOrders: counts('DELIVERED'),
        cancelledOrders: counts('CANCELLED'),
        delayedOrders: 0,
      };
    });
  }
  kitchenOrders(): Observable<KitchenOrderSummaryResponse[]> {
    return mockResponse(() =>
      this.kitchenOrdersState()
        .filter((item) => !['DELIVERED', 'CANCELLED'].includes(item.status))
        .map((item) => this.kitchenSummary(item)),
    );
  }
  kitchenHistory(filters: {
    status?: OrderStatus;
    from?: string;
    to?: string;
    page?: number;
    size?: number;
    direction?: 'ASC' | 'DESC';
  }): Observable<KitchenOrderPageResponse> {
    return mockResponse(() => {
      let items = [...this.kitchenOrdersState()];
      if (filters.status) items = items.filter((item) => item.status === filters.status);
      if (filters.from) items = items.filter((item) => item.createdAt >= filters.from!);
      if (filters.to) items = items.filter((item) => item.createdAt <= filters.to!);
      items.sort(
        (a, b) => (filters.direction === 'ASC' ? 1 : -1) * a.createdAt.localeCompare(b.createdAt),
      );
      const page = filters.page ?? 0;
      const size = filters.size ?? 10;
      const start = page * size;
      const totalPages = Math.ceil(items.length / size);
      return {
        orders: items.slice(start, start + size).map((item) => this.kitchenSummary(item)),
        page,
        size,
        totalElements: items.length,
        totalPages,
        first: page === 0,
        last: totalPages === 0 || page >= totalPages - 1,
      };
    });
  }
  kitchenOrder(id: number): Observable<KitchenOrderDetailsResponse> {
    return mockResponse(() =>
      this.require(
        this.kitchenOrdersState().find((item) => item.id === id),
        'Kitchen order',
      ),
    );
  }
  updateKitchenOrder(id: number, status: OrderStatus): Observable<KitchenOrderDetailsResponse> {
    return mockResponse(() => {
      const order = this.require(
        this.kitchenOrdersState().find((item) => item.id === id),
        'Kitchen order',
      );
      const updated = { ...order, status, updatedAt: new Date().toISOString() };
      this.kitchenOrdersState.update((items) =>
        items.map((item) => (item.id === id ? updated : item)),
      );
      return updated;
    });
  }
  kitchenItems(): Observable<KitchenMenuItemResponse[]> {
    return mockResponse(() => this.kitchenItemsState());
  }
  createKitchenItem(request: CreateMenuItemRequest): Observable<KitchenMenuItemResponse> {
    return mockResponse(() => {
      const section = this.require(
        this.kitchenSectionsState().find((item) => item.id === request.menuSectionId),
        'Menu section',
      );
      const created = {
        id: this.nextMenuItemId++,
        name: request.name,
        basePrice: request.basePrice,
        available: request.available ?? true,
        menuSectionId: section.id,
        menuSectionName: section.name,
      };
      this.kitchenItemsState.update((items) => [...items, created]);
      this.syncKitchenSections();
      return created;
    });
  }
  updateKitchenItem(
    id: number,
    request: UpdateMenuItemRequest,
  ): Observable<KitchenMenuItemResponse> {
    return mockResponse(() => {
      const current = this.require(
        this.kitchenItemsState().find((item) => item.id === id),
        'Menu item',
      );
      const section = request.menuSectionId
        ? this.require(
            this.kitchenSectionsState().find((item) => item.id === request.menuSectionId),
            'Menu section',
          )
        : undefined;
      const updated = {
        ...current,
        name: request.name ?? current.name,
        basePrice: request.basePrice ?? current.basePrice,
        available: request.available ?? current.available,
        menuSectionId: request.menuSectionId ?? current.menuSectionId,
        menuSectionName: section?.name ?? current.menuSectionName,
      };
      this.kitchenItemsState.update((items) =>
        items.map((item) => (item.id === id ? updated : item)),
      );
      this.syncKitchenSections();
      return updated;
    });
  }
  deleteKitchenItem(id: number): Observable<void> {
    return mockResponse(() => {
      this.require(
        this.kitchenItemsState().find((item) => item.id === id),
        'Menu item',
      );
      this.kitchenItemsState.update((items) => items.filter((item) => item.id !== id));
      this.syncKitchenSections();
    });
  }
  kitchenSections(): Observable<KitchenMenuSectionResponse[]> {
    return mockResponse(() => this.kitchenSectionsState());
  }
  createKitchenSection(request: CreateMenuSectionRequest): Observable<KitchenMenuSectionResponse> {
    return mockResponse(() => {
      const created = {
        id: this.nextSectionId++,
        name: request.name,
        description: request.description ?? '',
        active: request.active ?? true,
        menuItems: [],
      };
      this.kitchenSectionsState.update((items) => [...items, created]);
      return created;
    });
  }
  updateKitchenSection(
    id: number,
    request: UpdateMenuSectionRequest,
  ): Observable<KitchenMenuSectionResponse> {
    return mockResponse(() => {
      const current = this.require(
        this.kitchenSectionsState().find((item) => item.id === id),
        'Menu section',
      );
      const updated = { ...current, ...request };
      this.kitchenSectionsState.update((items) =>
        items.map((item) => (item.id === id ? updated : item)),
      );
      return updated;
    });
  }
  kitchenGroups(): Observable<KitchenAddonGroupResponse[]> {
    return mockResponse(() => this.kitchenGroupsState());
  }
  createKitchenGroup(request: CreateAddonGroupRequest): Observable<KitchenAddonGroupResponse> {
    return mockResponse(() => {
      const created = { id: this.nextGroupId++, ...request, addons: [] };
      this.kitchenGroupsState.update((items) => [...items, created]);
      return created;
    });
  }
  updateKitchenGroup(
    id: number,
    request: UpdateAddonGroupRequest,
  ): Observable<KitchenAddonGroupResponse> {
    return mockResponse(() => {
      const current = this.require(
        this.kitchenGroupsState().find((item) => item.id === id),
        'Addon group',
      );
      const updated = { ...current, ...request };
      this.kitchenGroupsState.update((items) =>
        items.map((item) => (item.id === id ? updated : item)),
      );
      return updated;
    });
  }
  deleteKitchenGroup(id: number): Observable<void> {
    return mockResponse(() => {
      this.require(
        this.kitchenGroupsState().find((item) => item.id === id),
        'Addon group',
      );
      this.kitchenGroupsState.update((items) => items.filter((item) => item.id !== id));
    });
  }
  kitchenAddons(groupId: number): Observable<KitchenAddonResponse[]> {
    return mockResponse(
      () =>
        this.require(
          this.kitchenGroupsState().find((item) => item.id === groupId),
          'Addon group',
        ).addons,
    );
  }
  createKitchenAddon(
    groupId: number,
    request: CreateAddonRequest,
  ): Observable<KitchenAddonResponse> {
    return mockResponse(() => {
      const group = this.require(
        this.kitchenGroupsState().find((item) => item.id === groupId),
        'Addon group',
      );
      const created = {
        id: this.nextAddonId++,
        name: request.name,
        additionalPrice: request.additionalPrice,
        available: request.available ?? true,
      };
      this.kitchenGroupsState.update((items) =>
        items.map((item) =>
          item.id === groupId ? { ...group, addons: [...group.addons, created] } : item,
        ),
      );
      return created;
    });
  }
  updateKitchenAddon(id: number, request: UpdateAddonRequest): Observable<KitchenAddonResponse> {
    return mockResponse(() => {
      const group = this.require(
        this.kitchenGroupsState().find((item) => item.addons.some((addon) => addon.id === id)),
        'Addon group',
      );
      const current = this.require(
        group.addons.find((item) => item.id === id),
        'Addon',
      );
      const updated = { ...current, ...request };
      this.kitchenGroupsState.update((groups) =>
        groups.map((item) =>
          item.id === group.id
            ? { ...item, addons: item.addons.map((addon) => (addon.id === id ? updated : addon)) }
            : item,
        ),
      );
      return updated;
    });
  }
  deleteKitchenAddon(id: number): Observable<void> {
    return mockResponse(() => {
      const group = this.require(
        this.kitchenGroupsState().find((item) => item.addons.some((addon) => addon.id === id)),
        'Addon group',
      );
      this.kitchenGroupsState.update((groups) =>
        groups.map((item) =>
          item.id === group.id
            ? { ...item, addons: item.addons.filter((addon) => addon.id !== id) }
            : item,
        ),
      );
    });
  }

  adminCategories(): Observable<AdminCategoryResponse[]> {
    return mockResponse(() => this.adminCategoriesState());
  }
  createAdminCategory(request: CategoryRequest): Observable<AdminCategoryResponse> {
    return mockResponse(() => {
      const created = {
        id: this.nextCategoryId++,
        name: request.name,
        description: request.description ?? '',
        active: true,
      };
      this.adminCategoriesState.update((items) => [...items, created]);
      this.categoriesState.update((items) => [
        ...items,
        { id: created.id, name: created.name, description: created.description },
      ]);
      return created;
    });
  }
  updateAdminCategory(id: number, request: CategoryRequest): Observable<AdminCategoryResponse> {
    return mockResponse(() => {
      const current = this.require(
        this.adminCategoriesState().find((item) => item.id === id),
        'Category',
      );
      const updated = { ...current, name: request.name, description: request.description ?? '' };
      this.adminCategoriesState.update((items) =>
        items.map((item) => (item.id === id ? updated : item)),
      );
      this.categoriesState.update((items) =>
        items.map((item) =>
          item.id === id ? { id, name: updated.name, description: updated.description } : item,
        ),
      );
      return updated;
    });
  }
  deleteAdminCategory(id: number): Observable<void> {
    return mockResponse(() => {
      this.require(
        this.adminCategoriesState().find((item) => item.id === id),
        'Category',
      );
      this.adminCategoriesState.update((items) => items.filter((item) => item.id !== id));
      this.categoriesState.update((items) => items.filter((item) => item.id !== id));
      this.restaurantCategoryIdsState.update((mapping) =>
        Object.fromEntries(
          Object.entries(mapping).map(([restaurantId, categoryIds]) => [
            restaurantId,
            categoryIds.filter((categoryId) => categoryId !== id),
          ]),
        ),
      );
    });
  }
  adminCustomers(search?: string): Observable<CustomerAdminResponse[]> {
    return mockResponse(() =>
      this.filterText(
        this.adminCustomersState(),
        search,
        (item) => `${item.name} ${item.email} ${item.phoneNumber}`,
      ),
    );
  }
  adminOrders(filters: {
    status?: OrderStatus;
    restaurantId?: number;
    from?: string;
    to?: string;
  }): Observable<OrderAdminResponse[]> {
    return mockResponse(() => {
      let items = this.adminOrdersState();
      if (filters.status) items = items.filter((item) => item.status === filters.status);
      if (filters.restaurantId) {
        const restaurant = this.adminRestaurantsState().find(
          (item) => item.id === filters.restaurantId,
        );
        items = items.filter((item) => item.restaurantName === restaurant?.name);
      }
      if (filters.from) items = items.filter((item) => item.updatedAt >= filters.from!);
      if (filters.to) items = items.filter((item) => item.updatedAt <= filters.to!);
      return items;
    });
  }
  cancelAdminOrder(id: number): Observable<OrderAdminResponse> {
    return mockResponse(() => {
      const current = this.require(
        this.adminOrdersState().find((item) => item.id === id),
        'Order',
      );
      const updated = {
        ...current,
        status: 'CANCELLED' as const,
        updatedAt: new Date().toISOString(),
      };
      this.adminOrdersState.update((items) =>
        items.map((item) => (item.id === id ? updated : item)),
      );
      return updated;
    });
  }
  adminRestaurants(search?: string): Observable<RestaurantAdminResponse[]> {
    return mockResponse(() =>
      this.filterText(
        this.adminRestaurantsState(),
        search,
        (item) => `${item.name} ${item.description} ${item.categoryNames.join(' ')}`,
      ),
    );
  }
  createAdminRestaurant(request: CreateRestaurantRequest): Observable<RestaurantAdminResponse> {
    return mockResponse(() => {
      const id = this.nextRestaurantId++;
      const categoryIds = request.categoryIds ?? [];
      const categoryNames = this.adminCategoriesState()
        .filter((item) => categoryIds.includes(item.id))
        .map((item) => item.name);
      const created = {
        id,
        name: request.name,
        phone: request.phone,
        email: request.email,
        address: request.address,
        governorateName: this.governorateName(request.governorateId),
        description: request.description ?? '',
        logoUrl: request.logoUrl ?? '',
        isActive: true,
        deliveryFee: request.deliveryFee,
        categoryNames,
      };
      this.adminRestaurantsState.update((items) => [...items, created]);
      this.restaurantCategoryIdsState.update((mapping) => ({ ...mapping, [id]: categoryIds }));
      this.restaurantsState.update((items) => [
        ...items,
        {
          id,
          name: created.name,
          description: created.description,
          logoUrl: created.logoUrl,
          categories: categoryNames,
        },
      ]);
      this.restaurantDetailsState.update((items) => [
        ...items,
        {
          id,
          name: created.name,
          description: created.description,
          logoUrl: created.logoUrl,
          menuSections: [],
        },
      ]);
      return created;
    });
  }
  adminRestaurant(id: number): Observable<RestaurantAdminResponse> {
    return mockResponse(() =>
      this.require(
        this.adminRestaurantsState().find((item) => item.id === id),
        'Restaurant',
      ),
    );
  }
  updateAdminRestaurantStatus(id: number, active: boolean): Observable<RestaurantAdminResponse> {
    return mockResponse(() => {
      const current = this.require(
        this.adminRestaurantsState().find((item) => item.id === id),
        'Restaurant',
      );
      const updated = { ...current, isActive: active };
      this.adminRestaurantsState.update((items) =>
        items.map((item) => (item.id === id ? updated : item)),
      );
      this.restaurantsState.update((items) => {
        const withoutRestaurant = items.filter((item) => item.id !== id);
        return active
          ? [
              ...withoutRestaurant,
              {
                id: updated.id,
                name: updated.name,
                description: updated.description,
                logoUrl: updated.logoUrl,
                categories: updated.categoryNames,
              },
            ]
          : withoutRestaurant;
      });
      return updated;
    });
  }
  assignManager(id: number, _request: AssignKitchenManagerRequest): Observable<void> {
    return mockResponse(() => {
      this.require(
        this.adminRestaurantsState().find((item) => item.id === id),
        'Restaurant',
      );
    });
  }
  adminRiders(search?: string): Observable<RiderAdminResponse[]> {
    return mockResponse(() =>
      this.filterText(
        this.ridersState(),
        search,
        (item) => `${item.name} ${item.phoneNumber} ${item.vehicleType}`,
      ),
    );
  }
  pendingRiders(): Observable<RiderAdminResponse[]> {
    return mockResponse(() =>
      this.ridersState().filter((item) => item.approvalStatus === 'PENDING'),
    );
  }
  riderAction(
    id: number,
    action: 'approve' | 'reject' | 'deactivate',
  ): Observable<RiderAdminResponse> {
    return mockResponse(() => {
      const rider = this.require(
        this.ridersState().find((item) => item.id === id),
        'Rider',
      );
      const updated = {
        ...rider,
        approvalStatus:
          action === 'approve'
            ? ('APPROVED' as const)
            : action === 'reject'
              ? ('REJECTED' as const)
              : rider.approvalStatus,
        isActive: action !== 'deactivate',
      };
      this.ridersState.update((items) => items.map((item) => (item.id === id ? updated : item)));
      return updated;
    });
  }
  adminFeedback(): Observable<AdminFeedback[]> {
    return mockResponse(() => this.adminFeedbackState());
  }

  private require<T>(value: T | undefined | null, entity: string): T {
    if (value === undefined || value === null) throw mockHttpError(404, `${entity} not found.`);
    return value;
  }
  private governorateName(id: number): string {
    return (
      ({ 1: 'Giza', 2: 'Cairo', 3: 'Alexandria' } as Record<number, string>)[id] ??
      `Governorate ${id}`
    );
  }
  private filterText<T>(items: T[], query: string | undefined, text: (item: T) => string): T[] {
    const term = query?.trim().toLowerCase();
    return term ? items.filter((item) => text(item).toLowerCase().includes(term)) : items;
  }
  private emptyCartIfNeeded(): CartResponse {
    return (
      this.cartState() ?? { id: 1, restaurantId: 0, restaurantName: '', subtotal: 0, items: [] }
    );
  }
  private makeCartItem(
    id: number,
    menuItem: MenuItemDetailsResponse,
    quantity: number,
    instructions = '',
    requests: AddCartItemRequest['addons'] = [],
  ): CartItemResponse {
    const addons: CartItemAddonResponse[] = (requests ?? []).map((request) => {
      const group = this.require(
        menuItem.addonGroups.find((entry) =>
          entry.addons.some((addon) => addon.id === request.menuItemAddonId),
        ),
        'Addon group',
      );
      const addon = this.require(
        group.addons.find((entry) => entry.id === request.menuItemAddonId),
        'Addon',
      );
      return {
        menuItemAddonId: addon.id,
        name: addon.name,
        addonGroupId: group.id,
        addonGroupName: group.name,
        quantity: request.quantity,
        priceAtAddition: addon.additionalPrice,
        totalPrice: addon.additionalPrice * request.quantity,
      };
    });
    const unit = menuItem.basePrice + addons.reduce((sum, addon) => sum + addon.totalPrice, 0);
    return {
      id,
      menuItemId: menuItem.id,
      menuItemName: menuItem.name,
      imageUrl: menuItem.imageUrl,
      basePrice: menuItem.basePrice,
      quantity,
      specialInstructions: instructions,
      itemTotalPrice: unit * quantity,
      addons,
    };
  }
  private updateCartItem(id: number, request: ReplaceCartItemRequest): CartResponse {
    const cart = this.emptyCartIfNeeded();
    const current = this.require(
      cart.items.find((item) => item.id === id),
      'Cart item',
    );
    const details = this.require(
      this.menuDetailsState().find((item) => item.id === current.menuItemId),
      'Menu item',
    );
    const updatedItem = this.makeCartItem(
      id,
      details,
      request.quantity,
      request.specialInstructions ?? current.specialInstructions,
      request.addons ??
        current.addons.map((addon) => ({
          menuItemAddonId: addon.menuItemAddonId,
          quantity: addon.quantity,
        })),
    );
    const updated = this.repriceCart({
      ...cart,
      items:
        request.quantity > 0
          ? cart.items.map((item) => (item.id === id ? updatedItem : item))
          : cart.items.filter((item) => item.id !== id),
    });
    this.cartState.set(updated);
    return updated;
  }
  private repriceCart(cart: CartResponse): CartResponse {
    return { ...cart, subtotal: cart.items.reduce((sum, item) => sum + item.itemTotalPrice, 0) };
  }
  private kitchenSummary(item: KitchenOrderDetailsResponse): KitchenOrderSummaryResponse {
    return {
      id: item.id,
      itemCount: item.orderItems.reduce((sum, orderItem) => sum + orderItem.quantity, 0),
      totalPrice: item.totalPrice,
      status: item.status,
      createdAt: item.createdAt,
    };
  }
  private syncKitchenSections(): void {
    this.kitchenSectionsState.update((sections) =>
      sections.map((section) => ({
        ...section,
        menuItems: this.kitchenItemsState().filter((item) => item.menuSectionId === section.id),
      })),
    );
  }
}
