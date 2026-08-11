import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { OTPRequestModel } from '../../models/otp.model';
import { HttpErrorResponse } from '@angular/common/http';
import { firstValueFrom } from 'rxjs';

@Component({
  selector: 'app-otp-verification',
  imports: [CommonModule, FormsModule],
  templateUrl: './otp-verification.html',
  styleUrl: './otp-verification.css',
})
export class OtpVerification implements OnInit {
  email: string = '';
  otp: string = '';
  isSubmitting: boolean = false;
  requestError: string | null = null;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.email = params['email'] || '';
    });
  }

  async onSubmit(event: Event): Promise<void> {
    event.preventDefault();
    this.requestError = null;

    if (!this.otp || this.otp.trim().length === 0) {
      this.requestError = 'Please enter the verification code.';
      return;
    }

    this.isSubmitting = true;

    const request: OTPRequestModel = {
      email: this.email,
      otp: this.otp
    };

    try {
      const response = await firstValueFrom(
        this.authService.verifyOtp(request)
      );
      console.log('OTP verification response:', response);
      this.router.navigate(['/auth/login']);
    } catch (error: unknown) {
      if (error instanceof HttpErrorResponse) {
        this.requestError = error.error?.message ?? 'OTP verification failed.';
      } else {
        this.requestError = 'OTP verification failed.';
      }
    } finally {
      this.isSubmitting = false;
    }
  }
}
