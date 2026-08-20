package com.talabaty.backend.service;

import com.talabaty.backend.dto.request.PlaceOrderRequest;
import com.talabaty.backend.dto.response.CustomerOrderDetailsResponse;
import com.talabaty.backend.dto.response.CustomerOrderPageResponse;
import com.talabaty.backend.dto.response.PlaceOrderResponse;
public interface OrderService {

    PlaceOrderResponse placeOrder(Long userId, PlaceOrderRequest request);
    CustomerOrderPageResponse getCustomerOrders(Long userId, int page, int size);
    CustomerOrderDetailsResponse getCustomerOrderDetails(Long userId, Long orderId);
    CustomerOrderDetailsResponse cancelCustomerOrder(Long userId, Long orderId, String reason);
}
