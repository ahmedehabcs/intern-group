import { HttpErrorResponse } from '@angular/common/http';
import { Component, DestroyRef, inject, signal } from '@angular/core';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { finalize } from 'rxjs';
import { AuthService } from '../../services/auth.service';
type SignupRole = 'CUSTOMER' | 'DRIVER';

// Both mirror the Bean Validation @Pattern constraints on the backend's
// CustomerSignupRequest / DriverSignupRequest. They are duplicated here rather
// than inferred so a rejected signup is a field-level message the user can act
// on, instead of an opaque 400 surfaced in the form-wide error banner.
const ALLOWED_EMAIL_DOMAINS = /^[a-zA-Z0-9._%+-]+@(gmail\.com|yahoo\.com|hotmail\.com|outlook\.com)$/i;
const PASSWORD_COMPLEXITY = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).*$/;
@Component({
  selector: 'app-register',
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './register.html',
})
export class Register {
  private api = inject(AuthService);
  private router = inject(Router);
  private destroy = inject(DestroyRef);
  readonly submitting = signal(false);
  readonly error = signal<string | null>(null);
  readonly form = new FormGroup({
    role: new FormControl<SignupRole>('CUSTOMER', { nonNullable: true }),
    name: new FormControl('', {
      nonNullable: true,
      validators: [Validators.required, Validators.minLength(2), Validators.maxLength(50)],
    }),
    email: new FormControl('', {
      nonNullable: true,
      validators: [Validators.required, Validators.email, Validators.pattern(ALLOWED_EMAIL_DOMAINS)],
    }),
    phoneNumber: new FormControl('', { nonNullable: true, validators: [Validators.maxLength(20)] }),
    password: new FormControl('', {
      nonNullable: true,
      validators: [
        Validators.required,
        Validators.minLength(8),
        Validators.maxLength(100),
        Validators.pattern(PASSWORD_COMPLEXITY),
      ],
    }),
    vehicleType: new FormControl('', { nonNullable: true }),
    licenseNumber: new FormControl('', { nonNullable: true }),
    nationalId: new FormControl('', { nonNullable: true }),
  });
  submit(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }
    const v = this.form.getRawValue();
    // DriverSignupRequest marks phoneNumber @NotBlank, unlike the customer
    // payload, so an empty phone here is a guaranteed 400 rather than an
    // optional field. Checked alongside the other driver-only fields because
    // the control itself is shared with the customer form.
    if (
      v.role === 'DRIVER' &&
      (!v.phoneNumber || !v.vehicleType || !v.licenseNumber || v.nationalId.length < 10)
    ) {
      this.error.set(
        'Phone number, vehicle type, license number, and a valid national ID are required.',
      );
      return;
    }
    this.submitting.set(true);
    this.error.set(null);
    const request =
      v.role === 'DRIVER'
        ? this.api.signupDriver({
            name: v.name,
            email: v.email,
            password: v.password,
            phoneNumber: v.phoneNumber,
            vehicleType: v.vehicleType,
            licenseNumber: v.licenseNumber,
            nationalId: v.nationalId,
          })
        : this.api.signupCustomer({
            name: v.name,
            email: v.email,
            password: v.password,
            phoneNumber: v.phoneNumber || undefined,
          });
    request
      .pipe(
        finalize(() => this.submitting.set(false)),
        takeUntilDestroyed(this.destroy),
      )
      .subscribe({
        next: () =>
          void this.router.navigate(['/auth/otp-verification'], {
            queryParams: { email: v.email },
          }),
        error: (e) =>
          this.error.set(
            e instanceof HttpErrorResponse
              ? (e.error?.message ?? 'Registration failed.')
              : 'Registration failed.',
          ),
      });
  }
}
