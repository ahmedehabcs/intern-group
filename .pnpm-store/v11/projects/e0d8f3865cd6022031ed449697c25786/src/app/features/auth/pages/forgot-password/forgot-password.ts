import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-forgot-password',
  imports: [FormsModule, RouterLink],
  templateUrl: './forgot-password.html',
  styleUrl: './forgot-password.css',
})
export class ForgotPassword {
  email = '';

  constructor(private readonly router: Router) {}

  submit(): void {
    this.router.navigate(['/auth/otp-verification'], { queryParams: { email: this.email } });
  }
}
