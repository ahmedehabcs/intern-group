package com.talabaty.backend.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
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

}
