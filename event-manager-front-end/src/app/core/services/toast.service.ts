import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ToastComponent } from '../components/modais/toast/toast.component';

@Injectable({
  providedIn: 'root'
})
export class ToastService {
  constructor(private readonly _toast: MatSnackBar) { }

  public success(message: string, duration: number = 3000) {
    this._toast.openFromComponent(ToastComponent, {
      data: message,
      duration,
      panelClass: ['toast-success'],
      horizontalPosition: 'right',
      verticalPosition: 'top',
    });
  }
  public error(message: string, duration: number = 3000) {
    this._toast.openFromComponent(ToastComponent, {
      data: message,
      duration,
      panelClass: ['toast-error'],
      horizontalPosition: 'right',
      verticalPosition: 'top',
    });
  }
  public warning(message: string, duration: number = 3000) {
    this._toast.openFromComponent(ToastComponent, {
      data: message,
      duration,
      panelClass: ['toast-warning'],
      horizontalPosition: 'right',
      verticalPosition: 'top',
    });
  }
}
