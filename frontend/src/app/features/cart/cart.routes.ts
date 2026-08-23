import { Routes } from '@angular/router';
import { authGuard } from '../../core/auth/guards/auth.guard';
import { roleGuard } from '../../core/auth/guards/role.guard';

// Pages will be added when the cart feature is implemented.
export const CART_ROUTES: Routes = [
  {
    path: '',
    canActivate: [authGuard, roleGuard],
    data: { roles: ['CUSTOMER'] },
    loadComponent: () => import('./pages/cart-page/cart-page').then((m) => m.CartPage),
  },
];
