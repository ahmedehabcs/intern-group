package com.talabaty.backend.dto.response;

import lombok.Getter;
import lombok.Setter;

import com.talabaty.backend.model.Role;

@Getter
@Setter
public class ProfileResponse {

    private Long userId;
    private String email;
    private Role role;
    private String name;
    private Long phoneNumber;
    private Integer loyaltyPoints;
    private String vehicleType;
    private String licenseNumber;
    private String nationalId;
    private Boolean online;

    public ProfileResponse() {
    }

    public ProfileResponse(
            Long userId,
            String email,
            Role role,
            String name,
            Long phoneNumber,
            Integer loyaltyPoints,
            String vehicleType,
            String licenseNumber,
            String nationalId,
            Boolean online
    ) {
        this.userId = userId;
        this.email = email;
        this.role = role;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.loyaltyPoints = loyaltyPoints;
        this.vehicleType = vehicleType;
        this.licenseNumber = licenseNumber;
        this.nationalId = nationalId;
        this.online = online;
    }




















}
