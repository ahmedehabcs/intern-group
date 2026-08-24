package com.talabaty.backend.dto.request;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateMenuItemAvailabilityRequest {

    @NotNull(message = "Availability is required")
    private Boolean available;

    public UpdateMenuItemAvailabilityRequest() {
    }


}
