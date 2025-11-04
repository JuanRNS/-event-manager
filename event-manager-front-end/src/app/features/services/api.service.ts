import { Injectable } from '@angular/core';
import { HttpServiceAbstract } from '../../core/abstract/http.abstract';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { IRequestEmployeeType, IRequestGarcom, IRequestMaterial, IResponseGarcom, IResponseListGarcom, IResponseMaterial } from '../../core/interface/event.interface';
import { IRequestUserRegister } from '../../core/interface/register.interface';
import { IRequestParty, IResponseListParty, IResponseParty } from '../../core/interface/party.interface';
import { IResponseDashboardContent } from '../../core/interface/dashboard.interface';
import { IRequestAddEmployeeParty, IResponseModalAddGarcom } from '../../core/interface/modal-add-garcom.interface';
import { IResponseModalViewParty } from '../../core/interface/modal-view-party.interface';
import { IRequestUpdateFesta } from '../../core/interface/modal-update-festa.interface';
import { IResponseModalViewPartyWaiter } from '../../core/interface/modal-view-party-waiter.interface';
import { IRequestModalValuesEmployee } from '../../core/interface/modal-values-employee.interface';

@Injectable({
  providedIn: 'root',
})
export class ApiService extends HttpServiceAbstract {
  
  constructor(http: HttpClient) {
    super(environment.api, http);
  }

  public getGarcomById(id: number) {
    return this.get<IResponseGarcom>(`employee/${id}`);
  }

  public postCreateGarcom(garcom: IRequestGarcom){
    return this.post<IResponseGarcom>(`employee/create`, garcom);
  }

  public putUpdateGarcom(id: number,garcom: IRequestGarcom){
    return this.put(`employee/${id}`, garcom);
  }

  public deleteGarcom(id: number){
    return this.delete(`employee/${id}`);
  }

  public getGarcoms(page: number, size: number) {
    return this.get<IResponseListGarcom>(`employee/list?page=${page}&size=${size}`);
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
    return this.post<any>(`user/register`, user, 'json');
  }

  public postRegisterParty(party: IRequestParty){
    return this.post<any>(`party/create`, party, 'json');
  }

  public putUpdateParty(id: number,party: IRequestUpdateFesta){
    return this.put(`party/${id}`, party);
  }

  public getListPartyStatus(page: number, size: number) {
    return this.get<IResponseListParty>(`party/list/status?page=${page}&size=${size}`);
  }

  public getListParty(page: number, size: number) {
    return this.get<IResponseListParty>(`party/list?page=${page}&size=${size}`);
  }

  public deleteParty(id: number){
    return this.delete(`party/${id}`);
  }

  public getPartyById(id: number) {
    return this.get<IResponseParty>(`party/${id}`);
  }

  public getListDashboard(page: number, size: number) {
    return this.get<IResponseDashboardContent>(`employee/list/dashboard?page=${page}&size=${size}`);
  }

  public getFileDownload(id: number, from?: string, to?: string) {
    if(!from || !to) return this.fileDownload<string>(`pdf/generate/employee/${id}`);
    return this.fileDownload<string>(`pdf/generate/employee/${id}?fromDate=${from}&toDate=${to}`);
  }

  public getListAddGarcom(page: number, size: number) {
    return this.get<IResponseModalAddGarcom>(`employee/list/add?page=${page}&size=${size}`);
  }

  public postAddEmployeeParty(request: IRequestAddEmployeeParty){
    return this.post(`party/add-employee`, request);
  }

  public getGarcomIdsByFestaId(festaId: number) {
    return this.get<number[]>(`employee/list/party/${festaId}`);
  }

  public getFestaGarcomById(festaId: number) {
    return this.get<IResponseModalViewParty>(`party/view/${festaId}`);
  }

  public getDashboardFromTo(from: string, to: string) {
    return this.get<IResponseDashboardContent>(`employee/list/dashboard/date?fromDate=${from}&toDate=${to}`);
  }

  public getPartiesByWaiterId(id: number) {
    return this.get<IResponseModalViewPartyWaiter>(`employee/list/employee/party/${id}`);
  }

  public postCreateEmployeeType(type: IRequestEmployeeType){
    return this.post(`type/create`, type, 'json');
  }

  public postCreatePartyValues(id:number, data: IRequestModalValuesEmployee){
    return this.post(`party/${id}/add/values`, data);
  }
}
