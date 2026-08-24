package com.talabaty.backend.service;

import com.talabaty.backend.dto.request.CreateAddonGroupRequest;
import com.talabaty.backend.dto.request.CreateAddonRequest;
import com.talabaty.backend.dto.request.CreateMenuItemRequest;
import com.talabaty.backend.dto.request.CreateMenuSectionRequest;
import com.talabaty.backend.dto.request.UpdateAddonGroupRequest;
import com.talabaty.backend.dto.request.UpdateAddonRequest;
import com.talabaty.backend.dto.request.UpdateMenuItemRequest;
import com.talabaty.backend.dto.request.UpdateMenuSectionRequest;
import com.talabaty.backend.dto.response.KitchenAddonGroupResponse;
import com.talabaty.backend.dto.response.KitchenAddonResponse;
import com.talabaty.backend.dto.response.KitchenMenuItemResponse;
import com.talabaty.backend.dto.response.KitchenMenuSectionResponse;

import java.util.List;

public interface KitchenMenuItemService {

    List<KitchenMenuItemResponse> getMenuItems(Long userId);

    KitchenMenuItemResponse updateAvailability(
            Long userId,
            Long menuItemId,
            Boolean available
    );

    // Menu Item CRUD
    KitchenMenuItemResponse createMenuItem(
            Long userId,
            CreateMenuItemRequest request
    );

    KitchenMenuItemResponse updateMenuItem(
            Long userId,
            Long menuItemId,
            UpdateMenuItemRequest request
    );

    void deleteMenuItem(
            Long userId,
            Long menuItemId
    );

    // Menu Section
    List<KitchenMenuSectionResponse> getMenuSections(Long userId);

    KitchenMenuSectionResponse createMenuSection(
            Long userId,
            CreateMenuSectionRequest request
    );

    KitchenMenuSectionResponse updateMenuSection(
            Long userId,
            Long menuSectionId,
            UpdateMenuSectionRequest request
    );

    // Addon Groups
    List<KitchenAddonGroupResponse> getAddonGroups(Long userId);

    KitchenAddonGroupResponse createAddonGroup(
            Long userId,
            CreateAddonGroupRequest request
    );

    KitchenAddonGroupResponse updateAddonGroup(
            Long userId,
            Long addonGroupId,
            UpdateAddonGroupRequest request
    );

    void deleteAddonGroup(Long userId, Long addonGroupId);

    // Addons
    List<KitchenAddonResponse> getAddonsByGroup(Long userId, Long addonGroupId);

    KitchenAddonResponse createAddon(
            Long userId,
            Long addonGroupId,
            CreateAddonRequest request
    );

    KitchenAddonResponse updateAddon(
            Long userId,
            Long addonId,
            UpdateAddonRequest request
    );

    void deleteAddon(Long userId, Long addonId);
}
