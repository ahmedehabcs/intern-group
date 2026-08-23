import { Component, input, output } from '@angular/core';
@Component({
  selector: 'app-quantity-selector',
  template: `<div class="inline-flex items-center rounded-lg border border-slate-200 bg-white">
    <button
      type="button"
      class="grid h-9 w-9 place-items-center text-lg"
      (click)="decrease()"
      aria-label="Decrease quantity"
    >
      −</button
    ><span class="min-w-8 text-center text-sm font-bold">{{ value() }}</span
    ><button
      type="button"
      class="grid h-9 w-9 place-items-center text-lg"
      (click)="change.emit(value() + 1)"
      aria-label="Increase quantity"
    >
      +
    </button>
  </div>`,
})
export class QuantitySelector {
  readonly value = input.required<number>();
  readonly change = output<number>();
  decrease(): void {
    this.change.emit(Math.max(1, this.value() - 1));
  }
}
