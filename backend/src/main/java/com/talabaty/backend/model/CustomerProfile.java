package com.talabaty.backend.model;

import lombok.Setter;

import lombok.Getter;
import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "customers")
@Getter
public class CustomerProfile {

    @Id
    private Long id;
    @Setter
    private String name;
    @Setter
    private Integer loyaltyPoints;
    @Setter
    private String phoneNumber;

    // Foreign key to the associated User
    @OneToOne(cascade = CascadeType.REMOVE)
    @MapsId

    @JoinColumn(name = "id", referencedColumnName = "id", nullable = false)
    @Setter
    private User user;

    // Foreign key to the associated User
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @Setter
    private List<Order> orderHistory = new ArrayList<>();


    // CascadeType.ALL propagates all operations (persist, merge, remove, etc.)
    // from Customer to its Addresses.
    // orphanRemoval = true automatically deletes an Address from the database
    // if it is removed from this collection.
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter
    private List<Address> addresses = new ArrayList<>();

    public CustomerProfile() {}
    public CustomerProfile(String name, Integer loyaltyPoints, String phoneNumber, User user, List<Order> orderHistory, List<Address> addresses) {
        this.name = name;
        this.loyaltyPoints = loyaltyPoints;
        this.phoneNumber = phoneNumber;
        this.user = user;
        this.orderHistory = orderHistory;
        this.addresses = addresses;
    }


    // Getters and Setters...














    @Override
    public String toString() {
        return "CustomerProfile{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", loyaltyPoints=" + loyaltyPoints +
                ", phoneNumber=" + phoneNumber +
                ", user=" + user +
                ", orderHistory=" + orderHistory +
                ", addresses=" + addresses +
                '}';
    }
}
