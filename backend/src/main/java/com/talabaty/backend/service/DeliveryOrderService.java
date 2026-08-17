package com.talabaty.backend.service;

import com.talabaty.backend.dto.response.OrderHistoryResponse;
import com.talabaty.backend.dto.response.OrderSummaryresponse;

import java.util.List;
import java.util.Optional;

public interface DeliveryOrderService {
    List<OrderSummaryresponse> getAvailableOrders(Long riderId);
    OrderSummaryresponse acceptOrder(Long orderId, Long riderId);
    OrderSummaryresponse markPickedUp(Long orderId, Long riderId);
    OrderSummaryresponse markDelivered(Long orderId, Long riderId);
    void cancelOrder(Long orderId, Long riderId, String reason);
    void updateOnlineStatus(Long riderId, boolean online);
    Optional<OrderSummaryresponse> getActiveOrder(Long riderId);
    List<OrderHistoryResponse> getDeliveryHistory(Long riderId);
}
