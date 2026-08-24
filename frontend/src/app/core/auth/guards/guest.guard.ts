import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { TokenService } from '../services/token.service';
import { roleHome } from './role.guard';

export const guestGuard: CanActivateFn = (route, state) => {
  const tokens = inject(TokenService);
  return !tokens.isValid() || inject(Router).createUrlTree([roleHome(tokens.role())]);
};
