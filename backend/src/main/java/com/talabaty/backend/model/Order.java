
package com.talabaty.backend.model;

import lombok.Setter;

import lombok.Getter;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Customer
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @Setter
    private CustomerProfile customer;

    // Restaurant
    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    @Setter
    private Restaurant restaurant;

    // Immutable restaurant-name snapshot
    @Column(name = "restaurant_name", nullable = false)
    @Setter
    private String restaurantName;

    // Rider
    @ManyToOne
    @JoinColumn(name = "rider_id")
    @Setter
    private DeliveryProfile rider;

    // Selected customer address
    @ManyToOne
    @JoinColumn(name = "address_id")
    @Setter
    private Address address;

    // Immutable formatted delivery-address snapshot
    @Column(name = "delivery_address", nullable = false, columnDefinition = "TEXT")
    @Setter
    private String deliveryAddress;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Setter
    private OrderStatus status = OrderStatus.PENDING;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false)
    @Setter
    private PaymentMethod paymentMethod;

    @Column(nullable = false)
    @Setter
    private BigDecimal subtotal;

    @Column(name = "delivery_fee", nullable = false)
    @Setter
    private BigDecimal deliveryFee;

    @Column(name = "total_price", nullable = false)
    @Setter
    private BigDecimal totalPrice;

    @Column(name = "created_at", nullable = false, updatable = false)
    @Setter
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @Setter
    private LocalDateTime updatedAt;

    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Setter
    private List<OrderItem> orderItems = new ArrayList<>();

    public Order() {
    }

    @PrePersist
    private void setTimestampsWhenCreated() {
        LocalDateTime now = LocalDateTime.now();

        if (createdAt == null) {
            createdAt = now;
        }

        updatedAt = now;
    }

    @PreUpdate
    private void updateTimestamp() {
        updatedAt = LocalDateTime.now();
    }

    // Getters & Setters






























    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer=" + customer +
                ", restaurant=" + restaurant +
                ", restaurantName='" + restaurantName + '\'' +
                ", rider=" + rider +
                ", address=" + address +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", status=" + status +
                ", paymentMethod=" + paymentMethod +
                ", subtotal=" + subtotal +
                ", deliveryFee=" + deliveryFee +
                ", totalPrice=" + totalPrice +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
