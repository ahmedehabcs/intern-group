package com.talabaty.backend.service;

import com.talabaty.backend.dto.request.AddCartItemRequest;
import com.talabaty.backend.dto.request.ReplaceCartItemRequest;
import com.talabaty.backend.dto.request.UpdateCartItemQuantityRequest;
import com.talabaty.backend.dto.response.CartResponse;

public interface CartService {

    CartResponse getCart(Long userId);
    CartResponse addItem(Long userId, AddCartItemRequest request);
    CartResponse updateItemQuantity(Long userId, Long cartItemId, UpdateCartItemQuantityRequest request);
    CartResponse replaceItemConfiguration(Long userId, Long cartItemId, ReplaceCartItemRequest request);
    CartResponse removeItem(Long userId, Long cartItemId);
    void clearCart(Long userId);
}