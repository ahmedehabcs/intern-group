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
public class CustomerOrderDetailsResponse {

    private Long id;
    private String restaurantName;
    private String deliveryAddress;
    private BigDecimal subtotal;
    private BigDecimal deliveryFee;
    private BigDecimal totalPrice;
    private OrderStatus status;
    private PaymentMethod paymentMethod;
    private LocalDateTime createdAt;
    private List<OrderItemResponse> orderItems;

    public CustomerOrderDetailsResponse() {
    }

    public CustomerOrderDetailsResponse(Long id, String restaurantName, String deliveryAddress, BigDecimal subtotal, BigDecimal deliveryFee, BigDecimal totalPrice, OrderStatus status, PaymentMethod paymentMethod, LocalDateTime createdAt, List<OrderItemResponse> orderItems) {
        this.id = id;
        this.restaurantName = restaurantName;
        this.deliveryAddress = deliveryAddress;
        this.subtotal = subtotal;
        this.deliveryFee = deliveryFee;
        this.totalPrice = totalPrice;
        this.status = status;
        this.paymentMethod = paymentMethod;
        this.createdAt = createdAt;
        this.orderItems = orderItems;
    }

}
