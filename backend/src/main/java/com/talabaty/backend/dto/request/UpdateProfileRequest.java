package com.talabaty.backend.dto.request;

// This DTO contains all possible fields for both Customer and Delivery profiles.
// The service logic will decide which fields to use based on the user's role.
public class UpdateProfileRequest {

    // Common fields for all profiles
    private String name;
    private Long phoneNumber;

    // Fields specific to DeliveryProfile
    private String vehicleType;
    private String licenseNumber;
    private String nationalId;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }
}
