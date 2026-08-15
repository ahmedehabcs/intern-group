package com.talabaty.backend.dto.response;


import java.math.BigDecimal;

public class OrderSummaryresponse {
    private Long id;
    private String restaurantName;
    private String deliveryAddress;
    private int itemCount;
    private BigDecimal totalPrice;
    private BigDecimal deliveryFee;

    public OrderSummaryresponse(Long id, String restaurantName, String deliveryAddress,
                                int itemCount, BigDecimal totalPrice, BigDecimal deliveryFee) {
        this.id = id;
        this.restaurantName = restaurantName;
        this.deliveryAddress = deliveryAddress;
        this.itemCount = itemCount;
        this.totalPrice = totalPrice;
        this.deliveryFee = deliveryFee;
    }

    public Long getId() { return id; }
    public String getRestaurantName() { return restaurantName; }
    public String getDeliveryAddress() { return deliveryAddress; }
    public int getItemCount() { return itemCount; }
    public BigDecimal getTotalPrice() { return totalPrice; }
    public BigDecimal getDeliveryFee() { return deliveryFee; }
}
