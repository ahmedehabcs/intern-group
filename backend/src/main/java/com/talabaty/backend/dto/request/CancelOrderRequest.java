package com.talabaty.backend.dto.request;



public class CancelOrderRequest {
    private String reason;

    public CancelOrderRequest() {}

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
}
