export type Role = 'CUSTOMER' | 'DRIVER' | 'ADMIN' | 'KITCHEN_MANAGER';

export interface AuthUser {
  role: Role;
}
