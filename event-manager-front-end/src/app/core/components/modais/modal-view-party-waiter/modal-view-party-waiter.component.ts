import { Component, Inject, OnInit } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { IResponseModalViewPartyWaiter } from '../../../interface/modal-view-party-waiter.interface';
import { ApiService } from '../../../../features/services/api.service';
import { ParseDateUtil } from '../../../utils/parse-date.util';

@Component({
  selector: 'app-modal-view-party-waiter',
  standalone: true,
  imports: [
    MatDialogModule,
    MatButtonModule,
    MatIconModule
  ],
  templateUrl: './modal-view-party-waiter.component.html',
  styleUrl: './modal-view-party-waiter.component.scss'
})
export class ModalViewPartyWaiterComponent implements OnInit {
  public listViewGarcom!: IResponseModalViewPartyWaiter;
  constructor(
    private readonly _dialogRef: MatDialogRef<ModalViewPartyWaiterComponent>,
    private readonly _service: ApiService,
    @Inject(MAT_DIALOG_DATA) public data: {id: number}
  ) {}

  ngOnInit(): void {
      this.getPartiesByWaiterId();
  }

  public getPartiesByWaiterId() {
    this._service.getPartiesByWaiterId(this.data.id).subscribe({
      next: (res) => {
        this.listViewGarcom = {
          ...res,
          parties: res.parties.map(party => ({
            ...party,
            date: ParseDateUtil.parseDate(party.date)
          }))
        };
      },
      error: (err) => {
        console.error('Erro ao buscar festas do garçom:', err);
      }
    });
  }
}
