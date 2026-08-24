package com.talabaty.backend.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
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










}
