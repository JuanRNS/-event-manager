import { IPage, IResponseMaterial } from "./event.interface";

export interface IResponseParty{
  id: number;
  location: string;
  nameClient: string;
  date: string;
  material: IResponseMaterial;
  garcomIds: number[];
  valuePerDay: number;
  numberOfPeople: number;
  status: string;
}

export interface IRequestParty{
  location: string;
  nameClient: string;
  date: string;
  idMaterial: number;
  numberOfPeople: number;
}

export interface IResponseListParty{
  content: IResponseParty[];
  page: IPage;
}