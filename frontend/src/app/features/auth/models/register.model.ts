export interface RegisterFormModel {
    name: string;
    email: string;
    password: string;
    confirmPassword: string;
    isDelivery: boolean;
}

export interface RegisterRequest {
    name: string;
    email: string;
    password: string;
    role: string;
}