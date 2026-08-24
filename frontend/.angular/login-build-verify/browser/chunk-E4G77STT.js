import { AuthService, FormField, form, minLength, required, submit } from './chunk-3G2ZU4HQ.js';
import './chunk-PU67HFL7.js';
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
  ɵɵconditional,
  ɵɵconditionalCreate,
  ɵɵcontrol,
  ɵɵcontrolCreate,
  ɵɵdefineComponent,
  ɵɵelement,
  ɵɵelementEnd,
  ɵɵelementStart,
  ɵɵlistener,
  ɵɵnextContext,
  ɵɵproperty,
  ɵɵtext,
  ɵɵtextInterpolate1,
} from './chunk-XUN6663C.js';
import './chunk-GOMI4DH3.js';

// src/app/features/auth/pages/login/login.ts
function Login_Conditional_21_Template(rf, ctx) {
  if (rf & 1) {
    ɵɵelementStart(0, 'p', 14);
    ɵɵtext(1);
    ɵɵelementEnd();
  }
  if (rf & 2) {
    const ctx_r0 = ɵɵnextContext();
    ɵɵadvance();
    ɵɵtextInterpolate1(' ', ctx_r0.loginForm.username().errors()[0]?.message, ' ');
  }
}
function Login_Conditional_30_Template(rf, ctx) {
  if (rf & 1) {
    ɵɵelementStart(0, 'p', 14);
    ɵɵtext(1);
    ɵɵelementEnd();
  }
  if (rf & 2) {
    const ctx_r0 = ɵɵnextContext();
    ɵɵadvance();
    ɵɵtextInterpolate1(' ', ctx_r0.loginForm.password().errors()[0]?.message, ' ');
  }
}
function Login_Conditional_31_Template(rf, ctx) {
  if (rf & 1) {
    ɵɵelementStart(0, 'div', 19);
    ɵɵtext(1);
    ɵɵelementEnd();
  }
  if (rf & 2) {
    const ctx_r0 = ɵɵnextContext();
    ɵɵadvance();
    ɵɵtextInterpolate1(' ', ctx_r0.requestError(), ' ');
  }
}
function Login_Conditional_33_Template(rf, ctx) {
  if (rf & 1) {
    ɵɵelement(0, 'span', 48);
    ɵɵelementStart(1, 'span');
    ɵɵtext(2, 'Signing in...');
    ɵɵelementEnd();
  }
}
function Login_Conditional_34_Template(rf, ctx) {
  if (rf & 1) {
    ɵɵelementStart(0, 'span');
    ɵɵtext(1, 'Sign in');
    ɵɵelementEnd();
    ɵɵelementStart(2, 'span', 49);
    ɵɵtext(3, '\u2192');
    ɵɵelementEnd();
  }
}
var Login = class _Login {
  authService = inject(AuthService);
  requestError = signal(
    null,
    ...(ngDevMode
      ? [{ debugName: 'requestError' }]
      : /* istanbul ignore next */
        []),
  );
  LoginModel = signal(
    {
      username: '',
      password: '',
    },
    ...(ngDevMode
      ? [{ debugName: 'LoginModel' }]
      : /* istanbul ignore next */
        []),
  );
  loginForm = form(this.LoginModel, (field) => {
    required(field.username, { message: 'Username is required.' });
    minLength(field.username, 3, {
      message: 'Username must contain at least 3 characters.',
    });
    required(field.password, { message: 'Password is required.' });
    minLength(field.password, 8, {
      message: 'Password must contain at least 8 characters.',
    });
  });
  async onSubmit(event) {
    event.preventDefault();
    this.requestError.set(null);
    await submit(this.loginForm, async () => {
      const request = this.LoginModel();
      try {
        const response = await firstValueFrom(this.authService.login(request));
        console.log('Login request:', response);
        this.resetForm();
      } catch (error) {
        if (error instanceof HttpErrorResponse) {
          this.requestError.set(error.error?.message ?? 'Login failed.');
        } else {
          this.requestError.set('Login failed.');
        }
      }
    });
  }
  resetForm() {
    this.LoginModel.set({
      username: '',
      password: '',
    });
  }
  static ɵfac = function Login_Factory(__ngFactoryType__) {
    return new (__ngFactoryType__ || _Login)();
  };
  static ɵcmp = /* @__PURE__ */ ɵɵdefineComponent({
    type: _Login,
    selectors: [['app-login']],
    decls: 82,
    vars: 7,
    consts: [
      [
        1,
        'grid',
        'min-h-dvh',
        'w-full',
        'bg-(--surface)',
        'lg:grid-cols-[minmax(0,1.15fr)_minmax(420px,0.85fr)]',
      ],
      [
        1,
        'flex',
        'min-h-dvh',
        'flex-col',
        'px-5',
        'py-7',
        'sm:px-10',
        'lg:px-[clamp(3rem,7vw,8rem)]',
        'lg:py-10',
      ],
      [1, 'flex', 'items-center', 'gap-3', 'text-(--brand)'],
      [
        1,
        'grid',
        'h-11',
        'w-11',
        'place-items-center',
        'rounded-2xl',
        'bg-(--brand)',
        'text-lg',
        'font-black',
        'text-white',
        'shadow-(--shadow-sm)',
      ],
      [1, "font-['Playfair_Display']", 'text-2xl', 'font-bold'],
      [1, 'flex', 'flex-1', 'items-center', 'py-10'],
      [1, 'w-full', 'max-w-2xl'],
      [1, 'text-xs', 'font-bold', 'uppercase', 'tracking-[0.24em]', 'text-(--brand)'],
      [
        1,
        'mt-4',
        'max-w-xl',
        "font-['Playfair_Display']",
        'text-4xl',
        'font-bold',
        'leading-tight',
        'tracking-[-0.04em]',
        'text-(--text)',
        'sm:text-5xl',
      ],
      [1, 'mt-4', 'text-base', 'leading-7', 'text-(--text-soft)'],
      ['novalidate', '', 1, 'mt-10', 'grid', 'gap-6', 3, 'submit'],
      ['for', 'login-username', 1, 'mb-2', 'block', 'text-sm', 'font-bold', 'text-(--text)'],
      [
        1,
        'flex',
        'h-14',
        'items-center',
        'gap-3',
        'rounded-2xl',
        'border',
        'border-(--border)',
        'bg-(--surface-2)',
        'px-4',
        'text-(--muted)',
        'transition',
        'focus-within:border-(--brand)',
        'focus-within:bg-(--surface)',
        'focus-within:ring-4',
        'focus-within:ring-(--brand-soft)',
      ],
      [
        'id',
        'login-username',
        'type',
        'text',
        'placeholder',
        'Enter your username',
        1,
        'h-full',
        'min-w-0',
        'flex-1',
        'border-0',
        'bg-transparent',
        'text-(--text)',
        'outline-none',
        'placeholder:text-(--muted)',
        3,
        'formField',
      ],
      ['role', 'alert', 1, 'mt-2', 'text-sm', 'font-medium', 'text-red-600'],
      [1, 'mb-2', 'flex', 'items-center', 'justify-between', 'gap-4'],
      ['for', 'login-password', 1, 'text-sm', 'font-bold', 'text-(--text)'],
      [1, 'text-xs', 'font-bold', 'text-(--brand)'],
      [
        'id',
        'login-password',
        'type',
        'password',
        'placeholder',
        'Enter your password',
        1,
        'h-full',
        'min-w-0',
        'flex-1',
        'border-0',
        'bg-transparent',
        'text-(--text)',
        'outline-none',
        'placeholder:text-(--muted)',
        3,
        'formField',
      ],
      [
        'role',
        'alert',
        1,
        'rounded-2xl',
        'border',
        'border-red-300',
        'bg-red-50',
        'px-4',
        'py-3',
        'text-sm',
        'font-medium',
        'text-red-700',
      ],
      [
        'type',
        'submit',
        1,
        'flex',
        'h-14',
        'w-full',
        'items-center',
        'justify-center',
        'gap-2',
        'rounded-2xl',
        'bg-(--brand)',
        'px-6',
        'font-bold',
        'text-white',
        'shadow-[0_12px_24px_color-mix(in_srgb,var(--brand)_22%,transparent)]',
        'transition',
        'hover:-translate-y-0.5',
        'hover:bg-(--brand-hover)',
        'focus:outline-none',
        'focus:ring-4',
        'focus:ring-(--brand-soft)',
        3,
        'disabled',
      ],
      [1, 'my-7', 'flex', 'items-center', 'gap-4'],
      [1, 'h-px', 'flex-1', 'bg-(--line)'],
      [1, 'text-xs', 'font-bold', 'uppercase', 'tracking-[0.18em]', 'text-(--muted)'],
      [
        'type',
        'button',
        1,
        'flex',
        'h-14',
        'w-full',
        'items-center',
        'justify-center',
        'gap-3',
        'rounded-2xl',
        'border',
        'border-(--border)',
        'bg-(--surface)',
        'font-bold',
        'text-(--text)',
        'transition',
        'hover:border-(--brand)',
        'hover:bg-(--brand-soft)',
        'focus:outline-none',
        'focus:ring-4',
        'focus:ring-(--brand-soft)',
      ],
      [1, 'text-xl', 'font-black', 'text-[#4285f4]'],
      [1, 'mt-8', 'text-sm', 'text-(--text-soft)'],
      [1, 'ml-1', 'font-bold', 'text-(--brand)'],
      [
        1,
        'relative',
        'hidden',
        'min-h-dvh',
        'overflow-hidden',
        'bg-[#20150f]',
        'p-[clamp(2.5rem,5vw,5rem)]',
        'text-white',
        'lg:flex',
        'lg:flex-col',
        'lg:justify-between',
      ],
      [
        1,
        'absolute',
        '-right-24',
        '-top-20',
        'h-80',
        'w-80',
        'rounded-full',
        'bg-(--brand)/30',
        'blur-2xl',
      ],
      [
        1,
        'absolute',
        '-bottom-32',
        '-left-20',
        'h-96',
        'w-96',
        'rounded-full',
        'bg-[#ff9a4d]/15',
        'blur-3xl',
      ],
      [1, 'relative', 'z-10', 'flex', 'items-center', 'justify-between'],
      [
        1,
        'rounded-full',
        'border',
        'border-white/15',
        'bg-white/10',
        'px-4',
        'py-2',
        'text-xs',
        'font-bold',
        'uppercase',
        'tracking-[0.18em]',
        'text-white/75',
      ],
      [
        1,
        'h-3',
        'w-3',
        'rounded-full',
        'bg-[#70d99b]',
        'shadow-[0_0_0_7px_rgba(112,217,155,0.12)]',
      ],
      [1, 'relative', 'z-10'],
      [
        1,
        'ml-auto',
        'max-w-sm',
        'rotate-2',
        'rounded-4xl',
        'border',
        'border-white/15',
        'bg-white/10',
        'p-5',
        'shadow-2xl',
        'backdrop-blur-xl',
      ],
      [1, 'flex', 'items-center', 'gap-4'],
      [
        1,
        'h-20',
        'w-20',
        'rounded-2xl',
        "bg-[url('/assets/images/talabaty-food-table.png')]",
        'bg-cover',
        'bg-center',
      ],
      [1, 'text-xs', 'font-bold', 'uppercase', 'tracking-wider', 'text-[#ffad70]'],
      [1, 'mt-1', 'text-xl', 'font-bold'],
      [1, 'mt-1', 'text-sm', 'text-white/60'],
      [1, 'mt-5', 'h-2', 'overflow-hidden', 'rounded-full', 'bg-white/10'],
      [1, 'h-full', 'w-2/3', 'rounded-full', 'bg-(--brand)'],
      [
        1,
        'mt-3',
        'flex',
        'justify-between',
        'text-[11px]',
        'font-bold',
        'uppercase',
        'tracking-wider',
        'text-white/45',
      ],
      [1, '-mt-3', 'max-w-xs', '-rotate-3', 'rounded-3xl', 'bg-(--brand)', 'p-6', 'shadow-2xl'],
      [1, 'text-sm', 'text-white/75'],
      [1, 'mt-2', "font-['Playfair_Display']", 'text-3xl', 'font-bold', 'leading-tight'],
      [1, 'relative', 'z-10', 'max-w-md', 'text-sm', 'leading-6', 'text-white/55'],
      [
        1,
        'h-5',
        'w-5',
        'animate-spin',
        'rounded-full',
        'border-2',
        'border-white/35',
        'border-t-white',
      ],
      ['aria-hidden', 'true'],
    ],
    template: function Login_Template(rf, ctx) {
      if (rf & 1) {
        ɵɵelementStart(0, 'main', 0)(1, 'section', 1)(2, 'div', 2)(3, 'span', 3);
        ɵɵtext(4, ' T ');
        ɵɵelementEnd();
        ɵɵelementStart(5, 'span', 4);
        ɵɵtext(6, 'Talabaty');
        ɵɵelementEnd()();
        ɵɵelementStart(7, 'div', 5)(8, 'div', 6)(9, 'span', 7);
        ɵɵtext(10, 'Welcome back');
        ɵɵelementEnd();
        ɵɵelementStart(11, 'h1', 8);
        ɵɵtext(12, ' Your next favourite meal is waiting. ');
        ɵɵelementEnd();
        ɵɵelementStart(13, 'p', 9);
        ɵɵtext(14, ' Sign in with your username and password to continue. ');
        ɵɵelementEnd();
        ɵɵelementStart(15, 'form', 10);
        ɵɵlistener('submit', function Login_Template_form_submit_15_listener($event) {
          return ctx.onSubmit($event);
        });
        ɵɵelementStart(16, 'div')(17, 'label', 11);
        ɵɵtext(18, 'Username');
        ɵɵelementEnd();
        ɵɵelementStart(19, 'div', 12);
        ɵɵelement(20, 'input', 13);
        ɵɵcontrolCreate();
        ɵɵelementEnd();
        ɵɵconditionalCreate(21, Login_Conditional_21_Template, 2, 1, 'p', 14);
        ɵɵelementEnd();
        ɵɵelementStart(22, 'div')(23, 'div', 15)(24, 'label', 16);
        ɵɵtext(25, 'Password');
        ɵɵelementEnd();
        ɵɵelementStart(26, 'span', 17);
        ɵɵtext(27, ' Forgot password? ');
        ɵɵelementEnd()();
        ɵɵelementStart(28, 'div', 12);
        ɵɵelement(29, 'input', 18);
        ɵɵcontrolCreate();
        ɵɵelementEnd();
        ɵɵconditionalCreate(30, Login_Conditional_30_Template, 2, 1, 'p', 14);
        ɵɵelementEnd();
        ɵɵconditionalCreate(31, Login_Conditional_31_Template, 2, 1, 'div', 19);
        ɵɵelementStart(32, 'button', 20);
        ɵɵconditionalCreate(33, Login_Conditional_33_Template, 3, 0)(
          34,
          Login_Conditional_34_Template,
          4,
          0,
        );
        ɵɵelementEnd()();
        ɵɵelementStart(35, 'div', 21);
        ɵɵelement(36, 'span', 22);
        ɵɵelementStart(37, 'span', 23);
        ɵɵtext(38, 'Or');
        ɵɵelementEnd();
        ɵɵelement(39, 'span', 22);
        ɵɵelementEnd();
        ɵɵelementStart(40, 'button', 24)(41, 'span', 25);
        ɵɵtext(42, 'G');
        ɵɵelementEnd();
        ɵɵtext(43, ' Continue with Google ');
        ɵɵelementEnd();
        ɵɵelementStart(44, 'p', 26);
        ɵɵtext(45, ' New to Talabaty? ');
        ɵɵelementStart(46, 'span', 27);
        ɵɵtext(47, 'Create an account');
        ɵɵelementEnd()()()()();
        ɵɵelementStart(48, 'aside', 28);
        ɵɵelement(49, 'div', 29)(50, 'div', 30);
        ɵɵelementStart(51, 'div', 31)(52, 'span', 32);
        ɵɵtext(53, ' Dinner, simplified ');
        ɵɵelementEnd();
        ɵɵelement(54, 'span', 33);
        ɵɵelementEnd();
        ɵɵelementStart(55, 'div', 34)(56, 'div', 35)(57, 'div', 36);
        ɵɵelement(58, 'div', 37);
        ɵɵelementStart(59, 'div')(60, 'span', 38);
        ɵɵtext(61, 'On the way');
        ɵɵelementEnd();
        ɵɵelementStart(62, 'h2', 39);
        ɵɵtext(63, 'Your order is cooking');
        ɵɵelementEnd();
        ɵɵelementStart(64, 'p', 40);
        ɵɵtext(65, 'Arriving in 24 minutes');
        ɵɵelementEnd()()();
        ɵɵelementStart(66, 'div', 41);
        ɵɵelement(67, 'div', 42);
        ɵɵelementEnd();
        ɵɵelementStart(68, 'div', 43)(69, 'span');
        ɵɵtext(70, 'Confirmed');
        ɵɵelementEnd();
        ɵɵelementStart(71, 'span');
        ɵɵtext(72, 'Cooking');
        ɵɵelementEnd();
        ɵɵelementStart(73, 'span');
        ɵɵtext(74, 'Delivered');
        ɵɵelementEnd()()();
        ɵɵelementStart(75, 'div', 44)(76, 'p', 45);
        ɵɵtext(77, "Tonight's reward");
        ɵɵelementEnd();
        ɵɵelementStart(78, 'p', 46);
        ɵɵtext(79, 'No cooking. No cleanup.');
        ɵɵelementEnd()()();
        ɵɵelementStart(80, 'p', 47);
        ɵɵtext(
          81,
          ' One login away from local favourites, live delivery updates, and easy reordering. ',
        );
        ɵɵelementEnd()()();
      }
      if (rf & 2) {
        ɵɵadvance(20);
        ɵɵproperty('formField', ctx.loginForm.username);
        ɵɵcontrol();
        ɵɵadvance();
        ɵɵconditional(
          ctx.loginForm.username().touched() && ctx.loginForm.username().invalid() ? 21 : -1,
        );
        ɵɵadvance(8);
        ɵɵproperty('formField', ctx.loginForm.password);
        ɵɵcontrol();
        ɵɵadvance();
        ɵɵconditional(
          ctx.loginForm.password().touched() && ctx.loginForm.password().invalid() ? 30 : -1,
        );
        ɵɵadvance();
        ɵɵconditional(ctx.requestError() ? 31 : -1);
        ɵɵadvance();
        ɵɵproperty('disabled', ctx.loginForm().submitting());
        ɵɵadvance();
        ɵɵconditional(ctx.loginForm().submitting() ? 33 : 34);
      }
    },
    dependencies: [FormField],
    encapsulation: 2,
  });
};
(() => {
  (typeof ngDevMode === 'undefined' || ngDevMode) &&
    setClassMetadata(
      Login,
      [
        {
          type: Component,
          args: [
            {
              selector: 'app-login',
              imports: [FormField],
              changeDetection: ChangeDetectionStrategy.OnPush,
              template: `<main class="grid min-h-dvh w-full bg-(--surface) lg:grid-cols-[minmax(0,1.15fr)_minmax(420px,0.85fr)]">
  <section class="flex min-h-dvh flex-col px-5 py-7 sm:px-10 lg:px-[clamp(3rem,7vw,8rem)] lg:py-10">
    <div class="flex items-center gap-3 text-(--brand)">
      <span class="grid h-11 w-11 place-items-center rounded-2xl bg-(--brand) text-lg font-black text-white shadow-(--shadow-sm)">
        T
      </span>
      <span class="font-['Playfair_Display'] text-2xl font-bold">Talabaty</span>
    </div>

    <div class="flex flex-1 items-center py-10">
      <div class="w-full max-w-2xl">
        <span class="text-xs font-bold uppercase tracking-[0.24em] text-(--brand)">Welcome back</span>
        <h1 class="mt-4 max-w-xl font-['Playfair_Display'] text-4xl font-bold leading-tight tracking-[-0.04em] text-(--text) sm:text-5xl">
          Your next favourite meal is waiting.
        </h1>
        <p class="mt-4 text-base leading-7 text-(--text-soft)">
          Sign in with your username and password to continue.
        </p>

        <form class="mt-10 grid gap-6" (submit)="onSubmit($event)" novalidate>
          <div>
            <label for="login-username" class="mb-2 block text-sm font-bold text-(--text)">Username</label>
            <div
              class="flex h-14 items-center gap-3 rounded-2xl border border-(--border) bg-(--surface-2) px-4 text-(--muted) transition focus-within:border-(--brand) focus-within:bg-(--surface) focus-within:ring-4 focus-within:ring-(--brand-soft)">
              <input
                id="login-username"
                class="h-full min-w-0 flex-1 border-0 bg-transparent text-(--text) outline-none placeholder:text-(--muted)"
                type="text"
                [formField]="loginForm.username"
                placeholder="Enter your username"
              />
            </div>
            @if (loginForm.username().touched() && loginForm.username().invalid()) {
              <p class="mt-2 text-sm font-medium text-red-600" role="alert">
                {{ loginForm.username().errors()[0]?.message }}
              </p>
            }
          </div>

          <div>
            <div class="mb-2 flex items-center justify-between gap-4">
              <label for="login-password" class="text-sm font-bold text-(--text)">Password</label>
              <span class="text-xs font-bold text-(--brand)">
                Forgot password?
              </span>
            </div>
            <div
              class="flex h-14 items-center gap-3 rounded-2xl border border-(--border) bg-(--surface-2) px-4 text-(--muted) transition focus-within:border-(--brand) focus-within:bg-(--surface) focus-within:ring-4 focus-within:ring-(--brand-soft)">
              <input
                id="login-password"
                class="h-full min-w-0 flex-1 border-0 bg-transparent text-(--text) outline-none placeholder:text-(--muted)"
                type="password"
                [formField]="loginForm.password"
                placeholder="Enter your password"
              />
            </div>
            @if (loginForm.password().touched() && loginForm.password().invalid()) {
              <p class="mt-2 text-sm font-medium text-red-600" role="alert">
                {{ loginForm.password().errors()[0]?.message }}
              </p>
            }
          </div>

          @if (requestError()) {
            <div
              class="rounded-2xl border border-red-300 bg-red-50 px-4 py-3 text-sm font-medium text-red-700"
              role="alert"
            >
              {{ requestError() }}
            </div>
          }

          <button
            class="flex h-14 w-full items-center justify-center gap-2 rounded-2xl bg-(--brand) px-6 font-bold text-white shadow-[0_12px_24px_color-mix(in_srgb,var(--brand)_22%,transparent)] transition hover:-translate-y-0.5 hover:bg-(--brand-hover) focus:outline-none focus:ring-4 focus:ring-(--brand-soft)"
            type="submit"
            [disabled]="loginForm().submitting()"
          >
            @if (loginForm().submitting()) {
              <span class="h-5 w-5 animate-spin rounded-full border-2 border-white/35 border-t-white"></span>
              <span>Signing in...</span>
            } @else {
              <span>Sign in</span>
              <span aria-hidden="true">\u2192</span>
            }
          </button>
        </form>

        <div class="my-7 flex items-center gap-4">
          <span class="h-px flex-1 bg-(--line)"></span>
          <span class="text-xs font-bold uppercase tracking-[0.18em] text-(--muted)">Or</span>
          <span class="h-px flex-1 bg-(--line)"></span>
        </div>

        <button
          class="flex h-14 w-full items-center justify-center gap-3 rounded-2xl border border-(--border) bg-(--surface) font-bold text-(--text) transition hover:border-(--brand) hover:bg-(--brand-soft) focus:outline-none focus:ring-4 focus:ring-(--brand-soft)"
          type="button"
        >
          <span class="text-xl font-black text-[#4285f4]">G</span>
          Continue with Google
        </button>

        <p class="mt-8 text-sm text-(--text-soft)">
          New to Talabaty?
          <span class="ml-1 font-bold text-(--brand)">Create an account</span>
        </p>
      </div>
    </div>
  </section>

  <aside class="relative hidden min-h-dvh overflow-hidden bg-[#20150f] p-[clamp(2.5rem,5vw,5rem)] text-white lg:flex lg:flex-col lg:justify-between">
    <div class="absolute -right-24 -top-20 h-80 w-80 rounded-full bg-(--brand)/30 blur-2xl"></div>
    <div class="absolute -bottom-32 -left-20 h-96 w-96 rounded-full bg-[#ff9a4d]/15 blur-3xl"></div>

    <div class="relative z-10 flex items-center justify-between">
      <span class="rounded-full border border-white/15 bg-white/10 px-4 py-2 text-xs font-bold uppercase tracking-[0.18em] text-white/75">
        Dinner, simplified
      </span>
      <span class="h-3 w-3 rounded-full bg-[#70d99b] shadow-[0_0_0_7px_rgba(112,217,155,0.12)]"></span>
    </div>

    <div class="relative z-10">
      <div class="ml-auto max-w-sm rotate-2 rounded-4xl border border-white/15 bg-white/10 p-5 shadow-2xl backdrop-blur-xl">
        <div class="flex items-center gap-4">
          <div class="h-20 w-20 rounded-2xl bg-[url('/assets/images/talabaty-food-table.png')] bg-cover bg-center"></div>
          <div>
            <span class="text-xs font-bold uppercase tracking-wider text-[#ffad70]">On the way</span>
            <h2 class="mt-1 text-xl font-bold">Your order is cooking</h2>
            <p class="mt-1 text-sm text-white/60">Arriving in 24 minutes</p>
          </div>
        </div>
        <div class="mt-5 h-2 overflow-hidden rounded-full bg-white/10">
          <div class="h-full w-2/3 rounded-full bg-(--brand)"></div>
        </div>
        <div class="mt-3 flex justify-between text-[11px] font-bold uppercase tracking-wider text-white/45">
          <span>Confirmed</span>
          <span>Cooking</span>
          <span>Delivered</span>
        </div>
      </div>

      <div class="-mt-3 max-w-xs -rotate-3 rounded-3xl bg-(--brand) p-6 shadow-2xl">
        <p class="text-sm text-white/75">Tonight's reward</p>
        <p class="mt-2 font-['Playfair_Display'] text-3xl font-bold leading-tight">No cooking. No cleanup.</p>
      </div>
    </div>

    <p class="relative z-10 max-w-md text-sm leading-6 text-white/55">
      One login away from local favourites, live delivery updates, and easy reordering.
    </p>
  </aside>
</main>
`,
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
    ɵsetClassDebugInfo(Login, {
      className: 'Login',
      filePath: 'src/app/features/auth/pages/login/login.ts',
      lineNumber: 14,
    });
})();
export { Login };
//# debugId=24294722-3246-5bae-823a-186924c739cf
//# sourceMappingURL=chunk-E4G77STT.js.map
