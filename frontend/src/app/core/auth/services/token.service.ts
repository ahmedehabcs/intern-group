import { Injectable, signal } from '@angular/core';
import { STORAGE_KEYS } from '../../config/storage-keys';
import { Role } from '../models/auth-user.model';

@Injectable({ providedIn: 'root' })
export class TokenService {
  readonly token = signal<string | null>(localStorage.getItem(STORAGE_KEYS.accessToken));
  readonly role = signal<Role | null>(localStorage.getItem(STORAGE_KEYS.role) as Role | null);

  setSession(token: string, role: Role): void {
    localStorage.setItem(STORAGE_KEYS.accessToken, token);
    localStorage.setItem(STORAGE_KEYS.role, role);
    this.token.set(token);
    this.role.set(role);
  }

  clear(): void {
    localStorage.removeItem(STORAGE_KEYS.accessToken);
    localStorage.removeItem(STORAGE_KEYS.role);
    this.token.set(null);
    this.role.set(null);
  }

  isValid(): boolean {
    const token = this.token();
    if (!token) return false;
    try {
      const payload = JSON.parse(atob(token.split('.')[1])) as { exp?: number };
      return !payload.exp || payload.exp * 1000 > Date.now();
    } catch {
      return true;
    }
  }
}
