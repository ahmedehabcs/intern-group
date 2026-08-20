package com.talabaty.backend.mapper;

import com.talabaty.backend.dto.response.OrderAddonResponse;
import com.talabaty.backend.dto.response.OrderItemResponse;
import com.talabaty.backend.dto.response.PlaceOrderResponse;
import com.talabaty.backend.model.HistoricalOrderItemAddon;
import com.talabaty.backend.model.Order;
import com.talabaty.backend.model.OrderItem;
import org.mapstruct.Mapper;
import com.talabaty.backend.dto.response.CustomerOrderDetailsResponse;
import com.talabaty.backend.dto.response.CustomerOrderSummaryResponse;
import org.mapstruct.Mapping;
import java.math.BigDecimal;
import java.util.List;
import com.talabaty.backend.dto.response.CustomerOrderPageResponse;
import org.springframework.data.domain.Page;
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
    @Mapping(target = "itemCount", expression = "java(countItems(order))")
    CustomerOrderSummaryResponse toCustomerOrderSummaryResponse(Order order);

    List<CustomerOrderSummaryResponse> toCustomerOrderSummaryResponseList(
            List<Order> orders);

    CustomerOrderDetailsResponse toCustomerOrderDetailsResponse(Order order);
    default Integer countItems(Order order) {
        if (order == null || order.getOrderItems() == null) {
            return 0;
        }

        return order.getOrderItems()
                .stream()
                .map(OrderItem::getQuantity)
                .filter(quantity -> quantity != null)
                .reduce(0, Integer::sum);
    }
    default CustomerOrderPageResponse toCustomerOrderPageResponse(
            Page<Order> orderPage
    ) {
        if (orderPage == null) {
            return null;
        }

        return new CustomerOrderPageResponse(toCustomerOrderSummaryResponseList(orderPage.getContent()), orderPage.getNumber(),
                orderPage.getSize(), orderPage.getTotalElements(), orderPage.getTotalPages(),
                orderPage.isFirst(), orderPage.isLast()
        );
    }
}
