import { Role } from '../core/auth/models/auth-user.model';

export const MOCK_PASSWORD = 'password123';
export const MOCK_OTP = '123456';

export const MOCK_ACCOUNTS = [
  { userId: 1, email: 'customer@talabaty.local', role: 'CUSTOMER' },
  { userId: 2, email: 'driver@talabaty.local', role: 'DRIVER' },
  { userId: 3, email: 'kitchen@talabaty.local', role: 'KITCHEN_MANAGER' },
  { userId: 4, email: 'admin@talabaty.local', role: 'ADMIN' },
] satisfies { userId: number; email: string; role: Role }[];
