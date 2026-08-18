package com.talabaty.backend.service;

import com.talabaty.backend.dto.request.PlaceOrderRequest;
import com.talabaty.backend.dto.response.PlaceOrderResponse;

public interface OrderService {

    PlaceOrderResponse placeOrder(Long userId, PlaceOrderRequest request);
}
