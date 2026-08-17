package com.talabaty.backend.dto.response;

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

    public Long getMenuItemAddonId() {
        return menuItemAddonId;
    }

    public void setMenuItemAddonId(Long menuItemAddonId) {
        this.menuItemAddonId = menuItemAddonId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAddonGroupId() {
        return addonGroupId;
    }

    public void setAddonGroupId(Long addonGroupId) {
        this.addonGroupId = addonGroupId;
    }

    public String getAddonGroupName() {
        return addonGroupName;
    }

    public void setAddonGroupName(String addonGroupName) {
        this.addonGroupName = addonGroupName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPriceAtAddition() {
        return priceAtAddition;
    }

    public void setPriceAtAddition(Double priceAtAddition) {
        this.priceAtAddition = priceAtAddition;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}