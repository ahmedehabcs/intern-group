package com.talabaty.backend.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderAdminResponse {
    private Long id;
    private String status;
    private String restaurantName;
    private String customerName;
    private String riderName;
    private BigDecimal totalPrice;
    private BigDecimal deliveryFee;
    private LocalDateTime updatedAt;

    public OrderAdminResponse(Long id, String status, String restaurantName, String customerName,
                              String riderName, BigDecimal totalPrice, BigDecimal deliveryFee,
                              LocalDateTime updatedAt) {
        this.id = id;
        this.status = status;
        this.restaurantName = restaurantName;
        this.customerName = customerName;
        this.riderName = riderName;
        this.totalPrice = totalPrice;
        this.deliveryFee = deliveryFee;
        this.updatedAt = updatedAt;
    }

    public Long getId() { return id; }
    public String getStatus() { return status; }
    public String getRestaurantName() { return restaurantName; }
    public String getCustomerName() { return customerName; }
    public String getRiderName() { return riderName; }
    public BigDecimal getTotalPrice() { return totalPrice; }
    public BigDecimal getDeliveryFee() { return deliveryFee; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}