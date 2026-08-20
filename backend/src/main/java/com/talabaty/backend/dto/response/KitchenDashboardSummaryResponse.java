package com.talabaty.backend.dto.response;

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

    public long getTotalOrdersToday() {
        return totalOrdersToday;
    }

    public void setTotalOrdersToday(long totalOrdersToday) {
        this.totalOrdersToday = totalOrdersToday;
    }

    public long getPendingOrders() {
        return pendingOrders;
    }

    public void setPendingOrders(long pendingOrders) {
        this.pendingOrders = pendingOrders;
    }

    public long getConfirmedOrders() {
        return confirmedOrders;
    }

    public void setConfirmedOrders(long confirmedOrders) {
        this.confirmedOrders = confirmedOrders;
    }

    public long getPreparingOrders() {
        return preparingOrders;
    }

    public void setPreparingOrders(long preparingOrders) {
        this.preparingOrders = preparingOrders;
    }

    public long getReadyOrders() {
        return readyOrders;
    }

    public void setReadyOrders(long readyOrders) {
        this.readyOrders = readyOrders;
    }

    public long getAcceptedOrders() {
        return acceptedOrders;
    }

    public void setAcceptedOrders(long acceptedOrders) {
        this.acceptedOrders = acceptedOrders;
    }

    public long getPickedUpOrders() {
        return pickedUpOrders;
    }

    public void setPickedUpOrders(long pickedUpOrders) {
        this.pickedUpOrders = pickedUpOrders;
    }

    public long getDeliveredOrders() {
        return deliveredOrders;
    }

    public void setDeliveredOrders(long deliveredOrders) {
        this.deliveredOrders = deliveredOrders;
    }

    public long getCancelledOrders() {
        return cancelledOrders;
    }

    public void setCancelledOrders(long cancelledOrders) {
        this.cancelledOrders = cancelledOrders;
    }

    public long getDelayedOrders() {
        return delayedOrders;
    }

    public void setDelayedOrders(long delayedOrders) {
        this.delayedOrders = delayedOrders;
    }
}
