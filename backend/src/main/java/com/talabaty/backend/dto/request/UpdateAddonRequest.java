package com.talabaty.backend.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public class UpdateAddonRequest {

    @Size(max = 100, message = "Name must not exceed 100 characters")
    private String name;

    @Min(value = 0, message = "Additional price must be non-negative")
    @Max(value = 999999, message = "Additional price must not exceed 999999")
    private Double additionalPrice;

    private Boolean available;

    public UpdateAddonRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAdditionalPrice() {
        return additionalPrice;
    }

    public void setAdditionalPrice(Double additionalPrice) {
        this.additionalPrice = additionalPrice;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}