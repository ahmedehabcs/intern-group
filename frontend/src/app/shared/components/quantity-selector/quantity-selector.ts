import { Component, input, output } from '@angular/core';
@Component({
  selector: 'app-quantity-selector',
  templateUrl: './quantity-selector.html',
})
export class QuantitySelector {
  readonly value = input.required<number>();
  readonly change = output<number>();
  decrease(): void {
    this.change.emit(Math.max(1, this.value() - 1));
  }
}
