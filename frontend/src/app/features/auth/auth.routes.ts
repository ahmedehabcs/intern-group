import { Routes } from '@angular/router';

import { guestGuard } from '../../core/auth/guards/guest.guard';

export const AUTH_ROUTES: Routes = [
  {
    path: 'login',
    canActivate: [guestGuard],
    loadComponent: () => import('./pages/login/login').then(({ Login }) => Login),
  },
  {
    path: 'register',
    canActivate: [guestGuard],
    loadComponent: () => import('./pages/register/register').then(({ Register }) => Register),
  },
  {
    path: 'forgot-password',
    canActivate: [guestGuard],
    loadComponent: () =>
      import('./pages/forgot-password/forgot-password').then(
        ({ ForgotPassword }) => ForgotPassword,
      ),
  },
  {
    path: 'reset-password',
    canActivate: [guestGuard],
    loadComponent: () =>
      import('./pages/reset-password/reset-password').then(({ ResetPassword }) => ResetPassword),
  },
  {
    path: 'otp-verification',
    canActivate: [guestGuard],
    loadComponent: () =>
      import('./pages/otp-verification/otp-verification').then(
        ({ OtpVerification }) => OtpVerification,
      ),
  },
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'login',
  },
];
