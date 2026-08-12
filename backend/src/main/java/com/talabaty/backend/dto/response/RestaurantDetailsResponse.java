package com.talabaty.backend.dto.response;

import java.util.List;

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

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public List<MenuSectionResponse> getMenuSections() {
        return menuSections;
    }

    public void setMenuSections(List<MenuSectionResponse> menuSections) {
        this.menuSections = menuSections;
    }
}