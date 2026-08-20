package com.talabaty.backend.controller;

import com.talabaty.backend.dto.request.UpdateMenuItemAvailabilityRequest;
import com.talabaty.backend.dto.response.KitchenMenuItemResponse;
import com.talabaty.backend.service.KitchenMenuItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/kitchen/menu-items")
@Tag(name = "Kitchen Menu Items", description = "Manage restaurant menu item availability")
@SecurityRequirement(name = "bearerAuth")
public class KitchenMenuItemController {

    private final KitchenMenuItemService kitchenMenuItemService;

    public KitchenMenuItemController(KitchenMenuItemService kitchenMenuItemService) {
        this.kitchenMenuItemService = kitchenMenuItemService;
    }

    @Operation(summary = "Get menu items for the kitchen manager's restaurant")
    @GetMapping
    public ResponseEntity<List<KitchenMenuItemResponse>> getMenuItems(
            Authentication authentication
    ) {
        Long userId = Long.valueOf(authentication.getName());

        return ResponseEntity.ok(kitchenMenuItemService.getMenuItems(userId));
    }

    @Operation(summary = "Update a menu item's availability")
    @PatchMapping("/{menuItemId}/availability")
    public ResponseEntity<KitchenMenuItemResponse> updateAvailability(
            Authentication authentication,
            @PathVariable Long menuItemId,
            @Valid @RequestBody UpdateMenuItemAvailabilityRequest request
    ) {
        Long userId = Long.valueOf(authentication.getName());

        return ResponseEntity.ok(
                kitchenMenuItemService.updateAvailability(
                        userId,
                        menuItemId,
                        request.getAvailable()
                )
        );
    }
}
