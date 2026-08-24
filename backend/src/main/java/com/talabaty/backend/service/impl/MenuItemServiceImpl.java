package com.talabaty.backend.service.impl;

import lombok.RequiredArgsConstructor;

import com.talabaty.backend.dto.response.AddonGroupResponse;
import com.talabaty.backend.dto.response.MenuItemAddonResponse;
import com.talabaty.backend.dto.response.MenuItemDetailsResponse;
import com.talabaty.backend.mapper.MenuMapper;
import com.talabaty.backend.model.AddonGroup;
import com.talabaty.backend.model.MenuItem;
import com.talabaty.backend.model.MenuItemAddon;
import com.talabaty.backend.repository.MenuItemAddonRepository;
import com.talabaty.backend.repository.MenuItemRepository;
import com.talabaty.backend.service.MenuItemService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuItemServiceImpl implements MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final MenuItemAddonRepository menuItemAddonRepository;
    private final MenuMapper menuMapper;


    @Override
    @Transactional(readOnly = true)
    public MenuItemDetailsResponse getMenuItemDetails(Long menuItemId) {
        MenuItem menuItem = menuItemRepository
                .findByIdAndIsAvailableTrueAndMenuSectionIsActiveTrueAndMenuSectionRestaurantIsActiveTrue(
                        menuItemId
                )
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Menu item not found"
                ));

        List<AddonGroup> addonGroups =
                new ArrayList<>(menuItem.getAddonGroups());

        addonGroups.sort(
                Comparator.comparing(
                        AddonGroup::getName,
                        String.CASE_INSENSITIVE_ORDER
                )
        );

        if (addonGroups.isEmpty()) {
            return menuMapper.toMenuItemDetailsResponse(
                    menuItem,
                    Collections.emptyList()
            );
        }

        List<Long> addonGroupIds = addonGroups.stream()
                .map(AddonGroup::getId)
                .toList();

        List<MenuItemAddon> availableAddons =
                menuItemAddonRepository
                        .findByAddonGroupIdInAndIsAvailableTrueOrderByNameAsc(
                                addonGroupIds
                        );

        Map<Long, List<MenuItemAddon>> addonsByGroupId =
                availableAddons.stream()
                        .collect(Collectors.groupingBy(
                                addon -> addon.getAddonGroup().getId()
                        ));

        List<AddonGroupResponse> addonGroupResponses =
                new ArrayList<>();

        for (AddonGroup addonGroup : addonGroups) {
            List<MenuItemAddon> groupAddons =
                    addonsByGroupId.getOrDefault(
                            addonGroup.getId(),
                            Collections.emptyList()
                    );

            List<MenuItemAddonResponse> addonResponses =
                    menuMapper.toMenuItemAddonResponseList(groupAddons);

            AddonGroupResponse groupResponse =
                    menuMapper.toAddonGroupResponse(
                            addonGroup,
                            addonResponses
                    );

            addonGroupResponses.add(groupResponse);
        }

        return menuMapper.toMenuItemDetailsResponse(
                menuItem,
                addonGroupResponses
        );
    }
}
