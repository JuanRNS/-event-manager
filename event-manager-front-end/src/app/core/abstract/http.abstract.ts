import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { HttpEnum } from "../enums/httpEnum";

export abstract class HttpServiceAbstract {
  constructor(
    private readonly _baseUrl: string,
    private readonly _http: HttpClient
  ) {}

  protected get<T>(path: string) {
    return this.sendRequest<T>(HttpEnum.GET, path);
  }

  protected post<T>(path: string, body: any) {
    return this.sendRequest<T>(HttpEnum.POST, path, body);
  }

  protected put<T>(path: string, body: any) {
    return this.sendRequest<T>(HttpEnum.PUT, path, body);
  }

  protected delete<T>(path: string) {
    return this.sendRequest<T>(HttpEnum.DELETE, path);
  }

  protected patch<T>(path: string, body: any) {
    return this.sendRequest<T>(HttpEnum.PATCH, path, body);
  }


  private sendRequest<T>(
    method: HttpEnum,
    path: string,
    body?: any,
    params?: { [param: string]: string | number | boolean },
  ) {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });

    const token = localStorage.getItem('token');
    if (token) {
      headers = headers.set('Authorization', `Bearer ${token}`);
  }

    const httpParams = new HttpParams({
      fromObject: params as any,
    });

    return this._http.request<T>(method, `${this._baseUrl}/${path}`, {
      body,
      headers,
      params: httpParams,
    });
  }
}