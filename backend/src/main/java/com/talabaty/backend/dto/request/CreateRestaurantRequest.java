package com.talabaty.backend.dto.request;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class CreateRestaurantRequest {

    @NotBlank(message = "Restaurant name is required")
    private String name;

    @NotBlank(message = "Phone is required")
    private String phone;

    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Address is required")
    private String address;

    @NotNull(message = "Governorate is required")
    private Long governorateId;

    private String description;
    private String logoUrl;

    @NotNull(message = "Delivery fee is required")
    private BigDecimal deliveryFee;

    private List<Long> categoryIds;

}
