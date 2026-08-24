package com.talabaty.backend.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RestaurantDetailsResponse {

    private Long id;
    private String name;
    private String description;
    private String logoUrl;
    private List<MenuSectionResponse> menuSections;

    public RestaurantDetailsResponse() {
    }

    public RestaurantDetailsResponse(
            Long id,
            String name,
            String description,
            String logoUrl,
            List<MenuSectionResponse> menuSections
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.logoUrl = logoUrl;
        this.menuSections = menuSections;
    }










}
