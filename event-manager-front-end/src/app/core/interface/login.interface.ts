export interface IRequestLogin{
    username: string;
    password: string;
}

export interface IRequestRegister extends IRequestLogin{
    email: string;
}