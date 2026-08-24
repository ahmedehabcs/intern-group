package com.talabaty.backend.controller;

import lombok.RequiredArgsConstructor;

import com.talabaty.backend.dto.response.MenuItemDetailsResponse;
import com.talabaty.backend.service.MenuItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "Menu Items",
        description = "Browse menu item details and available add-ons"
)
@RestController
@RequestMapping("/api/menu-items")
@RequiredArgsConstructor
public class MenuItemController {

    private final MenuItemService menuItemService;


    @Operation(
            summary = "Get menu item details",
            description = "Get one available menu item with its add-on groups and available add-ons."
    )
    @GetMapping("/{menuItemId}")
    public ResponseEntity<MenuItemDetailsResponse> getMenuItemDetails(
            @PathVariable Long menuItemId
    ) {
        return ResponseEntity.ok(
                menuItemService.getMenuItemDetails(menuItemId)
        );
    }
}
