import { IResponseValues } from "./party.interface";


export interface IRequestUpdateFesta {
    nameClient: string;
    location: string;
    date: string;
    idMaterial: number;
    values: IResponseValues[];
    numberOfPeople: number;
    status: string;
}