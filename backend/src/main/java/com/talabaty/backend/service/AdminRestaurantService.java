package com.talabaty.backend.service;

import com.talabaty.backend.dto.request.AssignKitchenManagerRequest;
import com.talabaty.backend.dto.request.CreateRestaurantRequest;
import com.talabaty.backend.dto.request.UpdateRestaurantRequest;
import com.talabaty.backend.dto.response.RestaurantAdminResponse;

import java.util.List;

public interface AdminRestaurantService {
    RestaurantAdminResponse createRestaurant(CreateRestaurantRequest request);
    RestaurantAdminResponse updateRestaurant(Long id, UpdateRestaurantRequest request);
    RestaurantAdminResponse setRestaurantActive(Long id, boolean active);    List<RestaurantAdminResponse> searchRestaurants(String search);
    void assignKitchenManager(Long restaurantId, AssignKitchenManagerRequest request);
}