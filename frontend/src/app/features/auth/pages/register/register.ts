import { ChangeDetectionStrategy, Component, inject, signal } from '@angular/core';
import { email, FormField, form, minLength, pattern, required, submit, validate } from '@angular/forms/signals';
import { RegisterFormModel, RegisterRequest } from '../../models/register.model';
import { firstValueFrom } from 'rxjs';
import { AuthService } from '../../services/auth.service';
import { HttpErrorResponse } from '@angular/common/http';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-register',
  imports: [RouterLink, FormField],
  templateUrl: './register.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class Register {
  private readonly AuthService = inject(AuthService);
  protected readonly requestError = signal<string | null>(null);

  protected readonly RegisterModel = signal<RegisterFormModel>({
    username: '',
    email: '',
    password: '',
    confirmPassword: '',
  });

  protected readonly registerForm = form(this.RegisterModel, (field) => {
    required(field.username, { message: 'Username is required.' });
    minLength(field.username, 3, { message: 'Username must contain at least 3 characters.' });
    pattern(field.username, /^[a-zA-Z0-9_]+$/, { message: 'Use only letters, numbers, and underscores.', });

    required(field.email, { message: 'Email is required.' });
    email(field.email, { message: 'Enter a valid email address.' });

    required(field.password, { message: 'Password is required.' });
    minLength(field.password, 8, { message: 'Password must contain at least 8 characters.' });
    // at least Uppercase letter, lowercase letter and a number (8 Chars) 
    pattern(field.password, /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).+$/, { message: 'Password must include an uppercase letter, lowercase letter, and number.' });

    required(field.confirmPassword, { message: 'Please confirm your password.' });
    validate(field.confirmPassword,
      ({ value, valueOf }) => {
        if (value() !== valueOf(field.password)) {
          return { kind: 'passwordMismatch', message: 'Passwords do not match.' };
        }
        return null;
      },
    );
  })

  protected async onSubmit(even: Event): Promise<void> {
    even.preventDefault();

    this.requestError.set(null);

    await submit(this.registerForm, async () => {
      const formValue = this.RegisterModel();

      const request: RegisterRequest = {
        username: formValue.username,
        email: formValue.email,
        password: formValue.password
      }

      try {
        const response = await firstValueFrom(
          this.AuthService.register(request)
        )
        console.log('Registration request:', response);
        this.resetForm();
      } catch (error: unknown) {
        if (error instanceof HttpErrorResponse) {
          this.requestError.set(
            error.error?.message ?? 'Registration failed.',
          );
        } else {
          this.requestError.set('Registration failed.');
        }

      }
    })
  }

  continueWithGoogle(): void {

  }




  private resetForm(): void {
    this.RegisterModel.set({
      username: '',
      email: '',
      password: '',
      confirmPassword: '',
    })
  }

}
