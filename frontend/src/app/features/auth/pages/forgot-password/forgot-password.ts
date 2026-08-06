import { CommonModule } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { ForgotPasswordRequest } from '../../models/forgot-password.model';

@Component({
  selector: 'app-forgot-password',
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './forgot-password.html',
})
export class ForgotPassword {
  email = '';
  isSubmitting = false;
  errorMessage: string | null = null;
  successMessage: string | null = null;

  private readonly authService = inject(AuthService);

  submit(): void {
    this.errorMessage = null;
    this.successMessage = null;

    const email = this.email.trim();
    if (!email) {
      this.errorMessage = 'Please enter your email address.';
      return;
    }

    this.isSubmitting = true;

    const request: ForgotPasswordRequest = { email };
    this.authService.forgot(request).subscribe({
      next: () => {
        this.successMessage = 'If that email exists, a reset link has been sent.';
        this.isSubmitting = false;
      },
      error: (error: unknown) => {
        this.isSubmitting = false;
        if (error instanceof HttpErrorResponse) {
          this.errorMessage = error.error?.message ?? 'Unable to send reset link.';
        } else {
          this.errorMessage = 'Unable to send reset link.';
        }
      },
    });
  }
}

