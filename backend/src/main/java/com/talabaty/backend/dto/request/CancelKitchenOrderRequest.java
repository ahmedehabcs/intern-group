package com.talabaty.backend.dto.request;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Getter
@Setter
public class CancelKitchenOrderRequest {

    @NotBlank(message = "Cancellation reason is required")
    @Size(max = 255, message = "Cancellation reason must not exceed 255 characters")
    private String reason;

    public CancelKitchenOrderRequest() {
    }


}
