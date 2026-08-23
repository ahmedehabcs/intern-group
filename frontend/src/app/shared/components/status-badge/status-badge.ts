import { Component, computed, input } from '@angular/core';
@Component({
  selector: 'app-status-badge',
  template: `<span
    class="inline-flex rounded-full px-2.5 py-1 text-xs font-medium"
    [class]="classes()"
    >{{ status().replace('_', ' ') }}</span
  >`,
})
export class StatusBadge {
  readonly status = input.required<string>();
  readonly classes = computed(() => {
    const status = this.status();
    if (['DELIVERED', 'APPROVED', 'READY'].includes(status))
      return 'bg-emerald-100 text-emerald-700';
    if (['CANCELLED', 'REJECTED'].includes(status)) return 'bg-red-100 text-red-700';
    if (['PREPARING', 'PICKED_UP', 'ACCEPTED'].includes(status)) return 'bg-blue-100 text-blue-700';
    return 'bg-amber-100 text-amber-800';
  });
}
