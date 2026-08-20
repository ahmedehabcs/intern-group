package com.talabaty.backend.service.impl;

import com.talabaty.backend.dto.response.KitchenMenuItemResponse;
import com.talabaty.backend.mapper.MenuMapper;
import com.talabaty.backend.model.KitchenManager;
import com.talabaty.backend.model.MenuItem;
import com.talabaty.backend.repository.KitchenManagerRepository;
import com.talabaty.backend.repository.MenuItemRepository;
import com.talabaty.backend.service.KitchenMenuItemService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class KitchenMenuItemServiceImpl implements KitchenMenuItemService {

    private final KitchenManagerRepository kitchenManagerRepository;
    private final MenuItemRepository menuItemRepository;
    private final MenuMapper menuMapper;

    public KitchenMenuItemServiceImpl(
            KitchenManagerRepository kitchenManagerRepository,
            MenuItemRepository menuItemRepository,
            MenuMapper menuMapper
    ) {
        this.kitchenManagerRepository = kitchenManagerRepository;
        this.menuItemRepository = menuItemRepository;
        this.menuMapper = menuMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<KitchenMenuItemResponse> getMenuItems(Long userId) {
        Long restaurantId = getManagerRestaurantId(userId);
        List<MenuItem> menuItems = menuItemRepository
                .findByMenuSectionRestaurantIdOrderByMenuSectionNameAscNameAsc(
                        restaurantId
                );

        return menuMapper.toKitchenMenuItemResponseList(menuItems);
    }

    @Override
    @Transactional
    public KitchenMenuItemResponse updateAvailability(
            Long userId,
            Long menuItemId,
            Boolean available
    ) {
        if (available == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Availability is required"
            );
        }

        Long restaurantId = getManagerRestaurantId(userId);
        MenuItem menuItem = menuItemRepository
                .findByIdAndMenuSectionRestaurantId(menuItemId, restaurantId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Menu item not found"
                ));

        menuItem.setAvailable(available);

        return menuMapper.toKitchenMenuItemResponse(
                menuItemRepository.save(menuItem)
        );
    }

    private Long getManagerRestaurantId(Long userId) {
        if (userId == null) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Authentication is required"
            );
        }

        KitchenManager manager = kitchenManagerRepository
                .findByUserId(userId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.FORBIDDEN,
                        "Kitchen manager profile not found"
                ));

        return manager.getRestaurant().getId();
    }
}
