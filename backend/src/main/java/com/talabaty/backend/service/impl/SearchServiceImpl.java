package com.talabaty.backend.service.impl;

import com.talabaty.backend.dto.response.MenuItemSearchResponse;
import com.talabaty.backend.dto.response.RestaurantResponse;
import com.talabaty.backend.dto.response.SearchResponse;
import com.talabaty.backend.model.MenuItem;
import com.talabaty.backend.model.MenuSection;
import com.talabaty.backend.model.Restaurant;
import com.talabaty.backend.repository.MenuItemRepository;
import com.talabaty.backend.service.RestaurantService;
import com.talabaty.backend.service.SearchService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    private final RestaurantService restaurantService;
    private final MenuItemRepository menuItemRepository;

    public SearchServiceImpl(
            RestaurantService restaurantService,
            MenuItemRepository menuItemRepository
    ) {
        this.restaurantService = restaurantService;
        this.menuItemRepository = menuItemRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public SearchResponse search(String search) {
        if (search == null || search.trim().isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Search text is required"
            );
        }

        String normalizedSearch = search.trim();

        List<RestaurantResponse> restaurants = restaurantService
                .searchRestaurants(normalizedSearch);

        List<MenuItem> menuItems = menuItemRepository
                .searchActiveMenuItems(normalizedSearch);

        List<MenuItemSearchResponse> menuItemResponses = new ArrayList<>();

        for (MenuItem menuItem : menuItems) {
            menuItemResponses.add(toMenuItemResponse(menuItem));
        }

        return new SearchResponse(restaurants, menuItemResponses);
    }

    private MenuItemSearchResponse toMenuItemResponse(MenuItem menuItem) {
        MenuSection menuSection = menuItem.getMenuSection();
        Restaurant restaurant = menuSection.getRestaurant();

        return new MenuItemSearchResponse(
                menuItem.getId(),
                menuItem.getName(),
                menuItem.getDescription(),
                menuItem.getBasePrice(),
                menuItem.getImageUrl(),
                restaurant.getId(),
                restaurant.getName(),
                menuSection.getId(),
                menuSection.getName()
        );
    }
}
