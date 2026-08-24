package com.talabaty.backend.dto.request;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Getter
@Setter
public class UpdateCartItemQuantityRequest {

    @NotNull(message = "Item quantity is required")
    @Positive(message = "Item quantity must be greater than zero")
    private Integer quantity;

    public UpdateCartItemQuantityRequest() {
    }

    public UpdateCartItemQuantityRequest(Integer quantity) {
        this.quantity = quantity;
    }


}
