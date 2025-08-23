import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { FormFieldEnum } from '../../../core/enums/formFieldEnum';
import { FormGroupArray } from '../../../core/interface/form.interface';
import { FormComponent } from '../../../core/components/form-group/form/form.component';
import { ApiService } from '../../services/api.service';
import { IRequestParty, IResponseParty } from '../../../core/interface/party.interface';
import { MatPaginatorModule, PageEvent } from '@angular/material/paginator';

@Component({
  selector: 'app-party-registration',
  standalone: true,
  imports: [
    FormsModule, 
    CommonModule, 
    FormComponent,
    MatPaginatorModule
  ],
  templateUrl: './party-registration.component.html',
  styleUrl: './party-registration.component.scss',
})
export class PartyRegistrationComponent implements OnInit{
  public form = new FormGroup({
    location: new FormControl<string | null>(null, [Validators.required]),
    nameClient: new FormControl<string | null>(null, [Validators.required]),
    date: new FormControl<string | null>(null, [Validators.required]),
    idMaterial: new FormControl<number | null>(null, [Validators.required]),
    valuePerDay: new FormControl<number | null>(null, [Validators.required]),
  });

  public listParty: IResponseParty[] = [];
  public page = 0;
  public pageSize = 5;
  public totalElements = 0;

  constructor(
    private readonly _service: ApiService,
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
      },
      {
        component: FormFieldEnum.INPUT,
        label: 'Nome do cliente',
        controlName: 'nameClient',
        type: 'text',
        placeholder: 'Nome do cliente',
      },
      {
        component: FormFieldEnum.INPUT,
        label: 'Data da festa',
        controlName: 'date',
        type: 'date',
        placeholder: 'Data da festa',
      },
      {
        component: FormFieldEnum.SELECT,
        label: 'Material usado',
        controlName: 'idMaterial',
        type: 'text',
        placeholder: 'Material usado',
        options: this._service.getMaterial(),
      },
      {
        component: FormFieldEnum.INPUT,
        label: 'Valor da festa',
        controlName: 'valuePerDay',
        type: 'number',
        placeholder: 'Valor da festa',
      },
    ];
  }


  public getListParty(){
    this._service.getListParty(this.page, this.pageSize).subscribe((res) => {
      this.listParty = res.content;
      this.totalElements = res.page.totalElements;
      console.log(this.listParty);
    });
  }
  public createParty(){
    if(this.form.invalid){
      this.form.markAllAsDirty();
      this.form.updateValueAndValidity();
      this.form.markAllAsTouched();
      return;
    }
    const data: IRequestParty = {
      location: this.form.controls.location.value as string,
      nameClient: this.form.controls.nameClient.value as string,
      date: new Date(this.form.controls.date.value as string).toISOString().substring(0, 19),
      idMaterial: Number(this.form.controls.idMaterial.value),
      valuePerDay: Number(this.form.controls.valuePerDay.value)
    }
    
    this._service.postRegisterParty(data).subscribe((res) => {
      console.log(res);
      this.getListParty();
    });
  }

  public onChangePage(event: PageEvent) {
    this.page = event.pageIndex;
    this.pageSize = event.pageSize;
    this.getListParty();
  }
}
