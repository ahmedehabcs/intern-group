package com.talabaty.backend.service.impl;

import com.talabaty.backend.dto.response.RestaurantResponse;
import com.talabaty.backend.model.Category;
import com.talabaty.backend.model.Restaurant;
import com.talabaty.backend.repository.RestaurantRepository;
import com.talabaty.backend.service.RestaurantService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<RestaurantResponse> browseRestaurants(
            String search,
            Long categoryId
    ) {

        // Remove extra spaces from the search value.
        String normalizedSearch = search == null ? "" : search.trim();

        boolean hasSearch = !normalizedSearch.isEmpty();
        boolean hasCategory = categoryId != null;

        List<Restaurant> restaurants;

        // Search by restaurant name and category.
        if (hasSearch && hasCategory) {

            restaurants = restaurantRepository
                    .findDistinctByIsActiveTrueAndNameContainingIgnoreCaseAndCategories_IdOrderByNameAsc(
                            normalizedSearch,
                            categoryId
                    );

            // Search only by restaurant name.
        } else if (hasSearch) {

            restaurants = restaurantRepository
                    .findDistinctByIsActiveTrueAndNameContainingIgnoreCaseOrderByNameAsc(
                            normalizedSearch
                    );

            // Filter only by category.
        } else if (hasCategory) {

            restaurants = restaurantRepository
                    .findDistinctByIsActiveTrueAndCategories_IdOrderByNameAsc(
                            categoryId
                    );

            // Return all active restaurants.
        } else {

            restaurants = restaurantRepository
                    .findDistinctByIsActiveTrueOrderByNameAsc();
        }

        // Convert Restaurant entities to RestaurantResponse DTOs.
        List<RestaurantResponse> responses = new ArrayList<>();

        for (Restaurant restaurant : restaurants) {
            RestaurantResponse response = toResponse(restaurant);
            responses.add(response);
        }

        return responses;
    }

    private RestaurantResponse toResponse(Restaurant restaurant) {

        // Store the names of active categories only.
        List<String> categoryNames = new ArrayList<>();

        for (Category category : restaurant.getCategories()) {

            if (Boolean.TRUE.equals(category.getActive())) {
                categoryNames.add(category.getName());
            }
        }

        // Sort category names alphabetically.
        Collections.sort(categoryNames);

        // Convert Restaurant entity to RestaurantResponse DTO.
        return new RestaurantResponse(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getDescription(),
                restaurant.getLogoUrl(),
                categoryNames
        );
    }
}