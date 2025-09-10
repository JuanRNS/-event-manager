import { Injectable } from '@angular/core';
import { HttpServiceAbstract } from '../abstract/http.abstract';
import { environment } from './../../../environments/environment'
import { HttpClient } from '@angular/common/http';
import { IRequestLogin, IRequestRegister } from '../interface/login.interface';

@Injectable({
  providedIn: 'root'
})
export class UserService extends HttpServiceAbstract {

  constructor(http: HttpClient) {
    super(environment.api, http);
  }

  public userLogin(user: IRequestLogin) {
    const basicAuth = btoa(`${user.username.replace(/\s+/g, '')}:${user.password}`);
    return this.post<string>('user/login', null, basicAuth, 'text');
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
