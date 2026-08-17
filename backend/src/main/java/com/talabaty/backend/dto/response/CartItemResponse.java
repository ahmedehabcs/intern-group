package com.talabaty.backend.dto.response;

import java.util.List;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(Long menuItemId) {
        this.menuItemId = menuItemId;
    }

    public String getMenuItemName() {
        return menuItemName;
    }

    public void setMenuItemName(String menuItemName) {
        this.menuItemName = menuItemName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }

    public Double getItemTotalPrice() {
        return itemTotalPrice;
    }

    public void setItemTotalPrice(Double itemTotalPrice) {
        this.itemTotalPrice = itemTotalPrice;
    }

    public List<CartItemAddonResponse> getAddons() {
        return addons;
    }

    public void setAddons(List<CartItemAddonResponse> addons) {
        this.addons = addons;
    }
}