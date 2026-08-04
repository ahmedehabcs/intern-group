package com.talabaty.backend.Entities.Marketing;

import com.talabaty.backend.Entities.order.Order;
import com.talabaty.backend.Entities.catalog.Restaurant;
import com.talabaty.backend.Entities.user.CustomerProfile;
import jakarta.persistence.*;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer rating;
    private String comment;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private CustomerProfile customer;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id")
    private Restaurant restaurant;

    @OneToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    // Getters and Setters...
}