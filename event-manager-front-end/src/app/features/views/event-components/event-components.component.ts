import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { SidebarComponent } from '../../../core/components/sidebar/sidebar.component';
import { FormGroupArray } from '../../../core/interface/form.interface';
import { FormFieldEnum } from '../../../core/enums/formFieldEnum';
import { FormComponent } from "../../../core/components/form-group/form/form.component";
import { ApiService } from '../../services/api.service';
import { IRequestGarcom } from '../../../core/interface/event.interface';
import { MatButtonModule } from '@angular/material/button';
import { OptionsService } from '../../services/options.service';
import { MatPaginatorModule } from '@angular/material/paginator';



@Component({
  selector: 'app-event-components',
  standalone: true,
  imports: [
    FormsModule, 
    CommonModule, 
    SidebarComponent, 
    MatButtonModule,
    FormComponent,
    MatPaginatorModule
  ],
  templateUrl: './event-components.component.html',
  styleUrl: './event-components.component.scss'
})
export class EventComponentsComponent implements OnInit{
  public formPrimary = new FormGroup({
    name: new FormControl<string | null>(null),
    pixKey: new FormControl<string | null>(null),
    phone: new FormControl<string | null>(null),
    status: new FormControl<string | null>(null),
  });

  public formSecondary = new FormGroup({
    description: new FormControl<string | null>(null),
  });

  constructor(
    private readonly _service: ApiService,
    private readonly _optionsService: OptionsService
  ) { }

  ngOnInit(): void {
      this.formPrimary.controls.status.valueChanges.subscribe((value) => {
        console.log(value);
      })
  }

  public get formGroupItensPrimary(): FormGroupArray{
    return [
      {
        component: FormFieldEnum.INPUT,
        label: 'Nome',
        controlName: 'name',
        type: 'text',
        placeholder: 'Digite seu nome'
      },
      {
        component: FormFieldEnum.INPUT,
        label: 'Chave Pix',
        controlName: 'pixKey',
        type: 'text',
        placeholder: 'Digite sua chave pix'
      },
      {
        component: FormFieldEnum.INPUT,
        label: 'Telefone',
        controlName: 'phone',
        type: 'text',
        placeholder: 'Digite seu telefone'
      },
      {
        component: FormFieldEnum.SELECT,
        label: 'Status',
        controlName: 'status',
        type: 'select',
        options: this._optionsService.getOptionsStatus()
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
      }
    ]
  }

  public createGarcom(){
    const garcom: IRequestGarcom = this.formPrimary.value as IRequestGarcom;

    this._service.postCreateGarcom(garcom).subscribe((res) => {
      console.log(res);
    });
  
  }

  
}

