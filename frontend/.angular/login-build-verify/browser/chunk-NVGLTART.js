import {
  RouterOutlet
} from "./chunk-TBWUJ4NH.js";
import {
  Component,
  setClassMetadata,
  ɵsetClassDebugInfo,
  ɵɵdefineComponent,
  ɵɵelement,
  ɵɵelementEnd,
  ɵɵelementStart
} from "./chunk-XUN6663C.js";
import "./chunk-GOMI4DH3.js";

// src/app/layouts/auth-layout/auth-layout.ts
var AuthLayout = class _AuthLayout {
  static \u0275fac = function AuthLayout_Factory(__ngFactoryType__) {
    return new (__ngFactoryType__ || _AuthLayout)();
  };
  static \u0275cmp = /* @__PURE__ */ \u0275\u0275defineComponent({ type: _AuthLayout, selectors: [["app-auth-layout"]], decls: 2, vars: 0, template: function AuthLayout_Template(rf, ctx) {
    if (rf & 1) {
      \u0275\u0275elementStart(0, "main");
      \u0275\u0275element(1, "router-outlet");
      \u0275\u0275elementEnd();
    }
  }, dependencies: [RouterOutlet], encapsulation: 2 });
};
(() => {
  (typeof ngDevMode === "undefined" || ngDevMode) && setClassMetadata(AuthLayout, [{
    type: Component,
    args: [{ selector: "app-auth-layout", imports: [RouterOutlet], template: "<main>\r\n  <router-outlet />\r\n</main>\r\n" }]
  }], null, null);
})();
(() => {
  (typeof ngDevMode === "undefined" || ngDevMode) && \u0275setClassDebugInfo(AuthLayout, { className: "AuthLayout", filePath: "src/app/layouts/auth-layout/auth-layout.ts", lineNumber: 9 });
})();
export {
  AuthLayout
};
//# debugId=04e1a4d0-2f6d-558d-bd97-9fba8fe363d2
//# sourceMappingURL=chunk-NVGLTART.js.map
