package com.talabaty.backend.dto.response;


public class RiderAdminResponse {
    private Long id;
    private String name;
    private String phoneNumber;
    private String vehicleType;
    private String licenseNumber;
    private String nationalId;
    private String approvalStatus;
    private Boolean online;
    private Boolean isActive;

    public RiderAdminResponse(Long id, String name, String phoneNumber, String vehicleType,
                              String licenseNumber, String nationalId, String approvalStatus,
                              Boolean online, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.vehicleType = vehicleType;
        this.licenseNumber = licenseNumber;
        this.nationalId = nationalId;
        this.approvalStatus = approvalStatus;
        this.online = online;
        this.isActive = isActive;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getVehicleType() { return vehicleType; }
    public String getLicenseNumber() { return licenseNumber; }
    public String getNationalId() { return nationalId; }
    public String getApprovalStatus() { return approvalStatus; }
    public Boolean getOnline() { return online; }
    public Boolean getIsActive() { return isActive; }
}