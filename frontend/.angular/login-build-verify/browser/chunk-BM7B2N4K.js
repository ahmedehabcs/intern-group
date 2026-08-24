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

// src/app/layouts/restaurant-layout/components/restaurant-navbar/restaurant-navbar.ts
var RestaurantNavbar = class _RestaurantNavbar {
  static ɵfac = function RestaurantNavbar_Factory(__ngFactoryType__) {
    return new (__ngFactoryType__ || _RestaurantNavbar)();
  };
  static ɵcmp = /* @__PURE__ */ ɵɵdefineComponent({
    type: _RestaurantNavbar,
    selectors: [['app-restaurant-navbar']],
    decls: 0,
    vars: 0,
    template: function RestaurantNavbar_Template(rf, ctx) {},
    encapsulation: 2,
  });
};
(() => {
  (typeof ngDevMode === 'undefined' || ngDevMode) &&
    setClassMetadata(
      RestaurantNavbar,
      [
        {
          type: Component,
          args: [{ selector: 'app-restaurant-navbar', imports: [], template: '' }],
        },
      ],
      null,
      null,
    );
})();
(() => {
  (typeof ngDevMode === 'undefined' || ngDevMode) &&
    ɵsetClassDebugInfo(RestaurantNavbar, {
      className: 'RestaurantNavbar',
      filePath:
        'src/app/layouts/restaurant-layout/components/restaurant-navbar/restaurant-navbar.ts',
      lineNumber: 8,
    });
})();

// src/app/layouts/restaurant-layout/components/restaurant-sidebar/restaurant-sidebar.ts
var RestaurantSidebar = class _RestaurantSidebar {
  static ɵfac = function RestaurantSidebar_Factory(__ngFactoryType__) {
    return new (__ngFactoryType__ || _RestaurantSidebar)();
  };
  static ɵcmp = /* @__PURE__ */ ɵɵdefineComponent({
    type: _RestaurantSidebar,
    selectors: [['app-restaurant-sidebar']],
    decls: 0,
    vars: 0,
    template: function RestaurantSidebar_Template(rf, ctx) {},
    encapsulation: 2,
  });
};
(() => {
  (typeof ngDevMode === 'undefined' || ngDevMode) &&
    setClassMetadata(
      RestaurantSidebar,
      [
        {
          type: Component,
          args: [{ selector: 'app-restaurant-sidebar', imports: [], template: '' }],
        },
      ],
      null,
      null,
    );
})();
(() => {
  (typeof ngDevMode === 'undefined' || ngDevMode) &&
    ɵsetClassDebugInfo(RestaurantSidebar, {
      className: 'RestaurantSidebar',
      filePath:
        'src/app/layouts/restaurant-layout/components/restaurant-sidebar/restaurant-sidebar.ts',
      lineNumber: 8,
    });
})();

// src/app/layouts/restaurant-layout/restaurant-layout.ts
var RestaurantLayout = class _RestaurantLayout {
  static ɵfac = function RestaurantLayout_Factory(__ngFactoryType__) {
    return new (__ngFactoryType__ || _RestaurantLayout)();
  };
  static ɵcmp = /* @__PURE__ */ ɵɵdefineComponent({
    type: _RestaurantLayout,
    selectors: [['app-restaurant-layout']],
    decls: 4,
    vars: 0,
    template: function RestaurantLayout_Template(rf, ctx) {
      if (rf & 1) {
        ɵɵelement(0, 'app-restaurant-navbar')(1, 'app-restaurant-sidebar');
        ɵɵelementStart(2, 'main');
        ɵɵelement(3, 'router-outlet');
        ɵɵelementEnd();
      }
    },
    dependencies: [RestaurantNavbar, RestaurantSidebar, RouterOutlet],
    encapsulation: 2,
  });
};
(() => {
  (typeof ngDevMode === 'undefined' || ngDevMode) &&
    setClassMetadata(
      RestaurantLayout,
      [
        {
          type: Component,
          args: [
            {
              selector: 'app-restaurant-layout',
              imports: [RestaurantNavbar, RestaurantSidebar, RouterOutlet],
              template:
                '<app-restaurant-navbar />\r\n<app-restaurant-sidebar />\r\n<main>\r\n  <router-outlet />\r\n</main>\r\n',
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
    ɵsetClassDebugInfo(RestaurantLayout, {
      className: 'RestaurantLayout',
      filePath: 'src/app/layouts/restaurant-layout/restaurant-layout.ts',
      lineNumber: 12,
    });
})();
export { RestaurantLayout };
//# debugId=3ab6ed5a-c62f-57fc-a815-ab8d9621374a
//# sourceMappingURL=chunk-BM7B2N4K.js.map
