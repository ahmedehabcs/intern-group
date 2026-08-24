import { RouterLink, RouterOutlet } from './chunk-TBWUJ4NH.js';
import {
  Component,
  setClassMetadata,
  ɵsetClassDebugInfo,
  ɵɵadvance,
  ɵɵattribute,
  ɵɵconditional,
  ɵɵconditionalCreate,
  ɵɵdefineComponent,
  ɵɵelement,
  ɵɵelementEnd,
  ɵɵelementStart,
  ɵɵlistener,
  ɵɵnamespaceHTML,
  ɵɵnamespaceSVG,
  ɵɵproperty,
  ɵɵtext,
} from './chunk-XUN6663C.js';
import './chunk-GOMI4DH3.js';

// src/app/layouts/customer-layout/components/navbar/navbar.ts
function Navbar_Conditional_23_Template(rf, ctx) {
  if (rf & 1) {
    ɵɵnamespaceSVG();
    ɵɵelementStart(0, 'svg', 4);
    ɵɵelement(1, 'circle', 18)(2, 'path', 19);
    ɵɵelementEnd();
  }
}
function Navbar_Conditional_24_Template(rf, ctx) {
  if (rf & 1) {
    ɵɵnamespaceSVG();
    ɵɵelementStart(0, 'svg', 4);
    ɵɵelement(1, 'path', 20);
    ɵɵelementEnd();
  }
}
var Navbar = class _Navbar {
  isDark = false;
  ngOnInit() {
    this.isDark = document.documentElement.dataset['theme'] === 'dark';
  }
  toggleTheme() {
    this.isDark = !this.isDark;
    document.documentElement.dataset['theme'] = this.isDark ? 'dark' : 'light';
    localStorage.setItem('talabaty-theme', this.isDark ? 'dark' : 'light');
  }
  static ɵfac = function Navbar_Factory(__ngFactoryType__) {
    return new (__ngFactoryType__ || _Navbar)();
  };
  static ɵcmp = /* @__PURE__ */ ɵɵdefineComponent({
    type: _Navbar,
    selectors: [['app-navbar']],
    decls: 35,
    vars: 3,
    consts: [
      [1, 'site-header'],
      ['routerLink', '/', 'aria-label', 'Talabaty home', 1, 'brand'],
      [1, 'brand-mark'],
      ['type', 'button', 1, 'location'],
      ['viewBox', '0 0 24 24', 'aria-hidden', 'true'],
      ['d', 'M20 10c0 5-8 12-8 12S4 15 4 10a8 8 0 1 1 16 0Z'],
      ['cx', '12', 'cy', '10', 'r', '2.5'],
      ['d', 'm8 10 4 4 4-4'],
      ['routerLink', '/search', 'aria-label', 'Open search', 1, 'nav-search'],
      ['cx', '11', 'cy', '11', 'r', '7'],
      ['d', 'm20 20-4-4'],
      ['type', 'button', 1, 'nav-theme', 3, 'click', 'title'],
      [1, 'nav-actions'],
      ['routerLink', '/auth/login', 1, 'login-link'],
      ['type', 'button', 'aria-label', 'Open cart', 1, 'cart-button'],
      ['d', 'M3 4h2l2.3 10.1a2 2 0 0 0 2 1.5h7.9a2 2 0 0 0 2-1.6L21 7H6'],
      ['cx', '10', 'cy', '20', 'r', '1'],
      ['cx', '18', 'cy', '20', 'r', '1'],
      ['cx', '12', 'cy', '12', 'r', '4'],
      [
        'd',
        'M12 2v2m0 16v2M4.9 4.9l1.4 1.4m11.4 11.4 1.4 1.4M2 12h2m16 0h2M4.9 19.1l1.4-1.4M17.7 6.3l1.4-1.4',
      ],
      ['d', 'M20.7 15.1A8.5 8.5 0 0 1 8.9 3.3 8.5 8.5 0 1 0 20.7 15.1Z'],
    ],
    template: function Navbar_Template(rf, ctx) {
      if (rf & 1) {
        ɵɵelementStart(0, 'header', 0)(1, 'a', 1)(2, 'span', 2);
        ɵɵtext(3, 'T');
        ɵɵelementEnd();
        ɵɵelementStart(4, 'span');
        ɵɵtext(5, 'Talabaty');
        ɵɵelementEnd()();
        ɵɵelementStart(6, 'button', 3);
        ɵɵnamespaceSVG();
        ɵɵelementStart(7, 'svg', 4);
        ɵɵelement(8, 'path', 5)(9, 'circle', 6);
        ɵɵelementEnd();
        ɵɵnamespaceHTML();
        ɵɵelementStart(10, 'span')(11, 'small');
        ɵɵtext(12, 'Delivering to');
        ɵɵelementEnd();
        ɵɵtext(13, 'Al-Mansour, Baghdad');
        ɵɵelementEnd();
        ɵɵnamespaceSVG();
        ɵɵelementStart(14, 'svg', 4);
        ɵɵelement(15, 'path', 7);
        ɵɵelementEnd()();
        ɵɵnamespaceHTML();
        ɵɵelementStart(16, 'a', 8);
        ɵɵnamespaceSVG();
        ɵɵelementStart(17, 'svg', 4);
        ɵɵelement(18, 'circle', 9)(19, 'path', 10);
        ɵɵelementEnd();
        ɵɵnamespaceHTML();
        ɵɵelementStart(20, 'span');
        ɵɵtext(21, 'Search dishes or restaurants');
        ɵɵelementEnd()();
        ɵɵelementStart(22, 'button', 11);
        ɵɵlistener('click', function Navbar_Template_button_click_22_listener() {
          return ctx.toggleTheme();
        });
        ɵɵconditionalCreate(23, Navbar_Conditional_23_Template, 3, 0, ':svg:svg', 4)(
          24,
          Navbar_Conditional_24_Template,
          2,
          0,
          ':svg:svg',
          4,
        );
        ɵɵelementEnd();
        ɵɵelementStart(25, 'div', 12)(26, 'a', 13);
        ɵɵtext(27, 'Sign in');
        ɵɵelementEnd();
        ɵɵelementStart(28, 'button', 14);
        ɵɵnamespaceSVG();
        ɵɵelementStart(29, 'svg', 4);
        ɵɵelement(30, 'path', 15)(31, 'circle', 16)(32, 'circle', 17);
        ɵɵelementEnd();
        ɵɵnamespaceHTML();
        ɵɵelementStart(33, 'span');
        ɵɵtext(34, '2');
        ɵɵelementEnd()()()();
      }
      if (rf & 2) {
        ɵɵadvance(22);
        ɵɵproperty('title', ctx.isDark ? 'Light mode' : 'Dark mode');
        ɵɵattribute('aria-label', ctx.isDark ? 'Switch to light mode' : 'Switch to dark mode');
        ɵɵadvance();
        ɵɵconditional(ctx.isDark ? 23 : 24);
      }
    },
    dependencies: [RouterLink],
    styles: [
      '\n.site-header[_ngcontent-%COMP%] {\n  position: sticky;\n  z-index: 30;\n  top: 0;\n  height: 78px;\n  display: flex;\n  align-items: center;\n  gap: 28px;\n  padding: 0 max(32px, calc((100vw - 1240px) / 2));\n  border-bottom: 1px solid var(--%NS%border);\n  background: color-mix(in srgb, var(--%NS%home-bg) 90%, transparent);\n  -webkit-backdrop-filter: blur(16px);\n  backdrop-filter: blur(16px);\n}\n.brand[_ngcontent-%COMP%] {\n  display: flex;\n  align-items: center;\n  gap: 10px;\n  color: var(--%NS%brand);\n  font-family: "Playfair Display", serif;\n  font-size: 24px;\n  font-weight: 700;\n}\n.brand-mark[_ngcontent-%COMP%] {\n  width: 36px;\n  height: 36px;\n  display: grid;\n  place-items: center;\n  border-radius: 11px;\n  background: var(--%NS%brand);\n  color: #fff;\n  font-family: "DM Sans", sans-serif;\n  font-size: 18px;\n}\n.location[_ngcontent-%COMP%] {\n  display: flex;\n  align-items: center;\n  gap: 9px;\n  border: 0;\n  background: transparent;\n  color: var(--%NS%text);\n  cursor: pointer;\n  text-align: left;\n}\n.location[_ngcontent-%COMP%]   svg[_ngcontent-%COMP%], \n.nav-search[_ngcontent-%COMP%]   svg[_ngcontent-%COMP%], \n.cart-button[_ngcontent-%COMP%]   svg[_ngcontent-%COMP%] {\n  width: 20px;\n  height: 20px;\n  fill: none;\n  stroke: currentColor;\n  stroke-linecap: round;\n  stroke-linejoin: round;\n  stroke-width: 1.8;\n}\n.location[_ngcontent-%COMP%]    > svg[_ngcontent-%COMP%]:first-child {\n  color: var(--%NS%brand);\n}\n.location[_ngcontent-%COMP%]    > svg[_ngcontent-%COMP%]:last-child {\n  width: 14px;\n}\n.location[_ngcontent-%COMP%]   span[_ngcontent-%COMP%] {\n  display: grid;\n  font-size: 14px;\n  font-weight: 600;\n}\n.location[_ngcontent-%COMP%]   small[_ngcontent-%COMP%] {\n  color: var(--%NS%text-soft);\n  font-size: 10px;\n  font-weight: 500;\n}\n.nav-search[_ngcontent-%COMP%] {\n  height: 44px;\n  min-width: 220px;\n  max-width: 400px;\n  flex: 1;\n  display: flex;\n  align-items: center;\n  gap: 10px;\n  padding: 0 15px;\n  border-radius: 13px;\n  background: var(--%NS%surface);\n  color: var(--%NS%muted);\n}\n.nav-search[_ngcontent-%COMP%]   span[_ngcontent-%COMP%] {\n  color: var(--%NS%muted);\n  font-size: 13px;\n}\n.nav-actions[_ngcontent-%COMP%] {\n  display: flex;\n  align-items: center;\n  gap: 18px;\n}\n.nav-theme[_ngcontent-%COMP%] {\n  width: 42px;\n  height: 42px;\n  flex: 0 0 auto;\n  display: grid;\n  place-items: center;\n  border: 1px solid var(--%NS%border);\n  border-radius: 50%;\n  background: var(--%NS%surface);\n  color: var(--%NS%text);\n  box-shadow: var(--%NS%shadow-sm);\n  cursor: pointer;\n}\n.nav-theme[_ngcontent-%COMP%]   svg[_ngcontent-%COMP%] {\n  width: 19px;\n  height: 19px;\n  fill: none;\n  stroke: currentColor;\n  stroke-linecap: round;\n  stroke-linejoin: round;\n  stroke-width: 1.8;\n}\n.login-link[_ngcontent-%COMP%] {\n  color: var(--%NS%brand);\n  font-size: 14px;\n  font-weight: 700;\n}\n.cart-button[_ngcontent-%COMP%] {\n  position: relative;\n  width: 42px;\n  height: 42px;\n  display: grid;\n  place-items: center;\n  border: 0;\n  border-radius: 13px;\n  background: var(--%NS%brand);\n  color: #fff;\n}\n.cart-button[_ngcontent-%COMP%]   span[_ngcontent-%COMP%] {\n  position: absolute;\n  top: -5px;\n  right: -5px;\n  width: 19px;\n  height: 19px;\n  display: grid;\n  place-items: center;\n  border: 2px solid var(--%NS%home-bg);\n  border-radius: 50%;\n  background: var(--%NS%text);\n  font-size: 9px;\n}\n@media (max-width: 800px) {\n  .site-header[_ngcontent-%COMP%] {\n    position: relative;\n    height: 70px;\n    padding: 0 70px 0 18px;\n  }\n  .brand[_ngcontent-%COMP%] {\n    font-size: 20px;\n  }\n  .brand-mark[_ngcontent-%COMP%] {\n    width: 33px;\n    height: 33px;\n  }\n  .location[_ngcontent-%COMP%], \n   .nav-search[_ngcontent-%COMP%], \n   .nav-actions[_ngcontent-%COMP%] {\n    display: none;\n  }\n  .nav-theme[_ngcontent-%COMP%] {\n    position: absolute;\n    right: 15px;\n    width: 40px;\n    height: 40px;\n  }\n}\n/*# sourceMappingURL=navbar.css.map */',
    ],
  });
};
(() => {
  (typeof ngDevMode === 'undefined' || ngDevMode) &&
    setClassMetadata(
      Navbar,
      [
        {
          type: Component,
          args: [
            {
              selector: 'app-navbar',
              imports: [RouterLink],
              template: `<header class="site-header">\r
  <a class="brand" routerLink="/" aria-label="Talabaty home">\r
    <span class="brand-mark">T</span>\r
    <span>Talabaty</span>\r
  </a>\r
\r
  <button class="location" type="button">\r
    <svg viewBox="0 0 24 24" aria-hidden="true"><path d="M20 10c0 5-8 12-8 12S4 15 4 10a8 8 0 1 1 16 0Z"></path><circle cx="12" cy="10" r="2.5"></circle></svg>\r
    <span><small>Delivering to</small>Al-Mansour, Baghdad</span>\r
    <svg viewBox="0 0 24 24" aria-hidden="true"><path d="m8 10 4 4 4-4"></path></svg>\r
  </button>\r
\r
  <a class="nav-search" routerLink="/search" aria-label="Open search">\r
    <svg viewBox="0 0 24 24" aria-hidden="true"><circle cx="11" cy="11" r="7"></circle><path d="m20 20-4-4"></path></svg>\r
    <span>Search dishes or restaurants</span>\r
  </a>\r
\r
  <button\r
    class="nav-theme"\r
    type="button"\r
    (click)="toggleTheme()"\r
    [attr.aria-label]="isDark ? 'Switch to light mode' : 'Switch to dark mode'"\r
    [title]="isDark ? 'Light mode' : 'Dark mode'"\r
  >\r
    @if (isDark) {\r
      <svg viewBox="0 0 24 24" aria-hidden="true">\r
        <circle cx="12" cy="12" r="4"></circle>\r
        <path d="M12 2v2m0 16v2M4.9 4.9l1.4 1.4m11.4 11.4 1.4 1.4M2 12h2m16 0h2M4.9 19.1l1.4-1.4M17.7 6.3l1.4-1.4"></path>\r
      </svg>\r
    } @else {\r
      <svg viewBox="0 0 24 24" aria-hidden="true">\r
        <path d="M20.7 15.1A8.5 8.5 0 0 1 8.9 3.3 8.5 8.5 0 1 0 20.7 15.1Z"></path>\r
      </svg>\r
    }\r
  </button>\r
\r
  <div class="nav-actions">\r
    <a class="login-link" routerLink="/auth/login">Sign in</a>\r
    <button class="cart-button" type="button" aria-label="Open cart">\r
      <svg viewBox="0 0 24 24" aria-hidden="true"><path d="M3 4h2l2.3 10.1a2 2 0 0 0 2 1.5h7.9a2 2 0 0 0 2-1.6L21 7H6"></path><circle cx="10" cy="20" r="1"></circle><circle cx="18" cy="20" r="1"></circle></svg>\r
      <span>2</span>\r
    </button>\r
  </div>\r
\r
</header>\r
`,
              styles: [
                '/* src/app/layouts/customer-layout/components/navbar/navbar.css */\n.site-header {\n  position: sticky;\n  z-index: 30;\n  top: 0;\n  height: 78px;\n  display: flex;\n  align-items: center;\n  gap: 28px;\n  padding: 0 max(32px, calc((100vw - 1240px) / 2));\n  border-bottom: 1px solid var(--border);\n  background: color-mix(in srgb, var(--home-bg) 90%, transparent);\n  -webkit-backdrop-filter: blur(16px);\n  backdrop-filter: blur(16px);\n}\n.brand {\n  display: flex;\n  align-items: center;\n  gap: 10px;\n  color: var(--brand);\n  font-family: "Playfair Display", serif;\n  font-size: 24px;\n  font-weight: 700;\n}\n.brand-mark {\n  width: 36px;\n  height: 36px;\n  display: grid;\n  place-items: center;\n  border-radius: 11px;\n  background: var(--brand);\n  color: #fff;\n  font-family: "DM Sans", sans-serif;\n  font-size: 18px;\n}\n.location {\n  display: flex;\n  align-items: center;\n  gap: 9px;\n  border: 0;\n  background: transparent;\n  color: var(--text);\n  cursor: pointer;\n  text-align: left;\n}\n.location svg,\n.nav-search svg,\n.cart-button svg {\n  width: 20px;\n  height: 20px;\n  fill: none;\n  stroke: currentColor;\n  stroke-linecap: round;\n  stroke-linejoin: round;\n  stroke-width: 1.8;\n}\n.location > svg:first-child {\n  color: var(--brand);\n}\n.location > svg:last-child {\n  width: 14px;\n}\n.location span {\n  display: grid;\n  font-size: 14px;\n  font-weight: 600;\n}\n.location small {\n  color: var(--text-soft);\n  font-size: 10px;\n  font-weight: 500;\n}\n.nav-search {\n  height: 44px;\n  min-width: 220px;\n  max-width: 400px;\n  flex: 1;\n  display: flex;\n  align-items: center;\n  gap: 10px;\n  padding: 0 15px;\n  border-radius: 13px;\n  background: var(--surface);\n  color: var(--muted);\n}\n.nav-search span {\n  color: var(--muted);\n  font-size: 13px;\n}\n.nav-actions {\n  display: flex;\n  align-items: center;\n  gap: 18px;\n}\n.nav-theme {\n  width: 42px;\n  height: 42px;\n  flex: 0 0 auto;\n  display: grid;\n  place-items: center;\n  border: 1px solid var(--border);\n  border-radius: 50%;\n  background: var(--surface);\n  color: var(--text);\n  box-shadow: var(--shadow-sm);\n  cursor: pointer;\n}\n.nav-theme svg {\n  width: 19px;\n  height: 19px;\n  fill: none;\n  stroke: currentColor;\n  stroke-linecap: round;\n  stroke-linejoin: round;\n  stroke-width: 1.8;\n}\n.login-link {\n  color: var(--brand);\n  font-size: 14px;\n  font-weight: 700;\n}\n.cart-button {\n  position: relative;\n  width: 42px;\n  height: 42px;\n  display: grid;\n  place-items: center;\n  border: 0;\n  border-radius: 13px;\n  background: var(--brand);\n  color: #fff;\n}\n.cart-button span {\n  position: absolute;\n  top: -5px;\n  right: -5px;\n  width: 19px;\n  height: 19px;\n  display: grid;\n  place-items: center;\n  border: 2px solid var(--home-bg);\n  border-radius: 50%;\n  background: var(--text);\n  font-size: 9px;\n}\n@media (max-width: 800px) {\n  .site-header {\n    position: relative;\n    height: 70px;\n    padding: 0 70px 0 18px;\n  }\n  .brand {\n    font-size: 20px;\n  }\n  .brand-mark {\n    width: 33px;\n    height: 33px;\n  }\n  .location,\n  .nav-search,\n  .nav-actions {\n    display: none;\n  }\n  .nav-theme {\n    position: absolute;\n    right: 15px;\n    width: 40px;\n    height: 40px;\n  }\n}\n/*# sourceMappingURL=navbar.css.map */\n',
              ],
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
    ɵsetClassDebugInfo(Navbar, {
      className: 'Navbar',
      filePath: 'src/app/layouts/customer-layout/components/navbar/navbar.ts',
      lineNumber: 10,
    });
})();

// src/app/layouts/customer-layout/customer-layout.ts
var CustomerLayout = class _CustomerLayout {
  static ɵfac = function CustomerLayout_Factory(__ngFactoryType__) {
    return new (__ngFactoryType__ || _CustomerLayout)();
  };
  static ɵcmp = /* @__PURE__ */ ɵɵdefineComponent({
    type: _CustomerLayout,
    selectors: [['app-customer-layout']],
    decls: 3,
    vars: 0,
    template: function CustomerLayout_Template(rf, ctx) {
      if (rf & 1) {
        ɵɵelement(0, 'app-navbar');
        ɵɵelementStart(1, 'main');
        ɵɵelement(2, 'router-outlet');
        ɵɵelementEnd();
      }
    },
    dependencies: [Navbar, RouterOutlet],
    styles: [
      '\nmain[_ngcontent-%COMP%] {\n  min-height: 100vh;\n}\n/*# sourceMappingURL=customer-layout.css.map */',
    ],
  });
};
(() => {
  (typeof ngDevMode === 'undefined' || ngDevMode) &&
    setClassMetadata(
      CustomerLayout,
      [
        {
          type: Component,
          args: [
            {
              selector: 'app-customer-layout',
              imports: [Navbar, RouterOutlet],
              template: '<app-navbar />\r\n<main>\r\n  <router-outlet />\r\n</main>\r\n',
              styles: [
                '/* src/app/layouts/customer-layout/customer-layout.css */\nmain {\n  min-height: 100vh;\n}\n/*# sourceMappingURL=customer-layout.css.map */\n',
              ],
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
    ɵsetClassDebugInfo(CustomerLayout, {
      className: 'CustomerLayout',
      filePath: 'src/app/layouts/customer-layout/customer-layout.ts',
      lineNumber: 12,
    });
})();
export { CustomerLayout };
//# debugId=093b3943-ffcd-5f66-b2e8-0627e82acd62
//# sourceMappingURL=chunk-SLJ47VHG.js.map
