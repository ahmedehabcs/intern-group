package com.talabaty.backend.service.impl;

import com.talabaty.backend.dto.response.KitchenOrderDetailsResponse;
import com.talabaty.backend.dto.response.KitchenOrderSummaryResponse;
import com.talabaty.backend.mapper.OrderMapper;
import com.talabaty.backend.model.KitchenManager;
import com.talabaty.backend.model.Order;
import com.talabaty.backend.model.OrderStatus;
import com.talabaty.backend.repository.KitchenManagerRepository;
import com.talabaty.backend.repository.OrderRepository;
import com.talabaty.backend.service.KitchenOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public KitchenOrderServiceImpl(
            KitchenManagerRepository kitchenManagerRepository,
            OrderRepository orderRepository,
            OrderMapper orderMapper
    ) {
        this.kitchenManagerRepository = kitchenManagerRepository;
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

}
