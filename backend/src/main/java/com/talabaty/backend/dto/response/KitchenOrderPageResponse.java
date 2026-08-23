package com.talabaty.backend.dto.response;

import java.util.List;

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

    public List<KitchenOrderSummaryResponse> getOrders() { return orders; }
    public void setOrders(List<KitchenOrderSummaryResponse> orders) { this.orders = orders; }
    public int getPage() { return page; }
    public void setPage(int page) { this.page = page; }
    public int getSize() { return size; }
    public void setSize(int size) { this.size = size; }
    public long getTotalElements() { return totalElements; }
    public void setTotalElements(long totalElements) { this.totalElements = totalElements; }
    public int getTotalPages() { return totalPages; }
    public void setTotalPages(int totalPages) { this.totalPages = totalPages; }
    public boolean isFirst() { return first; }
    public void setFirst(boolean first) { this.first = first; }
    public boolean isLast() { return last; }
    public void setLast(boolean last) { this.last = last; }
}
