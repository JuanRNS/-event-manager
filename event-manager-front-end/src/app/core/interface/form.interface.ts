import { Observable } from "rxjs";
import { FormFieldEnum } from "../enums/formFieldEnum";

// Tipos possíveis para o tipo de input
export type FormType =
  | 'text'
  | 'email'
  | 'password'
  | 'number'
  | 'tel'
  | 'date'
  | 'time'
  | 'checkbox'
  | 'radio'
  | 'select';

export interface FormGroupValue {
  component: FormFieldEnum;
  label: string;
  controlName: string;
  type: FormType;
  placeholder?: string;
  options?: IOptions[] | Observable<IOptions[]>;
  size?: values;
}

export type values = '0' | '1' | '2' | '3' | '4' | '5' | '6' | '7' | '8' | '9' | '10' | '11' | '12';

export interface IOptions{
    id: number | string;
    description: string;
}

export type FormGroupArray = FormGroupValue[];
