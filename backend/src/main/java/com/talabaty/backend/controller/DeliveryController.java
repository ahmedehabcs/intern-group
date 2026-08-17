package com.talabaty.backend.controller;

import com.talabaty.backend.dto.request.CancelOrderRequest;
import com.talabaty.backend.dto.response.OrderHistoryResponse;
import com.talabaty.backend.dto.response.OrderSummaryresponse;
import com.talabaty.backend.service.DeliveryOrderService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/delivery/orders")
@PreAuthorize("hasRole('DRIVER')")
@SecurityRequirement(name = "bearerAuth")
public class DeliveryController {

    private final DeliveryOrderService deliveryOrderService;

    public DeliveryController(DeliveryOrderService deliveryOrderService) {
        this.deliveryOrderService = deliveryOrderService;
    }

    @GetMapping("/available")
    public ResponseEntity<List<OrderSummaryresponse>> getAvailableOrders(Authentication authentication) {
        Long riderId = Long.parseLong(authentication.getName());
        return ResponseEntity.ok(deliveryOrderService.getAvailableOrders(riderId));
    }

    @PutMapping("/{orderId}/accept")
    public ResponseEntity<OrderSummaryresponse> acceptOrder(
            @PathVariable Long orderId,
            Authentication authentication
    ) {
        Long riderId = Long.parseLong(authentication.getName());
        OrderSummaryresponse result = deliveryOrderService.acceptOrder(orderId, riderId);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{orderId}/pickup")
    public ResponseEntity<OrderSummaryresponse> markPickedUp(
            @PathVariable Long orderId,
            Authentication authentication
    ) {
        Long riderId = Long.parseLong(authentication.getName());
        OrderSummaryresponse result = deliveryOrderService.markPickedUp(orderId, riderId);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{orderId}/deliver")
    public ResponseEntity<OrderSummaryresponse> markDelivered(
            @PathVariable Long orderId,
            Authentication authentication
    ) {
        Long riderId = Long.parseLong(authentication.getName());
        OrderSummaryresponse result = deliveryOrderService.markDelivered(orderId, riderId);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<Void> cancelOrder(
            @PathVariable Long orderId,
            @RequestBody CancelOrderRequest request,
            Authentication authentication
    ) {
        Long riderId = Long.parseLong(authentication.getName());
        deliveryOrderService.cancelOrder(orderId, riderId, request.getReason());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/active")
    public ResponseEntity<OrderSummaryresponse> getActiveOrder(Authentication authentication) {
        Long riderId = Long.parseLong(authentication.getName());
        return deliveryOrderService.getActiveOrder(riderId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @GetMapping("/history")
    public ResponseEntity<List<OrderHistoryResponse>> getDeliveryHistory(Authentication authentication) {
        Long riderId = Long.parseLong(authentication.getName());
        return ResponseEntity.ok(deliveryOrderService.getDeliveryHistory(riderId));
    }
}