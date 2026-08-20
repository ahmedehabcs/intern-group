package com.talabaty.backend.dto.request;

import jakarta.validation.constraints.Size;

public class CancelOrderRequest {

    @Size(
            max = 255,
            message = "Cancellation reason must not exceed 255 characters"
    )
    private String reason;

    public CancelOrderRequest() {
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}