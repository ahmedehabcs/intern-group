package com.talabaty.backend.dto.projection;

public interface MenuItemSearchProjection {

    Long getId();
    String getName();
    String getDescription();
    Double getBasePrice();
    String getImageUrl();
    MenuSectionInfo getMenuSection();
    interface MenuSectionInfo {
        Long getId();
        String getName();
        RestaurantInfo getRestaurant();
    }
    interface RestaurantInfo {
        Long getId();
        String getName();
    }
}