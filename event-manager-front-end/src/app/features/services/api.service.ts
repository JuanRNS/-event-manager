import { Injectable } from '@angular/core';
import { HttpServiceAbstract } from '../../core/abstract/http.abstract';
import { environment } from '../../../environments/enviroments';
import { HttpClient } from '@angular/common/http';
import { IOptions } from '../../core/interface/form.interface';
import { IRequestGarcom, IResponseGarcom } from '../../core/interface/event.interface';

@Injectable({
  providedIn: 'root',
})
export class ApiService extends HttpServiceAbstract {
  
  constructor(http: HttpClient) {
    super(environment.api, http);
  }

  public getStatusGarcom(){
    return this.get<IOptions[]>(`garcom/status`);
  }

  public postCreateGarcom(garcom: IRequestGarcom){
    return this.post<IResponseGarcom>(`garcom/create`, garcom);
  }
}
