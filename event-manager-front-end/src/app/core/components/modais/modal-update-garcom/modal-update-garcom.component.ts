import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { FormComponent } from "../../form-group/form/form.component";
import { FormControl, FormGroup } from '@angular/forms';
import { FormFieldEnum } from '../../../enums/formFieldEnum';
import { OptionsService } from '../../../../features/services/options.service';
import { FormGroupArray } from '../../../interface/form.interface';
import { ApiService } from '../../../../features/services/api.service';
import { IRequestGarcom } from '../../../interface/event.interface';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-modal-update-garcom',
  standalone: true,
  imports: [
    MatDialogModule,
    FormComponent,
    MatButtonModule
],
  templateUrl: './modal-update-garcom.component.html',
  styleUrl: './modal-update-garcom.component.scss'
})
export class ModalUpdateGarcomComponent implements OnInit {
  public form = new FormGroup({
    name: new FormControl<string | null>(null),
    pixKey: new FormControl<string | null>(null),
    phone: new FormControl<string | null>(null),
    statusGarcom: new FormControl<string | null>(null),
  });

  constructor(
    private readonly _optionsService: OptionsService,
    private readonly _service: ApiService,
    private readonly _dialogRef: MatDialogRef<ModalUpdateGarcomComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { id: number },
  ) { }

  ngOnInit(): void {
    this.getGarcomById();
  }

   public get formGroupItensPrimary(): FormGroupArray{
      return [
        {
          component: FormFieldEnum.INPUT,
          label: 'Nome',
          controlName: 'name',
          type: 'text',
          placeholder: 'Digite seu nome',
          size: '6',
        },
        {
          component: FormFieldEnum.INPUT,
          label: 'Chave Pix',
          controlName: 'pixKey',
          type: 'text',
          placeholder: 'Digite sua chave pix',
          size: '6',
        },
        {
          component: FormFieldEnum.INPUT,
          label: 'Telefone',
          controlName: 'phone',
          type: 'text',
          placeholder: 'Digite seu telefone',
          size: '6',
        },
        {
          component: FormFieldEnum.SELECT,
          label: 'Status',
          controlName: 'statusGarcom',
          type: 'select',
          options: this._optionsService.getOptionsStatus(),
          size: '6',
        }
      ]
    }

    public updateGarcom() {
      if(!this.form.dirty) {
        this._dialogRef.close();
        return
      };

      if(this.form.invalid) {
        this.form.markAllAsTouched();
        this.form.markAsDirty();
        return
      };
      
      const garcom: IRequestGarcom = this.form.value as IRequestGarcom;

      this._service.putUpdateGarcom(this.data.id, garcom).subscribe({
        next: (response) => {
          this._dialogRef.close(true);
        },
      });
    }

    public getGarcomById(){
      this._service.getGarcomById(this.data.id).subscribe((response) => {
        this.form.patchValue(response);
      });
    }
}
