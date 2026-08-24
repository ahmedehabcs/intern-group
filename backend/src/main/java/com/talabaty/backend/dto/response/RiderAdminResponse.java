package com.talabaty.backend.dto.response;

import lombok.Getter;
import lombok.experimental.Accessors;


@Getter
public class RiderAdminResponse {
    private Long id;
    private String name;
    private String phoneNumber;
    private String vehicleType;
    private String licenseNumber;
    private String nationalId;
    private String approvalStatus;
    private Boolean online;
    @Accessors(prefix = "is")
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

}
