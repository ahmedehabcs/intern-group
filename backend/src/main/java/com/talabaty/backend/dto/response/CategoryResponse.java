package com.talabaty.backend.dto.response;

import lombok.Getter;
import lombok.Setter;

import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
public class CategoryResponse {

    @Schema(description = "Category ID", example = "12")
    private Long id;

    @Schema(description = "Cuisine/category name", example = "Chinese")
    private String name;

    @Schema(
            description = "Cuisine/category description",
            example = "Traditional Chinese cuisine"
    )
    private String description;

    public CategoryResponse() {
    }

    public CategoryResponse(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }







}
