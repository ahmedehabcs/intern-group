package com.talabaty.backend.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
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
















}
