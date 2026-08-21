package com.talabaty.backend.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "drivers")
public class DeliveryProfile {

    @Id
    private Long id;
    private String name;
    private String vehicleType;

    @Column(name = "license_number", unique = true)
    private String licenseNumber;
    private Boolean isOnline;

    @Column(name = "is_active", nullable = false, columnDefinition = "boolean default true")
    private Boolean isActive = true;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "national_id", unique = true)
    private String nationalId;

    @Enumerated(EnumType.STRING)
    @Column(name = "approval_status", nullable = false)
    private ApprovalStatus approvalStatus = ApprovalStatus.PENDING;


    @OneToOne(cascade = CascadeType.REMOVE)
    @MapsId
    @JoinColumn(name = "id", referencedColumnName = "id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "rider", cascade = CascadeType.ALL)
    private List<Order> deliveryHistory = new ArrayList<>();

    public DeliveryProfile() {}

    public DeliveryProfile(String name, String vehicleType, String licenseNumber, Boolean isOnline, String phoneNumber, String nationalId, User user, List<Order> deliveryHistory) {
        this.name = name;
        this.vehicleType = vehicleType;
        this.licenseNumber = licenseNumber;
        this.isOnline = isOnline;
        this.phoneNumber = phoneNumber;
        this.nationalId = nationalId;
        this.user = user;
        this.deliveryHistory = deliveryHistory;
    }

    public Long getId() {
        return id;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Boolean getOnline() {
        return isOnline;
    }

    public void setOnline(Boolean online) {
        isOnline = online;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public ApprovalStatus getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(ApprovalStatus approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Order> getDeliveryHistory() {
        return deliveryHistory;
    }

    public void setDeliveryHistory(List<Order> deliveryHistory) {
        this.deliveryHistory = deliveryHistory;
    }

    @Override
    public String toString() {
        return "DeliveryProfile{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", vehicle Type='" + vehicleType + '\'' +
                ", license Number='" + licenseNumber + '\'' +
                ", isOnline=" + isOnline +
                ", phoneNumber=" + phoneNumber +
                ", national Id='" + nationalId + '\'' +
                ", user=" + user +
                ", delivery History=" + deliveryHistory +
                '}';
    }
}
