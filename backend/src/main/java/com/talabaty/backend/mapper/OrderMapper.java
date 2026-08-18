package com.talabaty.backend.mapper;

import com.talabaty.backend.dto.response.OrderAddonResponse;
import com.talabaty.backend.dto.response.OrderItemResponse;
import com.talabaty.backend.dto.response.PlaceOrderResponse;
import com.talabaty.backend.model.HistoricalOrderItemAddon;
import com.talabaty.backend.model.Order;
import com.talabaty.backend.model.OrderItem;
import org.mapstruct.Mapper;

import java.math.BigDecimal;
import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    PlaceOrderResponse toPlaceOrderResponse(Order order);

    OrderItemResponse toOrderItemResponse(OrderItem orderItem);

    List<OrderItemResponse> toOrderItemResponseList(List<OrderItem> orderItems);

    OrderAddonResponse toOrderAddonResponse(HistoricalOrderItemAddon addon);

    List<OrderAddonResponse> toOrderAddonResponseList(List<HistoricalOrderItemAddon> addons);

    default BigDecimal toBigDecimal(Double value) {
        if (value == null) {
            return null;
        }

        return BigDecimal.valueOf(value);
    }
}
