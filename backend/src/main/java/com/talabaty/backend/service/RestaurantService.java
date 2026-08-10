package com.talabaty.backend.service;

import com.talabaty.backend.dto.response.RestaurantResponse;

import java.util.List;

public interface RestaurantService {

    List<RestaurantResponse> browseRestaurants(
            String search,
            Long categoryId
    );
}