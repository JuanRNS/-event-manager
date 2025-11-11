import { Injectable } from '@angular/core';
import { HttpServiceAbstract } from '../../core/abstract/http.abstract';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { IOptions } from '../../core/interface/form.interface';

@Injectable({
  providedIn: 'root',
})
export class OptionsService extends HttpServiceAbstract {
  constructor(http: HttpClient) {
    super(environment.api, http);
  }

  public getOptionsStatusEmployee() {
    return this.get<IOptions[]>(`employee/status`);
  }
  
  public getOptionsGarcom() {
    return this.get<IOptions[]>(`employee/list/options`);
  }

  public getOptionsStatusFesta() {
    return this.get<IOptions[]>(`party/status`);
  }

  public getOptionsEmployeeType() {
    return this.get<IOptions[]>(`type/options/employee-type`);
  }
}
