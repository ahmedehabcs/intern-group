import {
  AuthService,
  FormField,
  email,
  form,
  minLength,
  pattern,
  required,
  submit,
  validate
} from "./chunk-3G2ZU4HQ.js";
import {
  RouterLink
} from "./chunk-TBWUJ4NH.js";
import "./chunk-PU67HFL7.js";
import {
  ChangeDetectionStrategy,
  Component,
  HttpErrorResponse,
  firstValueFrom,
  inject,
  setClassMetadata,
  signal,
  ɵsetClassDebugInfo,
  ɵɵadvance,
  ɵɵclassProp,
  ɵɵconditional,
  ɵɵconditionalCreate,
  ɵɵcontrol,
  ɵɵcontrolCreate,
  ɵɵdefineComponent,
  ɵɵelement,
  ɵɵelementEnd,
  ɵɵelementStart,
  ɵɵlistener,
  ɵɵnamespaceHTML,
  ɵɵnamespaceSVG,
  ɵɵnextContext,
  ɵɵproperty,
  ɵɵtext,
  ɵɵtextInterpolate1
} from "./chunk-XUN6663C.js";
import "./chunk-GOMI4DH3.js";

// src/app/features/auth/pages/register/register.ts
function Register_Conditional_32_Template(rf, ctx) {
  if (rf & 1) {
    \u0275\u0275elementStart(0, "p", 20);
    \u0275\u0275text(1);
    \u0275\u0275elementEnd();
  }
  if (rf & 2) {
    const ctx_r0 = \u0275\u0275nextContext();
    \u0275\u0275advance();
    \u0275\u0275textInterpolate1(" ", ctx_r0.registerForm.username().errors()[0]?.message, " ");
  }
}
function Register_Conditional_37_Template(rf, ctx) {
  if (rf & 1) {
    \u0275\u0275elementStart(0, "p", 20);
    \u0275\u0275text(1);
    \u0275\u0275elementEnd();
  }
  if (rf & 2) {
    const ctx_r0 = \u0275\u0275nextContext();
    \u0275\u0275advance();
    \u0275\u0275textInterpolate1(" ", ctx_r0.registerForm.email().errors()[0]?.message, " ");
  }
}
function Register_Conditional_43_Template(rf, ctx) {
  if (rf & 1) {
    \u0275\u0275elementStart(0, "p", 20);
    \u0275\u0275text(1);
    \u0275\u0275elementEnd();
  }
  if (rf & 2) {
    const ctx_r0 = \u0275\u0275nextContext();
    \u0275\u0275advance();
    \u0275\u0275textInterpolate1(" ", ctx_r0.registerForm.password().errors()[0]?.message, " ");
  }
}
function Register_Conditional_48_Template(rf, ctx) {
  if (rf & 1) {
    \u0275\u0275elementStart(0, "p", 20);
    \u0275\u0275text(1);
    \u0275\u0275elementEnd();
  }
  if (rf & 2) {
    const ctx_r0 = \u0275\u0275nextContext();
    \u0275\u0275advance();
    \u0275\u0275textInterpolate1(" ", ctx_r0.registerForm.confirmPassword().errors()[0]?.message, " ");
  }
}
function Register_Conditional_49_Template(rf, ctx) {
  if (rf & 1) {
    \u0275\u0275elementStart(0, "div", 28);
    \u0275\u0275text(1);
    \u0275\u0275elementEnd();
  }
  if (rf & 2) {
    const ctx_r0 = \u0275\u0275nextContext();
    \u0275\u0275advance();
    \u0275\u0275textInterpolate1(" ", ctx_r0.requestError(), " ");
  }
}
function Register_Conditional_51_Template(rf, ctx) {
  if (rf & 1) {
    \u0275\u0275namespaceSVG();
    \u0275\u0275elementStart(0, "svg", 37);
    \u0275\u0275element(1, "circle", 38)(2, "path", 39);
    \u0275\u0275elementEnd();
    \u0275\u0275namespaceHTML();
    \u0275\u0275elementStart(3, "span");
    \u0275\u0275text(4, "Creating account...");
    \u0275\u0275elementEnd();
  }
}
function Register_Conditional_52_Template(rf, ctx) {
  if (rf & 1) {
    \u0275\u0275elementStart(0, "span");
    \u0275\u0275text(1, "Create Account");
    \u0275\u0275elementEnd();
    \u0275\u0275elementStart(2, "span", 40);
    \u0275\u0275text(3, "\u2192");
    \u0275\u0275elementEnd();
  }
}
var Register = class _Register {
  AuthService = inject(AuthService);
  requestError = signal(
    null,
    ...ngDevMode ? [{ debugName: "requestError" }] : (
      /* istanbul ignore next */
      []
    )
  );
  RegisterModel = signal(
    {
      username: "",
      email: "",
      password: "",
      confirmPassword: ""
    },
    ...ngDevMode ? [{ debugName: "RegisterModel" }] : (
      /* istanbul ignore next */
      []
    )
  );
  registerForm = form(this.RegisterModel, (field) => {
    required(field.username, { message: "Username is required." });
    minLength(field.username, 3, { message: "Username must contain at least 3 characters." });
    pattern(field.username, /^[a-zA-Z0-9_]+$/, { message: "Use only letters, numbers, and underscores." });
    required(field.email, { message: "Email is required." });
    email(field.email, { message: "Enter a valid email address." });
    required(field.password, { message: "Password is required." });
    minLength(field.password, 8, { message: "Password must contain at least 8 characters." });
    pattern(field.password, /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).+$/, { message: "Password must include an uppercase letter, lowercase letter, and number." });
    required(field.confirmPassword, { message: "Please confirm your password." });
    validate(field.confirmPassword, ({ value, valueOf }) => {
      if (value() !== valueOf(field.password)) {
        return { kind: "passwordMismatch", message: "Passwords do not match." };
      }
      return null;
    });
  });
  async onSubmit(even) {
    even.preventDefault();
    this.requestError.set(null);
    await submit(this.registerForm, async () => {
      const formValue = this.RegisterModel();
      const request = {
        username: formValue.username,
        email: formValue.email,
        password: formValue.password
      };
      try {
        const response = await firstValueFrom(this.AuthService.register(request));
        console.log("Registration request:", response);
        this.resetForm();
      } catch (error) {
        if (error instanceof HttpErrorResponse) {
          this.requestError.set(error.error?.message ?? "Registration failed.");
        } else {
          this.requestError.set("Registration failed.");
        }
      }
    });
  }
  continueWithGoogle() {
  }
  resetForm() {
    this.RegisterModel.set({
      username: "",
      email: "",
      password: "",
      confirmPassword: ""
    });
  }
  static \u0275fac = function Register_Factory(__ngFactoryType__) {
    return new (__ngFactoryType__ || _Register)();
  };
  static \u0275cmp = /* @__PURE__ */ \u0275\u0275defineComponent({ type: _Register, selectors: [["app-register"]], decls: 66, vars: 27, consts: [[1, "min-h-dvh", "w-full", "bg-(--surface)"], [1, "grid", "min-h-dvh", "w-full", "lg:grid-cols-[45%_55%]"], [1, "relative", "hidden", "min-h-dvh", "flex-col", "justify-between", "overflow-hidden", "bg-[linear-gradient(180deg,rgba(20,10,4,0.2),rgba(20,10,4,0.88)),url('/assets/images/talabaty-food-table.png')]", "bg-cover", "bg-center", "p-[clamp(2.5rem,5vw,6rem)]", "text-white", "lg:flex"], [1, "absolute", "-right-24", "-top-24", "h-72", "w-72", "rounded-full", "bg-white/10"], [1, "absolute", "-bottom-24", "-left-24", "h-80", "w-80", "rounded-full", "bg-black/10"], [1, "relative", "z-10", "flex", "items-center", "gap-3", "text-2xl", "font-bold"], [1, "flex", "h-11", "w-11", "items-center", "justify-center", "rounded-xl", "bg-(--surface)", "text-(--brand)", "shadow-(--shadow-sm)"], [1, "relative", "z-10", "max-w-xl"], [1, "mb-4", "text-sm", "font-semibold", "uppercase", "tracking-[0.25em]", "text-white/70"], [1, "font-['Playfair_Display']", "text-[clamp(2.75rem,4.5vw,5.5rem)]", "font-bold", "leading-[1.02]", "tracking-[-0.04em]"], [1, "mt-6", "max-w-lg", "text-lg", "leading-8", "text-white/85"], [1, "relative", "z-10", "text-sm", "font-medium", "text-white/70"], [1, "flex", "min-h-dvh", "w-full", "items-center", "bg-(--surface)", "px-5", "py-10", "sm:px-10", "lg:justify-start", "lg:px-[clamp(3rem,6vw,7rem)]", "lg:py-12"], [1, "w-full"], [1, "inline-flex", "rounded-full", "bg-(--brand-soft)", "px-4", "py-2", "text-xs", "font-bold", "uppercase", "tracking-[0.2em]", "text-(--brand)"], [1, "mt-5", "font-['Playfair_Display']", "text-3xl", "font-bold", "tracking-tight", "text-(--text)", "sm:text-4xl"], [1, "mt-3", "text-base", "text-(--text-soft)"], ["novalidate", "", 1, "mt-8", "space-y-5", 3, "submit"], ["for", "username", 1, "mb-2", "block", "text-sm", "font-semibold", "text-(--text)"], ["id", "username", "type", "text", "placeholder", "Enter your username", "autocomplete", "username", 1, "w-full", "rounded-xl", "border", "border-(--border)", "bg-(--surface)", "px-4", "py-3.5", "text-sm", "text-(--text)", "outline-none", "transition", "placeholder:text-(--muted)", "focus:border-(--brand)", "focus:ring-4", "focus:ring-(--brand-soft)", 3, "formField"], ["role", "alert", 1, "mt-2", "text-sm", "font-medium", "text-red-600"], ["for", "email", 1, "mb-2", "block", "text-sm", "font-semibold", "text-(--text)"], ["id", "email", "type", "email", "placeholder", "you@example.com", "autocomplete", "email", 1, "w-full", "rounded-xl", "border", "border-(--border)", "bg-(--surface)", "px-4", "py-3.5", "text-sm", "text-(--text)", "outline-none", "transition", "placeholder:text-(--muted)", "focus:border-(--brand)", "focus:ring-4", "focus:ring-(--brand-soft)", 3, "formField"], [1, "grid", "gap-5", "sm:grid-cols-2"], ["for", "password", 1, "mb-2", "block", "text-sm", "font-semibold", "text-(--text)"], ["id", "password", "type", "password", "placeholder", "Enter password", "autocomplete", "new-password", 1, "w-full", "rounded-xl", "border", "border-(--border)", "bg-(--surface)", "px-4", "py-3.5", "text-sm", "text-(--text)", "outline-none", "transition", "placeholder:text-(--muted)", "focus:border-(--brand)", "focus:ring-4", "focus:ring-(--brand-soft)", 3, "formField"], ["for", "confirmPassword", 1, "mb-2", "block", "text-sm", "font-semibold", "text-(--text)"], ["id", "confirmPassword", "type", "password", "placeholder", "Confirm password", "autocomplete", "new-password", 1, "w-full", "rounded-xl", "border", "border-(--border)", "bg-(--surface)", "px-4", "py-3.5", "text-sm", "text-(--text)", "outline-none", "transition", "placeholder:text-(--muted)", "focus:border-(--brand)", "focus:ring-4", "focus:ring-(--brand-soft)", 3, "formField"], ["role", "alert", 1, "rounded-xl", "border", "border-red-300", "bg-red-50", "px-4", "py-3", "text-sm", "font-medium", "text-red-700", "dark:border-red-900", "dark:bg-red-950/40", "dark:text-red-300"], ["type", "submit", 1, "flex", "w-full", "items-center", "justify-center", "gap-2", "rounded-xl", "bg-(--brand)", "px-5", "py-3.5", "text-sm", "font-bold", "text-white", "shadow-(--shadow-sm)", "transition", "hover:bg-(--brand-hover)", "focus:outline-none", "focus:ring-4", "focus:ring-(--brand-soft)", "disabled:cursor-not-allowed", "disabled:opacity-60", 3, "disabled"], [1, "my-7", "flex", "items-center", "gap-4"], [1, "h-px", "flex-1", "bg-(--line)"], [1, "text-xs", "font-bold", "uppercase", "tracking-wider", "text-(--muted)"], ["type", "button", 1, "flex", "w-full", "items-center", "justify-center", "gap-3", "rounded-xl", "border", "border-(--border)", "bg-(--surface)", "px-5", "py-3.5", "text-sm", "font-semibold", "text-(--text)", "transition", "hover:bg-(--surface-2)", "focus:outline-none", "focus:ring-4", "focus:ring-(--brand-soft)"], [1, "flex", "h-7", "w-7", "items-center", "justify-center", "rounded-full", "bg-(--surface-2)", "text-base", "font-bold", "text-(--brand)"], [1, "mt-7", "text-center", "text-sm", "text-(--text-soft)"], ["routerLink", "/auth/login", 1, "cursor-pointer", "font-bold", "text-(--brand)", "transition", "hover:text-(--brand-hover)"], ["viewBox", "0 0 24 24", "fill", "none", "aria-hidden", "true", 1, "h-5", "w-5", "animate-spin"], ["cx", "12", "cy", "12", "r", "10", "stroke", "currentColor", "stroke-width", "4", 1, "opacity-25"], ["fill", "currentColor", "d", "M4 12a8 8 0 0 1 8-8v4a4 4 0 0 0-4 4H4z", 1, "opacity-75"], ["aria-hidden", "true"]], template: function Register_Template(rf, ctx) {
    if (rf & 1) {
      \u0275\u0275elementStart(0, "div", 0)(1, "div", 1)(2, "aside", 2);
      \u0275\u0275element(3, "div", 3)(4, "div", 4);
      \u0275\u0275elementStart(5, "div", 5)(6, "span", 6);
      \u0275\u0275text(7, " T ");
      \u0275\u0275elementEnd();
      \u0275\u0275elementStart(8, "span");
      \u0275\u0275text(9, "Talabaty");
      \u0275\u0275elementEnd()();
      \u0275\u0275elementStart(10, "div", 7)(11, "p", 8);
      \u0275\u0275text(12, " Welcome ");
      \u0275\u0275elementEnd();
      \u0275\u0275elementStart(13, "h2", 9);
      \u0275\u0275text(14, " Good food starts here. ");
      \u0275\u0275elementEnd();
      \u0275\u0275elementStart(15, "p", 10);
      \u0275\u0275text(16, " Create an account, pick a restaurant, and place your order. ");
      \u0275\u0275elementEnd()();
      \u0275\u0275elementStart(17, "span", 11);
      \u0275\u0275text(18, " Simple. Fast. Delivered. ");
      \u0275\u0275elementEnd()();
      \u0275\u0275elementStart(19, "section", 12)(20, "div", 13)(21, "span", 14);
      \u0275\u0275text(22, " Create account ");
      \u0275\u0275elementEnd();
      \u0275\u0275elementStart(23, "h1", 15);
      \u0275\u0275text(24, " Create your account ");
      \u0275\u0275elementEnd();
      \u0275\u0275elementStart(25, "p", 16);
      \u0275\u0275text(26, " Enter your details to get started. ");
      \u0275\u0275elementEnd();
      \u0275\u0275elementStart(27, "form", 17);
      \u0275\u0275listener("submit", function Register_Template_form_submit_27_listener($event) {
        return ctx.onSubmit($event);
      });
      \u0275\u0275elementStart(28, "div")(29, "label", 18);
      \u0275\u0275text(30, " Username ");
      \u0275\u0275elementEnd();
      \u0275\u0275element(31, "input", 19);
      \u0275\u0275controlCreate();
      \u0275\u0275conditionalCreate(32, Register_Conditional_32_Template, 2, 1, "p", 20);
      \u0275\u0275elementEnd();
      \u0275\u0275elementStart(33, "div")(34, "label", 21);
      \u0275\u0275text(35, " Email address ");
      \u0275\u0275elementEnd();
      \u0275\u0275element(36, "input", 22);
      \u0275\u0275controlCreate();
      \u0275\u0275conditionalCreate(37, Register_Conditional_37_Template, 2, 1, "p", 20);
      \u0275\u0275elementEnd();
      \u0275\u0275elementStart(38, "div", 23)(39, "div")(40, "label", 24);
      \u0275\u0275text(41, " Password ");
      \u0275\u0275elementEnd();
      \u0275\u0275element(42, "input", 25);
      \u0275\u0275controlCreate();
      \u0275\u0275conditionalCreate(43, Register_Conditional_43_Template, 2, 1, "p", 20);
      \u0275\u0275elementEnd();
      \u0275\u0275elementStart(44, "div")(45, "label", 26);
      \u0275\u0275text(46, " Confirm password ");
      \u0275\u0275elementEnd();
      \u0275\u0275element(47, "input", 27);
      \u0275\u0275controlCreate();
      \u0275\u0275conditionalCreate(48, Register_Conditional_48_Template, 2, 1, "p", 20);
      \u0275\u0275elementEnd()();
      \u0275\u0275conditionalCreate(49, Register_Conditional_49_Template, 2, 1, "div", 28);
      \u0275\u0275elementStart(50, "button", 29);
      \u0275\u0275conditionalCreate(51, Register_Conditional_51_Template, 5, 0)(52, Register_Conditional_52_Template, 4, 0);
      \u0275\u0275elementEnd()();
      \u0275\u0275elementStart(53, "div", 30);
      \u0275\u0275element(54, "div", 31);
      \u0275\u0275elementStart(55, "span", 32);
      \u0275\u0275text(56, " Or continue with ");
      \u0275\u0275elementEnd();
      \u0275\u0275element(57, "div", 31);
      \u0275\u0275elementEnd();
      \u0275\u0275elementStart(58, "button", 33)(59, "span", 34);
      \u0275\u0275text(60, " G ");
      \u0275\u0275elementEnd();
      \u0275\u0275text(61, " Continue with Google ");
      \u0275\u0275elementEnd();
      \u0275\u0275elementStart(62, "p", 35);
      \u0275\u0275text(63, " Already have an account? ");
      \u0275\u0275elementStart(64, "span", 36);
      \u0275\u0275text(65, " Login ");
      \u0275\u0275elementEnd()()()()()();
    }
    if (rf & 2) {
      \u0275\u0275advance(31);
      \u0275\u0275classProp("!border-red-500", ctx.registerForm.username().touched() && ctx.registerForm.username().invalid())("!ring-red-100", ctx.registerForm.username().touched() && ctx.registerForm.username().invalid());
      \u0275\u0275property("formField", ctx.registerForm.username);
      \u0275\u0275control();
      \u0275\u0275advance();
      \u0275\u0275conditional(ctx.registerForm.username().touched() && ctx.registerForm.username().invalid() ? 32 : -1);
      \u0275\u0275advance(4);
      \u0275\u0275classProp("!border-red-500", ctx.registerForm.email().touched() && ctx.registerForm.email().invalid())("!ring-red-100", ctx.registerForm.email().touched() && ctx.registerForm.email().invalid());
      \u0275\u0275property("formField", ctx.registerForm.email);
      \u0275\u0275control();
      \u0275\u0275advance();
      \u0275\u0275conditional(ctx.registerForm.email().touched() && ctx.registerForm.email().invalid() ? 37 : -1);
      \u0275\u0275advance(5);
      \u0275\u0275classProp("!border-red-500", ctx.registerForm.password().touched() && ctx.registerForm.password().invalid())("!ring-red-100", ctx.registerForm.password().touched() && ctx.registerForm.password().invalid());
      \u0275\u0275property("formField", ctx.registerForm.password);
      \u0275\u0275control();
      \u0275\u0275advance();
      \u0275\u0275conditional(ctx.registerForm.password().touched() && ctx.registerForm.password().invalid() ? 43 : -1);
      \u0275\u0275advance(4);
      \u0275\u0275classProp("!border-red-500", ctx.registerForm.confirmPassword().touched() && ctx.registerForm.confirmPassword().invalid())("!ring-red-100", ctx.registerForm.confirmPassword().touched() && ctx.registerForm.confirmPassword().invalid());
      \u0275\u0275property("formField", ctx.registerForm.confirmPassword);
      \u0275\u0275control();
      \u0275\u0275advance();
      \u0275\u0275conditional(ctx.registerForm.confirmPassword().touched() && ctx.registerForm.confirmPassword().invalid() ? 48 : -1);
      \u0275\u0275advance();
      \u0275\u0275conditional(ctx.requestError() ? 49 : -1);
      \u0275\u0275advance();
      \u0275\u0275property("disabled", ctx.registerForm().submitting());
      \u0275\u0275advance();
      \u0275\u0275conditional(ctx.registerForm().submitting() ? 51 : 52);
    }
  }, dependencies: [FormField, RouterLink], encapsulation: 2 });
};
(() => {
  (typeof ngDevMode === "undefined" || ngDevMode) && setClassMetadata(Register, [{
    type: Component,
    args: [{ selector: "app-register", imports: [FormField, RouterLink], changeDetection: ChangeDetectionStrategy.OnPush, template: `<div class="min-h-dvh w-full bg-(--surface)">\r
  <div class="grid min-h-dvh w-full lg:grid-cols-[45%_55%]">\r
    <aside\r
      class="relative hidden min-h-dvh flex-col justify-between overflow-hidden bg-[linear-gradient(180deg,rgba(20,10,4,0.2),rgba(20,10,4,0.88)),url('/assets/images/talabaty-food-table.png')] bg-cover bg-center p-[clamp(2.5rem,5vw,6rem)] text-white lg:flex">\r
      <div class="absolute -right-24 -top-24 h-72 w-72 rounded-full bg-white/10"></div>\r
\r
      <div class="absolute -bottom-24 -left-24 h-80 w-80 rounded-full bg-black/10"></div>\r
\r
      <div class="relative z-10 flex items-center gap-3 text-2xl font-bold">\r
        <span\r
          class="flex h-11 w-11 items-center justify-center rounded-xl bg-(--surface) text-(--brand) shadow-(--shadow-sm)">\r
          T\r
        </span>\r
\r
        <span>Talabaty</span>\r
      </div>\r
\r
      <div class="relative z-10 max-w-xl">\r
        <p class="mb-4 text-sm font-semibold uppercase tracking-[0.25em] text-white/70">\r
          Welcome\r
        </p>\r
\r
        <h2\r
          class="font-['Playfair_Display'] text-[clamp(2.75rem,4.5vw,5.5rem)] font-bold leading-[1.02] tracking-[-0.04em]">\r
          Good food starts here.\r
        </h2>\r
\r
        <p class="mt-6 max-w-lg text-lg leading-8 text-white/85">\r
          Create an account, pick a restaurant, and place your order.\r
        </p>\r
      </div>\r
\r
      <span class="relative z-10 text-sm font-medium text-white/70">\r
        Simple. Fast. Delivered.\r
      </span>\r
    </aside>\r
\r
    <section\r
      class="flex min-h-dvh w-full items-center bg-(--surface) px-5 py-10 sm:px-10 lg:justify-start lg:px-[clamp(3rem,6vw,7rem)] lg:py-12">\r
      <div class="w-full">\r
        <span\r
          class="inline-flex rounded-full bg-(--brand-soft) px-4 py-2 text-xs font-bold uppercase tracking-[0.2em] text-(--brand)">\r
          Create account\r
        </span>\r
\r
        <h1 class="mt-5 font-['Playfair_Display'] text-3xl font-bold tracking-tight text-(--text) sm:text-4xl">\r
          Create your account\r
        </h1>\r
\r
        <p class="mt-3 text-base text-(--text-soft)">\r
          Enter your details to get started.\r
        </p>\r
\r
        <form class="mt-8 space-y-5" (submit)="onSubmit($event)" novalidate>\r
          <!-- Username -->\r
          <div>\r
            <label for="username" class="mb-2 block text-sm font-semibold text-(--text)">\r
              Username\r
            </label>\r
\r
            <input id="username" type="text" placeholder="Enter your username" autocomplete="username"\r
              [formField]="registerForm.username" [class.!border-red-500]="\r
                registerForm.username().touched() &&\r
                registerForm.username().invalid()\r
              " [class.!ring-red-100]="\r
                registerForm.username().touched() &&\r
                registerForm.username().invalid()\r
              "\r
              class="w-full rounded-xl border border-(--border) bg-(--surface) px-4 py-3.5 text-sm text-(--text) outline-none transition placeholder:text-(--muted) focus:border-(--brand) focus:ring-4 focus:ring-(--brand-soft)" />\r
\r
            @if (\r
            registerForm.username().touched() &&\r
            registerForm.username().invalid()\r
            ) {\r
            <p class="mt-2 text-sm font-medium text-red-600" role="alert">\r
              {{ registerForm.username().errors()[0]?.message }}\r
            </p>\r
            }\r
          </div>\r
\r
          <!-- Email -->\r
          <div>\r
            <label for="email" class="mb-2 block text-sm font-semibold text-(--text)">\r
              Email address\r
            </label>\r
\r
            <input id="email" type="email" placeholder="you@example.com" autocomplete="email"\r
              [formField]="registerForm.email" [class.!border-red-500]="\r
                registerForm.email().touched() &&\r
                registerForm.email().invalid()\r
              " [class.!ring-red-100]="\r
                registerForm.email().touched() &&\r
                registerForm.email().invalid()\r
              "\r
              class="w-full rounded-xl border border-(--border) bg-(--surface) px-4 py-3.5 text-sm text-(--text) outline-none transition placeholder:text-(--muted) focus:border-(--brand) focus:ring-4 focus:ring-(--brand-soft)" />\r
\r
            @if (\r
            registerForm.email().touched() &&\r
            registerForm.email().invalid()\r
            ) {\r
            <p class="mt-2 text-sm font-medium text-red-600" role="alert">\r
              {{ registerForm.email().errors()[0]?.message }}\r
            </p>\r
            }\r
          </div>\r
\r
          <!-- Password fields -->\r
          <div class="grid gap-5 sm:grid-cols-2">\r
            <!-- Password -->\r
            <div>\r
              <label for="password" class="mb-2 block text-sm font-semibold text-(--text)">\r
                Password\r
              </label>\r
\r
              <input id="password" type="password" placeholder="Enter password" autocomplete="new-password"\r
                [formField]="registerForm.password" [class.!border-red-500]="\r
                  registerForm.password().touched() &&\r
                  registerForm.password().invalid()\r
                " [class.!ring-red-100]="\r
                  registerForm.password().touched() &&\r
                  registerForm.password().invalid()\r
                "\r
                class="w-full rounded-xl border border-(--border) bg-(--surface) px-4 py-3.5 text-sm text-(--text) outline-none transition placeholder:text-(--muted) focus:border-(--brand) focus:ring-4 focus:ring-(--brand-soft)" />\r
\r
              @if (\r
              registerForm.password().touched() &&\r
              registerForm.password().invalid()\r
              ) {\r
              <p class="mt-2 text-sm font-medium text-red-600" role="alert">\r
                {{ registerForm.password().errors()[0]?.message }}\r
              </p>\r
              }\r
            </div>\r
\r
            <!-- Confirm password -->\r
            <div>\r
              <label for="confirmPassword" class="mb-2 block text-sm font-semibold text-(--text)">\r
                Confirm password\r
              </label>\r
\r
              <input id="confirmPassword" type="password" placeholder="Confirm password" autocomplete="new-password"\r
                [formField]="registerForm.confirmPassword" [class.!border-red-500]="\r
                  registerForm.confirmPassword().touched() &&\r
                  registerForm.confirmPassword().invalid()\r
                " [class.!ring-red-100]="\r
                  registerForm.confirmPassword().touched() &&\r
                  registerForm.confirmPassword().invalid()\r
                "\r
                class="w-full rounded-xl border border-(--border) bg-(--surface) px-4 py-3.5 text-sm text-(--text) outline-none transition placeholder:text-(--muted) focus:border-(--brand) focus:ring-4 focus:ring-(--brand-soft)" />\r
\r
              @if (\r
              registerForm.confirmPassword().touched() &&\r
              registerForm.confirmPassword().invalid()\r
              ) {\r
              <p class="mt-2 text-sm font-medium text-red-600" role="alert">\r
                {{ registerForm.confirmPassword().errors()[0]?.message }}\r
              </p>\r
              }\r
            </div>\r
          </div>\r
\r
          <!-- Backend request error -->\r
          @if (requestError()) {\r
          <div\r
            class="rounded-xl border border-red-300 bg-red-50 px-4 py-3 text-sm font-medium text-red-700 dark:border-red-900 dark:bg-red-950/40 dark:text-red-300"\r
            role="alert">\r
            {{ requestError() }}\r
          </div>\r
          }\r
\r
          <!-- Submit button -->\r
          <button type="submit" [disabled]="registerForm().submitting()"\r
            class="flex w-full items-center justify-center gap-2 rounded-xl bg-(--brand) px-5 py-3.5 text-sm font-bold text-white shadow-(--shadow-sm) transition hover:bg-(--brand-hover) focus:outline-none focus:ring-4 focus:ring-(--brand-soft) disabled:cursor-not-allowed disabled:opacity-60">\r
            @if (registerForm().submitting()) {\r
            <svg class="h-5 w-5 animate-spin" viewBox="0 0 24 24" fill="none" aria-hidden="true">\r
              <circle class="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" stroke-width="4"></circle>\r
\r
              <path class="opacity-75" fill="currentColor" d="M4 12a8 8 0 0 1 8-8v4a4 4 0 0 0-4 4H4z"></path>\r
            </svg>\r
\r
            <span>Creating account...</span>\r
            } @else {\r
            <span>Create Account</span>\r
            <span aria-hidden="true">\u2192</span>\r
            }\r
          </button>\r
        </form>\r
\r
        <div class="my-7 flex items-center gap-4">\r
          <div class="h-px flex-1 bg-(--line)"></div>\r
\r
          <span class="text-xs font-bold uppercase tracking-wider text-(--muted)">\r
            Or continue with\r
          </span>\r
\r
          <div class="h-px flex-1 bg-(--line)"></div>\r
        </div>\r
\r
        <button type="button"\r
          class="flex w-full items-center justify-center gap-3 rounded-xl border border-(--border) bg-(--surface) px-5 py-3.5 text-sm font-semibold text-(--text) transition hover:bg-(--surface-2) focus:outline-none focus:ring-4 focus:ring-(--brand-soft)">\r
          <span\r
            class="flex h-7 w-7 items-center justify-center rounded-full bg-(--surface-2) text-base font-bold text-(--brand)">\r
            G\r
          </span>\r
\r
          Continue with Google\r
        </button>\r
\r
        <p class="mt-7 text-center text-sm text-(--text-soft)">\r
          Already have an account?\r
\r
          <span routerLink="/auth/login"\r
            class="cursor-pointer font-bold text-(--brand) transition hover:text-(--brand-hover)">\r
            Login\r
          </span>\r
        </p>\r
      </div>\r
    </section>\r
  </div>\r
</div>` }]
  }], null, null);
})();
(() => {
  (typeof ngDevMode === "undefined" || ngDevMode) && \u0275setClassDebugInfo(Register, { className: "Register", filePath: "src/app/features/auth/pages/register/register.ts", lineNumber: 15 });
})();
export {
  Register
};
//# debugId=caf508e8-898b-5915-a8b4-cd72d9a73770
//# sourceMappingURL=chunk-G2QUQ7BC.js.map
