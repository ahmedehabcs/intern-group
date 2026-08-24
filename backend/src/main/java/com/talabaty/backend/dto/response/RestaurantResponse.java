package com.talabaty.backend.dto.response;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class RestaurantResponse {

    private Long id;
    private String name;
    private String description;
    private String logoUrl;
    private List<String> categories;

    public RestaurantResponse() {
    }

    public RestaurantResponse(
            Long id,
            String name,
            String description,
            String logoUrl,
            List<String> categories
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.logoUrl = logoUrl;
        this.categories = categories;
    }

}
