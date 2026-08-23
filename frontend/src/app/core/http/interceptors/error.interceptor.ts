import { HttpErrorResponse, HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, throwError } from 'rxjs';
import { TokenService } from '../../auth/services/token.service';

export const errorInterceptor: HttpInterceptorFn = (req, next) => {
  const tokens = inject(TokenService);
  const router = inject(Router);
  return next(req).pipe(
    catchError((error: HttpErrorResponse) => {
      if (error.status === 401 && !req.url.includes('/api/auth/login')) {
        tokens.clear();
        void router.navigate(['/auth/login'], { queryParams: { returnUrl: router.url } });
      }
      return throwError(() => error);
    })
  );
};
