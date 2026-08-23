import { Component, DestroyRef, inject, signal } from '@angular/core';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { ActivatedRoute } from '@angular/router';
import { finalize } from 'rxjs';
import { RestaurantDetailsResponse } from '../../models/restaurant.models';
import { RestaurantService } from '../../services/restaurant.service';
import { MenuItemCard } from '../../../../shared/components/menu-item-card/menu-item-card';
import { ImageFallbackDirective } from '../../../../shared/directives/image-fallback.directive';
@Component({
  selector: 'app-restaurant-details',
  imports: [MenuItemCard, ImageFallbackDirective],
  templateUrl: './restaurant-details.html',
})
export class RestaurantDetails {
  private api = inject(RestaurantService);
  private route = inject(ActivatedRoute);
  private destroy = inject(DestroyRef);
  readonly item = signal<RestaurantDetailsResponse | null>(null);
  readonly loading = signal(true);
  readonly error = signal<string | null>(null);
  constructor() {
    this.api
      .restaurant(Number(this.route.snapshot.paramMap.get('restaurantId')))
      .pipe(
        finalize(() => this.loading.set(false)),
        takeUntilDestroyed(this.destroy),
      )
      .subscribe({
        next: (v) => this.item.set(v),
        error: () => this.error.set('Unable to load this restaurant.'),
      });
  }
}
