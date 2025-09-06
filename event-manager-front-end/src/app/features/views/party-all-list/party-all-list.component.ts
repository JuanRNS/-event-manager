import { Component, OnInit } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { MatPaginatorModule, PageEvent } from '@angular/material/paginator';
import { IResponseParty } from '../../../core/interface/party.interface';
import { ModalAddGarcomComponent } from '../../../core/components/modais/modal-add-garcom/modal-add-garcom.component';
import { ModalViewPartyComponent } from '../../../core/components/modais/modal-view-party/modal-view-party.component';
import { ApiService } from '../../services/api.service';
import { MatDialog } from '@angular/material/dialog';
import { ParseDateUtil } from '../../../core/utils/parse-date.util';
import { ModalUpdateFestaComponent } from '../../../core/components/modais/modal-update-festa/modal-update-festa.component';

@Component({
  selector: 'app-party-all-list',
  standalone: true,
  imports: [MatPaginatorModule, MatIconModule, MatButtonModule, MatMenuModule],
  templateUrl: './party-all-list.component.html',
  styleUrl: './party-all-list.component.scss',
})
export class PartyAllListComponent implements OnInit{
  public listParty: IResponseParty[] = [];
  public page = 0;
  public pageSize = 5;
  public totalElements = 0;

  constructor(
    private readonly _service: ApiService,
    private readonly _dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.getListParty();
  }

  public getListParty() {
    this._service.getListParty(this.page, this.pageSize).subscribe((res) => {
      this.listParty = res.content.map((party) => ({
        ...party,
        date: ParseDateUtil.parseDate(party.date),
      }));
      this.totalElements = res.page.totalElements;
    });
  }

  public deleteParty(id: number) {
    this._service.deleteParty(id).subscribe(() => {
      this.getListParty();
    });
  }

  public addGarcons(id: number) {
    this._dialog.open(ModalAddGarcomComponent, {
      height: '70vh',
      width: '90vw',
      maxWidth: '100vw',
      maxHeight: '100vh',
      data: { partyId: id },
    });
  }

  public viewParty(id: number) {
    this._dialog.open(ModalViewPartyComponent, {
      height: '70vh',
      width: '70vw',
      maxWidth: '100vw',
      maxHeight: '100vh',
      data: { id: id },
    });
  }

  public editParty(id: number) {
    this._dialog.open(ModalUpdateFestaComponent, {
      width: '500px',
      height: '600px',
      data: { partyId: id },
    });
  }
  
  public onChangePage(event: PageEvent) {
    this.page = event.pageIndex;
    this.pageSize = event.pageSize;
    this.getListParty();
  }

}
