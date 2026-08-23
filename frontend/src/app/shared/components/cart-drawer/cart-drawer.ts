import { Component, DestroyRef, inject, input, output, signal } from '@angular/core';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { RouterLink } from '@angular/router';
import { finalize } from 'rxjs';
import { CartService } from '../../../features/cart/services/cart.service';
import { ImageFallbackDirective } from '../../directives/image-fallback.directive';

@Component({
  selector: 'app-cart-drawer',
  imports: [RouterLink, ImageFallbackDirective],
  templateUrl: './cart-drawer.html',
})
export class CartDrawer {
  readonly open = input(false);
  readonly closed = output<void>();
  readonly cart = inject(CartService);
  private readonly destroyRef = inject(DestroyRef);
  readonly processingId = signal<number | null>(null);
  readonly error = signal<string | null>(null);

  updateQuantity(id: number, quantity: number): void {
    if (quantity < 1) return;
    this.processingId.set(id);
    this.cart.quantity(id, quantity).pipe(finalize(() => this.processingId.set(null)), takeUntilDestroyed(this.destroyRef)).subscribe({ error: () => this.error.set('Could not update this item.') });
  }

  remove(id: number): void {
    this.processingId.set(id);
    this.cart.remove(id).pipe(finalize(() => this.processingId.set(null)), takeUntilDestroyed(this.destroyRef)).subscribe({ error: () => this.error.set('Could not remove this item.') });
  }
}
