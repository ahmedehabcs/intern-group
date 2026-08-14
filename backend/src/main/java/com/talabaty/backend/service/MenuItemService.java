package com.talabaty.backend.service;

import com.talabaty.backend.dto.response.MenuItemDetailsResponse;

public interface MenuItemService {

    MenuItemDetailsResponse getMenuItemDetails(Long menuItemId);
}