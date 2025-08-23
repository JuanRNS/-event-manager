import { IPage, IResponseMaterial } from "./event.interface";

export interface IResponseParty{
  id: number;
  location: string;
  nameClient: string;
  date: Date;
  material: IResponseMaterial;
  garcomIds: number[];
  valuePerDay: number;
}

export interface IRequestParty{
  location: string;
  nameClient: string;
  date: string;
  idMaterial: number;
  valuePerDay: number;
}

export interface IResponseListParty{
  content: IResponseParty[];
  page: IPage;
}