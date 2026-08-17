package com.talabaty.backend.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

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

    public Long getMenuItemAddonId() {
        return menuItemAddonId;
    }

    public void setMenuItemAddonId(Long menuItemAddonId) {
        this.menuItemAddonId = menuItemAddonId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}