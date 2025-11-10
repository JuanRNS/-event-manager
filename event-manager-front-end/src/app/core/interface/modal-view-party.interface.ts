import { IResponseEmployee, IResponseMaterial } from "./event.interface";

export interface IResponseModalViewParty{
    id: number;
    date: string;
    location: string;
    nameClient: string;
    material: IResponseMaterial;
    valuePerDay: number;
    numberOfPeople: number;
    status: string;
    employees: IResponseEmployee[];
}