// Base environment. `ng serve` and `ng build` replace this file via the
// fileReplacements in angular.json, so the values here are what TypeScript
// type-checks against rather than what ships.
//
// mock.enabled is false so the `as const` literal narrows every
// `environment.mock.enabled ? mock : http` ternary to the http branch. That is
// what puts the real API path under the compiler: while this was `true` the
// mock branch was the only side ever type-checked, and a drifted request or
// response type on the http side could not fail the build.
export const environment = {
  production: false,
  apiUrl: 'http://localhost:8080',
  mock: { enabled: false, delayMs: 200, forceError: false },
} as const;
