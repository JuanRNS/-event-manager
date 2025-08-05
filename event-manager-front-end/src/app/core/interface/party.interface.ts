export interface Party {
  id: number;
  location: string;
  clientName: string;
  address: string;
  materials: string;
  value: number;
  date: string;
  status: 'ativo' | 'concluido' | 'cancelado';
}