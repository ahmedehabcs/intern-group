package com.talabaty.backend.dto.response;

import java.time.LocalDateTime;

public record ReviewResponse(
        Long orderId,
        Long restaurantId,
        String restaurantName,
        Integer rating,
        String comment,
        LocalDateTime createdAt
) {
}