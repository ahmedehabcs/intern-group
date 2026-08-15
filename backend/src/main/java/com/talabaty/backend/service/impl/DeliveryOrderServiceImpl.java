package com.talabaty.backend.service.impl;


import com.talabaty.backend.dto.response.OrderSummaryresponse;

import com.talabaty.backend.dto.response.OrderSummaryresponse;
import com.talabaty.backend.model.Address;
import com.talabaty.backend.model.Order;
import com.talabaty.backend.model.OrderStatus;
import com.talabaty.backend.repository.OrderRepository;
import com.talabaty.backend.service.DeliveryOrderService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeliveryOrderServiceImpl implements DeliveryOrderService {

    private final OrderRepository orderRepository;

    public DeliveryOrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderSummaryresponse> getAvailableOrders() {
        return orderRepository.findByStatusAndRiderIsNullOrderByIdAsc(OrderStatus.READY)
                .stream()
                .map(this::toSummaryDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public OrderSummaryresponse acceptOrder(Long orderId, Long riderId) {
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
}