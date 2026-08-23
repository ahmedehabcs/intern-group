package com.talabaty.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "kitchen_order_cancellations")
@Getter
@Setter
@NoArgsConstructor
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

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }
}