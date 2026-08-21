package com.talabaty.backend.controller;

import com.talabaty.backend.dto.request.CreateAddonGroupRequest;
import com.talabaty.backend.dto.request.CreateAddonRequest;
import com.talabaty.backend.dto.request.CreateMenuItemRequest;
import com.talabaty.backend.dto.request.CreateMenuSectionRequest;
import com.talabaty.backend.dto.request.UpdateAddonGroupRequest;
import com.talabaty.backend.dto.request.UpdateAddonRequest;
import com.talabaty.backend.dto.request.UpdateMenuItemAvailabilityRequest;
import com.talabaty.backend.dto.request.UpdateMenuItemRequest;
import com.talabaty.backend.dto.request.UpdateMenuSectionRequest;
import com.talabaty.backend.dto.response.KitchenAddonGroupResponse;
import com.talabaty.backend.dto.response.KitchenAddonResponse;
import com.talabaty.backend.dto.response.KitchenMenuItemResponse;
import com.talabaty.backend.dto.response.KitchenMenuSectionResponse;
import com.talabaty.backend.service.KitchenMenuItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/kitchen/menu-items")
@Tag(name = "Kitchen Menu Items", description = "Manage restaurant menu items, sections, addon groups, and addons")
@SecurityRequirement(name = "bearerAuth")
public class KitchenMenuItemController {

    private final KitchenMenuItemService kitchenMenuItemService;

    public KitchenMenuItemController(KitchenMenuItemService kitchenMenuItemService) {
        this.kitchenMenuItemService = kitchenMenuItemService;
    }

    // --- Menu Items ---
    @Operation(summary = "Get menu items for the kitchen manager's restaurant")
    @GetMapping
    public ResponseEntity<List<KitchenMenuItemResponse>> getMenuItems(
            Authentication authentication
    ) {
        Long userId = Long.valueOf(authentication.getName());
        return ResponseEntity.ok(kitchenMenuItemService.getMenuItems(userId));
    }

    @Operation(summary = "Create a new menu item")
    @PostMapping
    public ResponseEntity<KitchenMenuItemResponse> createMenuItem(
            Authentication authentication,
            @Valid @RequestBody CreateMenuItemRequest request
    ) {
        Long userId = Long.valueOf(authentication.getName());
        return ResponseEntity.ok(kitchenMenuItemService.createMenuItem(userId, request));
    }

    @Operation(summary = "Update a menu item")
    @PatchMapping("/{menuItemId}")
    public ResponseEntity<KitchenMenuItemResponse> updateMenuItem(
            Authentication authentication,
            @PathVariable Long menuItemId,
            @Valid @RequestBody UpdateMenuItemRequest request
    ) {
        Long userId = Long.valueOf(authentication.getName());
        return ResponseEntity.ok(
                kitchenMenuItemService.updateMenuItem(userId, menuItemId, request)
        );
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

    @Operation(summary = "Delete a menu item")
    @DeleteMapping("/{menuItemId}")
    public ResponseEntity<Void> deleteMenuItem(
            Authentication authentication,
            @PathVariable Long menuItemId
    ) {
        Long userId = Long.valueOf(authentication.getName());
        kitchenMenuItemService.deleteMenuItem(userId, menuItemId);
        return ResponseEntity.noContent().build();
    }

    // --- Menu Sections ---
    @Operation(summary = "Get menu sections with items for the kitchen manager's restaurant")
    @GetMapping("/sections")
    public ResponseEntity<List<KitchenMenuSectionResponse>> getMenuSections(
            Authentication authentication
    ) {
        Long userId = Long.valueOf(authentication.getName());
        return ResponseEntity.ok(kitchenMenuItemService.getMenuSections(userId));
    }

    @Operation(summary = "Create a new menu section")
    @PostMapping("/sections")
    public ResponseEntity<KitchenMenuSectionResponse> createMenuSection(
            Authentication authentication,
            @Valid @RequestBody CreateMenuSectionRequest request
    ) {
        Long userId = Long.valueOf(authentication.getName());
        return ResponseEntity.ok(kitchenMenuItemService.createMenuSection(userId, request));
    }

    @Operation(summary = "Update a menu section")
    @PatchMapping("/sections/{menuSectionId}")
    public ResponseEntity<KitchenMenuSectionResponse> updateMenuSection(
            Authentication authentication,
            @PathVariable Long menuSectionId,
            @Valid @RequestBody UpdateMenuSectionRequest request
    ) {
        Long userId = Long.valueOf(authentication.getName());
        return ResponseEntity.ok(
                kitchenMenuItemService.updateMenuSection(userId, menuSectionId, request)
        );
    }

    // --- Addon Groups ---
    @Operation(summary = "Get all addon groups for the kitchen manager's restaurant")
    @GetMapping("/addon-groups")
    public ResponseEntity<List<KitchenAddonGroupResponse>> getAddonGroups(
            Authentication authentication
    ) {
        Long userId = Long.valueOf(authentication.getName());
        return ResponseEntity.ok(kitchenMenuItemService.getAddonGroups(userId));
    }

    @Operation(summary = "Create a new addon group")
    @PostMapping("/addon-groups")
    public ResponseEntity<KitchenAddonGroupResponse> createAddonGroup(
            Authentication authentication,
            @Valid @RequestBody CreateAddonGroupRequest request
    ) {
        Long userId = Long.valueOf(authentication.getName());
        return ResponseEntity.ok(kitchenMenuItemService.createAddonGroup(userId, request));
    }

    @Operation(summary = "Update an addon group")
    @PatchMapping("/addon-groups/{addonGroupId}")
    public ResponseEntity<KitchenAddonGroupResponse> updateAddonGroup(
            Authentication authentication,
            @PathVariable Long addonGroupId,
            @Valid @RequestBody UpdateAddonGroupRequest request
    ) {
        Long userId = Long.valueOf(authentication.getName());
        return ResponseEntity.ok(
                kitchenMenuItemService.updateAddonGroup(userId, addonGroupId, request)
        );
    }

    @Operation(summary = "Delete an addon group")
    @DeleteMapping("/addon-groups/{addonGroupId}")
    public ResponseEntity<Void> deleteAddonGroup(
            Authentication authentication,
            @PathVariable Long addonGroupId
    ) {
        Long userId = Long.valueOf(authentication.getName());
        kitchenMenuItemService.deleteAddonGroup(userId, addonGroupId);
        return ResponseEntity.noContent().build();
    }

    // --- Addons ---
    @Operation(summary = "Get addons for a specific addon group")
    @GetMapping("/addon-groups/{addonGroupId}/addons")
    public ResponseEntity<List<KitchenAddonResponse>> getAddonsByGroup(
            Authentication authentication,
            @PathVariable Long addonGroupId
    ) {
        Long userId = Long.valueOf(authentication.getName());
        return ResponseEntity.ok(kitchenMenuItemService.getAddonsByGroup(userId, addonGroupId));
    }

    @Operation(summary = "Create a new addon in an addon group")
    @PostMapping("/addon-groups/{addonGroupId}/addons")
    public ResponseEntity<KitchenAddonResponse> createAddon(
            Authentication authentication,
            @PathVariable Long addonGroupId,
            @Valid @RequestBody CreateAddonRequest request
    ) {
        Long userId = Long.valueOf(authentication.getName());
        return ResponseEntity.ok(kitchenMenuItemService.createAddon(userId, addonGroupId, request));
    }

    @Operation(summary = "Update an addon")
    @PatchMapping("/addons/{addonId}")
    public ResponseEntity<KitchenAddonResponse> updateAddon(
            Authentication authentication,
            @PathVariable Long addonId,
            @Valid @RequestBody UpdateAddonRequest request
    ) {
        Long userId = Long.valueOf(authentication.getName());
        return ResponseEntity.ok(kitchenMenuItemService.updateAddon(userId, addonId, request));
    }

    @Operation(summary = "Delete an addon")
    @DeleteMapping("/addons/{addonId}")
    public ResponseEntity<Void> deleteAddon(
            Authentication authentication,
            @PathVariable Long addonId
    ) {
        Long userId = Long.valueOf(authentication.getName());
        kitchenMenuItemService.deleteAddon(userId, addonId);
        return ResponseEntity.noContent().build();
    }
}
