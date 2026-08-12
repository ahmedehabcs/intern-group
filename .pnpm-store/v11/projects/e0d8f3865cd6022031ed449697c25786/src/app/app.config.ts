import { provideHttpClient, withInterceptors } from '@angular/common/http';
import { ApplicationConfig, provideBrowserGlobalErrorListeners } from '@angular/core';
import { provideRouter } from '@angular/router';

import { authInterceptor } from './core/auth/interceptors/auth.interceptor';
import { apiInterceptor } from './core/http/interceptors/api.interceptor';
import { errorInterceptor } from './core/http/interceptors/error.interceptor';
import { routes } from './app.routes';

export const appConfig: ApplicationConfig = {
  providers: [
    provideBrowserGlobalErrorListeners(),
    provideHttpClient(withInterceptors([apiInterceptor, authInterceptor, errorInterceptor])),
    provideRouter(routes),
  ],
};
