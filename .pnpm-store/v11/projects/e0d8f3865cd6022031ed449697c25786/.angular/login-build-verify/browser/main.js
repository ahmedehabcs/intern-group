import {
  RouterOutlet,
  bootstrapApplication,
  provideRouter
} from "./chunk-TBWUJ4NH.js";
import {
  Component,
  provideBrowserGlobalErrorListeners,
  provideHttpClient,
  setClassMetadata,
  withInterceptors,
  ɵsetClassDebugInfo,
  ɵɵdefineComponent,
  ɵɵelement
} from "./chunk-XUN6663C.js";
import "./chunk-GOMI4DH3.js";

// src/app/core/auth/interceptors/auth.interceptor.ts
var authInterceptor = (req, next) => {
  return next(req);
};

// src/app/core/http/interceptors/api.interceptor.ts
var apiInterceptor = (req, next) => {
  return next(req);
};

// src/app/core/http/interceptors/error.interceptor.ts
var errorInterceptor = (req, next) => {
  return next(req);
};

// src/app/app.routes.ts
var routes = [
  {
    path: "auth",
    loadComponent: () => import("./chunk-NVGLTART.js").then(({ AuthLayout }) => AuthLayout),
    loadChildren: () => import("./chunk-NG7WDNQX.js").then(({ AUTH_ROUTES }) => AUTH_ROUTES)
  },
  {
    path: "",
    loadComponent: () => import("./chunk-SLJ47VHG.js").then(
      ({ CustomerLayout }) => CustomerLayout
    ),
    children: [
      {
        path: "",
        loadChildren: () => import("./chunk-4TRM2XYE.js").then(({ HOME_ROUTES }) => HOME_ROUTES)
      },
      {
        path: "restaurants",
        loadChildren: () => import("./chunk-YGGEPVUH.js").then(
          ({ RESTAURANTS_ROUTES }) => RESTAURANTS_ROUTES
        )
      },
      {
        path: "search",
        loadChildren: () => import("./chunk-SRVAVBRZ.js").then(({ SEARCH_ROUTES }) => SEARCH_ROUTES)
      },
      {
        path: "orders",
        loadChildren: () => import("./chunk-ORNEIPZU.js").then(({ ORDERS_ROUTES }) => ORDERS_ROUTES)
      },
      {
        path: "account",
        loadChildren: () => import("./chunk-23JSYKTU.js").then(({ ACCOUNT_ROUTES }) => ACCOUNT_ROUTES)
      }
    ]
  },
  {
    path: "",
    loadComponent: () => import("./chunk-BNKATLIU.js").then(
      ({ CheckoutLayout }) => CheckoutLayout
    ),
    children: [
      {
        path: "cart",
        loadChildren: () => import("./chunk-NUZLRBZO.js").then(({ CART_ROUTES }) => CART_ROUTES)
      },
      {
        path: "checkout",
        loadChildren: () => import("./chunk-OIVS26SY.js").then(
          ({ CHECKOUT_ROUTES }) => CHECKOUT_ROUTES
        )
      }
    ]
  },
  {
    path: "admin",
    loadComponent: () => import("./chunk-MWX2P4G4.js").then(({ AdminLayout }) => AdminLayout),
    loadChildren: () => import("./chunk-VVWT4XVU.js").then(({ ADMIN_ROUTES }) => ADMIN_ROUTES)
  },
  {
    path: "restaurant-portal",
    loadComponent: () => import("./chunk-BM7B2N4K.js").then(
      ({ RestaurantLayout }) => RestaurantLayout
    ),
    loadChildren: () => import("./chunk-S6K5XVDW.js").then(
      ({ RESTAURANT_PORTAL_ROUTES }) => RESTAURANT_PORTAL_ROUTES
    )
  },
  {
    path: "**",
    redirectTo: ""
  }
];

// src/app/app.config.ts
var appConfig = {
  providers: [
    provideBrowserGlobalErrorListeners(),
    provideHttpClient(withInterceptors([apiInterceptor, authInterceptor, errorInterceptor])),
    provideRouter(routes)
  ]
};

// src/app/app.ts
var App = class _App {
  ngOnInit() {
    const saved = localStorage.getItem("talabaty-theme");
    const isDark = saved === "dark" || !saved && window.matchMedia("(prefers-color-scheme: dark)").matches;
    document.documentElement.dataset["theme"] = isDark ? "dark" : "light";
  }
  static \u0275fac = function App_Factory(__ngFactoryType__) {
    return new (__ngFactoryType__ || _App)();
  };
  static \u0275cmp = /* @__PURE__ */ \u0275\u0275defineComponent({ type: _App, selectors: [["app-root"]], hostAttrs: [1, "block", "w-full", "overflow-x-clip"], decls: 1, vars: 0, template: function App_Template(rf, ctx) {
    if (rf & 1) {
      \u0275\u0275element(0, "router-outlet");
    }
  }, dependencies: [RouterOutlet], encapsulation: 2 });
};
(() => {
  (typeof ngDevMode === "undefined" || ngDevMode) && setClassMetadata(App, [{
    type: Component,
    args: [{ selector: "app-root", imports: [RouterOutlet], host: {
      class: "block w-full overflow-x-clip"
    }, template: "<router-outlet />\r\n" }]
  }], null, null);
})();
(() => {
  (typeof ngDevMode === "undefined" || ngDevMode) && \u0275setClassDebugInfo(App, { className: "App", filePath: "src/app/app.ts", lineNumber: 12 });
})();

// src/main.ts
bootstrapApplication(App, appConfig).catch((err) => console.error(err));
//# debugId=d0648142-ed2c-57f6-aee4-e987fc249d86
//# sourceMappingURL=main.js.map
