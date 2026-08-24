package com.talabaty.backend.service;

import com.talabaty.backend.dto.request.CreateReviewRequest;
import com.talabaty.backend.dto.response.ReviewResponse;

import java.util.List;

public interface ReviewService {

    ReviewResponse createReview(
            Long orderId,
            Long customerId,
            CreateReviewRequest request
    );

    List<ReviewResponse> getRestaurantReviews(Long restaurantId);

    List<ReviewResponse> getMyReviews(Long customerId);
}