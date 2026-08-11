import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import { RegisterRequest } from '../models/register.model';
import { LoginForm } from '../models/login.model';
import { ResetPasswordRequest } from '../models/reset.model';
import { OTPRequestModel } from '../models/otp.model';

@Injectable({
    providedIn: 'root',
})
export class AuthService {
    private readonly http = inject(HttpClient);
    private readonly baseUrl = `${environment.apiUrl}/auth`;

    register(data: RegisterRequest): Observable<RegisterRequest> {
        return this.http.post<RegisterRequest>(`${this.baseUrl}/signup`, data);
    }

    login(data: LoginForm): Observable<LoginForm> {
        return this.http.post<LoginForm>(`${this.baseUrl}/login`, data)
    }

    reset(data: ResetPasswordRequest): Observable<ResetPasswordRequest>{
        return this.http.post<ResetPasswordRequest>(`${this.baseUrl}/reset`, data);
    }

    verifyOtp(data: OTPRequestModel): Observable<OTPRequestModel> {
        return this.http.post<OTPRequestModel>(`${this.baseUrl}/verify-otp`, data);
    }
}
