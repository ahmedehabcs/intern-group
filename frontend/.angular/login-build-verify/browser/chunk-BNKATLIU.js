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

// src/app/layouts/checkout-layout/checkout-layout.ts
var CheckoutLayout = class _CheckoutLayout {
  static ɵfac = function CheckoutLayout_Factory(__ngFactoryType__) {
    return new (__ngFactoryType__ || _CheckoutLayout)();
  };
  static ɵcmp = /* @__PURE__ */ ɵɵdefineComponent({
    type: _CheckoutLayout,
    selectors: [['app-checkout-layout']],
    decls: 2,
    vars: 0,
    template: function CheckoutLayout_Template(rf, ctx) {
      if (rf & 1) {
        ɵɵelementStart(0, 'main');
        ɵɵelement(1, 'router-outlet');
        ɵɵelementEnd();
      }
    },
    dependencies: [RouterOutlet],
    encapsulation: 2,
  });
};
(() => {
  (typeof ngDevMode === 'undefined' || ngDevMode) &&
    setClassMetadata(
      CheckoutLayout,
      [
        {
          type: Component,
          args: [
            {
              selector: 'app-checkout-layout',
              imports: [RouterOutlet],
              template: '<main>\r\n  <router-outlet />\r\n</main>\r\n',
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
    ɵsetClassDebugInfo(CheckoutLayout, {
      className: 'CheckoutLayout',
      filePath: 'src/app/layouts/checkout-layout/checkout-layout.ts',
      lineNumber: 9,
    });
})();
export { CheckoutLayout };
//# debugId=14085771-38c5-5daa-849c-4e4167a7909c
//# sourceMappingURL=chunk-BNKATLIU.js.map
