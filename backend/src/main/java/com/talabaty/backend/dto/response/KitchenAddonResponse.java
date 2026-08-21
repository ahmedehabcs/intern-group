package com.talabaty.backend.dto.response;

public class KitchenAddonResponse {

    private Long id;
    private String name;
    private Double additionalPrice;
    private Boolean available;

    public KitchenAddonResponse() {
    }

    public KitchenAddonResponse(
            Long id,
            String name,
            Double additionalPrice,
            Boolean available
    ) {
        this.id = id;
        this.name = name;
        this.additionalPrice = additionalPrice;
        this.available = available;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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