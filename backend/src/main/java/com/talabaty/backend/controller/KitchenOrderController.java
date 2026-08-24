package com.talabaty.backend.controller;

import lombok.RequiredArgsConstructor;

import com.talabaty.backend.dto.request.CancelKitchenOrderRequest;
import com.talabaty.backend.dto.request.UpdateKitchenOrderStatusRequest;
import com.talabaty.backend.dto.response.KitchenOrderDetailsResponse;
import com.talabaty.backend.dto.response.KitchenOrderPageResponse;
import com.talabaty.backend.dto.response.KitchenOrderSummaryResponse;
import com.talabaty.backend.model.OrderStatus;
import com.talabaty.backend.service.KitchenOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/kitchen/orders")
@Tag(name = "Kitchen Orders", description = "Manage restaurant kitchen orders")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class KitchenOrderController {

    private final KitchenOrderService kitchenOrderService;


    @Operation(summary = "Get active orders for the kitchen manager's restaurant")
    @GetMapping
    public ResponseEntity<List<KitchenOrderSummaryResponse>> getActiveOrders(
            Authentication authentication
    ) {
        Long userId = Long.valueOf(authentication.getName());

        return ResponseEntity.ok(kitchenOrderService.getActiveOrders(userId));
    }

    @Operation(summary = "Get paginated order history for the kitchen manager's restaurant")
    @GetMapping("/history")
    public ResponseEntity<KitchenOrderPageResponse> getOrderHistory(
            Authentication authentication,
            @RequestParam(required = false) OrderStatus status,
            @RequestParam(required = false) LocalDate from,
            @RequestParam(required = false) LocalDate to,
            @RequestParam(defaultValue = "0")
            @Min(value = 0, message = "Page number must be zero or greater")
            int page,
            @RequestParam(defaultValue = "10")
            @Min(value = 1, message = "Page size must be at least 1")
            @Max(value = 100, message = "Page size must not exceed 100")
            int size,
            @RequestParam(defaultValue = "desc") String direction
    ) {
        Long userId = Long.valueOf(authentication.getName());

        return ResponseEntity.ok(
                kitchenOrderService.getOrderHistory(
                        userId,
                        status,
                        from,
                        to,
                        page,
                        size,
                        direction
                )
        );
    }

    @Operation(summary = "Get an order belonging to the kitchen manager's restaurant")
    @GetMapping("/{orderId}")
    public ResponseEntity<KitchenOrderDetailsResponse> getOrderDetails(
            Authentication authentication,
            @PathVariable Long orderId
    ) {
        Long userId = Long.valueOf(authentication.getName());

        return ResponseEntity.ok(
                kitchenOrderService.getOrderDetails(userId, orderId)
        );
    }

    @Operation(summary = "Update the status of a kitchen order")
    @PatchMapping("/{orderId}/status")
    public ResponseEntity<KitchenOrderDetailsResponse> updateOrderStatus(
            Authentication authentication,
            @PathVariable Long orderId,
            @Valid @RequestBody UpdateKitchenOrderStatusRequest request
    ) {
        Long userId = Long.valueOf(authentication.getName());

        return ResponseEntity.ok(
                kitchenOrderService.updateOrderStatus(
                        userId,
                        orderId,
                        request.getStatus()
                )
        );
    }

    @Operation(summary = "Cancel a pending or confirmed kitchen order")
    @PatchMapping("/{orderId}/cancel")
    public ResponseEntity<KitchenOrderDetailsResponse> cancelOrder(
            Authentication authentication,
            @PathVariable Long orderId,
            @Valid @RequestBody CancelKitchenOrderRequest request
    ) {
        Long userId = Long.valueOf(authentication.getName());

        return ResponseEntity.ok(
                kitchenOrderService.cancelOrder(
                        userId,
                        orderId,
                        request.getReason()
                )
        );
    }
}
