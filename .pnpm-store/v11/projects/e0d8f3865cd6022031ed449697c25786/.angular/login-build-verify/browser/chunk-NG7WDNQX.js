import "./chunk-GOMI4DH3.js";

// src/app/core/auth/guards/guest.guard.ts
var guestGuard = (route, state) => {
  return true;
};

// src/app/features/auth/auth.routes.ts
var AUTH_ROUTES = [
  {
    path: "login",
    canActivate: [guestGuard],
    loadComponent: () => import("./chunk-E4G77STT.js").then(({ Login }) => Login)
  },
  {
    path: "register",
    canActivate: [guestGuard],
    loadComponent: () => import("./chunk-G2QUQ7BC.js").then(({ Register }) => Register)
  },
  {
    path: "forgot-password",
    canActivate: [guestGuard],
    loadComponent: () => import("./chunk-HVA32T7P.js").then(
      ({ ForgotPassword }) => ForgotPassword
    )
  },
  {
    path: "reset-password",
    canActivate: [guestGuard],
    loadComponent: () => import("./chunk-4NIPR5JL.js").then(({ ResetPassword }) => ResetPassword)
  },
  {
    path: "otp-verification",
    canActivate: [guestGuard],
    loadComponent: () => import("./chunk-Z2EBPNHH.js").then(
      ({ OtpVerification }) => OtpVerification
    )
  },
  {
    path: "",
    pathMatch: "full",
    redirectTo: "login"
  }
];
export {
  AUTH_ROUTES
};
//# debugId=c1956428-38fe-5f23-b55c-de661fce7117
//# sourceMappingURL=chunk-NG7WDNQX.js.map
