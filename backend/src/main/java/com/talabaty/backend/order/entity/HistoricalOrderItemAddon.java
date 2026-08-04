package com.talabaty.backend.order.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "historical_order_item_addons")
public class Historicalcom.talabaty.backend.order.entity.OrderItemAddon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_item_id", nullable = false)
    private com.talabaty.backend.order.entity.OrderItem com.talabaty.backend.order.entity.OrderItem;

    @Column(nullable = false)
    private String addonName;

    @Column(nullable = false)
    private Double addonPrice;

    public Historicalcom.talabaty.backend.order.entity.OrderItemAddon() {
    }

    public Long getId() {
        return id;
    }

    public com.talabaty.backend.order.entity.OrderItem getcom.talabaty.backend.order.entity.OrderItem() {
        return com.talabaty.backend.order.entity.OrderItem;
    }

    public void setcom.talabaty.backend.order.entity.OrderItem(com.talabaty.backend.order.entity.OrderItem com.talabaty.backend.order.entity.OrderItem) {
        this.com.talabaty.backend.order.entity.OrderItem = com.talabaty.backend.order.entity.OrderItem;
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
        return "Historicalcom.talabaty.backend.order.entity.OrderItemAddon{" +
                "id=" + id +
                ", com.talabaty.backend.order.entity.OrderItem=" + com.talabaty.backend.order.entity.OrderItem +
                ", addonName='" + addonName + '\'' +
                ", addonPrice=" + addonPrice +
                '}';
    }
}
