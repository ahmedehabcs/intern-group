import { Component, inject } from '@angular/core';
import { ConfirmationDialogService } from '../../services/confirmation-dialog.service';
@Component({
  selector: 'app-confirmation-dialog',
  template: `@if (dialog.request(); as request) {
    <div
      class="fixed inset-0 z-[100] grid place-items-center bg-slate-950/50 p-4"
      role="presentation"
      (click)="dialog.close(false)"
    >
      <section
        role="alertdialog"
        aria-modal="true"
        [attr.aria-labelledby]="titleId"
        class="w-full max-w-sm rounded-xl bg-white p-6 shadow-2xl"
        (click)="$event.stopPropagation()"
      >
        <div class="grid h-10 w-10 place-items-center rounded-full bg-orange-100 text-orange-700">
          <svg
            class="h-5 w-5"
            viewBox="0 0 24 24"
            fill="none"
            stroke="currentColor"
            stroke-width="2"
          >
            <path
              d="M12 9v4m0 4h.01M10.3 3.6 2.4 18a2 2 0 0 0 1.8 3h15.6a2 2 0 0 0 1.8-3L13.7 3.6a2 2 0 0 0-3.4 0Z"
            />
          </svg>
        </div>
        <h2 [id]="titleId" class="mt-4 text-lg font-black text-slate-900">{{ request.title }}</h2>
        <p class="mt-2 text-sm leading-6 text-slate-600">{{ request.message }}</p>
        <div class="mt-6 flex justify-end gap-3">
          <button
            type="button"
            class="rounded-lg border border-slate-200 px-4 py-2 text-sm font-bold"
            (click)="dialog.close(false)"
          >
            Cancel</button
          ><button
            type="button"
            class="rounded-lg bg-orange-600 px-4 py-2 text-sm font-bold text-white"
            (click)="dialog.close(true)"
          >
            {{ request.confirmLabel }}
          </button>
        </div>
      </section>
    </div>
  }`,
})
export class ConfirmationDialog {
  readonly dialog = inject(ConfirmationDialogService);
  readonly titleId = 'confirmation-dialog-title';
}
