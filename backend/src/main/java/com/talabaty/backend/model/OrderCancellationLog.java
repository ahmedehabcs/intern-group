package com.talabaty.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "order_cancellation_logs")
@Getter
@Setter
@NoArgsConstructor
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

    public OrderCancellationLog(Long orderId, Long riderId, String reason) {
        this.orderId = orderId;
        this.riderId = riderId;
        this.reason = reason;
        this.cancelledAt = LocalDateTime.now();
    }
}