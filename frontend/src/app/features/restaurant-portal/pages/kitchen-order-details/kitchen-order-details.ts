import { DatePipe } from '@angular/common';
import { Component, DestroyRef, inject, signal } from '@angular/core';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { FormControl, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { finalize } from 'rxjs';
import { StatusBadge } from '../../../../shared/components/status-badge/status-badge';
import { ConfirmationDialogService } from '../../../../shared/services/confirmation-dialog.service';
import { OrderStatus } from '../../../orders/models/order.models';
import { KitchenOrderDetailsResponse } from '../../models/kitchen.models';
import { KitchenApiService } from '../../services/kitchen-api.service';

@Component({
  selector: 'app-kitchen-order-details',
  imports: [ReactiveFormsModule, RouterLink, DatePipe, StatusBadge],
  templateUrl: './kitchen-order-details.html',
})
export class KitchenOrderDetails {
  private readonly api = inject(KitchenApiService);
  private readonly route = inject(ActivatedRoute);
  private readonly destroy = inject(DestroyRef);
  private readonly confirmation = inject(ConfirmationDialogService);
  readonly order = signal<KitchenOrderDetailsResponse | null>(null);
  readonly loading = signal(true);
  readonly submitting = signal(false);
  readonly error = signal<string | null>(null);
  readonly status = new FormControl<OrderStatus>('CONFIRMED', { nonNullable: true });
  readonly reason = new FormControl('', {
    nonNullable: true,
    validators: [Validators.required, Validators.maxLength(255)],
  });

  constructor() {
    this.load();
  }

  load(): void {
    this.api
      .order(Number(this.route.snapshot.paramMap.get('orderId')))
      .pipe(
        finalize(() => this.loading.set(false)),
        takeUntilDestroyed(this.destroy),
      )
      .subscribe({
        next: (value) => {
          this.order.set(value);
          this.status.setValue(value.status);
        },
        error: () => this.error.set('Unable to load this kitchen order.'),
      });
  }

  update(): void {
    const order = this.order();
    if (!order) return;
    this.submitting.set(true);
    this.api
      .updateStatus(order.id, this.status.value)
      .pipe(
        finalize(() => this.submitting.set(false)),
        takeUntilDestroyed(this.destroy),
      )
      .subscribe({
        next: (value) => this.order.set(value),
        error: () => this.error.set('Order status could not be updated.'),
      });
  }

  async cancel(): Promise<void> {
    const order = this.order();
    if (
      !order ||
      this.reason.invalid ||
      !(await this.confirmation.confirm(
        'Cancel this kitchen order?',
        'Cancel kitchen order',
        'Cancel order',
      ))
    )
      return;
    this.submitting.set(true);
    this.api
      .cancel(order.id, this.reason.value)
      .pipe(
        finalize(() => this.submitting.set(false)),
        takeUntilDestroyed(this.destroy),
      )
      .subscribe({
        next: (value) => this.order.set(value),
        error: () => this.error.set('Order could not be cancelled.'),
      });
  }
}
