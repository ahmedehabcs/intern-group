package com.talabaty.backend.dto.response;

import lombok.Getter;
import lombok.Setter;

import com.talabaty.backend.model.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class CustomerOrderSummaryResponse {

    private Long id;
    private String restaurantName;
    private Integer itemCount;
    private BigDecimal totalPrice;
    private OrderStatus status;
    private LocalDateTime createdAt;

    public CustomerOrderSummaryResponse() {
    }

    public CustomerOrderSummaryResponse(Long id, String restaurantName, Integer itemCount, BigDecimal totalPrice, OrderStatus status, LocalDateTime createdAt) {
        this.id = id;
        this.restaurantName = restaurantName;
        this.itemCount = itemCount;
        this.totalPrice = totalPrice;
        this.status = status;
        this.createdAt = createdAt;
    }












}
