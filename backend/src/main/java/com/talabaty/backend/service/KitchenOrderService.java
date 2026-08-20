package com.talabaty.backend.service;

import com.talabaty.backend.dto.response.KitchenOrderDetailsResponse;
import com.talabaty.backend.dto.response.KitchenOrderSummaryResponse;
import com.talabaty.backend.model.OrderStatus;

import java.util.List;

public interface KitchenOrderService {

    List<KitchenOrderSummaryResponse> getActiveOrders(Long userId);

    KitchenOrderDetailsResponse getOrderDetails(Long userId, Long orderId);

    KitchenOrderDetailsResponse updateOrderStatus(
            Long userId,
            Long orderId,
            OrderStatus newStatus
    );

    KitchenOrderDetailsResponse cancelOrder(
            Long userId,
            Long orderId,
            String reason
    );
}
