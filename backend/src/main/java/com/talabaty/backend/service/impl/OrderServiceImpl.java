package com.talabaty.backend.service.impl;

import com.talabaty.backend.dto.request.PlaceOrderRequest;
import com.talabaty.backend.dto.response.PlaceOrderResponse;
import com.talabaty.backend.mapper.OrderMapper;
import com.talabaty.backend.model.AddonGroup;
import com.talabaty.backend.model.Address;
import com.talabaty.backend.model.Cart;
import com.talabaty.backend.model.CartItem;
import com.talabaty.backend.model.CartItemAddon;
import com.talabaty.backend.model.CustomerProfile;
import com.talabaty.backend.model.HistoricalOrderItemAddon;
import com.talabaty.backend.model.MenuItem;
import com.talabaty.backend.model.MenuItemAddon;
import com.talabaty.backend.model.Order;
import com.talabaty.backend.model.OrderItem;
import com.talabaty.backend.model.OrderStatus;
import com.talabaty.backend.model.Restaurant;
import com.talabaty.backend.model.Role;
import com.talabaty.backend.model.User;
import com.talabaty.backend.repository.AddressRepository;
import com.talabaty.backend.repository.CartRepository;
import com.talabaty.backend.repository.MenuItemAddonRepository;
import com.talabaty.backend.repository.MenuItemRepository;
import com.talabaty.backend.repository.OrderRepository;
import com.talabaty.backend.repository.UserRepository;
import com.talabaty.backend.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService {

    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final AddressRepository addressRepository;
    private final MenuItemRepository menuItemRepository;
    private final MenuItemAddonRepository menuItemAddonRepository;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderServiceImpl(
            UserRepository userRepository,
            CartRepository cartRepository,
            AddressRepository addressRepository,
            MenuItemRepository menuItemRepository,
            MenuItemAddonRepository menuItemAddonRepository,
            OrderRepository orderRepository,
            OrderMapper orderMapper
    ) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.addressRepository = addressRepository;
        this.menuItemRepository = menuItemRepository;
        this.menuItemAddonRepository = menuItemAddonRepository;
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    @Transactional
    public PlaceOrderResponse placeOrder(Long userId, PlaceOrderRequest request) {
        CustomerProfile customer = requireCustomer(userId);
        Cart cart = requireNonEmptyCart(customer.getId());
        Address address = requireOwnedAddress(request.getAddressId(), customer.getId());
        Restaurant restaurant = requireValidCartRestaurant(cart);

        Order order = createOrder(customer, restaurant, address, request);
        BigDecimal subtotal = createOrderItems(order, cart, restaurant);
        BigDecimal deliveryFee = requireDeliveryFee(restaurant);
        BigDecimal totalPrice = subtotal.add(deliveryFee);

        order.setSubtotal(subtotal);
        order.setDeliveryFee(deliveryFee);
        order.setTotalPrice(totalPrice);

        Order savedOrder = orderRepository.saveAndFlush(order);
        PlaceOrderResponse response = orderMapper.toPlaceOrderResponse(savedOrder);

        clearCart(cart);

        return response;
    }

    private CustomerProfile requireCustomer(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        if (user.getRole() != Role.CUSTOMER) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Order placement is available only to customers");
        }

        CustomerProfile customer = user.getCustomerProfile();

        if (customer == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer profile not found");
        }

        return customer;
    }

    private Cart requireNonEmptyCart(Long customerId) {
        Cart cart = cartRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cart is empty"));

        if (cart.getItems() == null || cart.getItems().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cart is empty");
        }

        return cart;
    }

    private Address requireOwnedAddress(Long addressId, Long customerId) {
        return addressRepository.findByIdAndCustomerId(addressId, customerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Delivery address not found"));
    }

    private Restaurant requireValidCartRestaurant(Cart cart) {
        Restaurant restaurant = cart.getRestaurant();

        if (restaurant == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cart restaurant is missing");
        }

        if (!Boolean.TRUE.equals(restaurant.getActive())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Restaurant is not available");
        }

        if (restaurant.getName() == null || restaurant.getName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Restaurant name is unavailable");
        }

        return restaurant;
    }

    private Order createOrder(CustomerProfile customer, Restaurant restaurant, Address address,
                              PlaceOrderRequest request) {
        if (request.getPaymentMethod() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Payment method is required");
        }

        Order order = new Order();
        order.setCustomer(customer);
        order.setRestaurant(restaurant);
        order.setRestaurantName(restaurant.getName());
        order.setAddress(address);
        order.setDeliveryAddress(formatDeliveryAddress(address));
        order.setPaymentMethod(request.getPaymentMethod());
        order.setStatus(OrderStatus.PENDING);

        return order;
    }

    private BigDecimal createOrderItems(Order order, Cart cart, Restaurant restaurant) {
        BigDecimal subtotal = BigDecimal.ZERO;

        for (CartItem cartItem : cart.getItems()) {
            MenuItem menuItem = requireCurrentMenuItem(cartItem);
            validateRestaurant(menuItem, restaurant);

            OrderItem orderItem = createOrderItem(order, cartItem, menuItem);
            order.getOrderItems().add(orderItem);
            subtotal = subtotal.add(calculateOrderItemTotal(orderItem));
        }

        return subtotal;
    }

    private MenuItem requireCurrentMenuItem(CartItem cartItem) {
        if (cartItem.getMenuItem() == null || cartItem.getMenuItem().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cart contains an invalid menu item");
        }

        return menuItemRepository
                .findByIdAndIsAvailableTrueAndMenuSectionIsActiveTrueAndMenuSectionRestaurantIsActiveTrue(
                        cartItem.getMenuItem().getId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "A menu item in the cart is no longer available"
                ));
    }

    private void validateRestaurant(MenuItem menuItem, Restaurant expectedRestaurant) {
        Restaurant itemRestaurant = menuItem.getMenuSection().getRestaurant();

        if (itemRestaurant == null || !itemRestaurant.getId().equals(expectedRestaurant.getId())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Cart contains items from an invalid restaurant"
            );
        }
    }

    private OrderItem createOrderItem(Order order, CartItem cartItem, MenuItem menuItem) {
        validateItemQuantity(cartItem.getQuantity());

        if (menuItem.getName() == null || menuItem.getName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Menu item name is unavailable");
        }

        if (menuItem.getBasePrice() == null || menuItem.getBasePrice() < 0) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Menu item price is invalid");
        }

        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setMenuItem(menuItem);
        orderItem.setProductName(menuItem.getName());
        orderItem.setUnitPrice(menuItem.getBasePrice());
        orderItem.setQuantity(cartItem.getQuantity());
        orderItem.setNotes(cartItem.getSpecialInstructions());

        List<HistoricalOrderItemAddon> addonSnapshots =
                createAddonSnapshots(orderItem, menuItem, cartItem.getSelectedAddons());

        orderItem.setAddons(addonSnapshots);

        return orderItem;
    }

    private List<HistoricalOrderItemAddon> createAddonSnapshots(
            OrderItem orderItem,
            MenuItem menuItem,
            List<CartItemAddon> selectedAddons
    ) {
        Map<Long, AddonGroup> allowedGroups = getAllowedGroups(menuItem);
        Map<Long, Integer> quantitiesByGroup = new HashMap<>();
        Set<Long> selectedAddonIds = new HashSet<>();
        List<HistoricalOrderItemAddon> snapshots = new ArrayList<>();

        if (selectedAddons == null) {
            selectedAddons = List.of();
        }

        for (CartItemAddon selectedAddon : selectedAddons) {
            MenuItemAddon currentAddon = requireCurrentAddon(selectedAddon);
            validateAddonSelection(currentAddon, allowedGroups, selectedAddonIds);

            Integer quantity = selectedAddon.getQuantity();
            validateAddonQuantity(quantity);

            AddonGroup group = currentAddon.getAddonGroup();
            quantitiesByGroup.merge(group.getId(), quantity, Integer::sum);

            HistoricalOrderItemAddon snapshot = new HistoricalOrderItemAddon();
            snapshot.setOrderItem(orderItem);
            snapshot.setAddonName(currentAddon.getName());
            snapshot.setAddonPrice(currentAddon.getAdditionalPrice());
            snapshot.setQuantity(quantity);

            snapshots.add(snapshot);
        }

        validateAddonGroupLimits(allowedGroups, quantitiesByGroup);

        return snapshots;
    }

    private Map<Long, AddonGroup> getAllowedGroups(MenuItem menuItem) {
        Map<Long, AddonGroup> allowedGroups = new HashMap<>();

        for (AddonGroup group : menuItem.getAddonGroups()) {
            allowedGroups.put(group.getId(), group);
        }

        return allowedGroups;
    }

    private MenuItemAddon requireCurrentAddon(CartItemAddon selectedAddon) {
        if (selectedAddon.getMenuItemAddon() == null || selectedAddon.getMenuItemAddon().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cart contains an invalid addon");
        }

        Long addonId = selectedAddon.getMenuItemAddon().getId();

        MenuItemAddon addon = menuItemAddonRepository.findById(addonId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "An addon in the cart no longer exists"
                ));

        if (!Boolean.TRUE.equals(addon.getAvailable())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "An addon in the cart is no longer available");
        }

        if (addon.getName() == null || addon.getName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Addon name is unavailable");
        }

        if (addon.getAdditionalPrice() == null || addon.getAdditionalPrice() < 0) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Addon price is invalid");
        }

        return addon;
    }

    private void validateAddonSelection(
            MenuItemAddon addon,
            Map<Long, AddonGroup> allowedGroups,
            Set<Long> selectedAddonIds
    ) {
        if (!selectedAddonIds.add(addon.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cart contains a duplicate addon");
        }

        AddonGroup group = addon.getAddonGroup();

        if (group == null || !allowedGroups.containsKey(group.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "An addon is not allowed for this menu item");
        }
    }

    private void validateAddonGroupLimits(
            Map<Long, AddonGroup> allowedGroups,
            Map<Long, Integer> quantitiesByGroup
    ) {
        for (AddonGroup group : allowedGroups.values()) {
            int selectedQuantity = quantitiesByGroup.getOrDefault(group.getId(), 0);

            if (selectedQuantity < group.getMinSelections()) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Addon group '" + group.getName()
                                + "' requires at least "
                                + group.getMinSelections()
                                + " selection(s)"
                );
            }

            if (selectedQuantity > group.getMaxSelections()) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Addon group '" + group.getName()
                                + "' allows at most "
                                + group.getMaxSelections()
                                + " selection(s)"
                );
            }
        }
    }

    private void validateItemQuantity(Integer quantity) {
        if (quantity == null || quantity <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cart item quantity must be greater than zero");
        }
    }

    private void validateAddonQuantity(Integer quantity) {
        if (quantity == null || quantity <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Addon quantity must be greater than zero");
        }
    }

    private BigDecimal calculateOrderItemTotal(OrderItem orderItem) {
        BigDecimal unitTotal = BigDecimal.valueOf(orderItem.getUnitPrice());

        for (HistoricalOrderItemAddon addon : orderItem.getAddons()) {
            BigDecimal addonTotal = BigDecimal.valueOf(addon.getAddonPrice())
                    .multiply(BigDecimal.valueOf(addon.getQuantity()));

            unitTotal = unitTotal.add(addonTotal);
        }

        return unitTotal.multiply(BigDecimal.valueOf(orderItem.getQuantity()));
    }

    private BigDecimal requireDeliveryFee(Restaurant restaurant) {
        BigDecimal deliveryFee = restaurant.getDeliveryFee();

        if (deliveryFee == null || deliveryFee.compareTo(BigDecimal.ZERO) < 0) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Restaurant delivery fee is invalid");
        }

        return deliveryFee;
    }

    private String formatDeliveryAddress(Address address) {
        List<String> parts = new ArrayList<>();

        addAddressPart(parts, address.getStreet());
        addLabeledAddressPart(parts, "Building", address.getBuilding());
        addLabeledAddressPart(parts, "Floor", address.getFloor());
        addLabeledAddressPart(parts, "Apartment", address.getApartment());
        addAddressPart(parts, address.getCity());

        if (address.getGovernorate() != null) {
            addAddressPart(parts, address.getGovernorate().getName());
        }

        if (parts.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Selected delivery address is incomplete");
        }

        return String.join(", ", parts);
    }

    private void addAddressPart(List<String> parts, String value) {
        if (value != null && !value.isBlank()) {
            parts.add(value.trim());
        }
    }

    private void addLabeledAddressPart(List<String> parts, String label, String value) {
        if (value != null && !value.isBlank()) {
            parts.add(label + " " + value.trim());
        }
    }

    private void clearCart(Cart cart) {
        cart.getItems().clear();
        cart.setRestaurant(null);
        cart.setSubtotal(0.0);
        cartRepository.save(cart);
    }
}
