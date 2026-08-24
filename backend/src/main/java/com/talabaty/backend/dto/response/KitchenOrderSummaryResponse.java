package com.talabaty.backend.dto.response;

import lombok.Getter;
import lombok.Setter;

import com.talabaty.backend.model.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class KitchenOrderSummaryResponse {

    private Long id;
    private Integer itemCount;
    private BigDecimal totalPrice;
    private OrderStatus status;
    private LocalDateTime createdAt;

    public KitchenOrderSummaryResponse() {
    }

    public KitchenOrderSummaryResponse(Long id, Integer itemCount, BigDecimal totalPrice,
                                       OrderStatus status, LocalDateTime createdAt) {
        this.id = id;
        this.itemCount = itemCount;
        this.totalPrice = totalPrice;
        this.status = status;
        this.createdAt = createdAt;
    }










}
