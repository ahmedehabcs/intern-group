package com.talabaty.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "addresses")
@Getter
@Setter
@NoArgsConstructor
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

    // @ManyToOne
    // @JoinColumn(name = "user_id")
    // private User user;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerProfile customer;

    @ManyToOne
    @JoinColumn(name = "governorate_id")
    private Governorate governorate;
}