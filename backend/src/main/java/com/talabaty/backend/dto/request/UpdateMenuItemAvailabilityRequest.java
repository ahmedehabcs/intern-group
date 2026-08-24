package com.talabaty.backend.dto.request;

import jakarta.validation.constraints.NotNull;

public class UpdateMenuItemAvailabilityRequest {

    @NotNull(message = "Availability is required")
    private Boolean available;

    public UpdateMenuItemAvailabilityRequest() {
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
