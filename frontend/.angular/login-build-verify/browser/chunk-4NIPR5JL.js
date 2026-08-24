import { Router } from './chunk-TBWUJ4NH.js';
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
  ɵɵattribute,
  ɵɵclassProp,
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
  ɵɵnextContext,
  ɵɵproperty,
  ɵɵpureFunction0,
  ɵɵrepeater,
  ɵɵrepeaterCreate,
  ɵɵrepeaterTrackByIdentity,
  ɵɵtext,
  ɵɵtextInterpolate,
  ɵɵtwoWayBindingSet,
  ɵɵtwoWayListener,
  ɵɵtwoWayProperty,
} from './chunk-XUN6663C.js';
import './chunk-GOMI4DH3.js';

// src/app/features/auth/pages/reset-password/reset-password.ts
var _c0 = () => [1, 2, 3, 4];
function ResetPassword_For_28_Template(rf, ctx) {
  if (rf & 1) {
    ɵɵelement(0, 'span');
  }
  if (rf & 2) {
    const part_r1 = ctx.$implicit;
    const ctx_r1 = ɵɵnextContext();
    ɵɵclassProp('active', ctx_r1.strength >= part_r1);
  }
}
var ResetPassword = class _ResetPassword {
  constructor(router) {
    this.router = router;
  }
  router;
  password = '';
  confirm = '';
  showPassword = false;
  showConfirm = false;
  get hasLength() {
    return this.password.length >= 8;
  }
  get hasMixed() {
    return /[A-Za-z]/.test(this.password) && /\d/.test(this.password);
  }
  get hasSpecial() {
    return /[^A-Za-z0-9]/.test(this.password);
  }
  get strength() {
    return [this.hasLength, this.hasMixed, this.hasSpecial, this.password.length >= 12].filter(
      Boolean,
    ).length;
  }
  get strengthLabel() {
    return ['TOO WEAK', 'WEAK', 'FAIR', 'STRONG', 'VERY STRONG'][this.strength];
  }
  submit() {
    this.router.navigateByUrl('/auth/login');
  }
  static ɵfac = function ResetPassword_Factory(__ngFactoryType__) {
    return new (__ngFactoryType__ || _ResetPassword)(ɵɵdirectiveInject(Router));
  };
  static ɵcmp = /* @__PURE__ */ ɵɵdefineComponent({
    type: _ResetPassword,
    selectors: [['app-reset-password']],
    decls: 55,
    vars: 19,
    consts: [
      [1, 'auth-page', 'reset-page'],
      [1, 'reset-shell'],
      [1, 'auth-icon'],
      ['viewBox', '0 0 24 24'],
      ['d', 'M4 7V4m0 0h3M4 4l4 4a7 7 0 1 1-1.5 7'],
      ['x', '9', 'y', '10', 'width', '7', 'height', '6', 'rx', '1'],
      ['d', 'M10.5 10V8.5a2 2 0 0 1 4 0V10'],
      [1, 'auth-heading'],
      [1, 'auth-copy'],
      [3, 'ngSubmit'],
      [1, 'field'],
      ['for', 'new-password'],
      [1, 'input-shell'],
      [
        'id',
        'new-password',
        'name',
        'password',
        'placeholder',
        '\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022',
        'required',
        '',
        3,
        'ngModelChange',
        'type',
        'ngModel',
      ],
      ['type', 'button', 'aria-label', 'Toggle password visibility', 1, 'eye-button', 3, 'click'],
      ['d', 'M2 12s3.5-6 10-6 10 6 10 6-3.5 6-10 6S2 12 2 12Z'],
      ['cx', '12', 'cy', '12', 'r', '2.5'],
      [1, 'strength-head'],
      [1, 'strength-bar'],
      [3, 'active'],
      [1, 'requirements'],
      [1, 'field', 'confirm-field'],
      ['for', 'confirm-password'],
      [
        'id',
        'confirm-password',
        'name',
        'confirm',
        'placeholder',
        '\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022',
        'required',
        '',
        3,
        'ngModelChange',
        'type',
        'ngModel',
      ],
      [
        'type',
        'button',
        'aria-label',
        'Toggle confirm password visibility',
        1,
        'eye-button',
        3,
        'click',
      ],
      ['type', 'submit', 1, 'primary-btn', 3, 'disabled'],
    ],
    template: function ResetPassword_Template(rf, ctx) {
      if (rf & 1) {
        ɵɵelementStart(0, 'div', 0)(1, 'section', 1)(2, 'div', 2);
        ɵɵnamespaceSVG();
        ɵɵelementStart(3, 'svg', 3);
        ɵɵelement(4, 'path', 4)(5, 'rect', 5)(6, 'path', 6);
        ɵɵelementEnd()();
        ɵɵnamespaceHTML();
        ɵɵelementStart(7, 'h1', 7);
        ɵɵtext(8, 'Secure Your Account');
        ɵɵelementEnd();
        ɵɵelementStart(9, 'p', 8);
        ɵɵtext(
          10,
          'Choose a strong, unique password to protect your culinary preferences and account details.',
        );
        ɵɵelementEnd();
        ɵɵelementStart(11, 'form', 9);
        ɵɵlistener('ngSubmit', function ResetPassword_Template_form_ngSubmit_11_listener() {
          return ctx.submit();
        });
        ɵɵelementStart(12, 'div', 10)(13, 'label', 11);
        ɵɵtext(14, 'NEW PASSWORD');
        ɵɵelementEnd();
        ɵɵelementStart(15, 'div', 12)(16, 'input', 13);
        ɵɵtwoWayListener(
          'ngModelChange',
          function ResetPassword_Template_input_ngModelChange_16_listener($event) {
            ɵɵtwoWayBindingSet(ctx.password, $event) || (ctx.password = $event);
            return $event;
          },
        );
        ɵɵelementEnd();
        ɵɵcontrolCreate();
        ɵɵelementStart(17, 'button', 14);
        ɵɵlistener('click', function ResetPassword_Template_button_click_17_listener() {
          return (ctx.showPassword = !ctx.showPassword);
        });
        ɵɵnamespaceSVG();
        ɵɵelementStart(18, 'svg', 3);
        ɵɵelement(19, 'path', 15)(20, 'circle', 16);
        ɵɵelementEnd()()()();
        ɵɵnamespaceHTML();
        ɵɵelementStart(21, 'div', 17)(22, 'span');
        ɵɵtext(23, 'Password strength');
        ɵɵelementEnd();
        ɵɵelementStart(24, 'strong');
        ɵɵtext(25);
        ɵɵelementEnd()();
        ɵɵelementStart(26, 'div', 18);
        ɵɵrepeaterCreate(
          27,
          ResetPassword_For_28_Template,
          1,
          2,
          'span',
          19,
          ɵɵrepeaterTrackByIdentity,
        );
        ɵɵelementEnd();
        ɵɵelementStart(29, 'ul', 20)(30, 'li')(31, 'span');
        ɵɵtext(32);
        ɵɵelementEnd();
        ɵɵtext(33, ' At least 8 characters');
        ɵɵelementEnd();
        ɵɵelementStart(34, 'li')(35, 'span');
        ɵɵtext(36);
        ɵɵelementEnd();
        ɵɵtext(37, ' A letter and a number');
        ɵɵelementEnd();
        ɵɵelementStart(38, 'li')(39, 'span');
        ɵɵtext(40);
        ɵɵelementEnd();
        ɵɵtext(41, ' One special character');
        ɵɵelementEnd()();
        ɵɵelementStart(42, 'div', 21)(43, 'label', 22);
        ɵɵtext(44, 'CONFIRM PASSWORD');
        ɵɵelementEnd();
        ɵɵelementStart(45, 'div', 12)(46, 'input', 23);
        ɵɵtwoWayListener(
          'ngModelChange',
          function ResetPassword_Template_input_ngModelChange_46_listener($event) {
            ɵɵtwoWayBindingSet(ctx.confirm, $event) || (ctx.confirm = $event);
            return $event;
          },
        );
        ɵɵelementEnd();
        ɵɵcontrolCreate();
        ɵɵelementStart(47, 'button', 24);
        ɵɵlistener('click', function ResetPassword_Template_button_click_47_listener() {
          return (ctx.showConfirm = !ctx.showConfirm);
        });
        ɵɵnamespaceSVG();
        ɵɵelementStart(48, 'svg', 3);
        ɵɵelement(49, 'path', 15)(50, 'circle', 16);
        ɵɵelementEnd()()()();
        ɵɵnamespaceHTML();
        ɵɵelementStart(51, 'button', 25);
        ɵɵtext(52, 'Update Password ');
        ɵɵelementStart(53, 'span');
        ɵɵtext(54, '\u2192');
        ɵɵelementEnd()()()()();
      }
      if (rf & 2) {
        ɵɵadvance(16);
        ɵɵproperty('type', ctx.showPassword ? 'text' : 'password');
        ɵɵtwoWayProperty('ngModel', ctx.password);
        ɵɵcontrol();
        ɵɵadvance(9);
        ɵɵtextInterpolate(ctx.strengthLabel);
        ɵɵadvance();
        ɵɵattribute('data-strength', ctx.strength);
        ɵɵadvance();
        ɵɵrepeater(ɵɵpureFunction0(18, _c0));
        ɵɵadvance(3);
        ɵɵclassProp('met', ctx.hasLength);
        ɵɵadvance(2);
        ɵɵtextInterpolate(ctx.hasLength ? '\u2713' : '\u25CB');
        ɵɵadvance(2);
        ɵɵclassProp('met', ctx.hasMixed);
        ɵɵadvance(2);
        ɵɵtextInterpolate(ctx.hasMixed ? '\u2713' : '\u25CB');
        ɵɵadvance(2);
        ɵɵclassProp('met', ctx.hasSpecial);
        ɵɵadvance(2);
        ɵɵtextInterpolate(ctx.hasSpecial ? '\u2713' : '\u25CB');
        ɵɵadvance(5);
        ɵɵclassProp('match', ctx.confirm && ctx.confirm === ctx.password);
        ɵɵadvance();
        ɵɵproperty('type', ctx.showConfirm ? 'text' : 'password');
        ɵɵtwoWayProperty('ngModel', ctx.confirm);
        ɵɵcontrol();
        ɵɵadvance(5);
        ɵɵproperty('disabled', ctx.strength < 2 || ctx.password !== ctx.confirm);
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
    ],
    styles: [
      '\n.reset-page[_ngcontent-%COMP%] {\n  background:\n    radial-gradient(\n      circle at 95% 80%,\n      color-mix(in srgb, var(--%NS%brand) 7%, transparent),\n      transparent 33%),\n    var(--%NS%bg);\n}\n.reset-shell[_ngcontent-%COMP%] {\n  width: min(100%, 560px);\n  padding: 48px;\n  border-radius: 28px;\n  background: var(--%NS%surface);\n  box-shadow: var(--%NS%shadow-lg);\n}\n.reset-shell[_ngcontent-%COMP%]   .auth-icon[_ngcontent-%COMP%] {\n  margin-bottom: 25px;\n}\n.reset-shell[_ngcontent-%COMP%]   .auth-copy[_ngcontent-%COMP%] {\n  font-size: 16px;\n}\n.reset-shell[_ngcontent-%COMP%]   form[_ngcontent-%COMP%] {\n  margin-top: 36px;\n}\n.eye-button[_ngcontent-%COMP%] {\n  width: 26px;\n  padding: 0;\n  border: 0;\n  background: transparent;\n  color: var(--%NS%text-soft);\n  cursor: pointer;\n}\n.strength-head[_ngcontent-%COMP%] {\n  margin-top: 16px;\n  display: flex;\n  justify-content: space-between;\n  color: var(--%NS%text-soft);\n  font-size: 10px;\n  text-transform: uppercase;\n}\n.strength-head[_ngcontent-%COMP%]   strong[_ngcontent-%COMP%] {\n  color: var(--%NS%brand);\n}\n.strength-bar[_ngcontent-%COMP%] {\n  margin-top: 8px;\n  display: grid;\n  grid-template-columns: repeat(4, 1fr);\n  gap: 3px;\n}\n.strength-bar[_ngcontent-%COMP%]   span[_ngcontent-%COMP%] {\n  height: 5px;\n  border-radius: 5px;\n  background: var(--%NS%line);\n}\n.strength-bar[_ngcontent-%COMP%]   span.active[_ngcontent-%COMP%] {\n  background: var(--%NS%brand);\n}\n.strength-bar[data-strength="3"][_ngcontent-%COMP%]   span.active[_ngcontent-%COMP%], \n.strength-bar[data-strength="4"][_ngcontent-%COMP%]   span.active[_ngcontent-%COMP%] {\n  background: var(--%NS%success);\n}\n.requirements[_ngcontent-%COMP%] {\n  margin: 12px 0 0;\n  padding: 0;\n  display: grid;\n  gap: 6px;\n  color: var(--%NS%text-soft);\n  font-size: 11px;\n  list-style: none;\n}\n.requirements[_ngcontent-%COMP%]   li[_ngcontent-%COMP%] {\n  display: flex;\n  align-items: center;\n  gap: 7px;\n}\n.requirements[_ngcontent-%COMP%]   li.met[_ngcontent-%COMP%] {\n  color: var(--%NS%success);\n}\n.requirements[_ngcontent-%COMP%]   span[_ngcontent-%COMP%] {\n  font-weight: 700;\n}\n.confirm-field[_ngcontent-%COMP%] {\n  margin-top: 26px;\n}\n.input-shell.match[_ngcontent-%COMP%] {\n  border-color: var(--%NS%success);\n}\n.reset-shell[_ngcontent-%COMP%]   .primary-btn[_ngcontent-%COMP%] {\n  width: 100%;\n  margin-top: 24px;\n}\n.reset-shell[_ngcontent-%COMP%]   .primary-btn[_ngcontent-%COMP%]   span[_ngcontent-%COMP%] {\n  font-size: 20px;\n}\n.reset-shell[_ngcontent-%COMP%]   .primary-btn[_ngcontent-%COMP%]:disabled {\n  opacity: 0.55;\n  cursor: not-allowed;\n  transform: none;\n}\n@media (max-width: 600px) {\n  .reset-page[_ngcontent-%COMP%] {\n    padding: 62px 18px 30px;\n  }\n  .reset-shell[_ngcontent-%COMP%] {\n    padding: 34px 22px;\n    border-radius: 23px;\n  }\n}\n/*# sourceMappingURL=reset-password.css.map */',
    ],
  });
};
(() => {
  (typeof ngDevMode === 'undefined' || ngDevMode) &&
    setClassMetadata(
      ResetPassword,
      [
        {
          type: Component,
          args: [
            {
              selector: 'app-reset-password',
              imports: [FormsModule],
              template: `<div class="auth-page reset-page">\r
  <section class="reset-shell">\r
    <div class="auth-icon">\r
      <svg viewBox="0 0 24 24"><path d="M4 7V4m0 0h3M4 4l4 4a7 7 0 1 1-1.5 7"></path><rect x="9" y="10" width="7" height="6" rx="1"></rect><path d="M10.5 10V8.5a2 2 0 0 1 4 0V10"></path></svg>\r
    </div>\r
    <h1 class="auth-heading">Secure Your Account</h1>\r
    <p class="auth-copy">Choose a strong, unique password to protect your culinary preferences and account details.</p>\r
\r
    <form (ngSubmit)="submit()">\r
      <div class="field">\r
        <label for="new-password">NEW PASSWORD</label>\r
        <div class="input-shell">\r
          <input id="new-password" [type]="showPassword ? 'text' : 'password'" name="password" [(ngModel)]="password" placeholder="\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022" required />\r
          <button class="eye-button" type="button" (click)="showPassword = !showPassword" aria-label="Toggle password visibility">\r
            <svg viewBox="0 0 24 24"><path d="M2 12s3.5-6 10-6 10 6 10 6-3.5 6-10 6S2 12 2 12Z"></path><circle cx="12" cy="12" r="2.5"></circle></svg>\r
          </button>\r
        </div>\r
      </div>\r
\r
      <div class="strength-head"><span>Password strength</span><strong>{{ strengthLabel }}</strong></div>\r
      <div class="strength-bar" [attr.data-strength]="strength">\r
        @for (part of [1, 2, 3, 4]; track part) {\r
          <span [class.active]="strength >= part"></span>\r
        }\r
      </div>\r
\r
      <ul class="requirements">\r
        <li [class.met]="hasLength"><span>{{ hasLength ? '\u2713' : '\u25CB' }}</span> At least 8 characters</li>\r
        <li [class.met]="hasMixed"><span>{{ hasMixed ? '\u2713' : '\u25CB' }}</span> A letter and a number</li>\r
        <li [class.met]="hasSpecial"><span>{{ hasSpecial ? '\u2713' : '\u25CB' }}</span> One special character</li>\r
      </ul>\r
\r
      <div class="field confirm-field">\r
        <label for="confirm-password">CONFIRM PASSWORD</label>\r
        <div class="input-shell" [class.match]="confirm && confirm === password">\r
          <input id="confirm-password" [type]="showConfirm ? 'text' : 'password'" name="confirm" [(ngModel)]="confirm" placeholder="\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022" required />\r
          <button class="eye-button" type="button" (click)="showConfirm = !showConfirm" aria-label="Toggle confirm password visibility">\r
            <svg viewBox="0 0 24 24"><path d="M2 12s3.5-6 10-6 10 6 10 6-3.5 6-10 6S2 12 2 12Z"></path><circle cx="12" cy="12" r="2.5"></circle></svg>\r
          </button>\r
        </div>\r
      </div>\r
\r
      <button class="primary-btn" type="submit" [disabled]="strength < 2 || password !== confirm">Update Password <span>\u2192</span></button>\r
    </form>\r
  </section>\r
</div>\r
`,
              styles: [
                '/* src/app/features/auth/pages/reset-password/reset-password.css */\n.reset-page {\n  background:\n    radial-gradient(\n      circle at 95% 80%,\n      color-mix(in srgb, var(--brand) 7%, transparent),\n      transparent 33%),\n    var(--bg);\n}\n.reset-shell {\n  width: min(100%, 560px);\n  padding: 48px;\n  border-radius: 28px;\n  background: var(--surface);\n  box-shadow: var(--shadow-lg);\n}\n.reset-shell .auth-icon {\n  margin-bottom: 25px;\n}\n.reset-shell .auth-copy {\n  font-size: 16px;\n}\n.reset-shell form {\n  margin-top: 36px;\n}\n.eye-button {\n  width: 26px;\n  padding: 0;\n  border: 0;\n  background: transparent;\n  color: var(--text-soft);\n  cursor: pointer;\n}\n.strength-head {\n  margin-top: 16px;\n  display: flex;\n  justify-content: space-between;\n  color: var(--text-soft);\n  font-size: 10px;\n  text-transform: uppercase;\n}\n.strength-head strong {\n  color: var(--brand);\n}\n.strength-bar {\n  margin-top: 8px;\n  display: grid;\n  grid-template-columns: repeat(4, 1fr);\n  gap: 3px;\n}\n.strength-bar span {\n  height: 5px;\n  border-radius: 5px;\n  background: var(--line);\n}\n.strength-bar span.active {\n  background: var(--brand);\n}\n.strength-bar[data-strength="3"] span.active,\n.strength-bar[data-strength="4"] span.active {\n  background: var(--success);\n}\n.requirements {\n  margin: 12px 0 0;\n  padding: 0;\n  display: grid;\n  gap: 6px;\n  color: var(--text-soft);\n  font-size: 11px;\n  list-style: none;\n}\n.requirements li {\n  display: flex;\n  align-items: center;\n  gap: 7px;\n}\n.requirements li.met {\n  color: var(--success);\n}\n.requirements span {\n  font-weight: 700;\n}\n.confirm-field {\n  margin-top: 26px;\n}\n.input-shell.match {\n  border-color: var(--success);\n}\n.reset-shell .primary-btn {\n  width: 100%;\n  margin-top: 24px;\n}\n.reset-shell .primary-btn span {\n  font-size: 20px;\n}\n.reset-shell .primary-btn:disabled {\n  opacity: 0.55;\n  cursor: not-allowed;\n  transform: none;\n}\n@media (max-width: 600px) {\n  .reset-page {\n    padding: 62px 18px 30px;\n  }\n  .reset-shell {\n    padding: 34px 22px;\n    border-radius: 23px;\n  }\n}\n/*# sourceMappingURL=reset-password.css.map */\n',
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
    ɵsetClassDebugInfo(ResetPassword, {
      className: 'ResetPassword',
      filePath: 'src/app/features/auth/pages/reset-password/reset-password.ts',
      lineNumber: 11,
    });
})();
export { ResetPassword };
//# debugId=cf391a28-0ca8-5a2a-a490-20ad5d83093f
//# sourceMappingURL=chunk-4NIPR5JL.js.map
