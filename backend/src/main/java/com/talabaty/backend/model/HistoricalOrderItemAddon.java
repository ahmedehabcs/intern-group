package com.talabaty.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "historical_order_item_addons")
@Getter
@Setter
@NoArgsConstructor
public class HistoricalOrderItemAddon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_item_id", nullable = false)
    private OrderItem orderItem;

    @Column(nullable = false)
    private String addonName;

    @Column(nullable = false)
    private Double addonPrice;

    @Column(nullable = false)
    private Integer quantity;
}