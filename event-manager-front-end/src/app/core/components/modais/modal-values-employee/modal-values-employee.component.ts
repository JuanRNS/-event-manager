import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { FormComponent } from "../../form-group/form/form.component";
import { FormControl, FormGroup } from '@angular/forms';
import { FormGroupArray } from '../../../interface/form.interface';
import { FormFieldEnum } from '../../../enums/formFieldEnum';
import { OptionsService } from '../../../../features/services/options.service';
import { IRequestModalValuesEmployee } from '../../../interface/modal-values-employee.interface';
import { ApiService } from '../../../../features/services/api.service';
import { ToastService } from '../../../services/toast.service';

@Component({
  selector: 'app-modal-values-employee',
  standalone: true,
  imports: [
    MatDialogModule,
    FormComponent
],
  templateUrl: './modal-values-employee.component.html',
  styleUrl: './modal-values-employee.component.scss'
})
export class ModalValuesEmployeeComponent {
  public formGroup = new FormGroup({
    value: new FormControl<number | null>(null),
    employeeType: new FormControl<number | null>(null),
  });

  constructor(
    private readonly _optionsService: OptionsService,
    private readonly _service: ApiService,
    private readonly _toast: ToastService,
    @Inject(MAT_DIALOG_DATA) public data: { id: number },
    private readonly _dialogRef: MatDialogRef<ModalValuesEmployeeComponent>,
  ) { }

  public get formGroupItens(): FormGroupArray{
    return [
      {
        component: FormFieldEnum.INPUT,
        label: 'Valor',
        controlName: 'value',
        type: 'text',
        placeholder: 'Digite o valor',
        size: '6',
      },
      {
        component: FormFieldEnum.SELECT,
        label: 'Tipo de Funcionário',
        controlName: 'employeeType',
        type: 'select',
        options: this._optionsService.getOptionsEmployeeType(),
        size: '6',
      }
    ];
  }

  public saveValues(){
    if(!this.formGroup.dirty){
      this._toast.warning('Nenhuma alteração foi feita.');
      return;
    }
    const form: IRequestModalValuesEmployee = {
      idEmployeeType: this.formGroup.controls.employeeType.value as number,
      value: this.formGroup.controls.value.value as number,
    };
    this._service.postCreatePartyValues(this.data.id, form).subscribe({
      next: () => {
        this._toast.success('Valores salvos com sucesso!');
        this._dialogRef.close(true);
      },
      error: () => {
        this._toast.error('Erro ao salvar os valores. Tente novamente.');
      }
    }); 
  }

  }

