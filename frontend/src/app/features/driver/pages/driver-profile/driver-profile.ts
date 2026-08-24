import { Component, DestroyRef, inject, signal } from '@angular/core';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { finalize } from 'rxjs';
import { DriverProfileService } from '../../services/driver-profile.service';
import { TokenService } from '../../../../core/auth/services/token.service';
@Component({
  selector: 'app-driver-profile',
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './driver-profile.html',
})
export class DriverProfile {
  private api = inject(DriverProfileService);
  private destroy = inject(DestroyRef);
  private tokens = inject(TokenService);
  private router = inject(Router);
  readonly email = signal('');
  readonly loading = signal(true);
  readonly submitting = signal(false);
  readonly error = signal<string | null>(null);
  readonly message = signal<string | null>(null);
  readonly isDark = signal(document.documentElement.dataset['theme'] === 'dark');
  readonly form = new FormGroup({
    name: new FormControl('', {
      nonNullable: true,
      validators: [Validators.required, Validators.minLength(2), Validators.maxLength(50)],
    }),
    phoneNumber: new FormControl('', { nonNullable: true, validators: [Validators.maxLength(20)] }),
    vehicleType: new FormControl('', {
      nonNullable: true,
      validators: [Validators.required, Validators.minLength(2), Validators.maxLength(50)],
    }),
    licenseNumber: new FormControl('', {
      nonNullable: true,
      validators: [Validators.required, Validators.minLength(2), Validators.maxLength(50)],
    }),
    nationalId: new FormControl('', {
      nonNullable: true,
      validators: [Validators.required, Validators.minLength(10), Validators.maxLength(20)],
    }),
  });
  constructor() {
    this.api
      .get()
      .pipe(
        finalize(() => this.loading.set(false)),
        takeUntilDestroyed(this.destroy),
      )
      .subscribe({
        next: (v) => {
          this.email.set(v.email);
          this.form.patchValue(v);
        },
        error: () => this.error.set('Unable to load your profile.'),
      });
  }
  submit(): void {
    if (this.form.invalid) return;
    this.submitting.set(true);
    this.api
      .update(this.form.getRawValue())
      .pipe(
        finalize(() => this.submitting.set(false)),
        takeUntilDestroyed(this.destroy),
      )
      .subscribe({
        next: () => {
          this.message.set('Profile updated.');
        },
        error: () => this.error.set('Profile could not be updated.'),
      });
  }
  toggleTheme(): void {
    this.isDark.update((value) => !value);
    const theme = this.isDark() ? 'dark' : 'light';
    document.documentElement.dataset['theme'] = theme;
    localStorage.setItem('talabaty-theme', theme);
  }
  logout(): void {
    this.tokens.clear();
    void this.router.navigate(['/auth/login']);
  }
}
