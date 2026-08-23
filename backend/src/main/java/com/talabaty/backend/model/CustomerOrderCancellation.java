package com.talabaty.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "customer_order_cancellations")
@Getter
@Setter
@NoArgsConstructor
public class CustomerOrderCancellation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false, unique = true)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerProfile customer;

    @Column(length = 255)
    private String reason;

    @Column(name = "cancelled_at", nullable = false, updatable = false)
    private LocalDateTime cancelledAt;

    public CustomerOrderCancellation(Order order, CustomerProfile customer, String reason) {
        this.order = order;
        this.customer = customer;
        this.reason = normalizeReason(reason);
    }

    @PrePersist
    private void setCancellationTime() {
        if (cancelledAt == null) {
            cancelledAt = LocalDateTime.now();
        }
    }

    private String normalizeReason(String reason) {
        if (reason == null || reason.isBlank()) {
            return null;
        }

        return reason.trim();
    }

    public void setReason(String reason) {
        this.reason = normalizeReason(reason);
    }
}