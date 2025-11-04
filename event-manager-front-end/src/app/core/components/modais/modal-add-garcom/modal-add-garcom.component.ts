import { Component, Inject, OnInit } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { IResponseListAddGarcom } from '../../../interface/modal-add-garcom.interface';
import { ApiService } from '../../../../features/services/api.service';
import { MatPaginatorModule, PageEvent } from '@angular/material/paginator';
import { MatIconModule } from '@angular/material/icon';
import { FormControl, FormGroup } from '@angular/forms';
import { FormGroupArray } from '../../../interface/form.interface';
import { FormFieldEnum } from '../../../enums/formFieldEnum';
import { FormComponent } from "../../form-group/form/form.component";
import { ToastService } from '../../../services/toast.service';

@Component({
  selector: 'app-modal-add-garcom',
  standalone: true,
  imports: [
    MatDialogModule,
    MatButtonModule,
    MatPaginatorModule,
    MatIconModule,
    FormComponent
],
  templateUrl: './modal-add-garcom.component.html',
  styleUrl: './modal-add-garcom.component.scss'
})
export class ModalAddGarcomComponent implements OnInit {
  public listGarcom: IResponseListAddGarcom[] = [];
  public listGarcomOriginal: IResponseListAddGarcom[] = [];
  public listGarcomAdd: number[] = [];
  public form = new FormGroup({
    search: new FormControl<string | null>(null),
  });
  public isSubmit: boolean = false;
  public page = 0;
  public totalElements = 0;
  public pageSize = 4;
  
  constructor(
    private readonly _service: ApiService,
    private readonly _toast: ToastService,
    private readonly _dialogRef: MatDialogRef<ModalAddGarcomComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { id: number },
  ) {}

  ngOnInit(): void {
    this.getGarcomIdsByFestaId();
    this.getListGarcom();

      this.form.controls.search.valueChanges.subscribe(value => {
        if (!value) {
          this.listGarcom = [...this.listGarcomOriginal];
          return;
        }
        this.listGarcom= this.listGarcom.filter(garcom => garcom.name.toLowerCase().includes(value.toLowerCase()));
      })
  }

  public get formGroupItens(): FormGroupArray{
    return [
      {
        component: FormFieldEnum.INPUT,
        label: 'Buscar Garçom',
        controlName: 'search',
        type: 'text',
        placeholder: 'Digite o nome do garçom',
        size: '6',
      }
    ]
  } 

  public getListGarcom(){
    this._service.getListAddGarcom(this.page, this.pageSize).subscribe({
      next: (res) => {
        this.listGarcom = res.content.filter(garcom => garcom.statusGarcom === 'ATIVO');
        this.listGarcomOriginal = res.content.filter(garcom => garcom.statusGarcom === 'ATIVO');
        this.totalElements = res.page.totalElements;
      },
      error: (err) => {
        this._toast.error(err.error.message || 'Erro ao carregar a lista de garçons.');
      }
    });
  }
  public getGarcomIdsByFestaId(){
    this._service.getGarcomIdsByFestaId(this.data.id).subscribe({
      next: (res) => {
        this.listGarcomAdd = res;
      },
      error: (err) => {
        this._toast.error(err.error.message || 'Erro ao carregar os garçons da festa.');
      }
    });
  }

  public addGarcom(id: number){
      this.isSubmit = true;
      this.listGarcomAdd.push(id);
  }

  public removeGarcom(id: number){
      this.isSubmit = true;
      const index = this.listGarcomAdd.indexOf(id);
      this.listGarcomAdd.splice(index, 1);
  }

  public onPageChange(event: PageEvent){
    this.page = event.pageIndex;
    this.pageSize = event.pageSize;
    this.getListGarcom();
  }

  public submit(){
    if(!this.isSubmit){
      this._dialogRef.close();
      return;
    }
    this._service.postAddEmployeeParty({
      partyId: this.data.id,
      employeeIds: this.listGarcomAdd
    }).subscribe({
      next: (res) => {
        this._dialogRef.close(true);
      },
      error: (err) => {
        this._toast.error(err.error.message || 'Erro ao adicionar o garçom à festa.');
      }
    });
  }

}
