package com.talabaty.backend.service.impl;

import lombok.RequiredArgsConstructor;

import com.talabaty.backend.dto.request.AddCartItemRequest;
import com.talabaty.backend.dto.request.CartItemAddonRequest;
import com.talabaty.backend.dto.request.ReplaceCartItemRequest;
import com.talabaty.backend.dto.request.UpdateCartItemQuantityRequest;
import com.talabaty.backend.dto.response.CartItemAddonResponse;
import com.talabaty.backend.dto.response.CartItemResponse;
import com.talabaty.backend.dto.response.CartResponse;
import com.talabaty.backend.mapper.CartMapper;
import com.talabaty.backend.model.AddonGroup;
import com.talabaty.backend.model.Cart;
import com.talabaty.backend.model.CartItem;
import com.talabaty.backend.model.CartItemAddon;
import com.talabaty.backend.model.CustomerProfile;
import com.talabaty.backend.model.MenuItem;
import com.talabaty.backend.model.MenuItemAddon;
import com.talabaty.backend.model.Restaurant;
import com.talabaty.backend.model.Role;
import com.talabaty.backend.model.User;
import com.talabaty.backend.repository.CartItemRepository;
import com.talabaty.backend.repository.CartRepository;
import com.talabaty.backend.repository.MenuItemAddonRepository;
import com.talabaty.backend.repository.MenuItemRepository;
import com.talabaty.backend.repository.UserRepository;
import com.talabaty.backend.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final MenuItemRepository menuItemRepository;
    private final MenuItemAddonRepository menuItemAddonRepository;
    private final UserRepository userRepository;
    private final CartMapper cartMapper;


    @Override
    @Transactional(readOnly = true)
    public CartResponse getCart(Long userId) {
        CustomerProfile customer = requireCustomer(userId);
        return cartRepository.findByCustomerId(customer.getId())
                .map(this::toCartResponse)
                .orElseGet(this::emptyCartResponse);
    }

    @Override
    @Transactional
    public CartResponse addItem(
            Long userId,
            AddCartItemRequest request
    ) {
        CustomerProfile customer = requireCustomer(userId);
        MenuItem menuItem = requireAvailableMenuItem(request.getMenuItemId());
        Restaurant restaurant = menuItem.getMenuSection().getRestaurant();

        Cart cart = cartRepository.findByCustomerId(customer.getId())
                .orElseGet(() -> createCart(customer));

        enforceSingleRestaurant(cart, restaurant);

        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setMenuItem(menuItem);
        cartItem.setQuantity(request.getQuantity());
        cartItem.setSpecialInstructions(
                normalizeInstructions(request.getSpecialInstructions())
        );
        cartItem.setSelectedAddons(
                createSelectedAddons(menuItem, request.getAddons(), cartItem));

        cart.getItems().add(cartItem);
        recalculateCartSubtotal(cart);

        return toCartResponse(cartRepository.save(cart));
    }

    @Override
    @Transactional
    public CartResponse updateItemQuantity(
            Long userId,
            Long cartItemId,
            UpdateCartItemQuantityRequest request
    ) {
        CustomerProfile customer = requireCustomer(userId);
        CartItem cartItem = requireOwnedCartItem(cartItemId, customer.getId());

        cartItem.setQuantity(request.getQuantity());
        Cart cart = cartItem.getCart();
        recalculateCartSubtotal(cart);

        return toCartResponse(cartRepository.save(cart));
    }

    @Override
    @Transactional
    public CartResponse replaceItemConfiguration(
            Long userId,
            Long cartItemId,
            ReplaceCartItemRequest request
    ) {
        CustomerProfile customer = requireCustomer(userId);
        CartItem cartItem = requireOwnedCartItem(cartItemId, customer.getId());
        MenuItem menuItem = requireAvailableMenuItem(
                cartItem.getMenuItem().getId()
        );

        List<CartItemAddon> replacements = createSelectedAddons(
                menuItem,
                request.getAddons(),
                cartItem
        );

        cartItem.setQuantity(request.getQuantity());
        cartItem.setSpecialInstructions(
                normalizeInstructions(request.getSpecialInstructions())
        );
        cartItem.getSelectedAddons().clear();
        cartItem.getSelectedAddons().addAll(replacements);

        Cart cart = cartItem.getCart();
        recalculateCartSubtotal(cart);

        return toCartResponse(cartRepository.save(cart));
    }

    @Override
    @Transactional
    public CartResponse removeItem(Long userId, Long cartItemId) {
        CustomerProfile customer = requireCustomer(userId);
        CartItem cartItem = requireOwnedCartItem(
                cartItemId,
                customer.getId()
        );
        Cart cart = cartItem.getCart();

        cart.getItems().remove(cartItem);
        resetEmptyCart(cart);
        recalculateCartSubtotal(cart);

        return toCartResponse(cartRepository.save(cart));
    }

    @Override
    @Transactional
    public void clearCart(Long userId) {
        CustomerProfile customer = requireCustomer(userId);

        cartRepository.findByCustomerId(customer.getId())
                .ifPresent(cart -> {
                    cart.getItems().clear();
                    resetEmptyCart(cart);
                    cartRepository.save(cart);
                });
    }

    private CustomerProfile requireCustomer(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User not found"
                ));

        if (user.getRole() != Role.CUSTOMER) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Cart operations are available only to customers"
            );
        }

        CustomerProfile customer = user.getCustomerProfile();
        if (customer == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Customer profile not found"
            );
        }

        return customer;
    }

    private CartItem requireOwnedCartItem(
            Long cartItemId,
            Long customerId
    ) {
        return cartItemRepository
                .findByIdAndCart_Customer_Id(cartItemId, customerId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Cart item not found"
                ));
    }

    private MenuItem requireAvailableMenuItem(Long menuItemId) {
        return menuItemRepository
                .findByIdAndIsAvailableTrueAndMenuSectionIsActiveTrueAndMenuSectionRestaurantIsActiveTrue(
                        menuItemId
                )
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Available menu item not found"
                ));
    }

    private List<CartItemAddon> createSelectedAddons(
            MenuItem menuItem,
            List<CartItemAddonRequest> requests,
            CartItem cartItem
    ) {
        Map<Long, AddonGroup> allowedGroupsById = new HashMap<>();
        for (AddonGroup group : menuItem.getAddonGroups()) {
            allowedGroupsById.put(group.getId(), group);
        }

        Set<Long> requestedAddonIds = new HashSet<>();
        Map<Long, Integer> quantitiesByGroupId = new HashMap<>();
        List<CartItemAddon> selectedAddons = new ArrayList<>();

        for (CartItemAddonRequest request : requests) {
            Long addonId = request.getMenuItemAddonId();
            if (!requestedAddonIds.add(addonId)) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Duplicate add-on ID: " + addonId
                );
            }

            MenuItemAddon menuItemAddon = menuItemAddonRepository
                    .findById(addonId)
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.BAD_REQUEST,
                            "Add-on not found: " + addonId
                    ));

            if (!Boolean.TRUE.equals(menuItemAddon.getAvailable())) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Add-on is not available: " + addonId
                );
            }

            AddonGroup group = menuItemAddon.getAddonGroup();
            if (group == null || !allowedGroupsById.containsKey(group.getId())) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Add-on is not allowed for this menu item: " + addonId
                );
            }

            if (menuItemAddon.getAdditionalPrice() == null) {
                throw new ResponseStatusException(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        "Add-on price is unavailable: " + addonId
                );
            }

            quantitiesByGroupId.merge(
                    group.getId(),
                    request.getQuantity(),
                    Integer::sum
            );

            CartItemAddon selectedAddon = new CartItemAddon();
            selectedAddon.setCartItem(cartItem);
            selectedAddon.setMenuItemAddon(menuItemAddon);
            selectedAddon.setQuantity(request.getQuantity());
            selectedAddon.setPriceAtAddition(menuItemAddon.getAdditionalPrice());
            selectedAddons.add(selectedAddon);
        }

        validateGroupLimits(allowedGroupsById, quantitiesByGroupId);
        return selectedAddons;
    }

    private void validateGroupLimits(
            Map<Long, AddonGroup> allowedGroupsById,
            Map<Long, Integer> quantitiesByGroupId
    ) {
        for (AddonGroup group : allowedGroupsById.values()) {
            int selectedQuantity = quantitiesByGroupId.getOrDefault(
                    group.getId(),
                    0
            );

            if (selectedQuantity < group.getMinSelections()) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Add-on group '" + group.getName()
                                + "' requires at least "
                                + group.getMinSelections()
                                + " selection(s)"
                );
            }

            if (selectedQuantity > group.getMaxSelections()) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Add-on group '" + group.getName()
                                + "' allows at most "
                                + group.getMaxSelections()
                                + " selection(s)"
                );
            }
        }
    }

    private void recalculateItemTotal(CartItem cartItem) {
        if (cartItem.getMenuItem() == null
                || cartItem.getMenuItem().getBasePrice() == null) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Menu item price is unavailable"
            );
        }

        if (cartItem.getQuantity() == null || cartItem.getQuantity() <= 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Item quantity must be greater than zero"
            );
        }

        double unitPrice = cartItem.getMenuItem().getBasePrice();

        for (CartItemAddon addon : cartItem.getSelectedAddons()) {
            if (addon.getPriceAtAddition() == null) {
                throw new ResponseStatusException(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        "Selected add-on price is unavailable"
                );
            }

            if (addon.getQuantity() == null || addon.getQuantity() <= 0) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Add-on quantity must be greater than zero"
                );
            }

            unitPrice += addon.getPriceAtAddition() * addon.getQuantity();
        }

        cartItem.setItemTotalPrice(unitPrice * cartItem.getQuantity());
    }

    private void recalculateCartSubtotal(Cart cart) {
        double subtotal = 0.0;
        for (CartItem item : cart.getItems()) {
            recalculateItemTotal(item);
            subtotal += item.getItemTotalPrice();
        }
        cart.setSubtotal(subtotal);
    }

    private CartResponse toCartResponse(Cart cart) {
        List<CartItemResponse> itemResponses = new ArrayList<>();

        for (CartItem item : cart.getItems()) {
            List<CartItemAddonResponse> addonResponses =
                    cartMapper.toCartItemAddonResponseList(
                            item.getSelectedAddons()
                    );

            itemResponses.add(
                    cartMapper.toCartItemResponse(item, addonResponses)
            );
        }

        return cartMapper.toCartResponse(cart, itemResponses);
    }

    private CartResponse emptyCartResponse() {
        return new CartResponse(null, null, null, 0.0, Collections.emptyList());
    }

    private Cart createCart(CustomerProfile customer) {
        Cart cart = new Cart();
        cart.setCustomer(customer);
        return cart;
    }

    private void enforceSingleRestaurant(
            Cart cart,
            Restaurant requestedRestaurant
    ) {
        if (cart.getRestaurant() == null) {
            cart.setRestaurant(requestedRestaurant);
            return;
        }

        if (!cart.getRestaurant().getId().equals(requestedRestaurant.getId())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Cart cannot contain items from different restaurants"
            );
        }
    }

    private void resetEmptyCart(Cart cart) {
        if (cart.getItems().isEmpty()) {
            cart.setRestaurant(null);
            cart.setSubtotal(0.0);
        }
    }

    private String normalizeInstructions(String instructions) {
        if (instructions == null) {
            return null;
        }

        String normalized = instructions.trim();
        return normalized.isEmpty() ? null : normalized;
    }
}
