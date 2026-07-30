import {
  Router,
  RouterLink
} from "./chunk-TBWUJ4NH.js";
import {
  DefaultValueAccessor,
  FormsModule,
  NgControlStatus,
  NgControlStatusGroup,
  NgForm,
  NgModel,
  RequiredValidator,
  ɵNgNoValidate
} from "./chunk-PU67HFL7.js";
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
  ɵɵtwoWayProperty
} from "./chunk-XUN6663C.js";
import "./chunk-GOMI4DH3.js";

// src/app/features/auth/pages/forgot-password/forgot-password.ts
var ForgotPassword = class _ForgotPassword {
  constructor(router) {
    this.router = router;
  }
  router;
  email = "";
  submit() {
    this.router.navigate(["/auth/otp-verification"], { queryParams: { email: this.email } });
  }
  static \u0275fac = function ForgotPassword_Factory(__ngFactoryType__) {
    return new (__ngFactoryType__ || _ForgotPassword)(\u0275\u0275directiveInject(Router));
  };
  static \u0275cmp = /* @__PURE__ */ \u0275\u0275defineComponent({ type: _ForgotPassword, selectors: [["app-forgot-password"]], decls: 26, vars: 1, consts: [[1, "auth-page", "forgot-page"], [1, "forgot-shell"], [1, "auth-icon"], ["viewBox", "0 0 24 24"], ["cx", "8", "cy", "15", "r", "4"], ["d", "m11 12 8-8m-3 3 3 3m-6 0 3 3"], [1, "auth-heading"], [1, "auth-copy"], [3, "ngSubmit"], [1, "field"], ["for", "forgot-email"], [1, "input-shell"], ["cx", "12", "cy", "12", "r", "9"], ["d", "M16 8v6a2 2 0 0 0 4 0v-2a8 8 0 1 0-3 6"], ["id", "forgot-email", "type", "email", "name", "email", "placeholder", "name@company.com", "required", "", 3, "ngModelChange", "ngModel"], ["type", "submit", 1, "primary-btn"], [1, "back-divider"], ["routerLink", "/auth/login", 1, "back-link"]], template: function ForgotPassword_Template(rf, ctx) {
    if (rf & 1) {
      \u0275\u0275elementStart(0, "div", 0)(1, "section", 1)(2, "div", 2);
      \u0275\u0275namespaceSVG();
      \u0275\u0275elementStart(3, "svg", 3);
      \u0275\u0275element(4, "circle", 4)(5, "path", 5);
      \u0275\u0275elementEnd()();
      \u0275\u0275namespaceHTML();
      \u0275\u0275elementStart(6, "h1", 6);
      \u0275\u0275text(7, "Forgot password?");
      \u0275\u0275elementEnd();
      \u0275\u0275elementStart(8, "p", 7);
      \u0275\u0275text(9, "Don't worry, it happens to the best of us. Enter your email and we'll send you a secure link to reset your access.");
      \u0275\u0275elementEnd();
      \u0275\u0275elementStart(10, "form", 8);
      \u0275\u0275listener("ngSubmit", function ForgotPassword_Template_form_ngSubmit_10_listener() {
        return ctx.submit();
      });
      \u0275\u0275elementStart(11, "div", 9)(12, "label", 10);
      \u0275\u0275text(13, "Email Address");
      \u0275\u0275elementEnd();
      \u0275\u0275elementStart(14, "div", 11);
      \u0275\u0275namespaceSVG();
      \u0275\u0275elementStart(15, "svg", 3);
      \u0275\u0275element(16, "circle", 12)(17, "path", 13);
      \u0275\u0275elementEnd();
      \u0275\u0275namespaceHTML();
      \u0275\u0275elementStart(18, "input", 14);
      \u0275\u0275twoWayListener("ngModelChange", function ForgotPassword_Template_input_ngModelChange_18_listener($event) {
        \u0275\u0275twoWayBindingSet(ctx.email, $event) || (ctx.email = $event);
        return $event;
      });
      \u0275\u0275elementEnd();
      \u0275\u0275controlCreate();
      \u0275\u0275elementEnd()();
      \u0275\u0275elementStart(19, "button", 15);
      \u0275\u0275text(20, "Send Reset Link ");
      \u0275\u0275elementStart(21, "span");
      \u0275\u0275text(22, "\u2192");
      \u0275\u0275elementEnd()()();
      \u0275\u0275element(23, "div", 16);
      \u0275\u0275elementStart(24, "a", 17);
      \u0275\u0275text(25, "\u2190 \xA0 Back to Login");
      \u0275\u0275elementEnd()()();
    }
    if (rf & 2) {
      \u0275\u0275advance(18);
      \u0275\u0275twoWayProperty("ngModel", ctx.email);
      \u0275\u0275control();
    }
  }, dependencies: [FormsModule, \u0275NgNoValidate, DefaultValueAccessor, NgControlStatus, NgControlStatusGroup, RequiredValidator, NgModel, NgForm, RouterLink], styles: ["\n.forgot-page[_ngcontent-%COMP%] {\n  background:\n    radial-gradient(\n      circle at 14% 84%,\n      color-mix(in srgb, var(--%NS%brand) 7%, transparent),\n      transparent 30%),\n    var(--%NS%bg);\n}\n.forgot-shell[_ngcontent-%COMP%] {\n  width: min(100%, 530px);\n  padding: 48px;\n  border-radius: 28px;\n  background: var(--%NS%surface);\n  box-shadow: var(--%NS%shadow-lg);\n}\n.forgot-shell[_ngcontent-%COMP%]   .auth-icon[_ngcontent-%COMP%] {\n  margin-bottom: 28px;\n}\n.forgot-shell[_ngcontent-%COMP%]   .auth-copy[_ngcontent-%COMP%] {\n  max-width: 480px;\n  font-size: 16px;\n}\n.forgot-shell[_ngcontent-%COMP%]   form[_ngcontent-%COMP%] {\n  margin-top: 38px;\n  display: grid;\n  gap: 22px;\n}\n.forgot-shell[_ngcontent-%COMP%]   .primary-btn[_ngcontent-%COMP%] {\n  margin-top: 2px;\n}\n.forgot-shell[_ngcontent-%COMP%]   .primary-btn[_ngcontent-%COMP%]   span[_ngcontent-%COMP%] {\n  font-size: 20px;\n}\n.back-divider[_ngcontent-%COMP%] {\n  height: 1px;\n  margin: 40px 0 24px;\n  background: var(--%NS%line);\n}\n.back-link[_ngcontent-%COMP%] {\n  display: block;\n  color: var(--%NS%text-soft);\n  font-size: 13px;\n  font-weight: 700;\n  text-align: center;\n}\n@media (max-width: 600px) {\n  .forgot-page[_ngcontent-%COMP%] {\n    padding: 60px 18px 28px;\n  }\n  .forgot-shell[_ngcontent-%COMP%] {\n    padding: 34px 22px;\n    border-radius: 23px;\n  }\n}\n/*# sourceMappingURL=forgot-password.css.map */"] });
};
(() => {
  (typeof ngDevMode === "undefined" || ngDevMode) && setClassMetadata(ForgotPassword, [{
    type: Component,
    args: [{ selector: "app-forgot-password", imports: [FormsModule, RouterLink], template: `<div class="auth-page forgot-page">\r
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
`, styles: ["/* src/app/features/auth/pages/forgot-password/forgot-password.css */\n.forgot-page {\n  background:\n    radial-gradient(\n      circle at 14% 84%,\n      color-mix(in srgb, var(--brand) 7%, transparent),\n      transparent 30%),\n    var(--bg);\n}\n.forgot-shell {\n  width: min(100%, 530px);\n  padding: 48px;\n  border-radius: 28px;\n  background: var(--surface);\n  box-shadow: var(--shadow-lg);\n}\n.forgot-shell .auth-icon {\n  margin-bottom: 28px;\n}\n.forgot-shell .auth-copy {\n  max-width: 480px;\n  font-size: 16px;\n}\n.forgot-shell form {\n  margin-top: 38px;\n  display: grid;\n  gap: 22px;\n}\n.forgot-shell .primary-btn {\n  margin-top: 2px;\n}\n.forgot-shell .primary-btn span {\n  font-size: 20px;\n}\n.back-divider {\n  height: 1px;\n  margin: 40px 0 24px;\n  background: var(--line);\n}\n.back-link {\n  display: block;\n  color: var(--text-soft);\n  font-size: 13px;\n  font-weight: 700;\n  text-align: center;\n}\n@media (max-width: 600px) {\n  .forgot-page {\n    padding: 60px 18px 28px;\n  }\n  .forgot-shell {\n    padding: 34px 22px;\n    border-radius: 23px;\n  }\n}\n/*# sourceMappingURL=forgot-password.css.map */\n"] }]
  }], () => [{ type: Router }], null);
})();
(() => {
  (typeof ngDevMode === "undefined" || ngDevMode) && \u0275setClassDebugInfo(ForgotPassword, { className: "ForgotPassword", filePath: "src/app/features/auth/pages/forgot-password/forgot-password.ts", lineNumber: 11 });
})();
export {
  ForgotPassword
};
//# debugId=5271d21a-7898-5b18-a246-ea9468a0ee78
//# sourceMappingURL=chunk-HVA32T7P.js.map
