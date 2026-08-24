// Used by `ng serve` (angular.json -> configurations.development).
//
// The dev server and the API are different origins (4200 vs 8080), so the
// browser sends real CORS preflights. The backend allows this origin by
// default - see security.cors.allowed-origins in application.properties and
// CORS_ALLOWED_ORIGINS in docker-compose.yml.
//
// Set mock.enabled back to true to work offline against src/app/mocks.
export const environment = {
  production: false,
  apiUrl: 'http://localhost:8080',
  mock: { enabled: false, delayMs: 200, forceError: false },
} as const;
