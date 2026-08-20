package com.talabaty.backend.dto.response;

import com.talabaty.backend.model.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getItemCount() {
        return itemCount;
    }

    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
