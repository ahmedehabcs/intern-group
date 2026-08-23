package com.talabaty.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "drivers")
@Getter
@Setter
@NoArgsConstructor
public class DeliveryProfile {

    @Id
    private Long id;

    private String Name;
    private String vehicleType;

    @Column(name = "license_number", unique = true)
    private String licenseNumber;

    private Boolean isOnline;

    @Column(name = "phone_number")
    private String PhoneNumber;

    @Column(name = "national_id", unique = true)
    private String nationalId;

    @OneToOne(cascade = CascadeType.REMOVE)
    @MapsId
    @JoinColumn(name = "id", referencedColumnName = "id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "rider", cascade = CascadeType.ALL)
    private List<Order> deliveryHistory = new ArrayList<>();

    public DeliveryProfile(
            String name,
            String vehicleType,
            String licenseNumber,
            Boolean isOnline,
            String phoneNumber,
            String nationalId,
            User user,
            List<Order> deliveryHistory
    ) {
        Name = name;
        this.vehicleType = vehicleType;
        this.licenseNumber = licenseNumber;
        this.isOnline = isOnline;
        PhoneNumber = phoneNumber;
        this.nationalId = nationalId;
        this.user = user;
        this.deliveryHistory = deliveryHistory;
    }

    public Boolean getOnline() {
        return isOnline;
    }

    public void setOnline(Boolean online) {
        isOnline = online;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }
}