package com.talabaty.backend.service;

import com.talabaty.backend.dto.response.OrderAdminResponse;
import com.talabaty.backend.model.OrderStatus;

import java.time.LocalDate;
import java.util.List;

public interface AdminOrderService {
    List<OrderAdminResponse> getOrders(OrderStatus status, Long restaurantId, LocalDate from, LocalDate to);
    OrderAdminResponse cancelOrder(Long orderId);
}