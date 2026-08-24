package com.talabaty.backend.model;

import lombok.Setter;

import lombok.Getter;
import lombok.experimental.Accessors;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "restaurants")
@Getter
@Setter
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String phone;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "governorate_id", nullable = false)
    private Governorate governorate;

    @Column(columnDefinition = "TEXT")
    private String description;


    private String logoUrl;

    @Column(nullable = false, columnDefinition = "boolean default true")
    @Accessors(prefix = "is")
    private Boolean isActive = true;

    @Column(name = "delivery_fee", nullable = false, precision = 10, scale = 2)
    private BigDecimal deliveryFee = BigDecimal.ZERO;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MenuSection> menuSections = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "restaurant_categories",
            joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories = new ArrayList<>();

    // Constructors, Getters, and Setters


    public Restaurant() {
    }

























    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", governorate=" + governorate +
                ", description='" + description + '\'' +
                ", logoUrl='" + logoUrl + '\'' +
                ", isActive=" + isActive +
                ", deliveryFee=" + deliveryFee +
                ", menuSections=" + menuSections +
                '}';
    }
}





