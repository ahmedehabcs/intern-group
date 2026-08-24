package com.talabaty.backend.service.impl;

import com.talabaty.backend.dto.response.OrderAdminResponse;
import com.talabaty.backend.model.Order;
import com.talabaty.backend.model.OrderStatus;
import com.talabaty.backend.repository.OrderRepository;
import com.talabaty.backend.service.AdminOrderService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminOrderServiceImpl implements AdminOrderService {

    private final OrderRepository orderRepository;

    public AdminOrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderAdminResponse> getOrders(OrderStatus status, Long restaurantId, LocalDate from, LocalDate to) {
        LocalDateTime startDateTime = from != null ? from.atStartOfDay() : null;
        LocalDateTime endDateTime = to != null ? to.plusDays(1).atStartOfDay() : null;

        return orderRepository.findOrdersForAdmin(status, restaurantId, startDateTime, endDateTime)
                .stream()
                .map(this::toAdminResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public OrderAdminResponse cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        if (order.getStatus() == OrderStatus.DELIVERED || order.getStatus() == OrderStatus.CANCELLED) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Cannot cancel an order that is already " + order.getStatus());
        }

        order.setStatus(OrderStatus.CANCELLED);
        order.setRider(null); // free up the rider if one was assigned

        Order saved = orderRepository.save(order);
        return toAdminResponse(saved);
    }

    private OrderAdminResponse toAdminResponse(Order order) {
        return new OrderAdminResponse(
                order.getId(),
                order.getStatus().name(),
                order.getRestaurant().getName(),
                order.getCustomer().getName(),
                order.getRider() != null ? order.getRider().getName() : null,
                order.getTotalPrice(),
                order.getDeliveryFee(),
                order.getUpdatedAt()
        );
    }
}