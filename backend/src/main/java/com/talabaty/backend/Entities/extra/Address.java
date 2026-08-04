package com.talabaty.backend.Entities.extra;

import com.talabaty.backend.Entities.user.CustomerProfile;
import jakarta.persistence.*;

@Entity
@Table(name="addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private String building;
    private String floor;
    private String apartment;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerProfile customer;


    @ManyToOne
    @JoinColumn(name = "governorate_id")
    private Governorate governorate;

    public Address() {}

    public Address(String street, String building, String floor, String apartment, CustomerProfile customer, Governorate governorate) {
        this.street = street;
        this.building = building;
        this.floor = floor;
        this.apartment = apartment;
        this.customer = customer;
        this.governorate = governorate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public CustomerProfile getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerProfile customer) {
        this.customer = customer;
    }

    public Governorate getGovernorate() {
        return governorate;
    }

    public void setGovernorate(Governorate governorate) {
        this.governorate = governorate;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", building='" + building + '\'' +
                ", floor='" + floor + '\'' +
                ", apartment='" + apartment + '\'' +
                ", customer=" + customer +
                ", governorate=" + governorate +
                '}';
    }
}
