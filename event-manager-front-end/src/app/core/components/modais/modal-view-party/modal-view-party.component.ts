import { Component, Inject, OnInit } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MAT_DIALOG_DATA, MatDialogModule } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { ApiService } from '../../../../features/services/api.service';

@Component({
  selector: 'app-modal-view-party',
  standalone: true,
  imports: [
    MatDialogModule,
    MatButtonModule,
    MatIconModule
  ],
  templateUrl: './modal-view-party.component.html',
  styleUrl: './modal-view-party.component.scss'
})
export class ModalViewPartyComponent implements OnInit{


  constructor(
    private readonly _service: ApiService,
    @Inject(MAT_DIALOG_DATA) public data: { id: number },
  ) { }

  ngOnInit(): void {
      this._service.getFestaGarcomById(this.data.id).subscribe((response) => {
        console.log(response);
      });
  }
}
