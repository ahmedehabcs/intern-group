import { DatePipe } from '@angular/common';
import { Component, DestroyRef, inject, signal } from '@angular/core';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { RouterLink } from '@angular/router';
import { finalize, forkJoin } from 'rxjs';
import { StatusBadge } from '../../../../shared/components/status-badge/status-badge';
import { ImageFallbackDirective } from '../../../../shared/directives/image-fallback.directive';
import { RestaurantResponse } from '../../../restaurants/models/restaurant.models';
import { RestaurantService } from '../../../restaurants/services/restaurant.service';
import { CustomerOrderPageResponse } from '../../models/order.models';
import { OrderService } from '../../services/order.service';

@Component({
  selector: 'app-orders-page',
  imports: [RouterLink, DatePipe, StatusBadge, ImageFallbackDirective],
  templateUrl: './orders-page.html',
})
export class OrdersPage {
  private readonly api = inject(OrderService);
  private readonly restaurantsApi = inject(RestaurantService);
  private readonly destroy = inject(DestroyRef);
  readonly data = signal<CustomerOrderPageResponse | null>(null);
  readonly restaurants = signal<RestaurantResponse[]>([]);
  readonly page = signal(0);
  readonly size = signal(10);
  readonly loading = signal(true);
  readonly error = signal<string | null>(null);

  constructor() { this.load(); }

  load(page = this.page()): void {
    this.page.set(page);
    this.loading.set(true);
    this.error.set(null);
    forkJoin({ orders: this.api.list(page, this.size()), restaurants: this.restaurantsApi.restaurants() })
      .pipe(finalize(() => this.loading.set(false)), takeUntilDestroyed(this.destroy))
      .subscribe({
        next: value => {
          this.data.set(value.orders);
          this.restaurants.set(value.restaurants);
        },
        error: () => this.error.set('Unable to load your orders.'),
      });
  }

  restaurant(name: string): RestaurantResponse | undefined {
    return this.restaurants().find(item => item.name === name);
  }
}
