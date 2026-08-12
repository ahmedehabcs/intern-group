import { Routes } from '@angular/router';

export const SEARCH_ROUTES: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('../home/pages/home/home').then(({ Home }) => Home),
  },
];
