package com.talabaty.backend.dto.request;

import lombok.Getter;
import lombok.Setter;

import com.talabaty.backend.model.OrderStatus;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateKitchenOrderStatusRequest {

    @NotNull(message = "Order status is required")
    private OrderStatus status;

    public UpdateKitchenOrderStatusRequest() {
    }


}
