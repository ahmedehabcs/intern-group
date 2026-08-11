package com.talabaty.backend.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "drivers")
public class DeliveryProfile {

    @Id
    private Long id;
    private String Name;
    private String vehicleType;
    private String licenseNumber;
    private Boolean isOnline;
    private String PhoneNumber;
    private String nationalId;


    @OneToOne(cascade = CascadeType.REMOVE)
    @MapsId
    @JoinColumn(name = "id", referencedColumnName = "id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "rider", cascade = CascadeType.ALL)
    private List<Order> deliveryHistory = new ArrayList<>();

    public DeliveryProfile() {}

    public DeliveryProfile(String name, String vehicleType, String licenseNumber, Boolean isOnline, String phoneNumber, String nationalId, User user, List<Order> deliveryHistory) {
        Name = name;
        this.vehicleType = vehicleType;
        this.licenseNumber = licenseNumber;
        this.isOnline = isOnline;
        PhoneNumber = phoneNumber;
        this.nationalId = nationalId;
        this.user = user;
        this.deliveryHistory = deliveryHistory;
    }

    public Long getId() {
        return id;
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
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
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
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
                ", Name='" + Name + '\'' +
                ", vehicle Type='" + vehicleType + '\'' +
                ", license Number='" + licenseNumber + '\'' +
                ", isOnline=" + isOnline +
                ", Phone Number=" + PhoneNumber +
                ", national Id='" + nationalId + '\'' +
                ", user=" + user +
                ", delivery History=" + deliveryHistory +
                '}';
    }
}
