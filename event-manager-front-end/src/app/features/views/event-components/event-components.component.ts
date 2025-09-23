import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { FormGroupArray } from '../../../core/interface/form.interface';
import { FormFieldEnum } from '../../../core/enums/formFieldEnum';
import { FormComponent } from "../../../core/components/form-group/form/form.component";
import { ApiService } from '../../services/api.service';
import { IRequestGarcom, IRequestMaterial, IResponseGarcom, IResponseMaterial } from '../../../core/interface/event.interface';
import { MatButtonModule } from '@angular/material/button';
import { OptionsService } from '../../services/options.service';
import { MatPaginatorModule, PageEvent } from '@angular/material/paginator';
import { MatDialog } from '@angular/material/dialog';
import { ModalUpdateGarcomComponent } from '../../../core/components/modais/modal-update-garcom/modal-update-garcom.component';
import ModalUpdateMaterialComponent from '../../../core/components/modais/modal-update-material/modal-update-material.component';
import { MaskEnum } from '../../../core/enums/maskEnum';
import { MatMenuModule } from "@angular/material/menu";
import { MatIconModule } from '@angular/material/icon';
import { ModalViewPartyWaiterComponent } from '../../../core/components/modais/modal-view-party-waiter/modal-view-party-waiter.component';



@Component({
  selector: 'app-event-components',
  standalone: true,
  imports: [
    FormsModule,
    CommonModule,
    MatButtonModule,
    FormComponent,
    MatPaginatorModule,
    MatMenuModule,
    MatIconModule
],
  templateUrl: './event-components.component.html',
  styleUrl: './event-components.component.scss'
})
export class EventComponentsComponent implements OnInit{
  public formPrimary = new FormGroup({
    name: new FormControl<string | null>(null),
    pixKey: new FormControl<string | null>(null),
    phone: new FormControl<string | null>(null),
    statusGarcom: new FormControl<string | null>(null),
  });

  public formSecondary = new FormGroup({
    description: new FormControl<string | null>(null),
  });

  public ListGarcoms: IResponseGarcom[] = [];
  public ListMaterials: IResponseMaterial[] = [];
  public page = 0;
  public pageSize = 5;
  public totalElements = 0;

  constructor(
    private readonly _service: ApiService,
    private readonly _optionsService: OptionsService,
    private readonly _dialog: MatDialog
  ) { }

  ngOnInit(): void {
     this.getGarcoms();
     this.getMaterials();
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
        maxlength: 50,
        mask: MaskEnum.NOME
      },
      {
        component: FormFieldEnum.INPUT,
        label: 'Chave Pix',
        controlName: 'pixKey',
        type: 'text',
        placeholder: 'Digite sua chave pix',
        size: '6',
        mask: MaskEnum.PIX,
        maxlength: 17
      },
      {
        component: FormFieldEnum.INPUT,
        label: 'Telefone',
        controlName: 'phone',
        type: 'text',
        placeholder: 'Digite seu telefone',
        size: '6',
        mask: MaskEnum.PHONE,
        maxlength: 15
      },
      {
        component: FormFieldEnum.SELECT,
        label: 'Status',
        controlName: 'statusGarcom',
        type: 'select',
        options: this._optionsService.getOptionsStatusGarcom(),
        size: '6',
      }
    ]
  }

  public get formGroupItensSecondary(): FormGroupArray{
    return [
      {
        component: FormFieldEnum.INPUT,
        label: 'Descrição do Material',
        controlName: 'description',
        type: 'text',
        size: '12',
      }
    ]
  }

  public createGarcom(){
    const garcom: IRequestGarcom = this.formPrimary.value as IRequestGarcom;

    this._service.postCreateGarcom(garcom).subscribe((res) => {
      this.getGarcoms();
      this.formPrimary.reset();
    });
  }

  public getGarcoms() {
    this._service.getGarcoms(this.page, this.pageSize).subscribe((response) => {
      this.ListGarcoms = response.content;
      this.totalElements = response.page.totalElements;
      this.pageSize = response.page.size;
    });
  }
  public onChangePage(event: PageEvent) {
    this.page = event.pageIndex;
    this.getGarcoms();
  }

  public getMaterials() {
    this._service.getMaterial().subscribe((response) => {
      this.ListMaterials = response;
    });
  }

  public editGarcom(id: number){
    const dialogRef = this._dialog.open(ModalUpdateGarcomComponent, {
      width: '600px',
      height: '600px',
      data: {
        id: id
      },
    });
    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.getGarcoms();
      }
    });
  }

  public viewFestas(id: number){
    const dialogRef = this._dialog.open(ModalViewPartyWaiterComponent, {
      width: '60vw',
      height: '70vh',
      maxWidth: '100vw',
      maxHeight: '100vh',
      autoFocus: false,
      data: {
        id: id
      },
    });
    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.getGarcoms();
      }
    });
  }

  public deleteGarcom(id: number) {
    this._service.deleteGarcom(id).subscribe(() => {
      this.getGarcoms();
    });
  }

  public createMaterial() {
    const material: IRequestMaterial = this.formSecondary.value as IRequestMaterial;
    this._service.postCreateMaterial(material).subscribe(() => {
      this.getMaterials();
      this.formSecondary.reset();
    });
  }

  public deleteMaterial(id: number) {
    this._service.deleteMaterial(id).subscribe(() => {
      this.getMaterials();
    });
  }

  public editMaterial(id: number) {
    const dialogRef = this._dialog.open(ModalUpdateMaterialComponent, {
      width: '400px',
      height: '300px',
      data: {
        id: id
      },
    });
    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.getMaterials();
      }
    });
  }
}

