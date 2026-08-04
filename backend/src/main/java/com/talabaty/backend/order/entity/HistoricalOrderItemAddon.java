package com.talabaty.backend.order.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "historical_order_item_addons")
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

    public HistoricalOrderItemAddon() {
    }

    public Long getId() {
        return id;
    }

    public OrderItem getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
    }

    public String getAddonName() {
        return addonName;
    }

    public void setAddonName(String addonName) {
        this.addonName = addonName;
    }

    public Double getAddonPrice() {
        return addonPrice;
    }

    public void setAddonPrice(Double addonPrice) {
        this.addonPrice = addonPrice;
    }

    @Override
    public String toString() {
        return "HistoricalOrderItemAddon{" +
                "id=" + id +
                ", orderItem=" + orderItem +
                ", addonName='" + addonName + '\'' +
                ", addonPrice=" + addonPrice +
                '}';
    }
}
