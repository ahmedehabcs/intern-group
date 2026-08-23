package com.talabaty.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
public class CustomerProfile {

    @Id
    private Long id;

    private String name;
    private Integer loyaltyPoints;
    private String phoneNumber;

    // Link the customer profile to the associated User
    @OneToOne(cascade = CascadeType.REMOVE)
    @MapsId
    @JoinColumn(name = "id", referencedColumnName = "id", nullable = false)
    private User user;

    // Customer order history
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> orderHistory = new ArrayList<>();

    // Customer addresses
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses = new ArrayList<>();

    public CustomerProfile(
            String name,
            Integer loyaltyPoints,
            String phoneNumber,
            User user,
            List<Order> orderHistory,
            List<Address> addresses
    ) {
        this.name = name;
        this.loyaltyPoints = loyaltyPoints;
        this.phoneNumber = phoneNumber;
        this.user = user;
        this.orderHistory = orderHistory;
        this.addresses = addresses;
    }
}