package com.talabaty.backend.service;

import com.talabaty.backend.dto.response.KitchenMenuItemResponse;

import java.util.List;

public interface KitchenMenuItemService {

    List<KitchenMenuItemResponse> getMenuItems(Long userId);

    KitchenMenuItemResponse updateAvailability(
            Long userId,
            Long menuItemId,
            Boolean available
    );
}
