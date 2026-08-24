import { Component, DestroyRef, inject, signal } from '@angular/core';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { ActivatedRoute, Router, RouterLink, RouterLinkActive } from '@angular/router';
import { finalize, Observable } from 'rxjs';
import {
  DeliveryFeedbackResponse,
  OrderHistoryResponse,
  OrderSummaryResponse,
} from '../../models/driver.models';
import { DeliveryFeedbackService } from '../../services/delivery-feedback.service';
import { DeliveryService } from '../../services/delivery.service';
import { DriverProfileService } from '../../services/driver-profile.service';
import { ConfirmationDialogService } from '../../../../shared/services/confirmation-dialog.service';
import { TokenService } from '../../../../core/auth/services/token.service';
type Mode = 'available' | 'active' | 'history' | 'feedback';
@Component({
  selector: 'app-driver-page',
  imports: [RouterLink, RouterLinkActive],
  templateUrl: './driver-page.html',
})
export class DriverPage {
  private delivery = inject(DeliveryService);
  private profileApi = inject(DriverProfileService);
  private feedbackApi = inject(DeliveryFeedbackService);
  private route = inject(ActivatedRoute);
  private destroy = inject(DestroyRef);
  private confirmation = inject(ConfirmationDialogService);
  private tokens = inject(TokenService);
  private router = inject(Router);
  readonly mode = this.route.snapshot.data['mode'] as Mode;
  readonly available = signal<OrderSummaryResponse[]>([]);
  readonly active = signal<OrderSummaryResponse | null>(null);
  readonly history = signal<OrderHistoryResponse[]>([]);
  readonly feedback = signal<DeliveryFeedbackResponse[]>([]);
  readonly loading = signal(true);
  readonly processingId = signal<number | null>(null);
  readonly error = signal<string | null>(null);
  readonly online = signal(false);
  readonly isDark = signal(document.documentElement.dataset['theme'] === 'dark');
  constructor() {
    this.load();

    // Separate from load(), which only fetches the list for the current tab.
    // The online flag belongs to the driver rather than to any one tab, so it
    // is restored from the profile on every entry to the page - otherwise the
    // signal's initial false silently misreports an online driver as offline
    // after a refresh.
    this.profileApi
      .get()
      .pipe(takeUntilDestroyed(this.destroy))
      .subscribe({ next: (profile) => this.online.set(profile.online) });
  }
  load(): void {
    this.loading.set(true);
    const req: Observable<unknown> = (
      this.mode === 'available'
        ? this.delivery.available()
        : this.mode === 'active'
          ? this.delivery.active()
          : this.mode === 'history'
            ? this.delivery.history()
            : this.feedbackApi.mine()
    ) as Observable<unknown>;
    req
      .pipe(
        finalize(() => this.loading.set(false)),
        takeUntilDestroyed(this.destroy),
      )
      .subscribe({
        next: (value) => {
          if (this.mode === 'available') this.available.set(value as OrderSummaryResponse[]);
          else if (this.mode === 'active') this.active.set(value as OrderSummaryResponse | null);
          else if (this.mode === 'history') this.history.set(value as OrderHistoryResponse[]);
          else this.feedback.set(value as DeliveryFeedbackResponse[]);
        },
        error: () => this.error.set('Unable to load driver data.'),
      });
  }
  async action(id: number, a: 'accept' | 'pickup' | 'deliver' | 'cancel'): Promise<void> {
    if (
      a === 'cancel' &&
      !(await this.confirmation.confirm(
        'Cancel this assigned delivery?',
        'Cancel delivery',
        'Cancel delivery',
      ))
    )
      return;
    this.processingId.set(id);
    this.delivery
      .action(id, a)
      .pipe(
        finalize(() => this.processingId.set(null)),
        takeUntilDestroyed(this.destroy),
      )
      .subscribe({
        next: () => this.load(),
        error: () => this.error.set('The delivery could not be updated.'),
      });
  }
  toggleOnline(): void {
    const next = !this.online();
    this.delivery
      .status(next)
      .pipe(takeUntilDestroyed(this.destroy))
      .subscribe({
        next: () => this.online.set(next),
        error: () => this.error.set('Status could not be updated.'),
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
