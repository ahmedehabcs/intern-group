import {
  ActivatedRoute,
  Router
} from "./chunk-TBWUJ4NH.js";
import {
  FormsModule,
  NgControlStatusGroup,
  NgForm,
  ɵNgNoValidate
} from "./chunk-PU67HFL7.js";
import {
  CommonModule,
  Component,
  ViewChildren,
  setClassMetadata,
  ɵsetClassDebugInfo,
  ɵɵadvance,
  ɵɵattribute,
  ɵɵdefineComponent,
  ɵɵdirectiveInject,
  ɵɵelement,
  ɵɵelementEnd,
  ɵɵelementStart,
  ɵɵgetCurrentView,
  ɵɵlistener,
  ɵɵloadQuery,
  ɵɵnamespaceHTML,
  ɵɵnamespaceSVG,
  ɵɵnextContext,
  ɵɵproperty,
  ɵɵqueryRefresh,
  ɵɵrepeater,
  ɵɵrepeaterCreate,
  ɵɵrepeaterTrackByIndex,
  ɵɵresetView,
  ɵɵrestoreView,
  ɵɵtext,
  ɵɵtextInterpolate,
  ɵɵviewQuery
} from "./chunk-XUN6663C.js";
import "./chunk-GOMI4DH3.js";

// src/app/features/auth/pages/otp-verification/otp-verification.ts
var _c0 = ["otpInput"];
function OtpVerification_For_18_Template(rf, ctx) {
  if (rf & 1) {
    const _r1 = \u0275\u0275getCurrentView();
    \u0275\u0275elementStart(0, "input", 17, 0);
    \u0275\u0275listener("input", function OtpVerification_For_18_Template_input_input_0_listener($event) {
      const $index_r2 = \u0275\u0275restoreView(_r1).$index;
      const ctx_r2 = \u0275\u0275nextContext();
      return \u0275\u0275resetView(ctx_r2.move($index_r2, $event));
    })("keydown", function OtpVerification_For_18_Template_input_keydown_0_listener($event) {
      const $index_r2 = \u0275\u0275restoreView(_r1).$index;
      const ctx_r2 = \u0275\u0275nextContext();
      return \u0275\u0275resetView(ctx_r2.keydown($index_r2, $event));
    });
    \u0275\u0275elementEnd();
  }
  if (rf & 2) {
    const $index_r2 = ctx.$index;
    const ctx_r2 = \u0275\u0275nextContext();
    \u0275\u0275property("value", ctx_r2.digits[$index_r2]);
    \u0275\u0275attribute("aria-label", "Code digit " + ($index_r2 + 1));
  }
}
var OtpVerification = class _OtpVerification {
  constructor(route, router) {
    this.router = router;
    this.email = route.snapshot.queryParamMap.get("email") || "a.design@studio.com";
    window.setInterval(() => {
      if (this.seconds > 0)
        this.seconds--;
    }, 1e3);
  }
  router;
  inputs;
  digits = ["", "", "", "", "", ""];
  email;
  seconds = 118;
  move(index, event) {
    const input = event.target;
    this.digits[index] = input.value.replace(/\D/g, "").slice(-1);
    if (this.digits[index] && index < 5)
      this.inputs.get(index + 1)?.nativeElement.focus();
  }
  keydown(index, event) {
    if (event.key === "Backspace" && !this.digits[index] && index > 0) {
      this.inputs.get(index - 1)?.nativeElement.focus();
    }
  }
  verify() {
    this.router.navigateByUrl("/");
  }
  get time() {
    return `${Math.floor(this.seconds / 60).toString().padStart(2, "0")}:${(this.seconds % 60).toString().padStart(2, "0")}`;
  }
  static \u0275fac = function OtpVerification_Factory(__ngFactoryType__) {
    return new (__ngFactoryType__ || _OtpVerification)(\u0275\u0275directiveInject(ActivatedRoute), \u0275\u0275directiveInject(Router));
  };
  static \u0275cmp = /* @__PURE__ */ \u0275\u0275defineComponent({ type: _OtpVerification, selectors: [["app-otp-verification"]], viewQuery: function OtpVerification_Query(rf, ctx) {
    if (rf & 1) {
      \u0275\u0275viewQuery(_c0, 5);
    }
    if (rf & 2) {
      let _t;
      \u0275\u0275queryRefresh(_t = \u0275\u0275loadQuery()) && (ctx.inputs = _t);
    }
  }, decls: 31, vars: 3, consts: [["otpInput", ""], [1, "auth-page", "otp-page"], [1, "otp-card", 3, "ngSubmit"], [1, "otp-icon"], ["viewBox", "0 0 24 24"], ["d", "M12 22s8-4 8-10V5l-8-3-8 3v7c0 6 8 10 8 10Z"], ["x", "8.5", "y", "10", "width", "7", "height", "6", "rx", "1"], ["d", "M10 10V8.5a2 2 0 0 1 4 0V10"], [1, "auth-heading"], [1, "auth-copy"], [1, "otp-inputs"], ["type", "text", "inputmode", "numeric", "maxlength", "1", 3, "value"], [1, "timer"], [1, "resend"], ["type", "button", 3, "disabled"], ["type", "submit", 1, "primary-btn", "verify-button"], [1, "secure"], ["type", "text", "inputmode", "numeric", "maxlength", "1", 3, "input", "keydown", "value"]], template: function OtpVerification_Template(rf, ctx) {
    if (rf & 1) {
      \u0275\u0275elementStart(0, "div", 1)(1, "form", 2);
      \u0275\u0275listener("ngSubmit", function OtpVerification_Template_form_ngSubmit_1_listener() {
        return ctx.verify();
      });
      \u0275\u0275elementStart(2, "div", 3);
      \u0275\u0275namespaceSVG();
      \u0275\u0275elementStart(3, "svg", 4);
      \u0275\u0275element(4, "path", 5)(5, "rect", 6)(6, "path", 7);
      \u0275\u0275elementEnd();
      \u0275\u0275namespaceHTML();
      \u0275\u0275elementStart(7, "span");
      \u0275\u0275text(8, "\u2709");
      \u0275\u0275elementEnd()();
      \u0275\u0275elementStart(9, "h1", 8);
      \u0275\u0275text(10, "Check your email");
      \u0275\u0275elementEnd();
      \u0275\u0275elementStart(11, "p", 9);
      \u0275\u0275text(12, "Enter the 6-digit code sent to");
      \u0275\u0275element(13, "br");
      \u0275\u0275elementStart(14, "strong");
      \u0275\u0275text(15);
      \u0275\u0275elementEnd()();
      \u0275\u0275elementStart(16, "div", 10);
      \u0275\u0275repeaterCreate(17, OtpVerification_For_18_Template, 2, 2, "input", 11, \u0275\u0275repeaterTrackByIndex);
      \u0275\u0275elementEnd();
      \u0275\u0275elementStart(19, "div", 12);
      \u0275\u0275text(20, "\u25F7 ");
      \u0275\u0275elementStart(21, "strong");
      \u0275\u0275text(22);
      \u0275\u0275elementEnd()();
      \u0275\u0275elementStart(23, "p", 13);
      \u0275\u0275text(24, "Didn't receive a code? ");
      \u0275\u0275elementStart(25, "button", 14);
      \u0275\u0275text(26, "Resend Code");
      \u0275\u0275elementEnd()();
      \u0275\u0275elementStart(27, "button", 15);
      \u0275\u0275text(28, "Verify & Proceed");
      \u0275\u0275elementEnd();
      \u0275\u0275elementStart(29, "span", 16);
      \u0275\u0275text(30, "Secure verification");
      \u0275\u0275elementEnd()()();
    }
    if (rf & 2) {
      \u0275\u0275advance(15);
      \u0275\u0275textInterpolate(ctx.email);
      \u0275\u0275advance(2);
      \u0275\u0275repeater(ctx.digits);
      \u0275\u0275advance(5);
      \u0275\u0275textInterpolate(ctx.time);
      \u0275\u0275advance(3);
      \u0275\u0275property("disabled", ctx.seconds > 0);
    }
  }, dependencies: [CommonModule, FormsModule, \u0275NgNoValidate, NgControlStatusGroup, NgForm], styles: ["\n.otp-page[_ngcontent-%COMP%] {\n  height: 100dvh;\n  min-height: 580px;\n  overflow: hidden;\n  background:\n    radial-gradient(\n      circle at 100% 80%,\n      color-mix(in srgb, var(--%NS%brand) 7%, transparent),\n      transparent 30%),\n    var(--%NS%bg);\n}\n.otp-card[_ngcontent-%COMP%] {\n  width: min(100%, 420px);\n  display: flex;\n  flex-direction: column;\n  align-items: center;\n  padding: 30px 32px 28px;\n  border-radius: 24px;\n  background: var(--%NS%surface);\n  box-shadow: var(--%NS%shadow-lg);\n  text-align: center;\n}\n.otp-icon[_ngcontent-%COMP%] {\n  position: relative;\n  width: 94px;\n  height: 94px;\n  display: grid;\n  place-items: center;\n  margin-bottom: 20px;\n  border: 9px solid var(--%NS%surface-3);\n  border-radius: 50%;\n  background: var(--%NS%surface-2);\n  color: var(--%NS%brand);\n}\n.otp-icon[_ngcontent-%COMP%]   svg[_ngcontent-%COMP%] {\n  width: 38px;\n  height: 38px;\n  fill: var(--%NS%brand);\n  stroke: #fff;\n  stroke-linecap: round;\n  stroke-linejoin: round;\n  stroke-width: 1.4;\n}\n.otp-icon[_ngcontent-%COMP%]    > span[_ngcontent-%COMP%] {\n  position: absolute;\n  top: 27px;\n  right: 27px;\n  width: 23px;\n  height: 23px;\n  display: grid;\n  place-items: center;\n  border-radius: 50%;\n  background: var(--%NS%brand-strong);\n  color: #fff;\n  font-size: 13px;\n}\n.otp-card[_ngcontent-%COMP%]   .auth-copy[_ngcontent-%COMP%]   strong[_ngcontent-%COMP%] {\n  color: var(--%NS%text);\n}\n.otp-card[_ngcontent-%COMP%]   .auth-heading[_ngcontent-%COMP%] {\n  font-size: 30px;\n}\n.otp-card[_ngcontent-%COMP%]   .auth-copy[_ngcontent-%COMP%] {\n  font-size: 14px;\n}\n.otp-inputs[_ngcontent-%COMP%] {\n  width: 100%;\n  margin-top: 25px;\n  display: grid;\n  grid-template-columns: repeat(6, 1fr);\n  gap: 8px;\n}\n.otp-inputs[_ngcontent-%COMP%]   input[_ngcontent-%COMP%] {\n  width: 100%;\n  height: 56px;\n  border: 2px solid transparent;\n  border-radius: 12px;\n  outline: 0;\n  background: var(--%NS%surface-2);\n  color: var(--%NS%text);\n  font-size: 22px;\n  font-weight: 700;\n  text-align: center;\n}\n.otp-inputs[_ngcontent-%COMP%]   input[_ngcontent-%COMP%]:focus {\n  border-color: var(--%NS%brand);\n  background: var(--%NS%surface);\n}\n.timer[_ngcontent-%COMP%] {\n  margin-top: 22px;\n  padding: 7px 16px;\n  border-radius: 22px;\n  background: var(--%NS%surface-2);\n  color: var(--%NS%brand);\n  font-size: 13px;\n}\n.timer[_ngcontent-%COMP%]   strong[_ngcontent-%COMP%] {\n  margin-left: 7px;\n  color: var(--%NS%text-soft);\n}\n.resend[_ngcontent-%COMP%] {\n  margin: 12px 0 22px;\n  color: var(--%NS%muted);\n  font-size: 13px;\n  font-weight: 600;\n}\n.resend[_ngcontent-%COMP%]   button[_ngcontent-%COMP%] {\n  padding: 0;\n  border: 0;\n  border-bottom: 1px solid currentColor;\n  background: transparent;\n  color: var(--%NS%text-soft);\n  cursor: pointer;\n  font-weight: 700;\n}\n.resend[_ngcontent-%COMP%]   button[_ngcontent-%COMP%]:disabled {\n  opacity: 0.55;\n}\n.verify-button[_ngcontent-%COMP%] {\n  width: 100%;\n}\n.secure[_ngcontent-%COMP%] {\n  margin-top: 22px;\n  color: var(--%NS%muted);\n  font-size: 10px;\n  letter-spacing: 0.24em;\n  text-transform: uppercase;\n}\n@media (max-width: 520px) {\n  .otp-page[_ngcontent-%COMP%] {\n    height: auto;\n    min-height: 100dvh;\n    padding: 70px 14px 24px;\n    overflow: visible;\n  }\n  .otp-card[_ngcontent-%COMP%] {\n    width: 100%;\n    padding: 28px 16px 25px;\n  }\n  .otp-icon[_ngcontent-%COMP%] {\n    width: 82px;\n    height: 82px;\n    margin-bottom: 17px;\n  }\n  .otp-inputs[_ngcontent-%COMP%] {\n    gap: 6px;\n  }\n  .otp-inputs[_ngcontent-%COMP%]   input[_ngcontent-%COMP%] {\n    min-width: 0;\n    height: clamp(46px, 13vw, 54px);\n    border-radius: 10px;\n    font-size: 19px;\n  }\n}\n@media (max-width: 360px) {\n  .otp-card[_ngcontent-%COMP%] {\n    padding-inline: 12px;\n  }\n  .otp-inputs[_ngcontent-%COMP%] {\n    gap: 4px;\n  }\n  .otp-card[_ngcontent-%COMP%]   .auth-heading[_ngcontent-%COMP%] {\n    font-size: 27px;\n  }\n}\n/*# sourceMappingURL=otp-verification.css.map */"] });
};
(() => {
  (typeof ngDevMode === "undefined" || ngDevMode) && setClassMetadata(OtpVerification, [{
    type: Component,
    args: [{ selector: "app-otp-verification", imports: [CommonModule, FormsModule], template: `<div class="auth-page otp-page">\r
  <form class="otp-card" (ngSubmit)="verify()">\r
    <div class="otp-icon">\r
      <svg viewBox="0 0 24 24"><path d="M12 22s8-4 8-10V5l-8-3-8 3v7c0 6 8 10 8 10Z"></path><rect x="8.5" y="10" width="7" height="6" rx="1"></rect><path d="M10 10V8.5a2 2 0 0 1 4 0V10"></path></svg>\r
      <span>\u2709</span>\r
    </div>\r
\r
    <h1 class="auth-heading">Check your email</h1>\r
    <p class="auth-copy">Enter the 6-digit code sent to<br /><strong>{{ email }}</strong></p>\r
\r
    <div class="otp-inputs">\r
      @for (digit of digits; track $index) {\r
        <input\r
          #otpInput\r
          type="text"\r
          inputmode="numeric"\r
          maxlength="1"\r
          [value]="digits[$index]"\r
          (input)="move($index, $event)"\r
          (keydown)="keydown($index, $event)"\r
          [attr.aria-label]="'Code digit ' + ($index + 1)"\r
        />\r
      }\r
    </div>\r
\r
    <div class="timer">\u25F7 <strong>{{ time }}</strong></div>\r
    <p class="resend">Didn't receive a code? <button type="button" [disabled]="seconds > 0">Resend Code</button></p>\r
    <button class="primary-btn verify-button" type="submit">Verify & Proceed</button>\r
    <span class="secure">Secure verification</span>\r
  </form>\r
</div>\r
`, styles: ["/* src/app/features/auth/pages/otp-verification/otp-verification.css */\n.otp-page {\n  height: 100dvh;\n  min-height: 580px;\n  overflow: hidden;\n  background:\n    radial-gradient(\n      circle at 100% 80%,\n      color-mix(in srgb, var(--brand) 7%, transparent),\n      transparent 30%),\n    var(--bg);\n}\n.otp-card {\n  width: min(100%, 420px);\n  display: flex;\n  flex-direction: column;\n  align-items: center;\n  padding: 30px 32px 28px;\n  border-radius: 24px;\n  background: var(--surface);\n  box-shadow: var(--shadow-lg);\n  text-align: center;\n}\n.otp-icon {\n  position: relative;\n  width: 94px;\n  height: 94px;\n  display: grid;\n  place-items: center;\n  margin-bottom: 20px;\n  border: 9px solid var(--surface-3);\n  border-radius: 50%;\n  background: var(--surface-2);\n  color: var(--brand);\n}\n.otp-icon svg {\n  width: 38px;\n  height: 38px;\n  fill: var(--brand);\n  stroke: #fff;\n  stroke-linecap: round;\n  stroke-linejoin: round;\n  stroke-width: 1.4;\n}\n.otp-icon > span {\n  position: absolute;\n  top: 27px;\n  right: 27px;\n  width: 23px;\n  height: 23px;\n  display: grid;\n  place-items: center;\n  border-radius: 50%;\n  background: var(--brand-strong);\n  color: #fff;\n  font-size: 13px;\n}\n.otp-card .auth-copy strong {\n  color: var(--text);\n}\n.otp-card .auth-heading {\n  font-size: 30px;\n}\n.otp-card .auth-copy {\n  font-size: 14px;\n}\n.otp-inputs {\n  width: 100%;\n  margin-top: 25px;\n  display: grid;\n  grid-template-columns: repeat(6, 1fr);\n  gap: 8px;\n}\n.otp-inputs input {\n  width: 100%;\n  height: 56px;\n  border: 2px solid transparent;\n  border-radius: 12px;\n  outline: 0;\n  background: var(--surface-2);\n  color: var(--text);\n  font-size: 22px;\n  font-weight: 700;\n  text-align: center;\n}\n.otp-inputs input:focus {\n  border-color: var(--brand);\n  background: var(--surface);\n}\n.timer {\n  margin-top: 22px;\n  padding: 7px 16px;\n  border-radius: 22px;\n  background: var(--surface-2);\n  color: var(--brand);\n  font-size: 13px;\n}\n.timer strong {\n  margin-left: 7px;\n  color: var(--text-soft);\n}\n.resend {\n  margin: 12px 0 22px;\n  color: var(--muted);\n  font-size: 13px;\n  font-weight: 600;\n}\n.resend button {\n  padding: 0;\n  border: 0;\n  border-bottom: 1px solid currentColor;\n  background: transparent;\n  color: var(--text-soft);\n  cursor: pointer;\n  font-weight: 700;\n}\n.resend button:disabled {\n  opacity: 0.55;\n}\n.verify-button {\n  width: 100%;\n}\n.secure {\n  margin-top: 22px;\n  color: var(--muted);\n  font-size: 10px;\n  letter-spacing: 0.24em;\n  text-transform: uppercase;\n}\n@media (max-width: 520px) {\n  .otp-page {\n    height: auto;\n    min-height: 100dvh;\n    padding: 70px 14px 24px;\n    overflow: visible;\n  }\n  .otp-card {\n    width: 100%;\n    padding: 28px 16px 25px;\n  }\n  .otp-icon {\n    width: 82px;\n    height: 82px;\n    margin-bottom: 17px;\n  }\n  .otp-inputs {\n    gap: 6px;\n  }\n  .otp-inputs input {\n    min-width: 0;\n    height: clamp(46px, 13vw, 54px);\n    border-radius: 10px;\n    font-size: 19px;\n  }\n}\n@media (max-width: 360px) {\n  .otp-card {\n    padding-inline: 12px;\n  }\n  .otp-inputs {\n    gap: 4px;\n  }\n  .otp-card .auth-heading {\n    font-size: 27px;\n  }\n}\n/*# sourceMappingURL=otp-verification.css.map */\n"] }]
  }], () => [{ type: ActivatedRoute }, { type: Router }], { inputs: [{
    type: ViewChildren,
    args: ["otpInput"]
  }] });
})();
(() => {
  (typeof ngDevMode === "undefined" || ngDevMode) && \u0275setClassDebugInfo(OtpVerification, { className: "OtpVerification", filePath: "src/app/features/auth/pages/otp-verification/otp-verification.ts", lineNumber: 12 });
})();
export {
  OtpVerification
};
//# debugId=d4ec326f-23c7-5c1e-8de9-0a0ea2c0783b
//# sourceMappingURL=chunk-Z2EBPNHH.js.map
