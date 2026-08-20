package com.talabaty.backend.service;

import com.talabaty.backend.dto.request.CreateDeliveryFeedbackRequest;
import com.talabaty.backend.dto.response.DeliveryFeedbackResponse;

import java.util.List;

public interface DeliveryFeedbackService {

    DeliveryFeedbackResponse createFeedback(
            Long orderId,
            Long customerId,
            CreateDeliveryFeedbackRequest request
    );

    List<DeliveryFeedbackResponse> getMyFeedback(Long riderId);
}