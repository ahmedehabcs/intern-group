import { Component, DestroyRef, inject, signal } from '@angular/core';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { catchError, forkJoin, finalize, of, throwError } from 'rxjs';
import { HttpErrorResponse } from '@angular/common/http';
import { AddressResponse, CustomerProfileResponse } from '../../models/account.models';
import { AddressService } from '../../services/address.service';
import { ProfileService } from '../../services/profile.service';
import { ConfirmationDialogService } from '../../../../shared/services/confirmation-dialog.service';
@Component({
  selector: 'app-account-page',
  imports: [ReactiveFormsModule],
  templateUrl: './account-page.html',
})
export class AccountPage {
  private addressApi = inject(AddressService);
  private profileApi = inject(ProfileService);
  private destroy = inject(DestroyRef);
  private confirmation = inject(ConfirmationDialogService);
  readonly addresses = signal<AddressResponse[]>([]);
  readonly profile = signal<CustomerProfileResponse | null>(null);
  readonly profileExists = signal(false);
  readonly loading = signal(true);
  readonly submitting = signal(false);
  readonly error = signal<string | null>(null);
  readonly message = signal<string | null>(null);
  readonly editingAddressId = signal<number | null>(null);
  readonly profileForm = new FormGroup({
    name: new FormControl('', {
      nonNullable: true,
      validators: [Validators.required, Validators.minLength(2), Validators.maxLength(50)],
    }),
    phoneNumber: new FormControl('', { nonNullable: true, validators: [Validators.maxLength(20)] }),
  });
  readonly addressForm = new FormGroup({
    street: new FormControl('', {
      nonNullable: true,
      validators: [Validators.required, Validators.maxLength(255)],
    }),
    building: new FormControl('', { nonNullable: true, validators: [Validators.maxLength(255)] }),
    floor: new FormControl('', { nonNullable: true, validators: [Validators.maxLength(255)] }),
    apartment: new FormControl('', { nonNullable: true, validators: [Validators.maxLength(255)] }),
    city: new FormControl('', {
      nonNullable: true,
      validators: [Validators.required, Validators.maxLength(255)],
    }),
    governorateId: new FormControl(0, {
      nonNullable: true,
      validators: [Validators.required, Validators.min(1)],
    }),
  });
  constructor() {
    this.load();
  }
  load(): void {
    this.loading.set(true);
    forkJoin({
      addresses: this.addressApi.list(),
      profile: this.profileApi
        .get()
        .pipe(
          catchError((error: HttpErrorResponse) =>
            error.status === 404 ? of(null) : throwError(() => error),
          ),
        ),
    })
      .pipe(
        finalize(() => this.loading.set(false)),
        takeUntilDestroyed(this.destroy),
      )
      .subscribe({
        next: (v) => {
          this.addresses.set(v.addresses);
          this.profile.set(v.profile);
          this.profileExists.set(v.profile !== null);
          if (v.profile) this.profileForm.patchValue(v.profile);
        },
        error: () => this.error.set('Unable to load your account.'),
      });
  }
  saveProfile(): void {
    if (this.profileForm.invalid) return;
    const value = this.profileForm.getRawValue();
    this.mutate(this.profileApi.update(value), () => {
      this.profile.set({ email: this.profile()?.email ?? '', ...value });
      this.profileExists.set(true);
      this.message.set('Profile saved.');
    });
  }
  addAddress(): void {
    if (this.addressForm.invalid) {
      this.addressForm.markAllAsTouched();
      return;
    }
    const request = this.editingAddressId()
      ? this.addressApi.update(this.editingAddressId()!, this.addressForm.getRawValue())
      : this.addressApi.create(this.addressForm.getRawValue());
    this.mutate(request, (v) => {
      this.addresses.update((a) =>
        this.editingAddressId() ? a.map((item) => (item.id === v.id ? v : item)) : [...a, v],
      );
      this.editingAddressId.set(null);
      this.addressForm.reset();
      this.message.set('Address saved.');
    });
  }
  editAddress(address: AddressResponse): void {
    this.editingAddressId.set(address.id);
    this.addressForm.patchValue(address);
  }
  setDefault(id: number): void {
    this.mutate(this.addressApi.setDefault(id), () => this.load());
  }
  async remove(id: number): Promise<void> {
    if (
      !(await this.confirmation.confirm(
        'Delete this delivery address?',
        'Delete address',
        'Delete',
      ))
    )
      return;
    this.mutate(this.addressApi.delete(id), () =>
      this.addresses.update((v) => v.filter((a) => a.id !== id)),
    );
  }
  private mutate<T>(request: import('rxjs').Observable<T>, next: (v: T) => void): void {
    this.submitting.set(true);
    this.error.set(null);
    request
      .pipe(
        finalize(() => this.submitting.set(false)),
        takeUntilDestroyed(this.destroy),
      )
      .subscribe({ next, error: () => this.error.set('The change could not be saved.') });
  }
}
