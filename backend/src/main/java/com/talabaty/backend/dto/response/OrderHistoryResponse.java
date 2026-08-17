package com.talabaty.backend.dto.response;


import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderHistoryResponse {
    private Long id;
    private String restaurantName;
    private LocalDateTime deliveredAt;
    private BigDecimal earnings;
    private BigDecimal orderTotal;

    public OrderHistoryResponse(Long id, String restaurantName, LocalDateTime deliveredAt,
                                BigDecimal earnings, BigDecimal orderTotal) {
        this.id = id;
        this.restaurantName = restaurantName;
        this.deliveredAt = deliveredAt;
        this.earnings = earnings;
        this.orderTotal = orderTotal;
    }

    public Long getId() { return id; }
    public String getRestaurantName() { return restaurantName; }
    public LocalDateTime getDeliveredAt() { return deliveredAt; }
    public BigDecimal getEarnings() { return earnings; }
    public BigDecimal getOrderTotal() { return orderTotal; }
}