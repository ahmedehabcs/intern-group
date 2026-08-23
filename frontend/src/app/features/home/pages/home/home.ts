import { Component, DestroyRef, inject, signal } from '@angular/core';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { ActivatedRoute, RouterLink } from '@angular/router';
import {
  catchError,
  distinctUntilChanged,
  finalize,
  forkJoin,
  map,
  of,
  switchMap,
  tap,
} from 'rxjs';
import { MenuItemCard } from '../../../../shared/components/menu-item-card/menu-item-card';
import { RestaurantCard } from '../../../../shared/components/restaurant-card/restaurant-card';
import { SectionHeading } from '../../../../shared/components/section-heading/section-heading';
import { ImageFallbackDirective } from '../../../../shared/directives/image-fallback.directive';
import { SearchResponse } from '../../../search/models/search.models';
import { SearchService } from '../../../search/services/search.service';
import {
  CategoryResponse,
  MenuItemResponse,
  RestaurantResponse,
} from '../../../restaurants/models/restaurant.models';
import { RestaurantService } from '../../../restaurants/services/restaurant.service';

@Component({
  selector: 'app-home',
  imports: [RouterLink, RestaurantCard, MenuItemCard, SectionHeading, ImageFallbackDirective],
  templateUrl: './home.html',
})
export class Home {
  private readonly api = inject(RestaurantService);
  private readonly searchApi = inject(SearchService);
  private readonly route = inject(ActivatedRoute);
  private readonly destroyRef = inject(DestroyRef);

  readonly categories = signal<CategoryResponse[]>([]);
  readonly restaurants = signal<RestaurantResponse[]>([]);
  readonly dishes = signal<MenuItemResponse[]>([]);
  readonly selectedCategoryId = signal<number | null>(null);
  readonly loading = signal(true);
  readonly dishesLoading = signal(false);
  readonly error = signal<string | null>(null);
  readonly activeSearch = signal('');
  readonly searchResult = signal<SearchResponse | null>(null);
  readonly searching = signal(false);
  readonly searchError = signal<string | null>(null);

  constructor() {
    this.api
      .categories()
      .pipe(takeUntilDestroyed())
      .subscribe({ next: (value) => this.categories.set(value) });
    this.loadRestaurants();
    this.route.queryParamMap
      .pipe(
        map((params) => (params.get('search') ?? '').trim()),
        distinctUntilChanged(),
        tap((query) => {
          this.activeSearch.set(query);
          this.searchError.set(null);
          if (query) this.searching.set(true);
          else this.searchResult.set(null);
        }),
        switchMap((query) =>
          query
            ? this.searchApi.search(query).pipe(finalize(() => this.searching.set(false)))
            : of(null),
        ),
        takeUntilDestroyed(this.destroyRef),
      )
      .subscribe({
        next: (result) => this.searchResult.set(result),
        error: () => {
          this.searching.set(false);
          this.searchError.set('Search is unavailable right now.');
        },
      });
  }

  loadRestaurants(categoryId?: number): void {
    this.selectedCategoryId.set(categoryId ?? null);
    this.loading.set(true);
    this.error.set(null);
    this.api
      .restaurants(categoryId)
      .pipe(
        finalize(() => this.loading.set(false)),
        takeUntilDestroyed(this.destroyRef),
      )
      .subscribe({
        next: (value) => {
          this.restaurants.set(value);
          this.loadDishes(value);
        },
        error: () => this.error.set('Unable to load restaurants. Please try again.'),
      });
  }

  private loadDishes(restaurants: RestaurantResponse[]): void {
    const requests = restaurants
      .slice(0, 3)
      .map((item) => this.api.restaurant(item.id).pipe(catchError(() => of(null))));
    if (!requests.length) {
      this.dishes.set([]);
      return;
    }
    this.dishesLoading.set(true);
    forkJoin(requests)
      .pipe(
        finalize(() => this.dishesLoading.set(false)),
        takeUntilDestroyed(this.destroyRef),
      )
      .subscribe((details) =>
        this.dishes.set(
          details
            .flatMap(
              (item) =>
                item?.menuSections.flatMap((section) => section.menuItems).slice(0, 2) ?? [],
            )
            .slice(0, 6),
        ),
      );
  }
}
