import { Role } from './auth-user.model';

export interface AuthToken {
  accessToken: string;
  role: Role;
}
