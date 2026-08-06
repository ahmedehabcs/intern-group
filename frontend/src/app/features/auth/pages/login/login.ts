import { HttpErrorResponse } from '@angular/common/http';
import { ChangeDetectionStrategy, Component, inject, signal } from '@angular/core';
import { RouterLink } from '@angular/router';
import { FormField, form, minLength, required, submit } from '@angular/forms/signals';
import { firstValueFrom } from 'rxjs';
import { LoginForm } from '../../models/login.model';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  imports: [FormField, RouterLink],
  templateUrl: './login.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class Login {
  private readonly authService = inject(AuthService);
  protected readonly requestError = signal<string | null>(null);

  protected readonly LoginModel = signal<LoginForm>({
    username: '',
    password: '',
  });

  protected readonly loginForm = form(this.LoginModel, (field) => {
    required(field.username, { message: 'Username is required.' });
    minLength(field.username, 3, {
      message: 'Username must contain at least 3 characters.',
    });

    required(field.password, { message: 'Password is required.' });
    minLength(field.password, 8, {
      message: 'Password must contain at least 8 characters.',
    });
  });

  protected async onSubmit(event: Event): Promise<void> {
    event.preventDefault();
    this.requestError.set(null);

    await submit(this.loginForm, async () => {
      const request: LoginForm = this.LoginModel();

      try {
        const response = await firstValueFrom(this.authService.login(request));
        console.log('Login request:', response);
        this.resetForm();
      } catch (error: unknown) {
        if (error instanceof HttpErrorResponse) {
          this.requestError.set(error.error?.message ?? 'Login failed.');
        } else {
          this.requestError.set('Login failed.');
        }
      }
    });
  }

  private resetForm(): void {
    this.LoginModel.set({
      username: '',
      password: '',
    });
  }
}
