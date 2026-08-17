package com.talabaty.backend.controller;

import com.talabaty.backend.dto.request.AddCartItemRequest;
import com.talabaty.backend.dto.request.ReplaceCartItemRequest;
import com.talabaty.backend.dto.request.UpdateCartItemQuantityRequest;
import com.talabaty.backend.dto.response.CartResponse;
import com.talabaty.backend.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "Cart",
        description = "Manage the authenticated customer's cart"
)
@RestController
@RequestMapping("/api/cart")
@SecurityRequirement(name = "bearerAuth")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<CartResponse> getCart(
            Authentication authentication
    ) {
        Long userId = getAuthenticatedUserId(authentication);

        return ResponseEntity.ok(
                cartService.getCart(userId)
        );
    }
    @Operation(summary = "Add an item to the cart")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Item added successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid item or add-on selection"),
            @ApiResponse(responseCode = "401", description = "Authentication required"),
            @ApiResponse(responseCode = "403", description = "Customer role required"),
            @ApiResponse(responseCode = "404", description = "Menu item not found"),
            @ApiResponse(responseCode = "409", description = "Items from different restaurants are not allowed")
    })

    @PostMapping("/items")
    public ResponseEntity<CartResponse> addItem(
            Authentication authentication,
            @Valid @RequestBody AddCartItemRequest request
    ) {
        Long userId = getAuthenticatedUserId(authentication);

        CartResponse response = cartService.addItem(
                userId,
                request
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PatchMapping("/items/{cartItemId}/quantity")
    public ResponseEntity<CartResponse> updateItemQuantity(
            Authentication authentication,
            @PathVariable Long cartItemId,
            @Valid @RequestBody
            UpdateCartItemQuantityRequest request
    ) {
        Long userId = getAuthenticatedUserId(authentication);

        return ResponseEntity.ok(
                cartService.updateItemQuantity(
                        userId,
                        cartItemId,
                        request
                )
        );
    }

    @PutMapping("/items/{cartItemId}")
    public ResponseEntity<CartResponse> replaceItemConfiguration(
            Authentication authentication,
            @PathVariable Long cartItemId,
            @Valid @RequestBody ReplaceCartItemRequest request
    ) {
        Long userId = getAuthenticatedUserId(authentication);

        return ResponseEntity.ok(
                cartService.replaceItemConfiguration(
                        userId,
                        cartItemId,
                        request
                )
        );
    }

    @DeleteMapping("/items/{cartItemId}")
    public ResponseEntity<CartResponse> removeItem(
            Authentication authentication,
            @PathVariable Long cartItemId
    ) {
        Long userId = getAuthenticatedUserId(authentication);

        return ResponseEntity.ok(
                cartService.removeItem(
                        userId,
                        cartItemId
                )
        );
    }

    @DeleteMapping("/items")
    public ResponseEntity<Void> clearCart(
            Authentication authentication
    ) {
        Long userId = getAuthenticatedUserId(authentication);

        cartService.clearCart(userId);

        return ResponseEntity.noContent().build();
    }

    private Long getAuthenticatedUserId(
            Authentication authentication
    ) {
        return Long.valueOf(authentication.getName());
    }
}