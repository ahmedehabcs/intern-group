export interface CustomerSignupRequest {
  name: string;
  email: string;
  password: string;
  phoneNumber?: string;
}
export interface DriverSignupRequest extends CustomerSignupRequest {
  vehicleType: string;
  licenseNumber: string;
  nationalId: string;
}
