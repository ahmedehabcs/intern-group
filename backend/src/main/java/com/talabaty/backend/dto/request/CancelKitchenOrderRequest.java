package com.talabaty.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CancelKitchenOrderRequest {

    @NotBlank(message = "Cancellation reason is required")
    @Size(max = 255, message = "Cancellation reason must not exceed 255 characters")
    private String reason;

    public CancelKitchenOrderRequest() {
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
