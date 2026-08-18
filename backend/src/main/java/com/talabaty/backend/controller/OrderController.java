package com.talabaty.backend.controller;

import com.talabaty.backend.dto.request.PlaceOrderRequest;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Orders", description = "Place and manage customer orders")
@RestController
@RequestMapping("/api/orders")
@SecurityRequirement(name = "bearerAuth")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

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
}
