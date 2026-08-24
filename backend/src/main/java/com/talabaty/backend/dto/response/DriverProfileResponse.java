package com.talabaty.backend.dto.response;

public class DriverProfileResponse {
    private String email;
    private String name;
    private String phoneNumber;
    private String vehicleType;
    private String licenseNumber;
    private String nationalId;

    /**
     * Whether the driver is currently accepting deliveries.
     * <p>
     * Persisted on DeliveryProfile and toggled through
     * PUT /api/delivery/profile/status. It is returned here because this is the
     * only endpoint a driver client calls on load: without it the UI has no way
     * to recover the flag and has to assume offline, which made a reload look
     * like it had silently switched the driver off.
     */
    private Boolean online;

    public DriverProfileResponse(String email, String name, String phoneNumber, String vehicleType, String licenseNumber, String nationalId, Boolean online) {
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.vehicleType = vehicleType;
        this.licenseNumber = licenseNumber;
        this.nationalId = nationalId;
        this.online = online;
    }

    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
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

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }
}
