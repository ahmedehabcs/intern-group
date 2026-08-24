import { Component, DestroyRef, inject, signal } from '@angular/core';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { FormControl, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { catchError, debounceTime, distinctUntilChanged, map, of, switchMap, tap } from 'rxjs';
import { RestaurantCard } from '../../../../shared/components/restaurant-card/restaurant-card';
import { SectionHeading } from '../../../../shared/components/section-heading/section-heading';
import { SearchResponse } from '../../models/search.models';
import { SearchService } from '../../services/search.service';

@Component({
  selector: 'app-search-page',
  imports: [ReactiveFormsModule, RouterLink, RestaurantCard, SectionHeading],
  templateUrl: './search-page.html',
})
export class SearchPage {
  private api = inject(SearchService);
  private route = inject(ActivatedRoute);
  private router = inject(Router);
  private destroy = inject(DestroyRef);
  readonly query = new FormControl(this.route.snapshot.queryParamMap.get('search') ?? '', {
    nonNullable: true,
  });
  readonly result = signal<SearchResponse | null>(null);
  readonly loading = signal(false);
  readonly error = signal<string | null>(null);
  constructor() {
    this.query.valueChanges
      .pipe(
        map((value) => value.trim()),
        debounceTime(250),
        distinctUntilChanged(),
        tap(
          (query) =>
            void this.router.navigate([], {
              relativeTo: this.route,
              queryParams: { search: query || null },
              replaceUrl: true,
            }),
        ),
        takeUntilDestroyed(this.destroy),
      )
      .subscribe();

    this.route.queryParamMap
      .pipe(
        map((params) => (params.get('search') ?? '').trim()),
        distinctUntilChanged(),
        tap((query) => {
          if (this.query.value.trim() !== query) {
            this.query.setValue(query, { emitEvent: false });
          }
        }),
        tap(() => {
          this.loading.set(true);
          this.error.set(null);
        }),
        switchMap((query) =>
          this.api.search(query).pipe(
            catchError(() => {
              this.error.set('Search is unavailable right now.');
              return of(null);
            }),
          ),
        ),
        takeUntilDestroyed(this.destroy),
      )
      .subscribe({
        next: (value) => {
          this.result.set(value);
          this.loading.set(false);
        },
      });
  }
}
