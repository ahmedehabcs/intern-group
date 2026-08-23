import { Routes } from '@angular/router';
import { authGuard } from '../../core/auth/guards/auth.guard';
import { roleGuard } from '../../core/auth/guards/role.guard';

// Pages will be added when the account feature is implemented.
export const ACCOUNT_ROUTES: Routes = [
  {
    path: '',
    canActivate: [authGuard, roleGuard],
    data: { roles: ['CUSTOMER'] },
    loadComponent: () => import('./pages/account-page/account-page').then((m) => m.AccountPage),
  },
];
