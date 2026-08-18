package com.talabaty.backend.dto.response;

import java.math.BigDecimal;
import java.util.List;

public class OrderItemResponse {

    private String productName;
    private BigDecimal unitPrice;
    private Integer quantity;
    private String notes;
    private List<OrderAddonResponse> addons;

    public OrderItemResponse() {
    }

    public OrderItemResponse(String productName, BigDecimal unitPrice, Integer quantity,
                             String notes, List<OrderAddonResponse> addons) {
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.notes = notes;
        this.addons = addons;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<OrderAddonResponse> getAddons() {
        return addons;
    }

    public void setAddons(List<OrderAddonResponse> addons) {
        this.addons = addons;
    }
}
