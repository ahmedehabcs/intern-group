package com.talabaty.backend.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
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










}
