import { ChangeDetectionStrategy, Component, inject, signal } from '@angular/core';
import { email, FormField, form, minLength, pattern, required, submit, validate } from '@angular/forms/signals';
import { RegisterFormModel, RegisterRequest } from '../../models/register.model';
import { firstValueFrom } from 'rxjs';
import { AuthService } from '../../services/auth.service';
import { HttpErrorResponse } from '@angular/common/http';
import { RouterLink, Router } from '@angular/router';

@Component({
  selector: 'app-register',
  imports: [RouterLink, FormField],
  templateUrl: './register.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class Register {
  private readonly AuthService = inject(AuthService);
  private readonly router = inject(Router);
  protected readonly requestError = signal<string | null>(null);

  protected readonly RegisterModel = signal<RegisterFormModel>({
    name: '',
    email: '',
    password: '',
    confirmPassword: '',
    isDelivery: false,
  });

  protected readonly registerForm = form(this.RegisterModel, (field) => {
    required(field.name, { message: 'Name is required.' });
    minLength(field.name, 3, { message: 'Name must contain at least 3 characters.' });
    pattern(field.name, /^[a-zA-Z\s]+$/, { message: 'Use only letters and spaces.' });

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

  protected onDeliveryCheckboxChange(event: Event): void {
    const isChecked = (event.target as HTMLInputElement).checked;
    this.RegisterModel.update(model => ({
      ...model,
      isDelivery: isChecked
    }));
  }

  protected async onSubmit(even: Event): Promise<void> {
    even.preventDefault();

    this.requestError.set(null);

    await submit(this.registerForm, async () => {
      const formValue = this.RegisterModel();

      const request: RegisterRequest = {
        name: formValue.name,
        email: formValue.email,
        password: formValue.password,
        role: formValue.isDelivery ? 'delivery' : 'customer'
      }

      try {
        const response = await firstValueFrom(
          this.AuthService.register(request)
        )
        console.log('Registration request:', response);
        this.router.navigate(['/auth/otp-verification'], {
          queryParams: {
            email: request.email
          }
        });
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
      name: '',
      email: '',
      password: '',
      confirmPassword: '',
      isDelivery: false,
    })
  }

}
