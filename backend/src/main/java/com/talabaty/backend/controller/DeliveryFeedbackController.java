package com.talabaty.backend.controller;

import com.talabaty.backend.dto.request.CreateDeliveryFeedbackRequest;
import com.talabaty.backend.dto.response.DeliveryFeedbackResponse;
import com.talabaty.backend.service.DeliveryFeedbackService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/delivery-feedback")
@SecurityRequirement(name = "bearerAuth")
public class DeliveryFeedbackController {

    private final DeliveryFeedbackService feedbackService;

    public DeliveryFeedbackController(DeliveryFeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping("/orders/{orderId}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<DeliveryFeedbackResponse> createFeedback(
            @PathVariable Long orderId,
            @Valid @RequestBody CreateDeliveryFeedbackRequest request,
            Authentication authentication
    ) {
        Long customerId = Long.parseLong(authentication.getName());

        DeliveryFeedbackResponse response =
                feedbackService.createFeedback(orderId, customerId, request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('DRIVER')")
    public ResponseEntity<List<DeliveryFeedbackResponse>> getMyFeedback(
            Authentication authentication
    ) {
        Long riderId = Long.parseLong(authentication.getName());

        return ResponseEntity.ok(feedbackService.getMyFeedback(riderId));
    }

    @GetMapping("/admin/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<DeliveryFeedbackResponse>> getAllFeedback() {
        return ResponseEntity.ok(feedbackService.getAllFeedback());
    }
}