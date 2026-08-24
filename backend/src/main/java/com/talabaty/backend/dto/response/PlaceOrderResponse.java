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
public class PlaceOrderResponse {

    private Long id;
    private String restaurantName;
    private String deliveryAddress;
    private BigDecimal subtotal;
    private BigDecimal deliveryFee;
    private BigDecimal totalPrice;
    private LocalDateTime createdAt;
    private OrderStatus status;
    private PaymentMethod paymentMethod;
    private List<OrderItemResponse> orderItems;

    public PlaceOrderResponse() {
    }




















}
