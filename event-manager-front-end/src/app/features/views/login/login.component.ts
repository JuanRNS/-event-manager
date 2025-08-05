import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormControl, FormGroup, FormsModule } from '@angular/forms';
import { MatIconModule } from '@angular/material/icon';
import { FormGroupArray } from '../../../core/interface/form.interface';
import { FormFieldEnum } from '../../../core/enums/formFieldEnum';
import { FormComponent } from "../../../core/components/form-group/form/form.component";
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    FormsModule, 
    MatIconModule, 
    FormComponent,
    MatButtonModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {

  public formGroup = new FormGroup({
    email: new FormControl<string | null>(null),
    password: new FormControl<string | null>(null),
  })

  constructor(private readonly router: Router) {}


  public get formGroupItens(): FormGroupArray{
    return [
      {
        component: FormFieldEnum.INPUT,
        label: 'Email',
        controlName: 'email',
        type: 'email',
        placeholder: 'Digite seu email'
      },
      {
        component: FormFieldEnum.INPUT,
        label: 'Password',
        controlName: 'password',
        type: 'password',
        placeholder: 'Digite sua senha'
      }
    ]
  }
}

