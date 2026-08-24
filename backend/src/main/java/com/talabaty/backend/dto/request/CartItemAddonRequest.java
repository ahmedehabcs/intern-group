package com.talabaty.backend.dto.request;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Getter
@Setter
public class CartItemAddonRequest {

    @NotNull(message = "Add-on ID is required")
    @Positive(message = "Add-on ID must be positive")
    private Long menuItemAddonId;

    @NotNull(message = "Add-on quantity is required")
    @Positive(message = "Add-on quantity must be greater than zero")
    private Integer quantity;

    public CartItemAddonRequest() {
    }

    public CartItemAddonRequest(
            Long menuItemAddonId,
            Integer quantity
    ) {
        this.menuItemAddonId = menuItemAddonId;
        this.quantity = quantity;
    }




}
