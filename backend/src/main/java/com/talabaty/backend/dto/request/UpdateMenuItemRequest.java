package com.talabaty.backend.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public class UpdateMenuItemRequest {

    @Size(max = 100, message = "Name must not exceed 100 characters")
    private String name;

    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;

    @Min(value = 0, message = "Base price must be non-negative")
    @Max(value = 999999, message = "Base price must not exceed 999999")
    private Double basePrice;

    @Size(max = 255, message = "Image URL must not exceed 255 characters")
    private String imageUrl;

    private Long menuSectionId;

    private Boolean available;

    private Long[] addonGroupIds;

    public UpdateMenuItemRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getMenuSectionId() {
        return menuSectionId;
    }

    public void setMenuSectionId(Long menuSectionId) {
        this.menuSectionId = menuSectionId;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Long[] getAddonGroupIds() {
        return addonGroupIds;
    }

    public void setAddonGroupIds(Long[] addonGroupIds) {
        this.addonGroupIds = addonGroupIds;
    }
}