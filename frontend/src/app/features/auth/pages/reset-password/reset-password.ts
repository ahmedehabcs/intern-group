import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-reset-password',
  imports: [FormsModule],
  templateUrl: './reset-password.html',
  styleUrl: './reset-password.css',
})
export class ResetPassword {
  password = '';
  confirm = '';
  showPassword = false;
  showConfirm = false;

  constructor(private readonly router: Router) {}

  get hasLength(): boolean {
    return this.password.length >= 8;
  }

  get hasMixed(): boolean {
    return /[A-Za-z]/.test(this.password) && /\d/.test(this.password);
  }

  get hasSpecial(): boolean {
    return /[^A-Za-z0-9]/.test(this.password);
  }

  get strength(): number {
    return [this.hasLength, this.hasMixed, this.hasSpecial, this.password.length >= 12].filter(Boolean).length;
  }

  get strengthLabel(): string {
    return ['TOO WEAK', 'WEAK', 'FAIR', 'STRONG', 'VERY STRONG'][this.strength];
  }

  submit(): void {
    this.router.navigateByUrl('/auth/login');
  }
}
