package com.talabaty.backend.model;

import lombok.Setter;

import lombok.Getter;

import jakarta.persistence.*;

@Entity
@Table(name = "historical_order_item_addons")
@Getter
public class HistoricalOrderItemAddon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_item_id", nullable = false)
    @Setter
    private OrderItem orderItem;

    @Column(nullable = false)
    @Setter
    private String addonName;

    @Column(nullable = false)
    @Setter
    private Double addonPrice;

    @Column(nullable = false)
    @Setter
    private Integer quantity;

    public HistoricalOrderItemAddon() {
    }










    @Override
    public String toString() {
        return "HistoricalOrderItemAddon{" +
                "id=" + id +
                ", orderItem=" + orderItem +
                ", addonName='" + addonName + '\'' +
                ", addonPrice=" + addonPrice +
                ", quantity=" + quantity +
                '}';
    }
}
