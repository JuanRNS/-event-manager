import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { FormControl, FormGroup, FormsModule, Validators } from '@angular/forms';
import { MatIconModule } from '@angular/material/icon';
import { FormGroupArray } from '../../../core/interface/form.interface';
import { FormFieldEnum } from '../../../core/enums/formFieldEnum';
import { FormComponent } from "../../../core/components/form-group/form/form.component";
import { MatButtonModule } from '@angular/material/button';
import { UserService } from '../../../core/services/user.service';
import { IRequestLogin } from '../../../core/interface/login.interface';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    FormsModule,
    MatIconModule,
    FormComponent,
    MatButtonModule,
    RouterLink
],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {

  public formGroup = new FormGroup({
    userName: new FormControl<string | null>(null, [Validators.required]),
    password: new FormControl<string | null>(null, [Validators.required]),
  })

  constructor(
    private readonly router: Router,
    private readonly userService: UserService
  ) {}

  public login(){
    console.log('Estou aqui', this.formGroup.value);
    if(this.formGroup.invalid){
      this.formGroup.markAllAsDirty();
      this.formGroup.updateValueAndValidity();
      this.formGroup.markAllAsTouched();
      return;
    }
    const form: IRequestLogin = {
      username: this.formGroup.controls.userName.value as string,
      password: this.formGroup.controls.password.value as string
    }

    this.userService.userLogin(form).subscribe((res) => {
      localStorage.setItem('token', res);
      this.router.navigate(['dashboard']);
    })
  }


  public get formGroupItens(): FormGroupArray{
    return [
      {
        component: FormFieldEnum.INPUT,
        label: 'Username',
        controlName: 'userName',
        type: 'email',
      },
      {
        component: FormFieldEnum.INPUT,
        label: 'Password',
        controlName: 'password',
        type: 'password',
      }
    ]
  }
}

