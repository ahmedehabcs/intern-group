package com.talabaty.backend.dto.request;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Getter
@Setter
public class AddressRequest {

    @NotBlank(message = "Street is required")
    @Size(max = 255, message = "Street must not exceed 255 characters")
    private String street;

    @Size(max = 255, message = "Building must not exceed 255 characters")
    private String building;

    @Size(max = 255, message = "Floor must not exceed 255 characters")
    private String floor;

    @Size(max = 255, message = "Apartment must not exceed 255 characters")
    private String apartment;

    @NotBlank(message = "City is required")
    @Size(max = 255, message = "City must not exceed 255 characters")
    private String city;

    @NotNull(message = "Governorate ID is required")
    @Positive(message = "Governorate ID must be positive")
    private Long governorateId;












}
