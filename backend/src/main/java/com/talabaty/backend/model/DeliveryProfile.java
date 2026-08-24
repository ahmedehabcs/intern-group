package com.talabaty.backend.model;

import lombok.Setter;

import lombok.Getter;
import lombok.experimental.Accessors;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "drivers")
@Getter
public class DeliveryProfile {

    @Id
    private Long id;
    @Setter
    private String name;
    @Setter
    private String vehicleType;

    @Column(name = "license_number", unique = true)
    @Setter
    private String licenseNumber;
    @Accessors(prefix = "is")
    @Setter
    private Boolean isOnline;

    @Column(name = "is_active", nullable = false, columnDefinition = "boolean default true")
    @Accessors(prefix = "is")
    @Setter
    private Boolean isActive = true;

    @Column(name = "phone_number")
    @Setter
    private String phoneNumber;

    @Column(name = "national_id", unique = true)
    @Setter
    private String nationalId;

    @Enumerated(EnumType.STRING)
    @Column(name = "approval_status", nullable = false)
    @Setter
    private ApprovalStatus approvalStatus = ApprovalStatus.PENDING;


    @OneToOne(cascade = CascadeType.REMOVE)
    @MapsId
    @JoinColumn(name = "id", referencedColumnName = "id", nullable = false)
    @Setter
    private User user;

    @OneToMany(mappedBy = "rider", cascade = CascadeType.ALL)
    @Setter
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
