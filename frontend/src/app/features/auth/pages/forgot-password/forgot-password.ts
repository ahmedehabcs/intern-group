import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-forgot-password',
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './forgot-password.html',
  styleUrl: './forgot-password.css',
})
export class ForgotPassword {
  email = '';
  isSubmitting = false;
  errorMessage: string | null = null;

  private readonly router = inject(Router);

  submit(): void {
    this.errorMessage = null;

    if (!this.email.trim()) {
      this.errorMessage = 'Please enter your email address.';
      return;
    }

    this.isSubmitting = true;
    this.router.navigate(['/auth/otp-verification'], {
      queryParams: { email: this.email.trim() },
    });
  }
}

