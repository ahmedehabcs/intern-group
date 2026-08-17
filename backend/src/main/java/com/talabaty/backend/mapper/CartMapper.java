package com.talabaty.backend.mapper;

import com.talabaty.backend.dto.response.CartItemAddonResponse;
import com.talabaty.backend.dto.response.CartItemResponse;
import com.talabaty.backend.dto.response.CartResponse;
import com.talabaty.backend.model.Cart;
import com.talabaty.backend.model.CartItem;
import com.talabaty.backend.model.CartItemAddon;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartMapper {

    @Mapping(target = "id", source = "cart.id")
    @Mapping(target = "restaurantId", source = "cart.restaurant.id")
    @Mapping(target = "restaurantName", source = "cart.restaurant.name")
    @Mapping(target = "subtotal", source = "cart.subtotal")
    @Mapping(target = "items", source = "itemResponses")
    CartResponse toCartResponse(Cart cart, List<CartItemResponse> itemResponses);

    @Mapping(target = "id", source = "cartItem.id")
    @Mapping(target = "menuItemId", source = "cartItem.menuItem.id")
    @Mapping(target = "menuItemName", source = "cartItem.menuItem.name")
    @Mapping(target = "imageUrl", source = "cartItem.menuItem.imageUrl")
    @Mapping(target = "basePrice", source = "cartItem.menuItem.basePrice")
    @Mapping(target = "quantity", source = "cartItem.quantity")
    @Mapping(target = "specialInstructions", source = "cartItem.specialInstructions")
    @Mapping(target = "itemTotalPrice", source = "cartItem.itemTotalPrice")
    @Mapping(target = "addons", source = "addonResponses")
    CartItemResponse toCartItemResponse(
            CartItem cartItem,
            List<CartItemAddonResponse> addonResponses
    );

    @Mapping(target = "menuItemAddonId", source = "menuItemAddon.id")
    @Mapping(target = "name", source = "menuItemAddon.name")
    @Mapping(target = "addonGroupId", source = "menuItemAddon.addonGroup.id")
    @Mapping(target = "addonGroupName", source = "menuItemAddon.addonGroup.name")
    @Mapping(target = "quantity", source = "quantity")
    @Mapping(target = "priceAtAddition", source = "priceAtAddition")
    @Mapping(target = "totalPrice", expression = "java(calculateAddonTotal(cartItemAddon))")
    CartItemAddonResponse toCartItemAddonResponse(
            CartItemAddon cartItemAddon
    );

    List<CartItemAddonResponse> toCartItemAddonResponseList(
            List<CartItemAddon> cartItemAddons
    );

    default Double calculateAddonTotal(CartItemAddon cartItemAddon) {
        if (cartItemAddon == null
                || cartItemAddon.getPriceAtAddition() == null
                || cartItemAddon.getQuantity() == null) {
            return 0.0;
        }

        return cartItemAddon.getPriceAtAddition()
                * cartItemAddon.getQuantity();
    }
}