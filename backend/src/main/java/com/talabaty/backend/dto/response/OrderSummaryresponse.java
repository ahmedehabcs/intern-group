package com.talabaty.backend.dto.response;

import lombok.Getter;


import java.math.BigDecimal;

@Getter
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

}
