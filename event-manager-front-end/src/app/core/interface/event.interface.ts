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
  festaGarcoms: number[];
}