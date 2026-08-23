import { Routes } from '@angular/router';
import { authGuard } from '../../core/auth/guards/auth.guard';
import { roleGuard } from '../../core/auth/guards/role.guard';

// Child feature routes will be added with the restaurant-owner portal.
const modes = ['dashboard', 'orders', 'history', 'menu-items', 'sections', 'addon-groups'] as const;
export const RESTAURANT_PORTAL_ROUTES: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'dashboard' },
  {
    path: 'orders/:orderId',
    canActivate: [authGuard, roleGuard],
    data: { roles: ['KITCHEN_MANAGER'] },
    loadComponent: () =>
      import('./pages/kitchen-order-details/kitchen-order-details').then(
        (m) => m.KitchenOrderDetails,
      ),
  },
  ...modes.map((mode) => ({
    path: mode,
    canActivate: [authGuard, roleGuard],
    data: { roles: ['KITCHEN_MANAGER'], mode },
    loadComponent: () => import('./pages/kitchen-page/kitchen-page').then((m) => m.KitchenPage),
  })),
];
