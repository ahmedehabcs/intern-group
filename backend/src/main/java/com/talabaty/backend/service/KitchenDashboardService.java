package com.talabaty.backend.service;

import com.talabaty.backend.dto.response.KitchenDashboardSummaryResponse;

public interface KitchenDashboardService {

    KitchenDashboardSummaryResponse getTodaySummary(Long userId);
}
