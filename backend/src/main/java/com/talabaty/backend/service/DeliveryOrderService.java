package com.talabaty.backend.service;

import com.talabaty.backend.dto.response.OrderSummaryresponse;

import java.util.List;

public interface DeliveryOrderService {
    List<OrderSummaryresponse> getAvailableOrders();
    OrderSummaryresponse acceptOrder(Long orderId, Long riderId);
    OrderSummaryresponse markPickedUp(Long orderId, Long riderId);
}
