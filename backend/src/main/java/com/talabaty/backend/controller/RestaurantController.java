package com.talabaty.backend.controller;

import com.talabaty.backend.dto.response.RestaurantDetailsResponse;
import com.talabaty.backend.dto.response.RestaurantResponse;
import com.talabaty.backend.service.RestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Restaurants",
        description = "Browse active restaurants"
)
@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Operation(
            summary = "Browse active restaurants",
            description = "Publicly browse active restaurants and optionally filter by category.",
            tags = "Restaurants"
    )
    @GetMapping
    public ResponseEntity<List<RestaurantResponse>> browseRestaurants(
            @RequestParam(required = false) Long categoryId
    ) {
        return ResponseEntity.ok(
                restaurantService.browseRestaurants(categoryId)
        );
    }
    @Operation(
            summary = "Get restaurant details",
            description = "Get one active restaurant with its active menu sections and available menu items.",
            tags = "Restaurants"
    )
    @GetMapping("/{restaurantId}")
    public ResponseEntity<RestaurantDetailsResponse> getRestaurantDetails(
            @PathVariable Long restaurantId
    ) {
        return ResponseEntity.ok(
                restaurantService.getRestaurantDetails(restaurantId)
        );
    }
}
