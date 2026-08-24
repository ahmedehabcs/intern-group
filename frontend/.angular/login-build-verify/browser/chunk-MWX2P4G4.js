import { RouterOutlet } from './chunk-TBWUJ4NH.js';
import {
  Component,
  setClassMetadata,
  ɵsetClassDebugInfo,
  ɵɵdefineComponent,
  ɵɵelement,
  ɵɵelementEnd,
  ɵɵelementStart,
} from './chunk-XUN6663C.js';
import './chunk-GOMI4DH3.js';

// src/app/layouts/admin-layout/components/admin-breadcrumbs/admin-breadcrumbs.ts
var AdminBreadcrumbs = class _AdminBreadcrumbs {
  static ɵfac = function AdminBreadcrumbs_Factory(__ngFactoryType__) {
    return new (__ngFactoryType__ || _AdminBreadcrumbs)();
  };
  static ɵcmp = /* @__PURE__ */ ɵɵdefineComponent({
    type: _AdminBreadcrumbs,
    selectors: [['app-admin-breadcrumbs']],
    decls: 0,
    vars: 0,
    template: function AdminBreadcrumbs_Template(rf, ctx) {},
    encapsulation: 2,
  });
};
(() => {
  (typeof ngDevMode === 'undefined' || ngDevMode) &&
    setClassMetadata(
      AdminBreadcrumbs,
      [
        {
          type: Component,
          args: [{ selector: 'app-admin-breadcrumbs', imports: [], template: '' }],
        },
      ],
      null,
      null,
    );
})();
(() => {
  (typeof ngDevMode === 'undefined' || ngDevMode) &&
    ɵsetClassDebugInfo(AdminBreadcrumbs, {
      className: 'AdminBreadcrumbs',
      filePath: 'src/app/layouts/admin-layout/components/admin-breadcrumbs/admin-breadcrumbs.ts',
      lineNumber: 8,
    });
})();

// src/app/layouts/admin-layout/components/admin-navbar/admin-navbar.ts
var AdminNavbar = class _AdminNavbar {
  static ɵfac = function AdminNavbar_Factory(__ngFactoryType__) {
    return new (__ngFactoryType__ || _AdminNavbar)();
  };
  static ɵcmp = /* @__PURE__ */ ɵɵdefineComponent({
    type: _AdminNavbar,
    selectors: [['app-admin-navbar']],
    decls: 0,
    vars: 0,
    template: function AdminNavbar_Template(rf, ctx) {},
    encapsulation: 2,
  });
};
(() => {
  (typeof ngDevMode === 'undefined' || ngDevMode) &&
    setClassMetadata(
      AdminNavbar,
      [
        {
          type: Component,
          args: [{ selector: 'app-admin-navbar', imports: [], template: '' }],
        },
      ],
      null,
      null,
    );
})();
(() => {
  (typeof ngDevMode === 'undefined' || ngDevMode) &&
    ɵsetClassDebugInfo(AdminNavbar, {
      className: 'AdminNavbar',
      filePath: 'src/app/layouts/admin-layout/components/admin-navbar/admin-navbar.ts',
      lineNumber: 8,
    });
})();

// src/app/layouts/admin-layout/components/admin-sidebar/admin-sidebar.ts
var AdminSidebar = class _AdminSidebar {
  static ɵfac = function AdminSidebar_Factory(__ngFactoryType__) {
    return new (__ngFactoryType__ || _AdminSidebar)();
  };
  static ɵcmp = /* @__PURE__ */ ɵɵdefineComponent({
    type: _AdminSidebar,
    selectors: [['app-admin-sidebar']],
    decls: 0,
    vars: 0,
    template: function AdminSidebar_Template(rf, ctx) {},
    encapsulation: 2,
  });
};
(() => {
  (typeof ngDevMode === 'undefined' || ngDevMode) &&
    setClassMetadata(
      AdminSidebar,
      [
        {
          type: Component,
          args: [{ selector: 'app-admin-sidebar', imports: [], template: '' }],
        },
      ],
      null,
      null,
    );
})();
(() => {
  (typeof ngDevMode === 'undefined' || ngDevMode) &&
    ɵsetClassDebugInfo(AdminSidebar, {
      className: 'AdminSidebar',
      filePath: 'src/app/layouts/admin-layout/components/admin-sidebar/admin-sidebar.ts',
      lineNumber: 8,
    });
})();

// src/app/layouts/admin-layout/admin-layout.ts
var AdminLayout = class _AdminLayout {
  static ɵfac = function AdminLayout_Factory(__ngFactoryType__) {
    return new (__ngFactoryType__ || _AdminLayout)();
  };
  static ɵcmp = /* @__PURE__ */ ɵɵdefineComponent({
    type: _AdminLayout,
    selectors: [['app-admin-layout']],
    decls: 5,
    vars: 0,
    template: function AdminLayout_Template(rf, ctx) {
      if (rf & 1) {
        ɵɵelement(0, 'app-admin-navbar')(1, 'app-admin-sidebar')(2, 'app-admin-breadcrumbs');
        ɵɵelementStart(3, 'main');
        ɵɵelement(4, 'router-outlet');
        ɵɵelementEnd();
      }
    },
    dependencies: [AdminNavbar, AdminSidebar, AdminBreadcrumbs, RouterOutlet],
    encapsulation: 2,
  });
};
(() => {
  (typeof ngDevMode === 'undefined' || ngDevMode) &&
    setClassMetadata(
      AdminLayout,
      [
        {
          type: Component,
          args: [
            {
              selector: 'app-admin-layout',
              imports: [AdminNavbar, AdminSidebar, AdminBreadcrumbs, RouterOutlet],
              template:
                '<app-admin-navbar />\r\n<app-admin-sidebar />\r\n<app-admin-breadcrumbs />\r\n<main>\r\n  <router-outlet />\r\n</main>\r\n',
            },
          ],
        },
      ],
      null,
      null,
    );
})();
(() => {
  (typeof ngDevMode === 'undefined' || ngDevMode) &&
    ɵsetClassDebugInfo(AdminLayout, {
      className: 'AdminLayout',
      filePath: 'src/app/layouts/admin-layout/admin-layout.ts',
      lineNumber: 13,
    });
})();
export { AdminLayout };
//# debugId=0002bbec-7405-54ff-b515-44cb51afda32
//# sourceMappingURL=chunk-MWX2P4G4.js.map
