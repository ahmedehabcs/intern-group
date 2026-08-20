package com.talabaty.backend.dto.response;

import com.talabaty.backend.model.OrderStatus;
import com.talabaty.backend.model.PaymentMethod;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class CustomerOrderDetailsResponse {

    private Long id;
    private String restaurantName;
    private String deliveryAddress;
    private BigDecimal subtotal;
    private BigDecimal deliveryFee;
    private BigDecimal totalPrice;
    private OrderStatus status;
    private PaymentMethod paymentMethod;
    private LocalDateTime createdAt;
    private List<OrderItemResponse> orderItems;

    public CustomerOrderDetailsResponse() {
    }

    public CustomerOrderDetailsResponse(Long id, String restaurantName, String deliveryAddress, BigDecimal subtotal, BigDecimal deliveryFee, BigDecimal totalPrice, OrderStatus status, PaymentMethod paymentMethod, LocalDateTime createdAt, List<OrderItemResponse> orderItems) {
        this.id = id;
        this.restaurantName = restaurantName;
        this.deliveryAddress = deliveryAddress;
        this.subtotal = subtotal;
        this.deliveryFee = deliveryFee;
        this.totalPrice = totalPrice;
        this.status = status;
        this.paymentMethod = paymentMethod;
        this.createdAt = createdAt;
        this.orderItems = orderItems;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getRestaurantName() {
        return restaurantName;
    }
    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }
    public String getDeliveryAddress() {
        return deliveryAddress;
    }
    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
    public BigDecimal getSubtotal() {
        return subtotal;
    }
    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
    public BigDecimal getDeliveryFee() {
        return deliveryFee;
    }
    public void setDeliveryFee(BigDecimal deliveryFee) {
        this.deliveryFee = deliveryFee;
    }
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
    public OrderStatus getStatus() {
        return status;
    }
    public void setStatus(OrderStatus status) {
        this.status = status;
    }
    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }
    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public List<OrderItemResponse> getOrderItems() {
        return orderItems;
    }
    public void setOrderItems(List<OrderItemResponse> orderItems) {
        this.orderItems = orderItems;
    }
}