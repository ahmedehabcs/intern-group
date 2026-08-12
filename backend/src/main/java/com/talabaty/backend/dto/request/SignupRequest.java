package com.talabaty.backend.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class SignupRequest {
    @NotBlank(message = "Email address is required")
    @Email(message = "Invalid email format")
    @Pattern(
            regexp = "(?i)^[a-zA-Z0-9._%+-]+@(gmail\\.com|yahoo\\.com|hotmail\\.com|outlook\\.com)$",
            message = "Email must end with a valid domain (e.g., gmail.com, yahoo.com, hotmail.com, outlook.com)"
    )
    private String email;


    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, and one number"
    )
    private String password;

    @NotBlank(message = "Role is required")

    private String role; // "CUSTOMER" أو "DRIVER"

    // بيانات مشتركة
    @NotBlank(message = "name is required")
    private String name;


    private Long phoneNumber;

    // بيانات خاصة بالدليفري
    private String vehicleType;
    private String licenseNumber;
    private String nationalId;


    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "SignupRequest{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", vehicleType='" + vehicleType + '\'' +
                ", licenseNumber='" + licenseNumber + '\'' +
                ", nationalId='" + nationalId + '\'' +
                '}';
    }
}
