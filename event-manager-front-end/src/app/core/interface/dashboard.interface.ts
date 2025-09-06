import { IPage } from "./event.interface";

export interface IResponseDashboard {
  id: number;
  name: string;
  totalFestas: number;
  valueTotal: number;
}

export interface IResponseDashboardContent {
  content : IResponseDashboard[];
  page: IPage;
}
