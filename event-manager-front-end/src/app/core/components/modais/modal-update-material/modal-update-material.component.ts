import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { FormComponent } from '../../form-group/form/form.component';
import { FormControl, FormGroup } from '@angular/forms';
import { FormFieldEnum } from '../../../enums/formFieldEnum';
import { FormGroupArray } from '../../../interface/form.interface';
import { IRequestMaterial } from '../../../interface/event.interface';
import { ApiService } from '../../../../features/services/api.service';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-modal-update-material',
  standalone: true,
  imports: [
    MatDialogModule, 
    FormComponent,
    MatButtonModule
  ],
  templateUrl: './modal-update-material.component.html',
  styleUrl: './modal-update-material.component.scss',
})
export default class ModalUpdateMaterialComponent implements OnInit {
  public form = new FormGroup({
    description: new FormControl<string | null>(null),
  });

  constructor(
    private readonly _dialogRef: MatDialogRef<ModalUpdateMaterialComponent>,
    private readonly _service: ApiService,
    @Inject(MAT_DIALOG_DATA) public data: { id: number },
  ) {}
  ngOnInit(): void {
    this.getMaterialById();
  }

  public get formGroupItensPrimary(): FormGroupArray {
    return [
      {
        component: FormFieldEnum.INPUT,
        label: 'Nome',
        controlName: 'description',
        type: 'text',
        placeholder: '',
      },
    ];
  }

  public updateMaterial(){
    const material: IRequestMaterial = this.form.value as IRequestMaterial;

    this._service.putUpdateMaterial(this.data.id, material).subscribe(() => {
      this._dialogRef.close(true);
    });
  }

  public getMaterialById() {
    this._service.getMaterialById(this.data.id).subscribe((response) => {
      this.form.patchValue(response);
    });
  }
}
