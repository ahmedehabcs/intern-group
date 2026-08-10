import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import { RegisterRequest } from '../models/register.model';
import { LoginForm } from '../models/login.model';
import { ResetPasswordRequest } from '../models/reset.model';
import { ForgotPasswordRequest } from '../models/forgot-password.model';

@Injectable({
    providedIn: 'root',
})
export class AuthService {
    private readonly http = inject(HttpClient);
    private readonly baseUrl = `${environment.apiUrl}/auth`;

    register(data: RegisterRequest): Observable<RegisterRequest> {
        return this.http.post<RegisterRequest>(`${this.baseUrl}/register`, data);
    }

    login(data: LoginForm): Observable<LoginForm> {
        return this.http.post<LoginForm>(`${this.baseUrl}/login`, data);
    }

    forgot(data: ForgotPasswordRequest): Observable<void> {
        return this.http.post<void>(`${this.baseUrl}/forgot-password`, data);
    }

    reset(data: ResetPasswordRequest): Observable<ResetPasswordRequest>{
        return this.http.post<ResetPasswordRequest>(`${this.baseUrl}/reset`, data);
    }
}
