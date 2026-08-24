package com.talabaty.backend.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuItemSearchResponse {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private String imageUrl;
    private Long restaurantId;
    private String restaurantName;
    private Long menuSectionId;
    private String menuSectionName;

    public MenuItemSearchResponse() {
    }

    public MenuItemSearchResponse(
            Long id,
            String name,
            String description,
            Double price,
            String imageUrl,
            Long restaurantId,
            String restaurantName,
            Long menuSectionId,
            String menuSectionName
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.menuSectionId = menuSectionId;
        this.menuSectionName = menuSectionName;
    }

}
