import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { FormFieldEnum } from '../../../core/enums/formFieldEnum';
import { FormGroupArray } from '../../../core/interface/form.interface';
import { FormComponent } from '../../../core/components/form-group/form/form.component';
import { ApiService } from '../../services/api.service';
import { IRequestParty, IResponseParty } from '../../../core/interface/party.interface';
import { MatPaginatorModule, PageEvent } from '@angular/material/paginator';
import { MatMenuModule } from '@angular/material/menu';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from "@angular/material/button";
import { MatDialog } from '@angular/material/dialog';
import { ModalUpdateFestaComponent } from '../../../core/components/modais/modal-update-festa/modal-update-festa.component';
import { ModalAddGarcomComponent } from '../../../core/components/modais/modal-add-garcom/modal-add-garcom.component';
import { ModalViewPartyComponent } from '../../../core/components/modais/modal-view-party/modal-view-party.component';
import { ParseDateUtil } from '../../../core/utils/parse-date.util';

@Component({
  selector: 'app-party-registration',
  standalone: true,
  imports: [
    FormsModule,
    CommonModule,
    FormComponent,
    MatPaginatorModule,
    MatMenuModule,
    MatIconModule,
    MatButtonModule,
  ],
  templateUrl: './party-registration.component.html',
  styleUrl: './party-registration.component.scss',
})
export class PartyRegistrationComponent implements OnInit {
  public form = new FormGroup({
    location: new FormControl<string | null>(null, [Validators.required]),
    nameClient: new FormControl<string | null>(null, [Validators.required]),
    date: new FormControl<string | null>(null, [Validators.required]),
    idMaterial: new FormControl<number | null>(null, [Validators.required]),
    valuePerDay: new FormControl<number | null>(null, [Validators.required]),
    numberOfPeople: new FormControl<string | null>(null, [
      Validators.required,
      Validators.min(1),
    ]),
  });

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

  public get formGroupItens(): FormGroupArray {
    return [
      {
        component: FormFieldEnum.INPUT,
        label: 'Local da festa',
        controlName: 'location',
        type: 'text',
        placeholder: 'Local da festa',
        size: '6',
      },
      {
        component: FormFieldEnum.INPUT,
        label: 'Nome do cliente',
        controlName: 'nameClient',
        type: 'text',
        placeholder: 'Nome do cliente',
        size: '6',
      },
      {
        component: FormFieldEnum.INPUT,
        label: 'Data da festa',
        controlName: 'date',
        type: 'date',
        placeholder: 'Data da festa',
        size: '6',
      },
      {
        component: FormFieldEnum.SELECT,
        label: 'Material usado',
        controlName: 'idMaterial',
        type: 'text',
        placeholder: 'Material usado',
        options: this._service.getMaterial(),
        size: '6',
      },
      {
        component: FormFieldEnum.INPUT,
        label: 'Valor da festa',
        controlName: 'valuePerDay',
        type: 'number',
        placeholder: 'Valor da festa',
        size: '6',
      },
      {
        component: FormFieldEnum.INPUT,
        label: 'Quantidade de Pessoas',
        controlName: 'numberOfPeople',
        placeholder: 'Quantidade de Pessoas',
        type: 'text',
        size: '6',
      },
    ];
  }

  public getListParty() {
    this._service.getListPartyStatus(this.page, this.pageSize).subscribe((res) => {
      this.listParty = res.content.map((party) => ({
        ...party,
        date: ParseDateUtil.parseDate(party.date),
      }));
      this.totalElements = res.page.totalElements;
    });
  }
  public createParty() {
    if (this.form.invalid) {
      this.form.markAllAsDirty();
      this.form.updateValueAndValidity();
      this.form.markAllAsTouched();
      return;
    }
    const data: IRequestParty = {
      location: this.form.controls.location.value as string,
      nameClient: this.form.controls.nameClient.value as string,
      date: new Date(this.form.controls.date.value as string)
        .toISOString()
        .substring(0, 19),
      idMaterial: Number(this.form.controls.idMaterial.value),
      valuePerDay: Number(this.form.controls.valuePerDay.value),
      numberOfPeople: Number(this.form.controls.numberOfPeople.value),
    };

    this._service.postRegisterParty(data).subscribe((res) => {
      this.getListParty();
    });
  }

  public onChangePage(event: PageEvent) {
    this.page = event.pageIndex;
    this.pageSize = event.pageSize;
    this.getListParty();
  }

  public editParty(id: number) {
    this._dialog.open(ModalUpdateFestaComponent, {
      width: '500px',
      height: '600px',
      data: { partyId: id },
    });
  }

  public deleteParty(id: number) {
    this._service.deleteParty(id).subscribe(() => {
      this.getListParty();
    });
  }

  public addGarcons(id: number) {
    this._dialog.open(ModalAddGarcomComponent,{
      height: '70vh',
      width: '90vw',
      maxWidth: '100vw',
      maxHeight: '100vh',
      data: { partyId: id },
    })
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

}
