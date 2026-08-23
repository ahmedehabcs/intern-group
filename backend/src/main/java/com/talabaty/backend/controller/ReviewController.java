package com.talabaty.backend.controller;

import com.talabaty.backend.dto.request.CreateReviewRequest;
import com.talabaty.backend.dto.response.ReviewResponse;
import com.talabaty.backend.service.ReviewService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/orders/{orderId}")
    @PreAuthorize("hasRole('CUSTOMER')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ReviewResponse> createReview(
            @PathVariable Long orderId,
            @Valid @RequestBody CreateReviewRequest request,
            Authentication authentication
    ) {
        Long customerId = Long.parseLong(authentication.getName());

        return ResponseEntity.status(HttpStatus.CREATED).body(
                reviewService.createReview(orderId, customerId, request)
        );
    }

    @GetMapping("/restaurants/{restaurantId}")
    public ResponseEntity<List<ReviewResponse>> getRestaurantReviews(
            @PathVariable Long restaurantId
    ) {
        return ResponseEntity.ok(
                reviewService.getRestaurantReviews(restaurantId)
        );
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('CUSTOMER')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<List<ReviewResponse>> getMyReviews(
            Authentication authentication
    ) {
        Long customerId = Long.parseLong(authentication.getName());

        return ResponseEntity.ok(reviewService.getMyReviews(customerId));
    }
}