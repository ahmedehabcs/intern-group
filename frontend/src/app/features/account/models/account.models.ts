export interface AddressRequest {
  street: string;
  building?: string;
  floor?: string;
  apartment?: string;
  city: string;
  governorateId: number;
}
export interface AddressResponse extends AddressRequest {
  id: number;
  governorateName: string;
  isDefault: boolean;
}
export interface CustomerProfileResponse {
  email: string;
  name: string;
  phoneNumber: string;
}
export interface CustomerProfileUpdateRequest {
  name?: string;
  phoneNumber?: string;
}
