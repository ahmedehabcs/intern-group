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

// src/app/layouts/admin-layout/components/admin-breadcrumbs/admin-breadcrumbs.ts
var AdminBreadcrumbs = class _AdminBreadcrumbs {
  static \u0275fac = function AdminBreadcrumbs_Factory(__ngFactoryType__) {
    return new (__ngFactoryType__ || _AdminBreadcrumbs)();
  };
  static \u0275cmp = /* @__PURE__ */ \u0275\u0275defineComponent({ type: _AdminBreadcrumbs, selectors: [["app-admin-breadcrumbs"]], decls: 0, vars: 0, template: function AdminBreadcrumbs_Template(rf, ctx) {
  }, encapsulation: 2 });
};
(() => {
  (typeof ngDevMode === "undefined" || ngDevMode) && setClassMetadata(AdminBreadcrumbs, [{
    type: Component,
    args: [{ selector: "app-admin-breadcrumbs", imports: [], template: "" }]
  }], null, null);
})();
(() => {
  (typeof ngDevMode === "undefined" || ngDevMode) && \u0275setClassDebugInfo(AdminBreadcrumbs, { className: "AdminBreadcrumbs", filePath: "src/app/layouts/admin-layout/components/admin-breadcrumbs/admin-breadcrumbs.ts", lineNumber: 8 });
})();

// src/app/layouts/admin-layout/components/admin-navbar/admin-navbar.ts
var AdminNavbar = class _AdminNavbar {
  static \u0275fac = function AdminNavbar_Factory(__ngFactoryType__) {
    return new (__ngFactoryType__ || _AdminNavbar)();
  };
  static \u0275cmp = /* @__PURE__ */ \u0275\u0275defineComponent({ type: _AdminNavbar, selectors: [["app-admin-navbar"]], decls: 0, vars: 0, template: function AdminNavbar_Template(rf, ctx) {
  }, encapsulation: 2 });
};
(() => {
  (typeof ngDevMode === "undefined" || ngDevMode) && setClassMetadata(AdminNavbar, [{
    type: Component,
    args: [{ selector: "app-admin-navbar", imports: [], template: "" }]
  }], null, null);
})();
(() => {
  (typeof ngDevMode === "undefined" || ngDevMode) && \u0275setClassDebugInfo(AdminNavbar, { className: "AdminNavbar", filePath: "src/app/layouts/admin-layout/components/admin-navbar/admin-navbar.ts", lineNumber: 8 });
})();

// src/app/layouts/admin-layout/components/admin-sidebar/admin-sidebar.ts
var AdminSidebar = class _AdminSidebar {
  static \u0275fac = function AdminSidebar_Factory(__ngFactoryType__) {
    return new (__ngFactoryType__ || _AdminSidebar)();
  };
  static \u0275cmp = /* @__PURE__ */ \u0275\u0275defineComponent({ type: _AdminSidebar, selectors: [["app-admin-sidebar"]], decls: 0, vars: 0, template: function AdminSidebar_Template(rf, ctx) {
  }, encapsulation: 2 });
};
(() => {
  (typeof ngDevMode === "undefined" || ngDevMode) && setClassMetadata(AdminSidebar, [{
    type: Component,
    args: [{ selector: "app-admin-sidebar", imports: [], template: "" }]
  }], null, null);
})();
(() => {
  (typeof ngDevMode === "undefined" || ngDevMode) && \u0275setClassDebugInfo(AdminSidebar, { className: "AdminSidebar", filePath: "src/app/layouts/admin-layout/components/admin-sidebar/admin-sidebar.ts", lineNumber: 8 });
})();

// src/app/layouts/admin-layout/admin-layout.ts
var AdminLayout = class _AdminLayout {
  static \u0275fac = function AdminLayout_Factory(__ngFactoryType__) {
    return new (__ngFactoryType__ || _AdminLayout)();
  };
  static \u0275cmp = /* @__PURE__ */ \u0275\u0275defineComponent({ type: _AdminLayout, selectors: [["app-admin-layout"]], decls: 5, vars: 0, template: function AdminLayout_Template(rf, ctx) {
    if (rf & 1) {
      \u0275\u0275element(0, "app-admin-navbar")(1, "app-admin-sidebar")(2, "app-admin-breadcrumbs");
      \u0275\u0275elementStart(3, "main");
      \u0275\u0275element(4, "router-outlet");
      \u0275\u0275elementEnd();
    }
  }, dependencies: [AdminNavbar, AdminSidebar, AdminBreadcrumbs, RouterOutlet], encapsulation: 2 });
};
(() => {
  (typeof ngDevMode === "undefined" || ngDevMode) && setClassMetadata(AdminLayout, [{
    type: Component,
    args: [{ selector: "app-admin-layout", imports: [AdminNavbar, AdminSidebar, AdminBreadcrumbs, RouterOutlet], template: "<app-admin-navbar />\r\n<app-admin-sidebar />\r\n<app-admin-breadcrumbs />\r\n<main>\r\n  <router-outlet />\r\n</main>\r\n" }]
  }], null, null);
})();
(() => {
  (typeof ngDevMode === "undefined" || ngDevMode) && \u0275setClassDebugInfo(AdminLayout, { className: "AdminLayout", filePath: "src/app/layouts/admin-layout/admin-layout.ts", lineNumber: 13 });
})();
export {
  AdminLayout
};
//# debugId=0002bbec-7405-54ff-b515-44cb51afda32
//# sourceMappingURL=chunk-MWX2P4G4.js.map
