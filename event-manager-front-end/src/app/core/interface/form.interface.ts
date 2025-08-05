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
  options?: IOptions[];
}

export interface IOptions{
    id: number | string;
    value: string;
}

export type FormGroupArray = FormGroupValue[];
