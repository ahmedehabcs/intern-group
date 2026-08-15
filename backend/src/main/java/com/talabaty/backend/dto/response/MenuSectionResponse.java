package com.talabaty.backend.dto.response;

import java.util.List;

public class MenuSectionResponse {

    private Long id;
    private String name;
    private String description;
    private List<MenuItemResponse> menuItems;

    public MenuSectionResponse() {
    }

    public MenuSectionResponse(
            Long id,
            String name,
            String description,
            List<MenuItemResponse> menuItems
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
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
    public List<MenuItemResponse> getMenuItems() {
        return menuItems;
    }
    public void setMenuItems(List<MenuItemResponse> menuItems) {
        this.menuItems = menuItems;
    }
}