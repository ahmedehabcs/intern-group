import { Router, RouterLink } from './chunk-TBWUJ4NH.js';
import {
  DefaultValueAccessor,
  FormsModule,
  NgControlStatus,
  NgControlStatusGroup,
  NgForm,
  NgModel,
  RequiredValidator,
  ɵNgNoValidate,
} from './chunk-PU67HFL7.js';
import {
  Component,
  setClassMetadata,
  ɵsetClassDebugInfo,
  ɵɵadvance,
  ɵɵcontrol,
  ɵɵcontrolCreate,
  ɵɵdefineComponent,
  ɵɵdirectiveInject,
  ɵɵelement,
  ɵɵelementEnd,
  ɵɵelementStart,
  ɵɵlistener,
  ɵɵnamespaceHTML,
  ɵɵnamespaceSVG,
  ɵɵtext,
  ɵɵtwoWayBindingSet,
  ɵɵtwoWayListener,
  ɵɵtwoWayProperty,
} from './chunk-XUN6663C.js';
import './chunk-GOMI4DH3.js';

// src/app/features/auth/pages/forgot-password/forgot-password.ts
var ForgotPassword = class _ForgotPassword {
  constructor(router) {
    this.router = router;
  }
  router;
  email = '';
  submit() {
    this.router.navigate(['/auth/otp-verification'], { queryParams: { email: this.email } });
  }
  static ɵfac = function ForgotPassword_Factory(__ngFactoryType__) {
    return new (__ngFactoryType__ || _ForgotPassword)(ɵɵdirectiveInject(Router));
  };
  static ɵcmp = /* @__PURE__ */ ɵɵdefineComponent({
    type: _ForgotPassword,
    selectors: [['app-forgot-password']],
    decls: 26,
    vars: 1,
    consts: [
      [1, 'auth-page', 'forgot-page'],
      [1, 'forgot-shell'],
      [1, 'auth-icon'],
      ['viewBox', '0 0 24 24'],
      ['cx', '8', 'cy', '15', 'r', '4'],
      ['d', 'm11 12 8-8m-3 3 3 3m-6 0 3 3'],
      [1, 'auth-heading'],
      [1, 'auth-copy'],
      [3, 'ngSubmit'],
      [1, 'field'],
      ['for', 'forgot-email'],
      [1, 'input-shell'],
      ['cx', '12', 'cy', '12', 'r', '9'],
      ['d', 'M16 8v6a2 2 0 0 0 4 0v-2a8 8 0 1 0-3 6'],
      [
        'id',
        'forgot-email',
        'type',
        'email',
        'name',
        'email',
        'placeholder',
        'name@company.com',
        'required',
        '',
        3,
        'ngModelChange',
        'ngModel',
      ],
      ['type', 'submit', 1, 'primary-btn'],
      [1, 'back-divider'],
      ['routerLink', '/auth/login', 1, 'back-link'],
    ],
    template: function ForgotPassword_Template(rf, ctx) {
      if (rf & 1) {
        ɵɵelementStart(0, 'div', 0)(1, 'section', 1)(2, 'div', 2);
        ɵɵnamespaceSVG();
        ɵɵelementStart(3, 'svg', 3);
        ɵɵelement(4, 'circle', 4)(5, 'path', 5);
        ɵɵelementEnd()();
        ɵɵnamespaceHTML();
        ɵɵelementStart(6, 'h1', 6);
        ɵɵtext(7, 'Forgot password?');
        ɵɵelementEnd();
        ɵɵelementStart(8, 'p', 7);
        ɵɵtext(
          9,
          "Don't worry, it happens to the best of us. Enter your email and we'll send you a secure link to reset your access.",
        );
        ɵɵelementEnd();
        ɵɵelementStart(10, 'form', 8);
        ɵɵlistener('ngSubmit', function ForgotPassword_Template_form_ngSubmit_10_listener() {
          return ctx.submit();
        });
        ɵɵelementStart(11, 'div', 9)(12, 'label', 10);
        ɵɵtext(13, 'Email Address');
        ɵɵelementEnd();
        ɵɵelementStart(14, 'div', 11);
        ɵɵnamespaceSVG();
        ɵɵelementStart(15, 'svg', 3);
        ɵɵelement(16, 'circle', 12)(17, 'path', 13);
        ɵɵelementEnd();
        ɵɵnamespaceHTML();
        ɵɵelementStart(18, 'input', 14);
        ɵɵtwoWayListener(
          'ngModelChange',
          function ForgotPassword_Template_input_ngModelChange_18_listener($event) {
            ɵɵtwoWayBindingSet(ctx.email, $event) || (ctx.email = $event);
            return $event;
          },
        );
        ɵɵelementEnd();
        ɵɵcontrolCreate();
        ɵɵelementEnd()();
        ɵɵelementStart(19, 'button', 15);
        ɵɵtext(20, 'Send Reset Link ');
        ɵɵelementStart(21, 'span');
        ɵɵtext(22, '\u2192');
        ɵɵelementEnd()()();
        ɵɵelement(23, 'div', 16);
        ɵɵelementStart(24, 'a', 17);
        ɵɵtext(25, '\u2190 \xA0 Back to Login');
        ɵɵelementEnd()()();
      }
      if (rf & 2) {
        ɵɵadvance(18);
        ɵɵtwoWayProperty('ngModel', ctx.email);
        ɵɵcontrol();
      }
    },
    dependencies: [
      FormsModule,
      ɵNgNoValidate,
      DefaultValueAccessor,
      NgControlStatus,
      NgControlStatusGroup,
      RequiredValidator,
      NgModel,
      NgForm,
      RouterLink,
    ],
    styles: [
      '\n.forgot-page[_ngcontent-%COMP%] {\n  background:\n    radial-gradient(\n      circle at 14% 84%,\n      color-mix(in srgb, var(--%NS%brand) 7%, transparent),\n      transparent 30%),\n    var(--%NS%bg);\n}\n.forgot-shell[_ngcontent-%COMP%] {\n  width: min(100%, 530px);\n  padding: 48px;\n  border-radius: 28px;\n  background: var(--%NS%surface);\n  box-shadow: var(--%NS%shadow-lg);\n}\n.forgot-shell[_ngcontent-%COMP%]   .auth-icon[_ngcontent-%COMP%] {\n  margin-bottom: 28px;\n}\n.forgot-shell[_ngcontent-%COMP%]   .auth-copy[_ngcontent-%COMP%] {\n  max-width: 480px;\n  font-size: 16px;\n}\n.forgot-shell[_ngcontent-%COMP%]   form[_ngcontent-%COMP%] {\n  margin-top: 38px;\n  display: grid;\n  gap: 22px;\n}\n.forgot-shell[_ngcontent-%COMP%]   .primary-btn[_ngcontent-%COMP%] {\n  margin-top: 2px;\n}\n.forgot-shell[_ngcontent-%COMP%]   .primary-btn[_ngcontent-%COMP%]   span[_ngcontent-%COMP%] {\n  font-size: 20px;\n}\n.back-divider[_ngcontent-%COMP%] {\n  height: 1px;\n  margin: 40px 0 24px;\n  background: var(--%NS%line);\n}\n.back-link[_ngcontent-%COMP%] {\n  display: block;\n  color: var(--%NS%text-soft);\n  font-size: 13px;\n  font-weight: 700;\n  text-align: center;\n}\n@media (max-width: 600px) {\n  .forgot-page[_ngcontent-%COMP%] {\n    padding: 60px 18px 28px;\n  }\n  .forgot-shell[_ngcontent-%COMP%] {\n    padding: 34px 22px;\n    border-radius: 23px;\n  }\n}\n/*# sourceMappingURL=forgot-password.css.map */',
    ],
  });
};
(() => {
  (typeof ngDevMode === 'undefined' || ngDevMode) &&
    setClassMetadata(
      ForgotPassword,
      [
        {
          type: Component,
          args: [
            {
              selector: 'app-forgot-password',
              imports: [FormsModule, RouterLink],
              template: `<div class="auth-page forgot-page">\r
  <section class="forgot-shell">\r
    <div class="auth-icon">\r
      <svg viewBox="0 0 24 24"><circle cx="8" cy="15" r="4"></circle><path d="m11 12 8-8m-3 3 3 3m-6 0 3 3"></path></svg>\r
    </div>\r
    <h1 class="auth-heading">Forgot password?</h1>\r
    <p class="auth-copy">Don't worry, it happens to the best of us. Enter your email and we'll send you a secure link to reset your access.</p>\r
\r
    <form (ngSubmit)="submit()">\r
      <div class="field">\r
        <label for="forgot-email">Email Address</label>\r
        <div class="input-shell">\r
          <svg viewBox="0 0 24 24"><circle cx="12" cy="12" r="9"></circle><path d="M16 8v6a2 2 0 0 0 4 0v-2a8 8 0 1 0-3 6"></path></svg>\r
          <input id="forgot-email" type="email" name="email" [(ngModel)]="email" placeholder="name@company.com" required />\r
        </div>\r
      </div>\r
      <button class="primary-btn" type="submit">Send Reset Link <span>\u2192</span></button>\r
    </form>\r
\r
    <div class="back-divider"></div>\r
    <a class="back-link" routerLink="/auth/login">\u2190 &nbsp; Back to Login</a>\r
  </section>\r
</div>\r
`,
              styles: [
                '/* src/app/features/auth/pages/forgot-password/forgot-password.css */\n.forgot-page {\n  background:\n    radial-gradient(\n      circle at 14% 84%,\n      color-mix(in srgb, var(--brand) 7%, transparent),\n      transparent 30%),\n    var(--bg);\n}\n.forgot-shell {\n  width: min(100%, 530px);\n  padding: 48px;\n  border-radius: 28px;\n  background: var(--surface);\n  box-shadow: var(--shadow-lg);\n}\n.forgot-shell .auth-icon {\n  margin-bottom: 28px;\n}\n.forgot-shell .auth-copy {\n  max-width: 480px;\n  font-size: 16px;\n}\n.forgot-shell form {\n  margin-top: 38px;\n  display: grid;\n  gap: 22px;\n}\n.forgot-shell .primary-btn {\n  margin-top: 2px;\n}\n.forgot-shell .primary-btn span {\n  font-size: 20px;\n}\n.back-divider {\n  height: 1px;\n  margin: 40px 0 24px;\n  background: var(--line);\n}\n.back-link {\n  display: block;\n  color: var(--text-soft);\n  font-size: 13px;\n  font-weight: 700;\n  text-align: center;\n}\n@media (max-width: 600px) {\n  .forgot-page {\n    padding: 60px 18px 28px;\n  }\n  .forgot-shell {\n    padding: 34px 22px;\n    border-radius: 23px;\n  }\n}\n/*# sourceMappingURL=forgot-password.css.map */\n',
              ],
            },
          ],
        },
      ],
      () => [{ type: Router }],
      null,
    );
})();
(() => {
  (typeof ngDevMode === 'undefined' || ngDevMode) &&
    ɵsetClassDebugInfo(ForgotPassword, {
      className: 'ForgotPassword',
      filePath: 'src/app/features/auth/pages/forgot-password/forgot-password.ts',
      lineNumber: 11,
    });
})();
export { ForgotPassword };
//# debugId=5271d21a-7898-5b18-a246-ea9468a0ee78
//# sourceMappingURL=chunk-HVA32T7P.js.map
