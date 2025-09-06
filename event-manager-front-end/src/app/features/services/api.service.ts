import { Injectable } from '@angular/core';
import { HttpServiceAbstract } from '../../core/abstract/http.abstract';
import { environment } from '../../../environments/enviroments';
import { HttpClient } from '@angular/common/http';
import { IRequestGarcom, IRequestMaterial, IResponseGarcom, IResponseListGarcom, IResponseMaterial } from '../../core/interface/event.interface';
import { IRequestUserRegister } from '../../core/interface/register.interface';
import { IRequestParty, IResponseListParty, IResponseParty } from '../../core/interface/party.interface';
import { IResponseDashboardContent } from '../../core/interface/dashboard.interface';
import { IRequestAddGarcomParty, IResponseModalAddGarcom } from '../../core/interface/modal-add-garcom.interface';
import { IResponseModalViewParty } from '../../core/interface/modal-view-party.interface';

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

  public deleteGarcom(id: number){
    return this.delete(`garcom/${id}`);
  }

  public getGarcoms(page: number, size: number) {
    return this.get<IResponseListGarcom>(`garcom/list?page=${page}&size=${size}`);
  }

  public getMaterial() {
    return this.get<IResponseMaterial[]>(`material/list`);
  }

  public postCreateMaterial(material: IRequestMaterial){
    return this.post<IResponseMaterial>(`material/create`, material);
  }

  public deleteMaterial(id: number){
    return this.delete(`material/${id}`);
  }

  public putUpdateMaterial(id: number,material: IRequestMaterial){
    return this.put(`material/${id}`, material);
  }

  public getMaterialById(id: number) {
    return this.get<IResponseMaterial>(`material/${id}`);
  }

  public postRegisterUser(user: IRequestUserRegister){
    return this.post<any>(`user/register`, user, undefined, 'json', true);
  }

  public postRegisterParty(party: IRequestParty){
    return this.post<any>(`festa/create`, party, undefined, 'json', true);
  }

  public getListPartyStatus(page: number, size: number) {
    return this.get<IResponseListParty>(`festa/list/status?page=${page}&size=${size}`);
  }

  public getListParty(page: number, size: number) {
    return this.get<IResponseListParty>(`festa/list?page=${page}&size=${size}`);
  }

  public deleteParty(id: number){
    return this.delete(`festa/${id}`);
  }

  public getPartyById(id: number) {
    return this.get<IResponseParty>(`festa/${id}`);
  }

  public getListDashboard(page: number, size: number) {
    return this.get<IResponseDashboardContent>(`garcom/list/dashboard?page=${page}&size=${size}`);
  }

  public getFileDownload(id: number){
    return this.fileDownload<string>(`pdf/generate/garcom/${id}`);
  }

  public getListAddGarcom(page: number, size: number) {
    return this.get<IResponseModalAddGarcom>(`garcom/list/add?page=${page}&size=${size}`);
  }

  public postAddGarcomParty(request: IRequestAddGarcomParty){
    return this.post(`festa/adicionar-garcom`, request);
  }

  public getGarcomIdsByFestaId(festaId: number) {
    return this.get<number[]>(`garcom/list/festa/${festaId}`);
  }

  public getFestaGarcomById(festaId: number) {
    return this.get<IResponseModalViewParty>(`festa/view/${festaId}`);
  }
}
