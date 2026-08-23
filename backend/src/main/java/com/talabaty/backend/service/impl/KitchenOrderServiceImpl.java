package com.talabaty.backend.service.impl;

import com.talabaty.backend.dto.response.KitchenOrderDetailsResponse;
import com.talabaty.backend.dto.response.KitchenOrderPageResponse;
import com.talabaty.backend.dto.response.KitchenOrderSummaryResponse;
import com.talabaty.backend.mapper.OrderMapper;
import com.talabaty.backend.model.KitchenManager;
import com.talabaty.backend.model.KitchenOrderCancellation;
import com.talabaty.backend.model.Order;
import com.talabaty.backend.model.OrderStatus;
import com.talabaty.backend.repository.KitchenManagerRepository;
import com.talabaty.backend.repository.KitchenOrderCancellationRepository;
import com.talabaty.backend.repository.OrderRepository;
import com.talabaty.backend.service.KitchenOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class KitchenOrderServiceImpl implements KitchenOrderService {

    private static final List<OrderStatus> ACTIVE_STATUSES = List.of(
            OrderStatus.PENDING,
            OrderStatus.CONFIRMED,
            OrderStatus.PREPARING,
            OrderStatus.READY
    );

    private final KitchenManagerRepository kitchenManagerRepository;
    private final KitchenOrderCancellationRepository kitchenOrderCancellationRepository;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public KitchenOrderServiceImpl(
            KitchenManagerRepository kitchenManagerRepository,
            KitchenOrderCancellationRepository kitchenOrderCancellationRepository,
            OrderRepository orderRepository,
            OrderMapper orderMapper
    ) {
        this.kitchenManagerRepository = kitchenManagerRepository;
        this.kitchenOrderCancellationRepository = kitchenOrderCancellationRepository;
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<KitchenOrderSummaryResponse> getActiveOrders(Long userId) {
        Long restaurantId = getManagerRestaurantId(userId);

        List<Order> orders = orderRepository
                .findByRestaurantIdAndStatusInOrderByCreatedAtAsc(
                        restaurantId,
                        ACTIVE_STATUSES
                );

        return orderMapper.toKitchenOrderSummaryResponseList(orders);
    }

    @Override
    @Transactional(readOnly = true)
    public KitchenOrderPageResponse getOrderHistory(
            Long userId,
            OrderStatus status,
            LocalDate from,
            LocalDate to,
            int page,
            int size,
            String direction
    ) {
        validateDateRange(from, to);

        Long restaurantId = getManagerRestaurantId(userId);
        LocalDateTime start = from == null
                ? LocalDateTime.of(1970, 1, 1, 0, 0)
                : from.atStartOfDay();
        LocalDateTime end = to == null
                ? LocalDateTime.of(9999, 12, 31, 23, 59, 59)
                : to.plusDays(1).atStartOfDay();

        Sort.Direction sortDirection = parseSortDirection(direction);
        PageRequest pageRequest = PageRequest.of(
                page,
                size,
                Sort.by(sortDirection, "createdAt")
        );

        Page<Order> orders = status == null
                ? orderRepository.findByRestaurantIdAndCreatedAtBetween(
                        restaurantId,
                        start,
                        end,
                        pageRequest
                )
                : orderRepository.findByRestaurantIdAndStatusAndCreatedAtBetween(
                        restaurantId,
                        status,
                        start,
                        end,
                        pageRequest
                );

        return orderMapper.toKitchenOrderPageResponse(orders);
    }

    @Override
    @Transactional(readOnly = true)
    public KitchenOrderDetailsResponse getOrderDetails(Long userId, Long orderId) {
        Long restaurantId = getManagerRestaurantId(userId);
        Order order = findRestaurantOrder(orderId, restaurantId);

        return orderMapper.toKitchenOrderDetailsResponse(order);
    }

    @Override
    @Transactional
    public KitchenOrderDetailsResponse updateOrderStatus(
            Long userId,
            Long orderId,
            OrderStatus newStatus
    ) {
        if (newStatus == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Order status is required"
            );
        }

        Long restaurantId = getManagerRestaurantId(userId);
        Order order = findRestaurantOrder(orderId, restaurantId);

        validateStatusTransition(order.getStatus(), newStatus);
        order.setStatus(newStatus);

        return orderMapper.toKitchenOrderDetailsResponse(orderRepository.save(order));
    }

    @Override
    @Transactional
    public KitchenOrderDetailsResponse cancelOrder(
            Long userId,
            Long orderId,
            String reason
    ) {
        if (reason == null || reason.isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Cancellation reason is required"
            );
        }

        String normalizedReason = reason.trim();
        if (normalizedReason.length() > 255) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Cancellation reason must not exceed 255 characters"
            );
        }

        KitchenManager manager = getKitchenManager(userId);
        Long restaurantId = manager.getRestaurant().getId();
        Order order = findRestaurantOrder(orderId, restaurantId);

        if (order.getStatus() != OrderStatus.PENDING
                && order.getStatus() != OrderStatus.CONFIRMED) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Only pending or confirmed orders can be cancelled by the kitchen"
            );
        }

        order.setStatus(OrderStatus.CANCELLED);
        Order savedOrder = orderRepository.save(order);

        kitchenOrderCancellationRepository.save(
                new KitchenOrderCancellation(
                        savedOrder,
                        manager,
                        normalizedReason
                )
        );

        return orderMapper.toKitchenOrderDetailsResponse(savedOrder);
    }

    private Long getManagerRestaurantId(Long userId) {
        return getKitchenManager(userId).getRestaurant().getId();
    }

    private KitchenManager getKitchenManager(Long userId) {
        if (userId == null) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Authentication is required"
            );
        }

        return kitchenManagerRepository
                .findByUserId(userId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.FORBIDDEN,
                        "Kitchen manager profile not found"
                ));
    }

    private Order findRestaurantOrder(Long orderId, Long restaurantId) {
        return orderRepository
                .findByIdAndRestaurantId(orderId, restaurantId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Order not found"
                ));
    }

    private void validateStatusTransition(OrderStatus currentStatus, OrderStatus newStatus) {
        boolean validTransition = switch (currentStatus) {
            case PENDING -> newStatus == OrderStatus.CONFIRMED;
            case CONFIRMED -> newStatus == OrderStatus.PREPARING;
            case PREPARING -> newStatus == OrderStatus.READY;
            default -> false;
        };

        if (!validTransition) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Cannot change order status from " + currentStatus + " to " + newStatus
            );
        }
    }

    private void validateDateRange(LocalDate from, LocalDate to) {
        if (from != null && to != null && from.isAfter(to)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "From date must not be after to date"
            );
        }
    }

    private Sort.Direction parseSortDirection(String direction) {
        if (direction == null || direction.equalsIgnoreCase("desc")) {
            return Sort.Direction.DESC;
        }

        if (direction.equalsIgnoreCase("asc")) {
            return Sort.Direction.ASC;
        }

        throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Sort direction must be asc or desc"
        );
    }

}
