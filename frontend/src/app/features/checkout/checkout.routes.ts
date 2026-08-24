import { Routes } from '@angular/router';
import { authGuard } from '../../core/auth/guards/auth.guard';
import { roleGuard } from '../../core/auth/guards/role.guard';

// Pages will be added when the checkout feature is implemented.
export const CHECKOUT_ROUTES: Routes = [
  {
    path: '',
    canActivate: [authGuard, roleGuard],
    data: { roles: ['CUSTOMER'] },
    loadComponent: () => import('./pages/checkout-page/checkout-page').then((m) => m.CheckoutPage),
  },
];
