package com.talabaty.backend.controller;

import com.talabaty.backend.config.OpenApiConfig;
import com.talabaty.backend.dto.response.RestaurantResponse;
import com.talabaty.backend.service.RestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(
        name = "Customer Restaurants",
        description = "Browse and search active restaurants"
)
@SecurityRequirement(name = OpenApiConfig.SECURITY_SCHEME_NAME)
@RestController
@RequestMapping("/api/customer/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Operation(summary = "Browse active restaurants")
    @GetMapping
    public ResponseEntity<List<RestaurantResponse>> browseRestaurants(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Long categoryId
    ) {
        return ResponseEntity.ok(
                restaurantService.browseRestaurants(search, categoryId)
        );
    }
}