import { IEmployeeType, IPage } from "./event.interface";

export interface IResponseDashboard {
  id: number;
  name: string;
  totalParties: number;
  valueTotal: number;
  employeeType: IEmployeeType;
}

export interface IResponseDashboardContent {
  content : IResponseDashboard[];
  page: IPage;
}


