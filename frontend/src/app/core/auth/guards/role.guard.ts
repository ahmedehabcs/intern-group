import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { Role } from '../models/auth-user.model';
import { TokenService } from '../services/token.service';

export function roleHome(role: Role | null): string {
  if (role === 'ADMIN') return '/admin';
  if (role === 'DRIVER') return '/driver';
  if (role === 'KITCHEN_MANAGER') return '/kitchen';
  return '/';
}

export const roleGuard: CanActivateFn = (route, state) => {
  const tokens = inject(TokenService);
  const allowed = (route.data['roles'] ?? []) as Role[];
  if (!tokens.isValid()) return inject(Router).createUrlTree(['/auth/login'], { queryParams: { returnUrl: state.url } });
  return allowed.length === 0 || (tokens.role() !== null && allowed.includes(tokens.role()!)) || inject(Router).createUrlTree([roleHome(tokens.role())]);
};
