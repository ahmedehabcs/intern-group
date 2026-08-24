package com.talabaty.backend.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuItemAddonResponse {

    private Long id;
    private String name;
    private Double additionalPrice;

    public MenuItemAddonResponse() {
    }

    public MenuItemAddonResponse(
            Long id,
            String name,
            Double additionalPrice
    ) {
        this.id = id;
        this.name = name;
        this.additionalPrice = additionalPrice;
    }






}
