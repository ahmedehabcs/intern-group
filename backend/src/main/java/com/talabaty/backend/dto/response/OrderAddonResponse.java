package com.talabaty.backend.dto.response;

import java.math.BigDecimal;

public class OrderAddonResponse {

    private String addonName;
    private BigDecimal addonPrice;
    private Integer quantity;

    public OrderAddonResponse() {
    }

    public OrderAddonResponse(String addonName, BigDecimal addonPrice, Integer quantity) {
        this.addonName = addonName;
        this.addonPrice = addonPrice;
        this.quantity = quantity;
    }

    public String getAddonName() {
        return addonName;
    }

    public void setAddonName(String addonName) {
        this.addonName = addonName;
    }

    public BigDecimal getAddonPrice() {
        return addonPrice;
    }

    public void setAddonPrice(BigDecimal addonPrice) {
        this.addonPrice = addonPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
