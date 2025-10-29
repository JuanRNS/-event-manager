import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { FormComponent } from '../../../core/components/form-group/form/form.component';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { FormFieldEnum } from '../../../core/enums/formFieldEnum';
import { FormGroupArray } from '../../../core/interface/form.interface';
import { ApiService } from '../../services/api.service';
import { IRequestUserRegister } from '../../../core/interface/register.interface';
import { Router, RouterLink } from '@angular/router';
import { MaskEnum } from '../../../core/enums/maskEnum';
import { ToastService } from '../../../core/services/toast.service';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    MatIconModule,
    MatButtonModule,
    FormComponent,
    RouterLink
],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent {
  public formGroup = new FormGroup({
    email: new FormControl<string | null>(null, [Validators.required, Validators.email]),
    userName: new FormControl<string | null>(null, [Validators.required]),
    password: new FormControl<string | null>(null, [Validators.required, Validators.minLength(8)]),
    confirmPassword: new FormControl<string | null>(null, [Validators.required, Validators.minLength(8)]),
  })

  constructor(
    private readonly _service: ApiService,
    private readonly _router: Router,
    private readonly _toast: ToastService
  ) { }

  public register(){
    if(this.formGroup.invalid){
      this.formGroup.markAllAsDirty();
      this.formGroup.updateValueAndValidity();
      this.formGroup.markAllAsTouched();
      return;
    }
    if(this.formGroup.value.password !== this.formGroup.value.confirmPassword){
      this.formGroup.markAllAsDirty();
      this.formGroup.updateValueAndValidity();
      this.formGroup.markAllAsTouched();
      return;
    }

    const user: IRequestUserRegister = {
      email: this.formGroup.controls.email.value as string,
      userName: this.formGroup.controls.userName.value as string,
      password: this.formGroup.controls.password.value as string
    }
    
    this._service.postRegisterUser(user).subscribe({
      next: (response) => {
        this._router.navigate(['login']);
      },
      error: (err) => {
        this._toast.error(err.error.message || 'Erro ao registrar o usuário.');
      },
    });
  }

  public get formGroupItens(): FormGroupArray {
    return [
      {
        component: FormFieldEnum.INPUT,
        label: 'Email',
        controlName: 'email',
        type: 'email',
        size: '12',
      },
      {
        component: FormFieldEnum.INPUT,
        label: 'Username',
        controlName: 'userName',
        type: 'text',
        size: '12',
        mask: MaskEnum.NOME
      },
      {
        component: FormFieldEnum.INPUT,
        label: 'Password',
        controlName: 'password',
        type: 'password',
        size: '12',
      },
      {
        component: FormFieldEnum.INPUT,
        label: 'Confirm Password',
        controlName: 'confirmPassword',
        type: 'password',
        size: '12',
      },
    ]
  }
}
