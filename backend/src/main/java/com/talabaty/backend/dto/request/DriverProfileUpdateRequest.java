package com.talabaty.backend.dto.request;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Getter
@Setter
public class DriverProfileUpdateRequest {
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @Pattern(regexp = "^[0-9]{10,15}$", message = "Phone number must be between 10 and 15 digits")
    private String phoneNumber;

    @Size(min = 2, max = 50, message = "Vehicle type must be between 2 and 50 characters")
    private String vehicleType;

    @Size(min = 2, max = 50, message = "License number must be between 2 and 50 characters")
    private String licenseNumber;

    @Size(min = 10, max = 20, message = "National ID must be between 10 and 20 characters")
    private String nationalId;

    // Getters and Setters









}
