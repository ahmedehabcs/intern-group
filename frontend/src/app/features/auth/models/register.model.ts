export interface CustomerSignupRequest {
  name: string;
  email: string;
  password: string;
  // Optional, unlike the driver payload. Stored at signup when supplied, and
  // editable afterwards through PUT /api/profile.
  phoneNumber?: string;
}
export interface DriverSignupRequest extends CustomerSignupRequest {
  // @NotBlank on the backend, unlike the customer payload.
  phoneNumber: string;
  vehicleType: string;
  licenseNumber: string;
  nationalId: string;
}
