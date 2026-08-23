import { HttpErrorResponse } from '@angular/common/http';
import { Component, DestroyRef, inject, signal } from '@angular/core';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { finalize } from 'rxjs';
import { AuthService } from '../../services/auth.service';
@Component({
  selector: 'app-otp-verification',
  imports: [ReactiveFormsModule],
  templateUrl: './otp-verification.html',
})
export class OtpVerification {
  private api = inject(AuthService);
  private router = inject(Router);
  private destroy = inject(DestroyRef);
  readonly email = this.router.url
    ? (inject(ActivatedRoute).snapshot.queryParamMap.get('email') ?? '')
    : '';
  readonly submitting = signal(false);
  readonly resending = signal(false);
  readonly message = signal<string | null>(null);
  readonly error = signal<string | null>(null);
  readonly form = new FormGroup({
    otp: new FormControl('', { nonNullable: true, validators: [Validators.required] }),
  });
  submit(): void {
    if (this.form.invalid || !this.email) return;
    this.submitting.set(true);
    this.api
      .verifyOtp({ email: this.email, otp: this.form.controls.otp.value.trim() })
      .pipe(
        finalize(() => this.submitting.set(false)),
        takeUntilDestroyed(this.destroy),
      )
      .subscribe({
        next: () => void this.router.navigate(['/auth/login']),
        error: (e) =>
          this.error.set(
            e instanceof HttpErrorResponse
              ? (e.error?.message ?? 'Verification failed.')
              : 'Verification failed.',
          ),
      });
  }
  resend(): void {
    if (!this.email) return;
    this.resending.set(true);
    this.api
      .resendOtp(this.email)
      .pipe(
        finalize(() => this.resending.set(false)),
        takeUntilDestroyed(this.destroy),
      )
      .subscribe({
        next: (r) => this.message.set(r || 'A new code was sent.'),
        error: () => this.error.set('Unable to resend the code.'),
      });
  }
}
