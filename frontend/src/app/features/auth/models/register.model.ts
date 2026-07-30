export interface RegisterFormModel {
    username: string;
    email: string;
    password: string;
    confirmPassword: string;
}

export interface RegisterRequest {
    username: string;
    email: string;
    password: string;
}