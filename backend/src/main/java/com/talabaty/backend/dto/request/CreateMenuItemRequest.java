package com.talabaty.backend.dto.request;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Getter
@Setter
public class CreateMenuItemRequest {

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must not exceed 100 characters")
    private String name;

    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;

    @NotNull(message = "Base price is required")
    @Min(value = 0, message = "Base price must be non-negative")
    @Max(value = 999999, message = "Base price must not exceed 999999")
    private Double basePrice;

    @Size(max = 255, message = "Image URL must not exceed 255 characters")
    private String imageUrl;

    @NotNull(message = "Menu section ID is required")
    private Long menuSectionId;

    private Boolean available = true;

    private Long[] addonGroupIds;

    public CreateMenuItemRequest() {
    }














}
