package com.talabaty.backend.service.impl;

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
import com.talabaty.backend.mapper.MenuMapper;
import com.talabaty.backend.model.AddonGroup;
import com.talabaty.backend.model.KitchenManager;
import com.talabaty.backend.model.MenuItem;
import com.talabaty.backend.model.MenuItemAddon;
import com.talabaty.backend.model.MenuSection;
import com.talabaty.backend.repository.AddonGroupRepository;
import com.talabaty.backend.repository.KitchenManagerRepository;
import com.talabaty.backend.repository.MenuItemAddonRepository;
import com.talabaty.backend.repository.MenuItemRepository;
import com.talabaty.backend.repository.MenuSectionRepository;
import com.talabaty.backend.service.KitchenMenuItemService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KitchenMenuItemServiceImpl implements KitchenMenuItemService {

    private final KitchenManagerRepository kitchenManagerRepository;
    private final MenuItemRepository menuItemRepository;
    private final MenuSectionRepository menuSectionRepository;
    private final AddonGroupRepository addonGroupRepository;
    private final MenuItemAddonRepository menuItemAddonRepository;
    private final MenuMapper menuMapper;

    public KitchenMenuItemServiceImpl(
            KitchenManagerRepository kitchenManagerRepository,
            MenuItemRepository menuItemRepository,
            MenuSectionRepository menuSectionRepository,
            AddonGroupRepository addonGroupRepository,
            MenuItemAddonRepository menuItemAddonRepository,
            MenuMapper menuMapper
    ) {
        this.kitchenManagerRepository = kitchenManagerRepository;
        this.menuItemRepository = menuItemRepository;
        this.menuSectionRepository = menuSectionRepository;
        this.addonGroupRepository = addonGroupRepository;
        this.menuItemAddonRepository = menuItemAddonRepository;
        this.menuMapper = menuMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<KitchenMenuItemResponse> getMenuItems(Long userId) {
        Long restaurantId = getManagerRestaurantId(userId);
        List<MenuItem> menuItems = menuItemRepository
                .findByMenuSectionRestaurantIdAndIsDeletedFalse(restaurantId);

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
                .findByIdAndMenuSectionRestaurantIdAndIsDeletedFalse(menuItemId, restaurantId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Menu item not found"
                ));

        menuItem.setAvailable(available);

        return menuMapper.toKitchenMenuItemResponse(
                menuItemRepository.save(menuItem)
        );
    }

    @Override
    @Transactional
    public KitchenMenuItemResponse createMenuItem(
            Long userId,
            CreateMenuItemRequest request
    ) {
        Long restaurantId = getManagerRestaurantId(userId);

        MenuSection menuSection = menuSectionRepository
                .findByIdAndRestaurantId(request.getMenuSectionId(), restaurantId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Menu section not found"
                ));

        MenuItem menuItem = new MenuItem();
        menuItem.setName(request.getName());
        menuItem.setDescription(request.getDescription());
        menuItem.setBasePrice(request.getBasePrice());
        menuItem.setImageUrl(request.getImageUrl());
        menuItem.setAvailable(request.getAvailable() != null ? request.getAvailable() : true);
        menuItem.setMenuSection(menuSection);

        if (request.getAddonGroupIds() != null && request.getAddonGroupIds().length > 0) {
            List<AddonGroup> addonGroups = addonGroupRepository
                    .findAllById(List.of(request.getAddonGroupIds()));
            menuItem.setAddonGroups(addonGroups);
        }

        return menuMapper.toKitchenMenuItemResponse(
                menuItemRepository.save(menuItem)
        );
    }

    @Override
    @Transactional
    public KitchenMenuItemResponse updateMenuItem(
            Long userId,
            Long menuItemId,
            UpdateMenuItemRequest request
    ) {
        Long restaurantId = getManagerRestaurantId(userId);

        MenuItem menuItem = menuItemRepository
                .findByIdAndMenuSectionRestaurantIdAndIsDeletedFalse(menuItemId, restaurantId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Menu item not found"
                ));

        if (request.getName() != null) {
            menuItem.setName(request.getName());
        }
        if (request.getDescription() != null) {
            menuItem.setDescription(request.getDescription());
        }
        if (request.getBasePrice() != null) {
            menuItem.setBasePrice(request.getBasePrice());
        }
        if (request.getImageUrl() != null) {
            menuItem.setImageUrl(request.getImageUrl());
        }
        if (request.getAvailable() != null) {
            menuItem.setAvailable(request.getAvailable());
        }
        if (request.getMenuSectionId() != null) {
            MenuSection menuSection = menuSectionRepository
                    .findByIdAndRestaurantId(request.getMenuSectionId(), restaurantId)
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Menu section not found"
                    ));
            menuItem.setMenuSection(menuSection);
        }
        if (request.getAddonGroupIds() != null) {
            List<AddonGroup> addonGroups = addonGroupRepository
                    .findAllById(List.of(request.getAddonGroupIds()));
            menuItem.setAddonGroups(addonGroups);
        }

        return menuMapper.toKitchenMenuItemResponse(
                menuItemRepository.save(menuItem)
        );
    }


    @Override
    @Transactional
    public void deleteMenuItem(Long userId, Long menuItemId) {
        Long restaurantId = getManagerRestaurantId(userId);

        MenuItem menuItem = menuItemRepository
                .findByIdAndMenuSectionRestaurantId(menuItemId, restaurantId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Menu item not found"
                ));

        menuItem.setDeleted(true);
        menuItem.setAvailable(false); // also hide it from ordering immediately
        menuItemRepository.save(menuItem);
    }

    @Override
    @Transactional(readOnly = true)
    public List<KitchenMenuSectionResponse> getMenuSections(Long userId) {
        Long restaurantId = getManagerRestaurantId(userId);
        List<MenuSection> sections = menuSectionRepository
                .findByRestaurantIdAndIsActiveTrueOrderByNameAsc(restaurantId);

        return sections.stream()
                .map(section -> {
                    List<MenuItem> items = menuItemRepository
                            .findByMenuSectionIdAndIsAvailableTrueOrderByNameAsc(section.getId());
                    return new KitchenMenuSectionResponse(
                            section.getId(),
                            section.getName(),
                            section.getDescription(),
                            section.getActive(),
                            menuMapper.toKitchenMenuItemResponseList(items)
                    );
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public KitchenMenuSectionResponse createMenuSection(
            Long userId,
            CreateMenuSectionRequest request
    ) {
        Long restaurantId = getManagerRestaurantId(userId);

        // Verify the restaurant belongs to the manager (already done in getManagerRestaurantId)
        // We need to get the restaurant entity to associate with the section
        KitchenManager manager = kitchenManagerRepository
                .findByUserId(userId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.FORBIDDEN,
                        "Kitchen manager profile not found"
                ));

        MenuSection section = new MenuSection();
        section.setName(request.getName());
        section.setDescription(request.getDescription());
        section.setActive(request.getActive() != null ? request.getActive() : true);
        section.setRestaurant(manager.getRestaurant());

        MenuSection saved = menuSectionRepository.save(section);

        return new KitchenMenuSectionResponse(
                saved.getId(),
                saved.getName(),
                saved.getDescription(),
                saved.getActive(),
                List.of()
        );
    }

    @Override
    @Transactional
    public KitchenMenuSectionResponse updateMenuSection(
            Long userId,
            Long menuSectionId,
            UpdateMenuSectionRequest request
    ) {
        Long restaurantId = getManagerRestaurantId(userId);

        MenuSection section = menuSectionRepository
                .findByIdAndRestaurantId(menuSectionId, restaurantId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Menu section not found"
                ));

        if (request.getName() != null) {
            section.setName(request.getName());
        }
        if (request.getDescription() != null) {
            section.setDescription(request.getDescription());
        }
        if (request.getActive() != null) {
            section.setActive(request.getActive());
        }

        MenuSection saved = menuSectionRepository.save(section);

        List<MenuItem> items = menuItemRepository
                .findByMenuSectionIdAndIsAvailableTrueOrderByNameAsc(section.getId());

        return new KitchenMenuSectionResponse(
                saved.getId(),
                saved.getName(),
                saved.getDescription(),
                saved.getActive(),
                menuMapper.toKitchenMenuItemResponseList(items)
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

    // --- Addon Groups ---

    @Override
    @Transactional(readOnly = true)
    public List<KitchenAddonGroupResponse> getAddonGroups(Long userId) {
        Long restaurantId = getManagerRestaurantId(userId);
        List<AddonGroup> groups = addonGroupRepository.findByRestaurantId(restaurantId);

        return groups.stream()
                .map(group -> {
                    List<MenuItemAddon> addons = menuItemAddonRepository
                            .findByAddonGroupIdAndIsAvailableTrue(group.getId());
                    KitchenAddonGroupResponse response = menuMapper.toKitchenAddonGroupResponse(group);
                    response.setAddons(addons.stream().map(ma -> {
                        KitchenAddonResponse r = new KitchenAddonResponse();
                        r.setId(ma.getId());
                        r.setName(ma.getName());
                        r.setAdditionalPrice(ma.getAdditionalPrice());
                        r.setAvailable(ma.getAvailable());
                        return r;
                    }).toList());
                    return response;
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public KitchenAddonGroupResponse createAddonGroup(
            Long userId,
            CreateAddonGroupRequest request
    ) {
        Long restaurantId = getManagerRestaurantId(userId);

        // Verify restaurant access by creating a dummy menu item check or just trust the manager
        // The addon group will be linked to menu items later

        AddonGroup group = new AddonGroup();
        group.setName(request.getName());
        group.setMinSelections(request.getMinSelections());
        group.setMaxSelections(request.getMaxSelections());

        AddonGroup saved = addonGroupRepository.save(group);

        return menuMapper.toKitchenAddonGroupResponse(saved);
    }

    @Override
    @Transactional
    public KitchenAddonGroupResponse updateAddonGroup(
            Long userId,
            Long addonGroupId,
            UpdateAddonGroupRequest request
    ) {
        Long restaurantId = getManagerRestaurantId(userId);

        AddonGroup group = addonGroupRepository
                .findByIdAndRestaurantId(addonGroupId, restaurantId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Addon group not found"
                ));

        if (request.getName() != null) {
            group.setName(request.getName());
        }
        if (request.getMinSelections() != null) {
            group.setMinSelections(request.getMinSelections());
        }
        if (request.getMaxSelections() != null) {
            group.setMaxSelections(request.getMaxSelections());
        }

        AddonGroup saved = addonGroupRepository.save(group);

        return menuMapper.toKitchenAddonGroupResponse(saved);
    }

    @Override
    @Transactional
    public void deleteAddonGroup(Long userId, Long addonGroupId) {
        Long restaurantId = getManagerRestaurantId(userId);

        AddonGroup group = addonGroupRepository
                .findByIdAndRestaurantId(addonGroupId, restaurantId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Addon group not found"
                ));

        addonGroupRepository.delete(group);
    }

    // --- Addons ---

    @Override
    @Transactional(readOnly = true)
    public List<KitchenAddonResponse> getAddonsByGroup(Long userId, Long addonGroupId) {
        Long restaurantId = getManagerRestaurantId(userId);

        // Verify the addon group belongs to this restaurant
        addonGroupRepository.findByIdAndRestaurantId(addonGroupId, restaurantId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Addon group not found"
                ));

        List<MenuItemAddon> addons = menuItemAddonRepository
                .findByAddonGroupIdAndIsAvailableTrue(addonGroupId);

        return addons.stream()
                .map(ma -> {
                    KitchenAddonResponse r = new KitchenAddonResponse();
                    r.setId(ma.getId());
                    r.setName(ma.getName());
                    r.setAdditionalPrice(ma.getAdditionalPrice());
                    r.setAvailable(ma.getAvailable());
                    return r;
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public KitchenAddonResponse createAddon(
            Long userId,
            Long addonGroupId,
            CreateAddonRequest request
    ) {
        Long restaurantId = getManagerRestaurantId(userId);

        AddonGroup group = addonGroupRepository
                .findByIdAndRestaurantId(addonGroupId, restaurantId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Addon group not found"
                ));

        MenuItemAddon addon = new MenuItemAddon();
        addon.setName(request.getName());
        addon.setAdditionalPrice(request.getAdditionalPrice());
        addon.setAvailable(request.getAvailable() != null ? request.getAvailable() : true);
        addon.setAddonGroup(group);

        MenuItemAddon saved = menuItemAddonRepository.save(addon);

        KitchenAddonResponse response = new KitchenAddonResponse();
        response.setId(saved.getId());
        response.setName(saved.getName());
        response.setAdditionalPrice(saved.getAdditionalPrice());
        response.setAvailable(saved.getAvailable());
        return response;
    }

    @Override
    @Transactional
    public KitchenAddonResponse updateAddon(
            Long userId,
            Long addonId,
            UpdateAddonRequest request
    ) {
        Long restaurantId = getManagerRestaurantId(userId);

        MenuItemAddon addon = menuItemAddonRepository
                .findByIdAndRestaurantId(addonId, restaurantId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Addon not found"
                ));

        if (request.getName() != null) {
            addon.setName(request.getName());
        }
        if (request.getAdditionalPrice() != null) {
            addon.setAdditionalPrice(request.getAdditionalPrice());
        }
        if (request.getAvailable() != null) {
            addon.setAvailable(request.getAvailable());
        }

        MenuItemAddon saved = menuItemAddonRepository.save(addon);

        KitchenAddonResponse response = new KitchenAddonResponse();
        response.setId(saved.getId());
        response.setName(saved.getName());
        response.setAdditionalPrice(saved.getAdditionalPrice());
        response.setAvailable(saved.getAvailable());
        return response;
    }

    @Override
    @Transactional
    public void deleteAddon(Long userId, Long addonId) {
        Long restaurantId = getManagerRestaurantId(userId);

        MenuItemAddon addon = menuItemAddonRepository
                .findByIdAndRestaurantId(addonId, restaurantId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Addon not found"
                ));

        menuItemAddonRepository.delete(addon);
    }
}
