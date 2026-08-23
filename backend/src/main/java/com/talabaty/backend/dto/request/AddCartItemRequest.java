package com.talabaty.backend.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddCartItemRequest {

    @NotNull(message = "Menu item ID is required")
    @Positive(message = "Menu item ID must be positive")
    private Long menuItemId;

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
}