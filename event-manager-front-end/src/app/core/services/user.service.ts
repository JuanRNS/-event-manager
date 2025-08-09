import { Injectable } from '@angular/core';
import { HttpServiceAbstract } from '../abstract/http.abstract';
import { environment } from '../../../environments/enviroments';
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
    const basicAuth = btoa(`${user.username}:${user.password}`);
    return this.post<string>('user/login', null, basicAuth, 'text');
  }

  public userRegister(user: IRequestRegister) {
    return this.post<string>('user/register', user);
  }
}
