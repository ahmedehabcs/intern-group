package com.talabaty.backend.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
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

}
