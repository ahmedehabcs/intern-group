package com.talabaty.backend.dto.response;

import com.talabaty.backend.model.Role;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

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

    public Integer getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(Integer loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
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

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }
}
