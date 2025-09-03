import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';

@Component({
  selector: 'app-modal-view-garcom',
  standalone: true,
  imports: [
    MatDialogModule, 
    MatButtonModule
  ],
  templateUrl: './modal-view-garcom.component.html',
  styleUrl: './modal-view-garcom.component.scss'
})
export class ModalViewGarcomComponent {
  

  constructor() {}
}
