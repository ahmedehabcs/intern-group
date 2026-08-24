package com.talabaty.backend.dto.request;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.List;

@Getter
@Setter
public class ReplaceCartItemRequest {

    @NotNull(message = "Item quantity is required")
    @Positive(message = "Item quantity must be greater than zero")
    private Integer quantity;

    @Size(
            max = 500,
            message = "Special instructions must not exceed 500 characters"
    )
    private String specialInstructions;

    @NotNull(message = "Add-ons list is required")
    @Valid
    private List<
            @NotNull(message = "Add-on entry must not be null")
            @Valid CartItemAddonRequest
            > addons;

    public ReplaceCartItemRequest() {
    }

    public ReplaceCartItemRequest(
            Integer quantity,
            String specialInstructions,
            List<CartItemAddonRequest> addons
    ) {
        this.quantity = quantity;
        this.specialInstructions = specialInstructions;
        this.addons = addons;
    }






}
