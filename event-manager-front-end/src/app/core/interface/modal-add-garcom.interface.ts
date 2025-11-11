import { IPage } from "./event.interface";

export interface IRequestModalAddGarcom{
    partyId: number;
    name: string;
    phone: string;
    pixKey: string;
    statusEmployee: string;
}

export interface IResponseListAddEmployee{
    id: number;
    name: string;
    phone: string;
    statusEmployee: string;
}

export interface IResponseModalAddEmployee{
    content: IResponseListAddEmployee[];
    page: IPage;
}

export interface IRequestAddEmployeeParty{
    partyId: number;
    employeeIds: number[];
}