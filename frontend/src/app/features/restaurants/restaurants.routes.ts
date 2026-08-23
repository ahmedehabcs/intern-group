import { Routes } from '@angular/router';

// Pages will be added when the restaurants feature is implemented.
export const RESTAURANTS_ROUTES: Routes = [
  { path: '', pathMatch: 'full', redirectTo: '/' },
  {
    path: ':restaurantId',
    loadComponent: () =>
      import('./pages/restaurant-details/restaurant-details').then((m) => m.RestaurantDetails),
  },
];
