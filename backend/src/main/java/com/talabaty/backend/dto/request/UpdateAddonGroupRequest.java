package com.talabaty.backend.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public class UpdateAddonGroupRequest {

    @Size(max = 100, message = "Name must not exceed 100 characters")
    private String name;

    @Min(value = 0, message = "Minimum selections must be at least 0")
    private Integer minSelections;

    @Min(value = 1, message = "Maximum selections must be at least 1")
    @Max(value = 20, message = "Maximum selections must not exceed 20")
    private Integer maxSelections;

    public UpdateAddonGroupRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMinSelections() {
        return minSelections;
    }

    public void setMinSelections(Integer minSelections) {
        this.minSelections = minSelections;
    }

    public Integer getMaxSelections() {
        return maxSelections;
    }

    public void setMaxSelections(Integer maxSelections) {
        this.maxSelections = maxSelections;
    }
}