package com.talabaty.backend.controller;

import lombok.RequiredArgsConstructor;

import com.talabaty.backend.dto.response.OrderAdminResponse;
import com.talabaty.backend.model.OrderStatus;
import com.talabaty.backend.service.AdminOrderService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/admin/orders")
@PreAuthorize("hasRole('ADMIN')")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class AdminOrderController {

    private final AdminOrderService adminOrderService;


    @GetMapping
    public ResponseEntity<List<OrderAdminResponse>> getOrders(
            @RequestParam(required = false) OrderStatus status,
            @RequestParam(required = false) Long restaurantId,
            @RequestParam(required = false) LocalDate from,
            @RequestParam(required = false) LocalDate to
    ) {
        return ResponseEntity.ok(adminOrderService.getOrders(status, restaurantId, from, to));
    }

    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<OrderAdminResponse> cancelOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(adminOrderService.cancelOrder(orderId));
    }
}
