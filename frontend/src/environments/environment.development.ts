export const environment = {
  production: false,
  apiUrl: 'http://localhost:8080',
  mock: { enabled: true, delayMs: 200, forceError: false },
} as const;
