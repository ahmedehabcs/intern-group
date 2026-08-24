package com.talabaty.backend.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class KitchenOrderPageResponse {
    private List<KitchenOrderSummaryResponse> orders;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
    private boolean first;
    private boolean last;

    public KitchenOrderPageResponse() {}

    public KitchenOrderPageResponse(List<KitchenOrderSummaryResponse> orders, int page, int size,
                                    long totalElements, int totalPages, boolean first, boolean last) {
        this.orders = orders;
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.first = first;
        this.last = last;
    }

}
