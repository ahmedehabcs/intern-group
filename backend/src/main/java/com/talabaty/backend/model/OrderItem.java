package com.talabaty.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "order_items")
@Getter
@Setter
@NoArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_item_id", nullable = false)
    private MenuItem menuItem;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Double unitPrice;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @OneToMany(
            mappedBy = "orderItem",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<HistoricalOrderItemAddon> addons = new ArrayList<>();

    public OrderItem(
            Order order,
            MenuItem menuItem,
            String productName,
            Integer quantity,
            Double unitPrice,
            String notes,
            List<HistoricalOrderItemAddon> addons
    ) {
        this.order = order;
        this.menuItem = menuItem;
        this.productName = productName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.notes = notes;
        this.addons = addons;
    }
}