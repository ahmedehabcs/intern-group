package com.talabaty.backend.model;

import lombok.Setter;

import lombok.Getter;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "order_items")
@Getter
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    @Setter
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_item_id", nullable = false)
    @Setter
    private MenuItem menuItem;

    @Column(name = "product_name", nullable = false)
    @Setter
    private String productName;

    @Column(nullable = false)
    @Setter
    private Integer quantity;

    @Column(nullable = false)
    @Setter
    private Double unitPrice;

    @Column(columnDefinition = "TEXT")
    @Setter
    private String notes;

    @OneToMany(mappedBy = "orderItem", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter
    private List<HistoricalOrderItemAddon> addons = new ArrayList<>();

    public OrderItem() {
    }

    public OrderItem(Order order, MenuItem menuItem, String productName, Integer quantity, Double unitPrice, String notes, List<HistoricalOrderItemAddon> addons) {
        this.order = order;
        this.menuItem = menuItem;
        this.productName = productName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.notes = notes;
        this.addons = addons;
    }
// Getters & Setters














    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", order=" + order +
                ", menuItem=" + menuItem +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", notes='" + notes + '\'' +
                ", addons=" + addons +
                '}';
    }
}
