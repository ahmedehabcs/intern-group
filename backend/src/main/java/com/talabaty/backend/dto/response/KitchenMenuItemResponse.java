package com.talabaty.backend.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KitchenMenuItemResponse {

    private Long id;
    private String name;
    private Double basePrice;
    private Boolean available;
    private Long menuSectionId;
    private String menuSectionName;

    public KitchenMenuItemResponse() {
    }












}
