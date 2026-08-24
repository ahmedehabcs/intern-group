import { HttpClient, HttpParams } from '@angular/common/http';
import { computed, inject, Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { environment } from '../../../../environments/environment';
import { TokenService } from '../../../core/auth/services/token.service';
import { LoginRequest, LoginResponse } from '../models/login.model';
import { RegisterResponse, VerifyOtpRequest } from '../models/otp.model';
import { CustomerSignupRequest, DriverSignupRequest } from '../models/register.model';
import { ForgotPasswordRequest, ResetPasswordRequest } from '../models/reset.model';
import { MockDataStore } from '../../../mocks/mock-data.store';
@Injectable({ providedIn: 'root' })
export class AuthService {
  private http = inject(HttpClient);
  private tokens = inject(TokenService);
  private mock = inject(MockDataStore);
  private base = `${environment.apiUrl}/api/auth`;
  readonly authenticated = computed(() => this.tokens.isValid());
  readonly role = this.tokens.role.asReadonly();
  login(b: LoginRequest): Observable<LoginResponse> {
    return (
      environment.mock.enabled
        ? this.mock.login(b)
        : this.http.post<LoginResponse>(`${this.base}/login`, b)
    ).pipe(tap((r) => this.tokens.setSession(r.accessToken, r.role)));
  }
  signupCustomer(b: CustomerSignupRequest): Observable<RegisterResponse> {
    // TODO(api-contract): generated CustomerSignupRequest documentation is malformed;
    // this mapping preserves the fields already present in the original frontend form.
    return environment.mock.enabled
      ? this.mock.register()
      : this.http.post<RegisterResponse>(`${this.base}/signup/customer`, b);
  }
  signupDriver(b: DriverSignupRequest): Observable<RegisterResponse> {
    // TODO(api-contract): base DriverSignupRequest fields are malformed in generated docs.
    return environment.mock.enabled
      ? this.mock.register()
      : this.http.post<RegisterResponse>(`${this.base}/signup/driver`, b);
  }
  verifyOtp(b: VerifyOtpRequest): Observable<string> {
    if (environment.mock.enabled) return this.mock.verifyOtp(b);
    return this.http.post(`${this.base}/verify-otp`, b, { responseType: 'text' });
  }
  resendOtp(email: string): Observable<string> {
    if (environment.mock.enabled) return this.mock.authMessage(`OTP resent to ${email}`);
    return this.http.post(`${this.base}/resend-otp`, null, {
      params: new HttpParams().set('email', email),
      responseType: 'text',
    });
  }
  forgotPassword(b: ForgotPasswordRequest): Observable<string> {
    if (environment.mock.enabled)
      return this.mock.authMessage(`Password reset instructions sent to ${b.email}`);
    return this.http.post(`${this.base}/forgot-password`, b, { responseType: 'text' });
  }
  resetPassword(b: ResetPasswordRequest): Observable<string> {
    if (environment.mock.enabled) return this.mock.authMessage(`Password reset for ${b.email}`);
    return this.http.post(`${this.base}/reset-password`, b, { responseType: 'text' });
  }
  logout(): void {
    this.tokens.clear();
  }
}
