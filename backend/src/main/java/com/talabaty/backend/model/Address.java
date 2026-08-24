package com.talabaty.backend.model;

import lombok.Setter;

import lombok.Getter;
import jakarta.persistence.*;

@Entity
@Table(name="addresses")
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private String building;
    private String floor;
    private String apartment;
    private String city;

    @Column(name = "is_default", nullable = false)
    private boolean isDefault;


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

    public Address(String street, String building, String floor, String apartment, String city, CustomerProfile customer, Governorate governorate) {
        this.street = street;
        this.building = building;
        this.floor = floor;
        this.apartment = apartment;
        this.city = city;
        this.customer = customer;
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
                ", city='" + city + '\'' +
                ", customer=" + customer +
                ", governorate=" + governorate +
                '}';
    }
}
