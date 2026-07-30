import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-register',
  imports: [FormsModule, RouterLink],
  templateUrl: './register.html',
  styleUrl: './register.scss',
})
export class Register {
  username = '';
  email = '';
  password = '';
  confirm = '';
  agreed = false;

  constructor(private readonly router: Router) {}

  submit(): void {
    this.router.navigate(['/auth/otp-verification'], { queryParams: { email: this.email } });
  }
}
