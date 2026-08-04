package com.talabaty.backend.payment.entity;
import com.talabaty.backend.order.entity.Order;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    private String paymentMethod;
    // مثال: Cash, Visa, Wallet

    private String paymentStatus;
    // مثال: Pending, Paid, Failed

    private LocalDateTime paymentDate;

    @OneToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

}


