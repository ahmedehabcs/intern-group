package com.talabaty.backend.dto.response;

public class MenuItemAddonResponse {

    private Long id;
    private String name;
    private Double additionalPrice;

    public MenuItemAddonResponse() {
    }

    public MenuItemAddonResponse(
            Long id,
            String name,
            Double additionalPrice
    ) {
        this.id = id;
        this.name = name;
        this.additionalPrice = additionalPrice;
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
}