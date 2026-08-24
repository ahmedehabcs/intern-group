package com.talabaty.backend.dto.response;

import lombok.Getter;
import lombok.Setter;

import com.talabaty.backend.model.OrderStatus;
import com.talabaty.backend.model.PaymentMethod;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class KitchenOrderDetailsResponse {

    private Long id;
    private String deliveryAddress;
    private BigDecimal subtotal;
    private BigDecimal deliveryFee;
    private BigDecimal totalPrice;
    private OrderStatus status;
    private PaymentMethod paymentMethod;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<OrderItemResponse> orderItems;

    public KitchenOrderDetailsResponse() {
    }

    public KitchenOrderDetailsResponse(Long id, String deliveryAddress, BigDecimal subtotal,
                                       BigDecimal deliveryFee, BigDecimal totalPrice,
                                       OrderStatus status, PaymentMethod paymentMethod,
                                       LocalDateTime createdAt, LocalDateTime updatedAt,
                                       List<OrderItemResponse> orderItems) {
        this.id = id;
        this.deliveryAddress = deliveryAddress;
        this.subtotal = subtotal;
        this.deliveryFee = deliveryFee;
        this.totalPrice = totalPrice;
        this.status = status;
        this.paymentMethod = paymentMethod;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.orderItems = orderItems;
    }




















}
