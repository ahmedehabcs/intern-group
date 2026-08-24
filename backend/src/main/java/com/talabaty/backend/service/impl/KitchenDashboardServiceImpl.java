package com.talabaty.backend.service.impl;

import com.talabaty.backend.dto.response.KitchenDashboardSummaryResponse;
import com.talabaty.backend.model.KitchenManager;
import com.talabaty.backend.model.Order;
import com.talabaty.backend.model.OrderStatus;
import com.talabaty.backend.repository.KitchenManagerRepository;
import com.talabaty.backend.repository.OrderRepository;
import com.talabaty.backend.service.KitchenDashboardService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

@Service
public class KitchenDashboardServiceImpl implements KitchenDashboardService {

    private static final long DELAY_THRESHOLD_MINUTES = 30;
    private static final Set<OrderStatus> DELAY_ELIGIBLE_STATUSES = EnumSet.of(
            OrderStatus.PENDING,
            OrderStatus.CONFIRMED,
            OrderStatus.PREPARING
    );

    private final KitchenManagerRepository kitchenManagerRepository;
    private final OrderRepository orderRepository;

    public KitchenDashboardServiceImpl(
            KitchenManagerRepository kitchenManagerRepository,
            OrderRepository orderRepository
    ) {
        this.kitchenManagerRepository = kitchenManagerRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public KitchenDashboardSummaryResponse getTodaySummary(Long userId) {
        Long restaurantId = getManagerRestaurantId(userId);
        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1);
        LocalDateTime delayCutoff = LocalDateTime.now()
                .minusMinutes(DELAY_THRESHOLD_MINUTES);

        List<Order> orders = orderRepository
                .findByRestaurantIdAndCreatedAtBetweenOrderByCreatedAtAsc(
                        restaurantId,
                        startOfDay,
                        endOfDay
                );

        return new KitchenDashboardSummaryResponse(
                orders.size(),
                countByStatus(orders, OrderStatus.PENDING),
                countByStatus(orders, OrderStatus.CONFIRMED),
                countByStatus(orders, OrderStatus.PREPARING),
                countByStatus(orders, OrderStatus.READY),
                countByStatus(orders, OrderStatus.ACCEPTED),
                countByStatus(orders, OrderStatus.PICKED_UP),
                countByStatus(orders, OrderStatus.DELIVERED),
                countByStatus(orders, OrderStatus.CANCELLED),
                countDelayedOrders(orders, delayCutoff)
        );
    }

    private long countByStatus(List<Order> orders, OrderStatus status) {
        return orders.stream()
                .filter(order -> order.getStatus() == status)
                .count();
    }

    private long countDelayedOrders(
            List<Order> orders,
            LocalDateTime delayCutoff
    ) {
        return orders.stream()
                .filter(order -> DELAY_ELIGIBLE_STATUSES.contains(order.getStatus()))
                .filter(order -> order.getCreatedAt().isBefore(delayCutoff))
                .count();
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
