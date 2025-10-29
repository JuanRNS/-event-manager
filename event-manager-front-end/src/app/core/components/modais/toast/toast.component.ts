import { Component, Inject } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { MAT_SNACK_BAR_DATA, MatSnackBarRef, MatSnackBarActions, MatSnackBarLabel } from '@angular/material/snack-bar';

@Component({
  selector: 'app-toast',
  standalone: true,
  imports: [MatIconModule, MatSnackBarActions, MatSnackBarLabel],
  templateUrl: './toast.component.html',
  styleUrl: './toast.component.scss'
})
export class ToastComponent {

  constructor(
    @Inject(MAT_SNACK_BAR_DATA) public message: string,
    public snackBar: MatSnackBarRef<ToastComponent>
  ) { }
}
