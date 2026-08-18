package com.talabaty.backend.dto.response;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class EarningsSummaryResponse {
    private LocalDate from;
    private LocalDate to;
    private BigDecimal totalEarnings;
    private long totalDeliveries;
    private List<EarningsDayResponse> byDay;

    public EarningsSummaryResponse(LocalDate from, LocalDate to, BigDecimal totalEarnings,
                                   long totalDeliveries, List<EarningsDayResponse> byDay) {
        this.from = from;
        this.to = to;
        this.totalEarnings = totalEarnings;
        this.totalDeliveries = totalDeliveries;
        this.byDay = byDay;
    }

    public LocalDate getFrom() { return from; }
    public LocalDate getTo() { return to; }
    public BigDecimal getTotalEarnings() { return totalEarnings; }
    public long getTotalDeliveries() { return totalDeliveries; }
    public List<EarningsDayResponse> getByDay() { return byDay; }
}
