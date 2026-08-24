package com.talabaty.backend.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CartItemResponse {

    private Long id;
    private Long menuItemId;
    private String menuItemName;
    private String imageUrl;
    private Double basePrice;
    private Integer quantity;
    private String specialInstructions;
    private Double itemTotalPrice;
    private List<CartItemAddonResponse> addons;

    public CartItemResponse() {
    }

    public CartItemResponse(
            Long id,
            Long menuItemId,
            String menuItemName,
            String imageUrl,
            Double basePrice,
            Integer quantity,
            String specialInstructions,
            Double itemTotalPrice,
            List<CartItemAddonResponse> addons
    ) {
        this.id = id;
        this.menuItemId = menuItemId;
        this.menuItemName = menuItemName;
        this.imageUrl = imageUrl;
        this.basePrice = basePrice;
        this.quantity = quantity;
        this.specialInstructions = specialInstructions;
        this.itemTotalPrice = itemTotalPrice;
        this.addons = addons;
    }


















}
