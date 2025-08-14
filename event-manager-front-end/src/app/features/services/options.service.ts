import { Injectable } from '@angular/core';
import { HttpServiceAbstract } from '../../core/abstract/http.abstract';
import { environment } from '../../../environments/enviroments';
import { HttpClient } from '@angular/common/http';
import { IOptions } from '../../core/interface/form.interface';

@Injectable({
  providedIn: 'root',
})
export class OptionsService extends HttpServiceAbstract {
  constructor(http: HttpClient) {
    super(environment.api, http);
  }

  public getOptionsStatus() {
    return this.get<IOptions[]>(`garcom/status`);
  }
  
  public getOptionsGarcom() {
    return this.get<IOptions[]>(`garcom/list/options`);
  }
}
