import { Component, inject } from '@angular/core';
import { ConfirmationDialogService } from '../../services/confirmation-dialog.service';
@Component({
  selector: 'app-confirmation-dialog',
  templateUrl: './confirmation-dialog.html',
})
export class ConfirmationDialog {
  readonly dialog = inject(ConfirmationDialogService);
  readonly titleId = 'confirmation-dialog-title';
}
