package com.talabaty.backend.controller;

import com.talabaty.backend.dto.request.AssignKitchenManagerRequest;
import com.talabaty.backend.dto.request.CreateRestaurantRequest;
import com.talabaty.backend.dto.request.UpdateRestaurantRequest;
import com.talabaty.backend.dto.response.RestaurantAdminResponse;
import com.talabaty.backend.service.AdminRestaurantService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/restaurants")
@PreAuthorize("hasRole('ADMIN')")
@SecurityRequirement(name = "bearerAuth")
public class AdminRestaurantController {

    private final AdminRestaurantService adminRestaurantService;

    public AdminRestaurantController(AdminRestaurantService adminRestaurantService) {
        this.adminRestaurantService = adminRestaurantService;
    }

    @PostMapping("/add")
    public ResponseEntity<RestaurantAdminResponse> createRestaurant(
            @Valid @RequestBody CreateRestaurantRequest request
    ) {
        return ResponseEntity.ok(adminRestaurantService.createRestaurant(request));
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<RestaurantAdminResponse> updateRestaurant(
            @PathVariable Long id,
            @RequestBody UpdateRestaurantRequest request
    ) {
        return ResponseEntity.ok(adminRestaurantService.updateRestaurant(id, request));
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<RestaurantAdminResponse> deactivateRestaurant(@PathVariable Long id) {
        return ResponseEntity.ok(adminRestaurantService.deactivateRestaurant(id));
    }

    @PostMapping("/{restaurantId}/kitchen-manager")
    public ResponseEntity<Void> assignKitchenManager(
            @PathVariable Long restaurantId,
            @Valid @RequestBody AssignKitchenManagerRequest request
    ) {
        adminRestaurantService.assignKitchenManager(restaurantId, request);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<RestaurantAdminResponse>> searchRestaurants(
            @RequestParam(required = false) String search
    ) {
        return ResponseEntity.ok(adminRestaurantService.searchRestaurants(search));
    }
}