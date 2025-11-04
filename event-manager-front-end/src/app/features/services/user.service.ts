import { Injectable } from '@angular/core';

import { environment } from './../../../environments/environment'
import { HttpClient } from '@angular/common/http';
import { HttpServiceAbstract } from '../../core/abstract/http.abstract';
import { IRequestLogin, IRequestRegister } from '../../core/interface/login.interface';

@Injectable({
  providedIn: 'root'
})
export class UserService extends HttpServiceAbstract {

  constructor(http: HttpClient) {
    super(environment.api, http);
  }

  public userLogin(user: IRequestLogin) {
    return this.post<string>('user/login', user, 'text');
  }

  public userRegister(user: IRequestRegister) {
    return this.post<string>('user/register', user);
  }

  public isLogged(): boolean {
    const token = localStorage.getItem('token');
    if(!token) return false;

    const payload = JSON.parse(atob(token.split('.')[1]));
    return payload.exp * 1000 > Date.now();
  }
}
