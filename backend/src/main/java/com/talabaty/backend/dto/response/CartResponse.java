package com.talabaty.backend.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CartResponse {

    private Long id;
    private Long restaurantId;
    private String restaurantName;
    private Double subtotal;
    private List<CartItemResponse> items;

    public CartResponse() {
    }

    public CartResponse(
            Long id,
            Long restaurantId,
            String restaurantName,
            Double subtotal,
            List<CartItemResponse> items
    ) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.subtotal = subtotal;
        this.items = items;
    }










}
