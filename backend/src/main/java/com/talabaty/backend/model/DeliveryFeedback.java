package com.talabaty.backend.model;

import lombok.Setter;

import lombok.Getter;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "delivery_feedback")
@Getter
public class DeliveryFeedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Setter
    private Integer rating;

    @Column(length = 500)
    @Setter
    private String comment;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @Setter
    private CustomerProfile customer;

    @ManyToOne
    @JoinColumn(name = "rider_id", nullable = false)
    @Setter
    private DeliveryProfile rider;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false, unique = true)
    @Setter
    private Order order;

    @PrePersist
    private void setCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }












}
