package com.talabaty.backend.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
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






}
