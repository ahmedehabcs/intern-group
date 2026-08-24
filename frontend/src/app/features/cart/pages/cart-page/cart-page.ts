import { Component, DestroyRef, inject, signal } from '@angular/core';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { RouterLink } from '@angular/router';
import { finalize } from 'rxjs';
import { CartItemResponse } from '../../models/cart.models';
import { CartService } from '../../services/cart.service';
import { ConfirmationDialogService } from '../../../../shared/services/confirmation-dialog.service';
import { QuantitySelector } from '../../../../shared/components/quantity-selector/quantity-selector';
import { ImageFallbackDirective } from '../../../../shared/directives/image-fallback.directive';
@Component({
  selector: 'app-cart-page',
  imports: [RouterLink, QuantitySelector, ImageFallbackDirective],
  templateUrl: './cart-page.html',
})
export class CartPage {
  readonly cartService = inject(CartService);
  private destroy = inject(DestroyRef);
  private confirmation = inject(ConfirmationDialogService);
  readonly loading = signal(true);
  readonly error = signal<string | null>(null);
  readonly processingId = signal<number | null>(null);
  constructor() {
    this.reload();
  }
  reload(): void {
    this.loading.set(true);
    this.cartService
      .load()
      .pipe(
        finalize(() => this.loading.set(false)),
        takeUntilDestroyed(this.destroy),
      )
      .subscribe({ error: () => this.error.set('Unable to load your cart.') });
  }
  quantity(id: number, value: number): void {
    if (value < 1) return;
    this.processingId.set(id);
    this.cartService
      .quantity(id, value)
      .pipe(
        finalize(() => this.processingId.set(null)),
        takeUntilDestroyed(this.destroy),
      )
      .subscribe({ error: () => this.error.set('Unable to update quantity.') });
  }
  replaceConfiguration(item: CartItemResponse, specialInstructions: string): void {
    this.processingId.set(item.id);
    this.cartService
      .replace(item.id, {
        quantity: item.quantity,
        specialInstructions: specialInstructions.trim() || undefined,
        addons: item.addons.map((addon) => ({
          menuItemAddonId: addon.menuItemAddonId,
          quantity: addon.quantity,
        })),
      })
      .pipe(
        finalize(() => this.processingId.set(null)),
        takeUntilDestroyed(this.destroy),
      )
      .subscribe({
        error: () => this.error.set('Unable to update item configuration.'),
      });
  }
  async remove(id: number): Promise<void> {
    if (
      !(await this.confirmation.confirm(
        'Remove this item from your cart?',
        'Remove item',
        'Remove',
      ))
    )
      return;
    this.processingId.set(id);
    this.cartService
      .remove(id)
      .pipe(
        finalize(() => this.processingId.set(null)),
        takeUntilDestroyed(this.destroy),
      )
      .subscribe({
        next: () => this.reload(),
        error: () => this.error.set('Unable to remove item.'),
      });
  }
  async clear(): Promise<void> {
    if (
      !(await this.confirmation.confirm(
        'This removes every item from your cart.',
        'Clear cart',
        'Clear cart',
      ))
    )
      return;
    this.cartService
      .clear()
      .pipe(takeUntilDestroyed(this.destroy))
      .subscribe({ error: () => this.error.set('Unable to clear cart.') });
  }
}
