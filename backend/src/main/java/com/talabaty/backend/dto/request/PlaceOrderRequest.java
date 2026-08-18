package com.talabaty.backend.dto.request;

import com.talabaty.backend.model.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

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

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
