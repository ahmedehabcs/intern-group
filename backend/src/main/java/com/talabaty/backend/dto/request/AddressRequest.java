package com.talabaty.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getGovernorateId() {
        return governorateId;
    }

    public void setGovernorateId(Long governorateId) {
        this.governorateId = governorateId;
    }
}
