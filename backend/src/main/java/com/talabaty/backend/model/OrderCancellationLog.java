package com.talabaty.backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "order_cancellation_logs")
public class OrderCancellationLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @Column(name = "rider_id", nullable = false)
    private Long riderId;

    @Column(nullable = false)
    private String reason;

    @Column(name = "cancelled_at", nullable = false)
    private LocalDateTime cancelledAt;

    public OrderCancellationLog() {}

    public OrderCancellationLog(Long orderId, Long riderId, String reason) {
        this.orderId = orderId;
        this.riderId = riderId;
        this.reason = reason;
        this.cancelledAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public Long getOrderId() { return orderId; }
    public Long getRiderId() { return riderId; }
    public String getReason() { return reason; }
    public LocalDateTime getCancelledAt() { return cancelledAt; }
}