package com.talabaty.backend.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KitchenDashboardSummaryResponse {

    private long totalOrdersToday;
    private long pendingOrders;
    private long confirmedOrders;
    private long preparingOrders;
    private long readyOrders;
    private long acceptedOrders;
    private long pickedUpOrders;
    private long deliveredOrders;
    private long cancelledOrders;
    private long delayedOrders;

    public KitchenDashboardSummaryResponse() {
    }

    public KitchenDashboardSummaryResponse(
            long totalOrdersToday,
            long pendingOrders,
            long confirmedOrders,
            long preparingOrders,
            long readyOrders,
            long acceptedOrders,
            long pickedUpOrders,
            long deliveredOrders,
            long cancelledOrders,
            long delayedOrders
    ) {
        this.totalOrdersToday = totalOrdersToday;
        this.pendingOrders = pendingOrders;
        this.confirmedOrders = confirmedOrders;
        this.preparingOrders = preparingOrders;
        this.readyOrders = readyOrders;
        this.acceptedOrders = acceptedOrders;
        this.pickedUpOrders = pickedUpOrders;
        this.deliveredOrders = deliveredOrders;
        this.cancelledOrders = cancelledOrders;
        this.delayedOrders = delayedOrders;
    }




















}
