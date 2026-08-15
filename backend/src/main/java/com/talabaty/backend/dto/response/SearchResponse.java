package com.talabaty.backend.dto.response;

import java.util.List;

public class SearchResponse {

    private List<RestaurantResponse> restaurants;
    private List<MenuItemSearchResponse> menuItems;

    public SearchResponse() {
    }

    public SearchResponse(
            List<RestaurantResponse> restaurants,
            List<MenuItemSearchResponse> menuItems
    ) {
        this.restaurants = restaurants;
        this.menuItems = menuItems;
    }

    public List<RestaurantResponse> getRestaurants() {
        return restaurants;
    }
    public void setRestaurants(List<RestaurantResponse> restaurants) {
        this.restaurants = restaurants;
    }
    public List<MenuItemSearchResponse> getMenuItems() {
        return menuItems;
    }
    public void setMenuItems(List<MenuItemSearchResponse> menuItems) {
        this.menuItems = menuItems;
    }
}
