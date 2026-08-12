package com.talabaty.backend.service;

import com.talabaty.backend.dto.response.RestaurantResponse;

import java.util.List;

public interface RestaurantService {

    List<RestaurantResponse> browseRestaurants(Long categoryId);

    List<RestaurantResponse> searchRestaurants(String search);
}
