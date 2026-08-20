package com.talabaty.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "kitchen_order_cancellations")
public class KitchenOrderCancellation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false, unique = true)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "kitchen_manager_id", nullable = false)
    private KitchenManager kitchenManager;

    @Column(nullable = false, length = 255)
    private String reason;

    @Column(name = "cancelled_at", nullable = false, updatable = false)
    private LocalDateTime cancelledAt;

    public KitchenOrderCancellation() {
    }

    public KitchenOrderCancellation(
            Order order,
            KitchenManager kitchenManager,
            String reason
    ) {
        this.order = order;
        this.kitchenManager = kitchenManager;
        this.reason = reason.trim();
    }

    @PrePersist
    private void setCancellationTime() {
        if (cancelledAt == null) {
            cancelledAt = LocalDateTime.now();
        }
    }

    public Long getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public KitchenManager getKitchenManager() {
        return kitchenManager;
    }

    public void setKitchenManager(KitchenManager kitchenManager) {
        this.kitchenManager = kitchenManager;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public LocalDateTime getCancelledAt() {
        return cancelledAt;
    }
}
