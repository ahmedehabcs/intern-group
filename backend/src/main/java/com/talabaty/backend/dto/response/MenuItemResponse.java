package com.talabaty.backend.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuItemResponse {

    private Long id;
    private String name;
    private String description;
    private Double basePrice;
    private String imageUrl;

    public MenuItemResponse() {
    }

    public MenuItemResponse(
            Long id,
            String name,
            String description,
            Double basePrice,
            String imageUrl
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.basePrice = basePrice;
        this.imageUrl = imageUrl;
    }










}
