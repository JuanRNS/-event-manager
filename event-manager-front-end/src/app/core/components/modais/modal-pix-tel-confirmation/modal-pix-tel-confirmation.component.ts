import { Component } from '@angular/core';
import { MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-modal-pix-tel-confirmation',
  standalone: true,
  imports: [MatDialogModule, MatButtonModule],
  templateUrl: './modal-pix-tel-confirmation.component.html',
  styleUrl: './modal-pix-tel-confirmation.component.scss'
})
export class ModalPixTelConfirmationComponent {
  constructor(private readonly dialogRef: MatDialogRef<ModalPixTelConfirmationComponent>) {}

  public onConfirm(): void {
    this.dialogRef.close(true);
  }

  public onReject(): void {
    this.dialogRef.close(false);
  }
}
