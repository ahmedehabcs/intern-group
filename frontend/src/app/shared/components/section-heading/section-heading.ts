import { Component, input } from '@angular/core';
@Component({
  selector: 'app-section-heading',
  template: `<div class="flex items-end justify-between gap-4">
    <div>
      <p class="text-xs font-medium uppercase tracking-[0.18em] text-orange-600">{{ eyebrow() }}</p>
      <h2 class="mt-1 text-xl font-medium tracking-tight text-slate-900 sm:text-2xl">
        {{ title() }}
      </h2>
      @if (description()) {
        <p class="mt-1 text-sm text-slate-500">{{ description() }}</p>
      }
    </div>
    <ng-content />
  </div>`,
})
export class SectionHeading {
  readonly title = input.required<string>();
  readonly eyebrow = input('');
  readonly description = input('');
}
