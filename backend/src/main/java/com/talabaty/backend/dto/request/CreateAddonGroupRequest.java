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
public class CreateAddonGroupRequest {

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must not exceed 100 characters")
    private String name;

    @NotNull(message = "Minimum selections is required")
    @Min(value = 0, message = "Minimum selections must be at least 0")
    private Integer minSelections = 0;

    @NotNull(message = "Maximum selections is required")
    @Min(value = 1, message = "Maximum selections must be at least 1")
    @Max(value = 20, message = "Maximum selections must not exceed 20")
    private Integer maxSelections = 1;

    public CreateAddonGroupRequest() {
    }






}
