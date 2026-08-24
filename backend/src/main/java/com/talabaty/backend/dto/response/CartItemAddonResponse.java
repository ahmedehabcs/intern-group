package com.talabaty.backend.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemAddonResponse {

    private Long menuItemAddonId;
    private String name;
    private Long addonGroupId;
    private String addonGroupName;
    private Integer quantity;
    private Double priceAtAddition;
    private Double totalPrice;

    public CartItemAddonResponse() {
    }

    public CartItemAddonResponse(
            Long menuItemAddonId,
            String name,
            Long addonGroupId,
            String addonGroupName,
            Integer quantity,
            Double priceAtAddition,
            Double totalPrice
    ) {
        this.menuItemAddonId = menuItemAddonId;
        this.name = name;
        this.addonGroupId = addonGroupId;
        this.addonGroupName = addonGroupName;
        this.quantity = quantity;
        this.priceAtAddition = priceAtAddition;
        this.totalPrice = totalPrice;
    }














}
