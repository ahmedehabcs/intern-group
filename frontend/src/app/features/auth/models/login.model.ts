import { Role } from '../../../core/auth/models/auth-user.model';

export interface LoginRequest {
    email: string;
    password: string;
}
export interface LoginResponse { message: string; accessToken: string; tokenType: string; expiresIn: number; userId: number; email: string; role: Role; }
