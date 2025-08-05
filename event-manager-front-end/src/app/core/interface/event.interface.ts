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