import { Component, inject, signal } from '@angular/core';
import { AbstractControl, FormControl, FormGroup, ReactiveFormsModule, ValidationErrors, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { ResetPasswordRequest } from '../../models/reset.model';
import { AuthService } from '../../services/auth.service';
import { finalize } from 'rxjs';
@Component({
  selector: 'app-reset-password',
  imports: [ReactiveFormsModule],
  templateUrl: './reset-password.html',
  styleUrl: './reset-password.css'
})
export class ResetPassword {
  private readonly route = inject(ActivatedRoute);
  private readonly authService = inject(AuthService);

  readonly token = this.route.snapshot.queryParamMap.get('token');

  protected readonly viewPassword = signal(false);
  protected readonly viewConfirmPassword = signal(false);
  protected readonly isSubmitting = signal(false);

  readonly form = new FormGroup(
    {
      password: new FormControl('', {
        nonNullable: true,
        validators: [
          Validators.required,
          Validators.minLength(8)
        ]
      }),
      confirmPassword: new FormControl('', {
        nonNullable: true,
        validators: [Validators.required]
      })
    },
    {
      validators: [
        (form: AbstractControl): ValidationErrors | null => {
          const password = form.get('password')?.value;
          const confirmPassword = form.get('confirmPassword')?.value;

          if (!password || !confirmPassword) return null;
          return password === confirmPassword ? null : { passwordsMismatch: true };
        }
      ]
    }
  );

  submit(): void {
    this.form.updateValueAndValidity();

    if (this.form.invalid || !this.token || this.isSubmitting()) {
      this.form.markAllAsTouched();
      return;
    }

    const request: ResetPasswordRequest = {
      token: this.token,
      password: this.form.controls.password.value
    };

    this.isSubmitting.set(true);

    this.authService.reset(request).pipe(finalize(() => this.isSubmitting.set(false))).subscribe({
      next: response => {
        console.log('Password reset successfully', response);
      },
      error: error => {
        console.error('Password reset failed', error);
      }
    });
  }

  togglePassword(
    inputName: 'password' | 'confirmPassword'
  ): void {
    if (inputName === 'password') {
      this.viewPassword.update(value => !value);
    } else {
      this.viewConfirmPassword.update(value => !value);
    }
  }
}