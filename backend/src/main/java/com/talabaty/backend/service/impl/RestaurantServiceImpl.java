package com.talabaty.backend.service.impl;

import lombok.RequiredArgsConstructor;

import com.talabaty.backend.dto.response.MenuItemResponse;
import com.talabaty.backend.dto.response.MenuSectionResponse;
import com.talabaty.backend.dto.response.RestaurantDetailsResponse;
import com.talabaty.backend.dto.response.RestaurantResponse;
import com.talabaty.backend.model.Category;
import com.talabaty.backend.model.MenuItem;
import com.talabaty.backend.model.MenuSection;
import com.talabaty.backend.model.Restaurant;
import com.talabaty.backend.repository.MenuItemRepository;
import com.talabaty.backend.repository.MenuSectionRepository;
import com.talabaty.backend.repository.RestaurantRepository;
import com.talabaty.backend.service.RestaurantService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final MenuSectionRepository menuSectionRepository;
    private final MenuItemRepository menuItemRepository;


    @Override
    @Transactional(readOnly = true)
    public List<RestaurantResponse> browseRestaurants(Long categoryId) {
        List<Restaurant> restaurants;

        if (categoryId != null) {
            restaurants = restaurantRepository
                    .findDistinctByIsActiveTrueAndCategories_IdOrderByNameAsc(
                            categoryId
                    );
        } else {
            restaurants = restaurantRepository
                    .findDistinctByIsActiveTrueOrderByNameAsc();
        }

        return toResponseList(restaurants);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RestaurantResponse> searchRestaurants(String search) {
        List<Restaurant> restaurants = restaurantRepository
                .findDistinctByIsActiveTrueAndNameContainingIgnoreCaseOrderByNameAsc(
                        search
                );

        return toResponseList(restaurants);
    }

    @Override
    @Transactional(readOnly = true)
    public RestaurantDetailsResponse getRestaurantDetails(Long restaurantId) {
        Restaurant restaurant = restaurantRepository
                .findByIdAndIsActiveTrue(restaurantId);

        if (restaurant == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Restaurant not found"
            );
        }

        List<MenuSection> menuSections = menuSectionRepository
                .findByRestaurantIdAndIsActiveTrueOrderByNameAsc(restaurantId);

        List<MenuSectionResponse> menuSectionResponses = new ArrayList<>();

        for (MenuSection menuSection : menuSections) {
            List<MenuItem> menuItems = menuItemRepository
                    .findByMenuSectionIdAndIsAvailableTrueOrderByNameAsc(
                            menuSection.getId()
                    );

            List<MenuItemResponse> menuItemResponses = new ArrayList<>();

            for (MenuItem menuItem : menuItems) {
                MenuItemResponse menuItemResponse = new MenuItemResponse(
                        menuItem.getId(),
                        menuItem.getName(),
                        menuItem.getDescription(),
                        menuItem.getBasePrice(),
                        menuItem.getImageUrl()
                );

                menuItemResponses.add(menuItemResponse);
            }

            MenuSectionResponse menuSectionResponse =
                    new MenuSectionResponse(
                            menuSection.getId(),
                            menuSection.getName(),
                            menuSection.getDescription(),
                            menuItemResponses
                    );

            menuSectionResponses.add(menuSectionResponse);
        }

        return new RestaurantDetailsResponse(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getDescription(),
                restaurant.getLogoUrl(),
                menuSectionResponses
        );
    }

    private List<RestaurantResponse> toResponseList(
            List<Restaurant> restaurants
    ) {
        List<RestaurantResponse> responses = new ArrayList<>();

        for (Restaurant restaurant : restaurants) {
            RestaurantResponse response = toResponse(restaurant);
            responses.add(response);
        }

        return responses;
    }

    private RestaurantResponse toResponse(Restaurant restaurant) {
        List<String> categoryNames = new ArrayList<>();

        for (Category category : restaurant.getCategories()) {
            if (Boolean.TRUE.equals(category.getActive())) {
                categoryNames.add(category.getName());
            }
        }

        Collections.sort(categoryNames);

        return new RestaurantResponse(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getDescription(),
                restaurant.getLogoUrl(),
                categoryNames
        );
    }
}
