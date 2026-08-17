package com.talabaty.backend.dto.response;

import java.util.List;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public List<CartItemResponse> getItems() {
        return items;
    }

    public void setItems(List<CartItemResponse> items) {
        this.items = items;
    }
}