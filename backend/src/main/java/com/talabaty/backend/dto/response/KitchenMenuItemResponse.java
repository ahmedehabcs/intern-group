package com.talabaty.backend.dto.response;

public class KitchenMenuItemResponse {

    private Long id;
    private String name;
    private Double basePrice;
    private Boolean available;
    private Long menuSectionId;
    private String menuSectionName;

    public KitchenMenuItemResponse() {
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

    public Double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Long getMenuSectionId() {
        return menuSectionId;
    }

    public void setMenuSectionId(Long menuSectionId) {
        this.menuSectionId = menuSectionId;
    }

    public String getMenuSectionName() {
        return menuSectionName;
    }

    public void setMenuSectionName(String menuSectionName) {
        this.menuSectionName = menuSectionName;
    }
}
