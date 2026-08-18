package com.talabaty.backend.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EarningsDayResponse {
    private LocalDate date;
    private BigDecimal earnings;
    private long deliveryCount;

    public EarningsDayResponse(LocalDate date, BigDecimal earnings, long deliveryCount) {
        this.date = date;
        this.earnings = earnings;
        this.deliveryCount = deliveryCount;
    }

    public LocalDate getDate() { return date; }
    public BigDecimal getEarnings() { return earnings; }
    public long getDeliveryCount() { return deliveryCount; }
}
