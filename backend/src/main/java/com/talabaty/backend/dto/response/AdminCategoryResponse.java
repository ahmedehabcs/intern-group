package com.talabaty.backend.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public class AdminCategoryResponse {

    @Schema(description = "Category ID", example = "12")
    private Long id;

    @Schema(description = "Cuisine/category name", example = "Chinese")
    private String name;

    @Schema(
            description = "Cuisine/category description",
            example = "Traditional Chinese cuisine"
    )
    private String description;

    @Schema(
            description = "Whether the category is active",
            example = "true"
    )
    private Boolean isActive;

    public AdminCategoryResponse() {
    }

    public AdminCategoryResponse(
            Long id,
            String name,
            String description,
            Boolean isActive
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isActive = isActive;
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

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
}