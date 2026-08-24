# TalabatFrontend

## Development mock mode

Mock mode is controlled only by `src/environments/environment*.ts`:

```ts
mock: { enabled: true, delayMs: 200, forceError: false }
```

Set `enabled` to `true` to use the typed in-memory storefront, customer, driver, kitchen, and admin data without backend requests. Set it to `false` to use the existing real HTTP APIs. `delayMs` makes loading states visible, while `forceError: true` exercises error states before any mock mutation is applied. Mock runtime changes reset on browser refresh and are not persisted. Production explicitly sets `enabled: false`.

All mock accounts use password `password123`:

- `customer@talabaty.local` — CUSTOMER
- `driver@talabaty.local` — DRIVER
- `kitchen@talabaty.local` — KITCHEN_MANAGER
- `admin@talabaty.local` — ADMIN

The mock-only OTP is `123456`.

This project was generated using [Angular CLI](https://github.com/angular/angular-cli) version 22.0.5.

## Development server

To start a local development server, run:

```bash
ng serve
```

Once the server is running, open your browser and navigate to `http://localhost:4200/`. The application will automatically reload whenever you modify any of the source files.

## Code scaffolding

Angular CLI includes powerful code scaffolding tools. To generate a new component, run:

```bash
ng generate component component-name
```

For a complete list of available schematics (such as `components`, `directives`, or `pipes`), run:

```bash
ng generate --help
```

## Building

To build the project run:

```bash
ng build
```

This will compile your project and store the build artifacts in the `dist/` directory. By default, the production build optimizes your application for performance and speed.

## Running unit tests

To execute unit tests with the [Vitest](https://vitest.dev/) test runner, use the following command:

```bash
ng test
```

## Running end-to-end tests

For end-to-end (e2e) testing, run:

```bash
ng e2e
```

Angular CLI does not come with an end-to-end testing framework by default. You can choose one that suits your needs.

## Additional Resources

For more information on using the Angular CLI, including detailed command references, visit the [Angular CLI Overview and Command Reference](https://angular.dev/tools/cli) page.
