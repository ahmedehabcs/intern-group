package com.talabaty.backend.dto.request;

import lombok.Getter;
import lombok.Setter;

import com.talabaty.backend.model.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Getter
@Setter
public class PlaceOrderRequest {

    @NotNull(message = "Delivery address ID is required")
    @Positive(message = "Delivery address ID must be positive")
    private Long addressId;

    @NotNull(message = "Payment method is required")
    private PaymentMethod paymentMethod;

    public PlaceOrderRequest() {
    }

    public PlaceOrderRequest(Long addressId, PaymentMethod paymentMethod) {
        this.addressId = addressId;
        this.paymentMethod = paymentMethod;
    }




}
