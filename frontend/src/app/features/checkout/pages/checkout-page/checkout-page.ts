import { Component, DestroyRef, inject, signal } from '@angular/core';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { finalize, forkJoin } from 'rxjs';
import { AddressResponse } from '../../../account/models/account.models';
import { AddressService } from '../../../account/services/address.service';
import { CartService } from '../../../cart/services/cart.service';
import { OrderService } from '../../../orders/services/order.service';

@Component({
  selector: 'app-checkout-page',
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './checkout-page.html',
})
export class CheckoutPage {
  private readonly addressesApi = inject(AddressService);
  readonly cart = inject(CartService);
  private readonly orders = inject(OrderService);
  private readonly router = inject(Router);
  private readonly destroy = inject(DestroyRef);

  readonly addresses = signal<AddressResponse[]>([]);
  readonly loading = signal(true);
  readonly submitting = signal(false);
  readonly error = signal<string | null>(null);
  readonly form = new FormGroup({
    addressId: new FormControl<number | null>(null, { validators: [Validators.required, Validators.min(1)] }),
  });

  constructor() {
    forkJoin({ addresses: this.addressesApi.list(), cart: this.cart.load() })
      .pipe(finalize(() => this.loading.set(false)), takeUntilDestroyed(this.destroy))
      .subscribe({
        next: value => {
          this.addresses.set(value.addresses);
          this.form.controls.addressId.setValue(value.addresses.find(address => address.isDefault)?.id ?? value.addresses[0]?.id ?? null);
        },
        error: () => this.error.set('Unable to prepare checkout.'),
      });
  }

  submit(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }
    this.submitting.set(true);
    this.error.set(null);
    const value = this.form.getRawValue();
    this.orders.place({ addressId: value.addressId!, paymentMethod: 'CASH' })
      .pipe(finalize(() => this.submitting.set(false)), takeUntilDestroyed(this.destroy))
      .subscribe({
        next: order => {
          this.cart.cart.set(null);
          void this.router.navigate(['/orders', order.id]);
        },
        error: () => this.error.set('Your order could not be placed.'),
      });
  }
}
