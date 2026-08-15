package com.talabaty.backend.controller;

import com.talabaty.backend.dto.response.OrderSummaryresponse;
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
@PreAuthorize("hasRole('DELIVERY')")
@SecurityRequirement(name = "bearerAuth")
public class DeliveryController {

    private final DeliveryOrderService deliveryOrderService;

    public DeliveryController(DeliveryOrderService deliveryOrderService) {
        this.deliveryOrderService = deliveryOrderService;
    }

    @GetMapping("/available")
    public ResponseEntity<List<OrderSummaryresponse>> getAvailableOrders() {
        return ResponseEntity.ok(deliveryOrderService.getAvailableOrders());
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
}