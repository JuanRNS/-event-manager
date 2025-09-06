import { IPage } from "./event.interface";

export interface IRequestModalAddGarcom{
    partyId: number;
    name: string;
    phone: string;
    pixKey: string;
    statusGarcom: string;
}

export interface IResponseListAddGarcom{
    id: number;
    name: string;
    phone: string;
    statusGarcom: string;
}

export interface IResponseModalAddGarcom{
    content: IResponseListAddGarcom[];
    page: IPage;
}

export interface IRequestAddGarcomParty{
    festaId: number;
    garcomIds: number[];
}