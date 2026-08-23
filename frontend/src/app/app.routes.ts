import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: 'auth',
    loadComponent: () =>
      import('./layouts/auth-layout/auth-layout').then(({ AuthLayout }) => AuthLayout),
    loadChildren: () =>
      import('./features/auth/auth.routes').then(({ AUTH_ROUTES }) => AUTH_ROUTES),
  },
  {
    path: '',
    loadComponent: () =>
      import('./layouts/customer-layout/customer-layout').then(
        ({ CustomerLayout }) => CustomerLayout,
      ),
    children: [
      {
        path: '',
        loadChildren: () =>
          import('./features/home/home.routes').then(({ HOME_ROUTES }) => HOME_ROUTES),
      },
      {
        path: 'restaurants',
        loadChildren: () =>
          import('./features/restaurants/restaurants.routes').then(
            ({ RESTAURANTS_ROUTES }) => RESTAURANTS_ROUTES,
          ),
      },
      {
        path: 'menu-items/:menuItemId',
        loadComponent: () => import('./features/restaurants/pages/menu-item-details/menu-item-details').then(m => m.MenuItemDetails),
      },
      {
        path: 'search',
        loadChildren: () =>
          import('./features/search/search.routes').then(({ SEARCH_ROUTES }) => SEARCH_ROUTES),
      },
      {
        path: 'orders',
        loadChildren: () =>
          import('./features/orders/orders.routes').then(({ ORDERS_ROUTES }) => ORDERS_ROUTES),
      },
      {
        path: 'account',
        loadChildren: () =>
          import('./features/account/account.routes').then(({ ACCOUNT_ROUTES }) => ACCOUNT_ROUTES),
      },
    ],
  },
  {
    path: '',
    loadComponent: () =>
      import('./layouts/checkout-layout/checkout-layout').then(
        ({ CheckoutLayout }) => CheckoutLayout,
      ),
    children: [
      {
        path: 'cart',
        loadChildren: () =>
          import('./features/cart/cart.routes').then(({ CART_ROUTES }) => CART_ROUTES),
      },
      {
        path: 'checkout',
        loadChildren: () =>
          import('./features/checkout/checkout.routes').then(
            ({ CHECKOUT_ROUTES }) => CHECKOUT_ROUTES,
          ),
      },
    ],
  },
  {
    path: 'admin',
    loadComponent: () =>
      import('./layouts/admin-layout/admin-layout').then(({ AdminLayout }) => AdminLayout),
    loadChildren: () =>
      import('./features/admin/admin.routes').then(({ ADMIN_ROUTES }) => ADMIN_ROUTES),
  },
  {
    path: 'driver',
    loadChildren: () => import('./features/driver/driver.routes').then(m => m.DRIVER_ROUTES),
  },
  {
    path: 'restaurant-portal',
    loadComponent: () =>
      import('./layouts/restaurant-layout/restaurant-layout').then(
        ({ RestaurantLayout }) => RestaurantLayout,
      ),
    loadChildren: () =>
      import('./features/restaurant-portal/restaurant-portal.routes').then(
        ({ RESTAURANT_PORTAL_ROUTES }) => RESTAURANT_PORTAL_ROUTES,
      ),
  },
  {
    path: 'kitchen',
    loadComponent: () => import('./layouts/restaurant-layout/restaurant-layout').then(({ RestaurantLayout }) => RestaurantLayout),
    loadChildren: () => import('./features/restaurant-portal/restaurant-portal.routes').then(m => m.RESTAURANT_PORTAL_ROUTES),
  },
  {
    path: '**',
    redirectTo: '',
  },
];
