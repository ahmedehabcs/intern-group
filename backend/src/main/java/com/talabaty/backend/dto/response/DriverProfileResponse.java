package com.talabaty.backend.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DriverProfileResponse {
    private String email;
    private String name;
    private String phoneNumber;
    private String vehicleType;
    private String licenseNumber;
    private String nationalId;

    public DriverProfileResponse(String email, String name, String phoneNumber, String vehicleType, String licenseNumber, String nationalId) {
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.vehicleType = vehicleType;
        this.licenseNumber = licenseNumber;
        this.nationalId = nationalId;
    }

    // Getters and Setters











}
