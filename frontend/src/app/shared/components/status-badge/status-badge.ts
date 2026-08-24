import { Component, computed, input } from '@angular/core';
@Component({
  selector: 'app-status-badge',
  templateUrl: './status-badge.html',
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
