import { Component, DestroyRef, inject, signal } from '@angular/core';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { finalize } from 'rxjs';
import { AuthService } from '../../services/auth.service';
@Component({
  selector: 'app-forgot-password',
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './forgot-password.html',
})
export class ForgotPassword {
  private api = inject(AuthService);
  private router = inject(Router);
  private destroy = inject(DestroyRef);
  readonly submitting = signal(false);
  readonly error = signal<string | null>(null);
  readonly form = new FormGroup({
    email: new FormControl('', {
      nonNullable: true,
      validators: [Validators.required, Validators.email],
    }),
  });
  submit(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }
    this.submitting.set(true);
    const email = this.form.controls.email.value;
    this.api
      .forgotPassword({ email })
      .pipe(
        finalize(() => this.submitting.set(false)),
        takeUntilDestroyed(this.destroy),
      )
      .subscribe({
        next: () => void this.router.navigate(['/auth/reset-password'], { queryParams: { email } }),
        error: () => this.error.set('Unable to send the reset code.'),
      });
  }
}
