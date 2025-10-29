export interface IRequestMaterial {
  description: string;
}

export interface IRequestGarcom{
  name: string;
  phone: string;
  pixKey: string;
  statusGarcom: string;
}

export interface IResponseGarcom{
  id: number;
  name: string;
  phone: string;
  pixKey: string;
  statusGarcom: string;
}

export interface IResponseMaterial{
  id: number;
  description: string;
}

export interface IResponseListGarcom {
  content: IResponseGarcom[];
  page: IPage;
}

export interface IPage{
  number: number;
  size: number;
  totalElements: number;
  totalPages: number;
}


export interface IRequestEmployeeType{
  type: string;
}