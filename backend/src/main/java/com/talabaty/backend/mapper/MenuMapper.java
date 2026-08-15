package com.talabaty.backend.mapper;

import com.talabaty.backend.dto.projection.MenuItemSearchProjection;
import com.talabaty.backend.dto.response.MenuItemResponse;
import com.talabaty.backend.dto.response.MenuItemSearchResponse;
import com.talabaty.backend.dto.response.MenuSectionResponse;
import com.talabaty.backend.model.MenuItem;
import com.talabaty.backend.model.MenuSection;
import com.talabaty.backend.dto.response.AddonGroupResponse;
import com.talabaty.backend.dto.response.MenuItemAddonResponse;
import com.talabaty.backend.dto.response.MenuItemDetailsResponse;
import com.talabaty.backend.model.AddonGroup;
import com.talabaty.backend.model.MenuItemAddon;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MenuMapper {

    MenuItemResponse toMenuItemResponse(MenuItem menuItem);

    List<MenuItemResponse> toMenuItemResponseList(
            List<MenuItem> menuItems
    );

    @Mapping(target = "menuItems", source = "menuItemResponses")
    MenuSectionResponse toMenuSectionResponse(
            MenuSection menuSection,
            List<MenuItemResponse> menuItemResponses
    );

    @Mapping(target = "price", source = "basePrice")
    @Mapping(
            target = "restaurantId",
            source = "menuSection.restaurant.id"
    )
    @Mapping(
            target = "restaurantName",
            source = "menuSection.restaurant.name"
    )
    @Mapping(
            target = "menuSectionId",
            source = "menuSection.id"
    )
    @Mapping(
            target = "menuSectionName",
            source = "menuSection.name"
    )
    MenuItemSearchResponse toMenuItemSearchResponse(
            MenuItemSearchProjection projection
    );

    List<MenuItemSearchResponse> toMenuItemSearchResponseList(
            List<MenuItemSearchProjection> projections
    );
    MenuItemAddonResponse toMenuItemAddonResponse(
            MenuItemAddon menuItemAddon
    );

    List<MenuItemAddonResponse> toMenuItemAddonResponseList(
            List<MenuItemAddon> menuItemAddons
    );

    @Mapping(target = "addons", source = "addonResponses")
    AddonGroupResponse toAddonGroupResponse(
            AddonGroup addonGroup,
            List<MenuItemAddonResponse> addonResponses
    );

    @Mapping(
            target = "restaurantId",
            source = "menuItem.menuSection.restaurant.id"
    )
    @Mapping(
            target = "menuSectionId",
            source = "menuItem.menuSection.id"
    )
    @Mapping(
            target = "addonGroups",
            source = "addonGroupResponses"
    )
    MenuItemDetailsResponse toMenuItemDetailsResponse(
            MenuItem menuItem,
            List<AddonGroupResponse> addonGroupResponses
    );
}