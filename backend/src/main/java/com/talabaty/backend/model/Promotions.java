package com.talabaty.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "promotions")
@Getter
@Setter
@NoArgsConstructor
public class Promotions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private Double discountValue;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id")
    private Restaurant restaurant;

    public Promotions(
            String title,
            String description,
            Double discountValue,
            LocalDateTime startDate,
            LocalDateTime endDate,
            Boolean active,
            Restaurant restaurant
    ) {
        this.title = title;
        this.description = description;
        this.discountValue = discountValue;
        this.startDate = startDate;
        this.endDate = endDate;
        this.active = active;
        this.restaurant = restaurant;
    }
}