package com.talabaty.backend.controller;

import lombok.RequiredArgsConstructor;

import com.talabaty.backend.dto.request.PlaceOrderRequest;
import com.talabaty.backend.dto.response.CustomerOrderDetailsResponse;
import com.talabaty.backend.dto.response.PlaceOrderResponse;
import com.talabaty.backend.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.talabaty.backend.dto.response.CustomerOrderPageResponse;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.validation.annotation.Validated;
import com.talabaty.backend.dto.request.CancelOrderRequest;
@Validated
@Tag(name = "Orders", description = "Place and manage customer orders")
@RestController
@RequestMapping("/api/orders")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


    @Operation(summary = "Place an order from the authenticated customer's cart")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Order placed successfully"),
            @ApiResponse(responseCode = "400", description = "Cart or order data is invalid"),
            @ApiResponse(responseCode = "401", description = "Authentication required"),
            @ApiResponse(responseCode = "403", description = "Customer role required"),
            @ApiResponse(responseCode = "404", description = "Customer or delivery address not found")
    })
    @PostMapping
    public ResponseEntity<PlaceOrderResponse> placeOrder(
            Authentication authentication,
            @Valid @RequestBody PlaceOrderRequest request
    ) {
        Long userId = Long.valueOf(authentication.getName());
        PlaceOrderResponse response = orderService.placeOrder(userId, request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Get authenticated customer's orders")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Orders retrieved successfully"),
            @ApiResponse(responseCode = "401", description = "Authentication required"),
            @ApiResponse(responseCode = "403", description = "Customer role required")
    })

    @GetMapping
    public ResponseEntity<CustomerOrderPageResponse> getCustomerOrders(
            Authentication authentication,
            @RequestParam(defaultValue = "0")
            @Min(value = 0, message = "Page number must be zero or greater")
            int page,

            @RequestParam(defaultValue = "10")
            @Min(value = 1, message = "Page size must be at least 1")
            @Max(value = 100, message = "Page size must not exceed 100")
            int size
    ) {
        Long userId = Long.valueOf(authentication.getName());

        CustomerOrderPageResponse response =
                orderService.getCustomerOrders(userId, page, size);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get authenticated customer's order details")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Order retrieved successfully"),
            @ApiResponse(responseCode = "401", description = "Authentication required"),
            @ApiResponse(responseCode = "403", description = "Customer role required"),
            @ApiResponse(responseCode = "404", description = "Order not found")
    })
    @GetMapping("/{orderId}")
    public ResponseEntity<CustomerOrderDetailsResponse> getCustomerOrderDetails(Authentication authentication, @PathVariable Long orderId) {
        Long userId = Long.valueOf(authentication.getName());

        CustomerOrderDetailsResponse response =
                orderService.getCustomerOrderDetails(userId, orderId);

        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Cancel a pending customer order",
            description = "Allows the authenticated customer to cancel an order only while it is pending."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Order cancelled successfully"),
            @ApiResponse(responseCode = "400", description = "Cancellation request is invalid"),
            @ApiResponse(responseCode = "401", description = "Authentication required"),
            @ApiResponse(responseCode = "403", description = "Customer role required"),
            @ApiResponse(responseCode = "404", description = "Order not found"),
            @ApiResponse(responseCode = "409", description = "Order can no longer be cancelled")
    })
    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<CustomerOrderDetailsResponse> cancelCustomerOrder(Authentication authentication, @PathVariable Long orderId, @Valid @RequestBody CancelOrderRequest request) {
        Long userId = Long.valueOf(authentication.getName());
        CustomerOrderDetailsResponse response = orderService.cancelCustomerOrder(userId, orderId, request.getReason());
        return ResponseEntity.ok(response);
    }
}
