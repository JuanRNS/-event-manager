import { Component } from '@angular/core';
import { MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-modal-delete-confirmation',
  standalone: true,
  imports: [MatDialogModule, MatButtonModule],
  templateUrl: './modal-delete-confirmation.component.html',
  styleUrl: './modal-delete-confirmation.component.scss'
})
export class ModalDeleteConfirmationComponent {
  constructor(
    private readonly dialogRef: MatDialogRef<ModalDeleteConfirmationComponent>
  ) {}

  public onConfirm(): void {
    this.dialogRef.close(true);
  }

  public onCancel(): void {
    this.dialogRef.close(false);
  }
}
