package com.talabaty.backend.service.impl;

import com.talabaty.backend.dto.response.EarningsDayResponse;
import com.talabaty.backend.dto.response.EarningsSummaryResponse;
import com.talabaty.backend.dto.response.OrderHistoryResponse;
import com.talabaty.backend.dto.response.OrderSummaryresponse;
import com.talabaty.backend.model.Address;
import com.talabaty.backend.model.DeliveryProfile;
import com.talabaty.backend.model.Order;
import com.talabaty.backend.model.OrderCancellationLog;
import com.talabaty.backend.model.OrderStatus;
import com.talabaty.backend.repository.DeliveryProfileRepository;
import com.talabaty.backend.repository.OrderCancellationLogRepository;
import com.talabaty.backend.repository.OrderRepository;
import com.talabaty.backend.service.DeliveryOrderService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DeliveryOrderServiceImpl implements DeliveryOrderService {

    private final OrderRepository orderRepository;
    private final OrderCancellationLogRepository cancellationLogRepository;
    private final DeliveryProfileRepository deliveryProfileRepository;

    public DeliveryOrderServiceImpl(OrderRepository orderRepository,
                                    OrderCancellationLogRepository cancellationLogRepository,
                                    DeliveryProfileRepository deliveryProfileRepository) {
        this.orderRepository = orderRepository;
        this.cancellationLogRepository = cancellationLogRepository;
        this.deliveryProfileRepository = deliveryProfileRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderSummaryresponse> getAvailableOrders(Long riderId) {
        DeliveryProfile rider = deliveryProfileRepository.findById(riderId)
                .orElseThrow(() -> new EntityNotFoundException("Rider profile not found"));

        if (!Boolean.TRUE.equals(rider.getOnline())) {
            return List.of(); // offline riders see no available orders
        }

        return orderRepository.findByStatusAndRiderIsNullOrderByIdAsc(OrderStatus.READY)
                .stream()
                .map(this::toSummaryDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public OrderSummaryresponse acceptOrder(Long orderId, Long riderId) {
        boolean hasActiveOrder = orderRepository
                .findFirstByRiderIdAndStatusIn(riderId, List.of(OrderStatus.ACCEPTED, OrderStatus.PICKED_UP))
                .isPresent();

        if (hasActiveOrder) {
            throw new IllegalStateException("You already have an active order — finish it before accepting another");
        }

        int updated = orderRepository.acceptOrderIfAvailable(
                orderId, riderId, OrderStatus.ACCEPTED, OrderStatus.READY
        );

        if (updated == 0) {
            throw new IllegalStateException("Order is no longer available for pickup");
        }

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found after accept"));

        return toSummaryDto(order);
    }

    @Override
    @Transactional
    public OrderSummaryresponse markPickedUp(Long orderId, Long riderId) {
        int updated = orderRepository.markPickedUpIfOwned(
                orderId, riderId, OrderStatus.PICKED_UP, OrderStatus.ACCEPTED
        );

        if (updated == 0) {
            throw new IllegalStateException(
                    "Cannot mark picked up — order not found, not yours, or not in ACCEPTED state"
            );
        }

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found after pickup update"));

        return toSummaryDto(order);
    }

    @Override
    @Transactional
    public OrderSummaryresponse markDelivered(Long orderId, Long riderId) {
        int updated = orderRepository.markDeliveredIfOwned(
                orderId, riderId, OrderStatus.DELIVERED, OrderStatus.PICKED_UP
        );

        if (updated == 0) {
            throw new IllegalStateException(
                    "Cannot mark delivered — order not found, not yours, or not in PICKED_UP state"
            );
        }

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found after delivery update"));

        return toSummaryDto(order);
    }

    @Override
    @Transactional
    public void cancelOrder(Long orderId, Long riderId, String reason) {
        int updated = orderRepository.cancelOrderIfOwned(
                orderId, riderId, OrderStatus.READY, OrderStatus.ACCEPTED
        );

        if (updated == 0) {
            throw new IllegalStateException(
                    "Cannot cancel — order not found, not yours, or already picked up"
            );
        }

        cancellationLogRepository.save(new OrderCancellationLog(orderId, riderId, reason));
    }

    @Override
    @Transactional
    public void updateOnlineStatus(Long riderId, boolean online) {
        DeliveryProfile rider = deliveryProfileRepository.findById(riderId)
                .orElseThrow(() -> new EntityNotFoundException("Rider profile not found"));

        rider.setOnline(online);
        deliveryProfileRepository.save(rider);
    }

    private OrderSummaryresponse toSummaryDto(Order order) {
        Address address = order.getAddress();
        String formattedAddress = String.format("%s, Bldg %s, Floor %s, Apt %s, %s (%s)",
                address.getStreet(),
                address.getBuilding(),
                address.getFloor(),
                address.getApartment(),
                address.getCity(),
                address.getGovernorate() != null ? address.getGovernorate().getName() : "N/A"
        );

        return new OrderSummaryresponse(
                order.getId(),
                order.getRestaurant().getName(),
                formattedAddress,
                order.getOrderItems().size(),
                order.getTotalPrice(),
                order.getDeliveryFee()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<OrderSummaryresponse> getActiveOrder(Long riderId) {
        return orderRepository
                .findFirstByRiderIdAndStatusIn(riderId, List.of(OrderStatus.ACCEPTED, OrderStatus.PICKED_UP))
                .map(this::toSummaryDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderHistoryResponse> getDeliveryHistory(Long riderId) {
        return orderRepository.findByRiderIdAndStatusOrderByUpdatedAtDesc(riderId, OrderStatus.DELIVERED)
                .stream()
                .map(order -> new OrderHistoryResponse(
                        order.getId(),
                        order.getRestaurant().getName(),
                        order.getUpdatedAt(),
                        order.getDeliveryFee(),
                        order.getTotalPrice()
                ))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public EarningsSummaryResponse getEarningsSummary(Long riderId, LocalDate from, LocalDate to) {
        LocalDateTime startDateTime = from.atStartOfDay();
        LocalDateTime endDateTime = to.plusDays(1).atStartOfDay(); // exclusive upper bound

        List<Order> deliveredOrders = orderRepository.findDeliveredOrdersInRange(riderId, startDateTime, endDateTime);

        Map<LocalDate, List<Order>> groupedByDay = deliveredOrders.stream()
                .collect(Collectors.groupingBy(o -> o.getUpdatedAt().toLocalDate()));

        List<EarningsDayResponse> byDay = new ArrayList<>();
        for (Map.Entry<LocalDate, List<Order>> entry : groupedByDay.entrySet()) {
            BigDecimal dayTotal = entry.getValue().stream()
                    .map(Order::getDeliveryFee)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            byDay.add(new EarningsDayResponse(entry.getKey(), dayTotal, entry.getValue().size()));
        }
        byDay.sort(Comparator.comparing(EarningsDayResponse::getDate));

        BigDecimal totalEarnings = deliveredOrders.stream()
                .map(Order::getDeliveryFee)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new EarningsSummaryResponse(from, to, totalEarnings, deliveredOrders.size(), byDay);
    }
}