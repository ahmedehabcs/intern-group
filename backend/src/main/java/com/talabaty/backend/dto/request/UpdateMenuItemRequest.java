package com.talabaty.backend.dto.request;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

@Getter
@Setter
public class UpdateMenuItemRequest {

    @Size(max = 100, message = "Name must not exceed 100 characters")
    private String name;

    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;

    @Min(value = 0, message = "Base price must be non-negative")
    @Max(value = 999999, message = "Base price must not exceed 999999")
    private Double basePrice;

    @Size(max = 255, message = "Image URL must not exceed 255 characters")
    private String imageUrl;

    private Long menuSectionId;

    private Boolean available;

    private Long[] addonGroupIds;

    public UpdateMenuItemRequest() {
    }














}
