import { Component } from '@angular/core';
import { FormControl, FormGroup, FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { SidebarComponent } from '../../../core/components/sidebar/sidebar.component';
import { FormGroupArray } from '../../../core/interface/form.interface';
import { FormFieldEnum } from '../../../core/enums/formFieldEnum';
import { FormComponent } from "../../../core/components/form-group/form/form.component";



@Component({
  selector: 'app-event-components',
  standalone: true,
  imports: [
    FormsModule, 
    CommonModule, 
    SidebarComponent, 
    FormComponent],
  templateUrl: './event-components.component.html',
  styleUrl: './event-components.component.scss'
})
export class EventComponentsComponent {
  public formPrimary = new FormGroup({
    nome: new FormControl<string | null>(null),
    pixKey: new FormControl<string | null>(null),
    phone: new FormControl<string | null>(null),
    status: new FormControl<string | null>(null),
  });

  public formSecondary = new FormGroup({
    description: new FormControl<string | null>(null),
  });

  constructor() { }

  public get formGroupItensPrimary(): FormGroupArray{
    return [
      {
        component: FormFieldEnum.INPUT,
        label: 'Nome',
        controlName: 'nome',
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
        options: [
          {
            id: 'A',
            value: 'Ativo'
          },
          {
            id: 'I',
            value: 'Inativo'
          }
        ]
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
        placeholder: 'Digite a descrição do material'
      }
    ]
  }

  
}

