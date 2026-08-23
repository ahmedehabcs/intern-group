import { DatePipe } from '@angular/common';
import { Component, DestroyRef, inject, signal } from '@angular/core';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { FormControl, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { catchError, finalize, map, of, switchMap } from 'rxjs';
import { StatusBadge } from '../../../../shared/components/status-badge/status-badge';
import { ImageFallbackDirective } from '../../../../shared/directives/image-fallback.directive';
import { ConfirmationDialogService } from '../../../../shared/services/confirmation-dialog.service';
import {
  RestaurantDetailsResponse,
  RestaurantResponse,
} from '../../../restaurants/models/restaurant.models';
import { RestaurantService } from '../../../restaurants/services/restaurant.service';
import { CustomerOrderDetailsResponse } from '../../models/order.models';
import { DeliveryFeedbackService } from '../../services/delivery-feedback.service';
import { OrderService } from '../../services/order.service';

@Component({
  selector: 'app-order-details',
  imports: [ReactiveFormsModule, RouterLink, DatePipe, StatusBadge, ImageFallbackDirective],
  templateUrl: './order-details.html',
})
export class OrderDetails {
  private readonly api = inject(OrderService);
  private readonly feedbackApi = inject(DeliveryFeedbackService);
  private readonly restaurantsApi = inject(RestaurantService);
  private readonly route = inject(ActivatedRoute);
  private readonly destroy = inject(DestroyRef);
  private readonly confirmation = inject(ConfirmationDialogService);
  readonly order = signal<CustomerOrderDetailsResponse | null>(null);
  readonly restaurant = signal<RestaurantResponse | null>(null);
  readonly restaurantDetails = signal<RestaurantDetailsResponse | null>(null);
  readonly loading = signal(true);
  readonly processing = signal(false);
  readonly feedbackSent = signal(false);
  readonly error = signal<string | null>(null);
  readonly rating = new FormControl(5, {
    nonNullable: true,
    validators: [Validators.required, Validators.min(1), Validators.max(5)],
  });

  constructor() {
    this.load();
  }

  load(): void {
    const id = Number(this.route.snapshot.paramMap.get('orderId'));
    this.api
      .get(id)
      .pipe(
        switchMap((order) =>
          this.restaurantsApi
            .restaurants()
            .pipe(
              map((restaurants) => ({
                order,
                restaurant: restaurants.find((item) => item.name === order.restaurantName) ?? null,
              })),
            ),
        ),
        switchMap((context) =>
          context.restaurant
            ? this.restaurantsApi.restaurant(context.restaurant.id).pipe(
                map((details) => ({ ...context, details })),
                catchError(() => of({ ...context, details: null })),
              )
            : of({ ...context, details: null }),
        ),
        finalize(() => this.loading.set(false)),
        takeUntilDestroyed(this.destroy),
      )
      .subscribe({
        next: (context) => {
          this.order.set(context.order);
          this.restaurant.set(context.restaurant);
          this.restaurantDetails.set(context.details);
        },
        error: () => this.error.set('Unable to load this order.'),
      });
  }

  itemImage(productName: string): string {
    return (
      this.restaurantDetails()
        ?.menuSections.flatMap((section) => section.menuItems)
        .find((item) => item.name === productName)?.imageUrl ||
      '/assets/images/talabaty-food-table.png'
    );
  }

  async cancel(): Promise<void> {
    const order = this.order();
    if (
      !order ||
      !(await this.confirmation.confirm('Cancel this order?', 'Cancel order', 'Cancel order'))
    )
      return;
    this.processing.set(true);
    this.api
      .cancel(order.id)
      .pipe(
        finalize(() => this.processing.set(false)),
        takeUntilDestroyed(this.destroy),
      )
      .subscribe({
        next: (value) => this.order.set(value),
        error: () => this.error.set('The order could not be cancelled.'),
      });
  }

  sendFeedback(): void {
    const order = this.order();
    if (!order || this.rating.invalid) return;
    this.processing.set(true);
    this.feedbackApi
      .create(order.id, this.rating.value)
      .pipe(
        finalize(() => this.processing.set(false)),
        takeUntilDestroyed(this.destroy),
      )
      .subscribe({
        next: () => this.feedbackSent.set(true),
        error: () => this.error.set('Feedback could not be submitted.'),
      });
  }
}
