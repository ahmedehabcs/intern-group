import { Injectable, signal } from '@angular/core';
export interface ConfirmationRequest {
  title: string;
  message: string;
  confirmLabel: string;
  resolve: (result: boolean) => void;
}
@Injectable({ providedIn: 'root' })
export class ConfirmationDialogService {
  readonly request = signal<ConfirmationRequest | null>(null);
  confirm(message: string, title = 'Please confirm', confirmLabel = 'Confirm'): Promise<boolean> {
    return new Promise((resolve) => this.request.set({ title, message, confirmLabel, resolve }));
  }
  close(result: boolean): void {
    const current = this.request();
    this.request.set(null);
    current?.resolve(result);
  }
}
