import { Component, Inject, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogModule } from "@angular/material/dialog";
import { FormFieldEnum } from '../../../enums/formFieldEnum';
import { FormGroupArray } from '../../../interface/form.interface';
import { FormComponent } from '../../form-group/form/form.component';
import { ApiService } from '../../../../features/services/api.service';

@Component({
  selector: 'app-modal-update-festa',
  standalone: true,
  imports: [
    MatDialogModule,
    FormComponent
  ],
  templateUrl: './modal-update-festa.component.html',
  styleUrl: './modal-update-festa.component.scss'
})
export class ModalUpdateFestaComponent implements OnInit {
  public form = new FormGroup({
    location: new FormControl<string | null>(null, [Validators.required]),
    nameClient: new FormControl<string | null>(null, [Validators.required]),
    date: new FormControl<string | null>(null, [Validators.required]),
    idMaterial: new FormControl<string | null>(null, [Validators.required]),
    valuePerDay: new FormControl<number | null>(null, [Validators.required]),
  })

  constructor(
    private readonly _service: ApiService,
    @Inject(MAT_DIALOG_DATA) public data: { partyId: number }
  ) { }

  ngOnInit(): void {
    this.getParty();
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
        label: 'ID do material',
        controlName: 'idMaterial',
        type: 'text',
        placeholder: 'ID do material',
        options: this._service.getMaterial()
      },
      {
        component: FormFieldEnum.INPUT,
        label: 'Valor por dia',
        controlName: 'valuePerDay',
        type: 'number',
        placeholder: 'Valor por dia',
      },
    ];
  }

  public getParty() {
    this._service.getPartyById(this.data.partyId).subscribe((response) => {
      this.form.patchValue({
        location: response.location,
        nameClient: response.nameClient,
        date: response.date.toString().substring(0, 10),
        idMaterial: response.material.id.toString(),
        valuePerDay: response.valuePerDay
      });
    }
    );
  }
  public updateParty() {
    if (this.form.valid) {
      // Lógica para atualizar a festa
    }
  }
}
