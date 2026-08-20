package com.talabaty.backend.dto.response;

import java.time.LocalDateTime;

public record DeliveryFeedbackResponse(
        Long orderId,
        Integer rating,
        String comment,
        LocalDateTime createdAt
) {
}