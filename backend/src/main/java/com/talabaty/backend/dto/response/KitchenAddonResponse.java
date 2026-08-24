package com.talabaty.backend.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KitchenAddonResponse {

    private Long id;
    private String name;
    private Double additionalPrice;
    private Boolean available;

    public KitchenAddonResponse() {
    }

    public KitchenAddonResponse(
            Long id,
            String name,
            Double additionalPrice,
            Boolean available
    ) {
        this.id = id;
        this.name = name;
        this.additionalPrice = additionalPrice;
        this.available = available;
    }








}
