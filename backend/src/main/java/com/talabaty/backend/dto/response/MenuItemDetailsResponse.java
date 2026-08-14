package com.talabaty.backend.dto.response;

import java.util.List;

public class MenuItemDetailsResponse {

    private Long id;
    private String name;
    private String description;
    private Double basePrice;
    private String imageUrl;
    private Long restaurantId;
    private Long menuSectionId;
    private List<AddonGroupResponse> addonGroups;

    public MenuItemDetailsResponse() {
    }

    public MenuItemDetailsResponse(
            Long id,
            String name,
            String description,
            Double basePrice,
            String imageUrl,
            Long restaurantId,
            Long menuSectionId,
            List<AddonGroupResponse> addonGroups
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.basePrice = basePrice;
        this.imageUrl = imageUrl;
        this.restaurantId = restaurantId;
        this.menuSectionId = menuSectionId;
        this.addonGroups = addonGroups;
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

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Long getMenuSectionId() {
        return menuSectionId;
    }

    public void setMenuSectionId(Long menuSectionId) {
        this.menuSectionId = menuSectionId;
    }

    public List<AddonGroupResponse> getAddonGroups() {
        return addonGroups;
    }

    public void setAddonGroups(List<AddonGroupResponse> addonGroups) {
        this.addonGroups = addonGroups;
    }
}