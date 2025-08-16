import { Injectable } from '@angular/core';
import { HttpServiceAbstract } from '../../core/abstract/http.abstract';
import { environment } from '../../../environments/enviroments';
import { HttpClient } from '@angular/common/http';
import { IRequestGarcom, IResponseGarcom, IResponseListGarcom, IResponseMaterial } from '../../core/interface/event.interface';

@Injectable({
  providedIn: 'root',
})
export class ApiService extends HttpServiceAbstract {
  
  constructor(http: HttpClient) {
    super(environment.api, http);
  }

  public getGarcomById(id: number) {
    return this.get<IResponseGarcom>(`garcom/${id}`);
  }

  public postCreateGarcom(garcom: IRequestGarcom){
    return this.post<IResponseGarcom>(`garcom/create`, garcom);
  }

  public putUpdateGarcom(id: number,garcom: IRequestGarcom){
    return this.put(`garcom/${id}`, garcom);
  }

  public getGarcoms(page: number, size: number) {
    return this.get<IResponseListGarcom>(`garcom/list?page=${page}&size=${size}`);
  }

  public getMaterial() {
    return this.get<IResponseMaterial[]>(`material/list`);
  }
}
