package com.talabaty.backend.dto.request;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

@Getter
@Setter
public class UpdateAddonRequest {

    @Size(max = 100, message = "Name must not exceed 100 characters")
    private String name;

    @Min(value = 0, message = "Additional price must be non-negative")
    @Max(value = 999999, message = "Additional price must not exceed 999999")
    private Double additionalPrice;

    private Boolean available;

    public UpdateAddonRequest() {
    }






}
