package com.talabaty.backend.dto.response;

import java.util.List;

public class KitchenMenuSectionResponse {

    private Long id;
    private String name;
    private String description;
    private Boolean active;
    private List<KitchenMenuItemResponse> menuItems;

    public KitchenMenuSectionResponse() {
    }

    public KitchenMenuSectionResponse(
            Long id,
            String name,
            String description,
            Boolean active,
            List<KitchenMenuItemResponse> menuItems
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.active = active;
        this.menuItems = menuItems;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<KitchenMenuItemResponse> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<KitchenMenuItemResponse> menuItems) {
        this.menuItems = menuItems;
    }
}