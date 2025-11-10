export interface IRequestMaterial {
  description: string;
}

export interface IRequestEmployee{
  name: string;
  phone: string;
  pixKey: string;
  statusGarcom: string;
  idEmployeeType: number;
}
  
export interface IResponseEmployee{
  id: number;
  name: string;
  phone: string;
  pixKey: string;
  statusEmployee: string;
  employeeType: IEmployeeType;
}

export interface IResponseMaterial{
  id: number;
  description: string;
}

export interface IResponseListEmployee {
  content: IResponseEmployee[];
  page: IPage;
}

export interface IEmployeeType{
  id: number;
  type: string;
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