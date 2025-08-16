export interface Waiter {
  id: number;
  name: string;
  cpf: string;
  phone: string;
  pixKey: string;
  status: 'ativo' | 'inativo' | 'disponivel' | 'ocupado';
  createdAt: string;
}

export interface Material {
  id: number;
  description: string;
  category: string;
  quantity: number;
  unitPrice: number;
  createdAt: string;
}

export interface IRequestGarcom{
  name: string;
  phone: string;
  pixKey: string;
  status: string;
}

export interface IResponseGarcom{
  id: number;
  name: string;
  phone: string;
  pixKey: string;
  status: string;
}

export interface IResponseMaterial{
  id: number;
  descricao: string;
}

export interface IResponseListGarcom {
  content: IResponseGarcom[];
  pageable: IPageable;
  last: boolean;
  totalPages: number;
  totalElements: number;
  size: number;
  number: number;
  sort: any;
  first: boolean;
  numberOfElements: number;
  empty: boolean;
}

interface IPageable{
  sort: IPageableSort;
  offset: number;
  pageSize: number;
  pageNumber: number;
  unpaged: boolean;
  paged: boolean;
}

interface IPageableSort{
  sorted: boolean;
  unsorted: boolean;
  empty: boolean;
}