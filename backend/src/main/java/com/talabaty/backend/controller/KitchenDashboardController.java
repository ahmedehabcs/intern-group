package com.talabaty.backend.controller;

import com.talabaty.backend.dto.response.KitchenDashboardSummaryResponse;
import com.talabaty.backend.service.KitchenDashboardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/kitchen/dashboard")
@Tag(name = "Kitchen Dashboard", description = "View today's kitchen order summary")
@SecurityRequirement(name = "bearerAuth")
public class KitchenDashboardController {

    private final KitchenDashboardService kitchenDashboardService;

    public KitchenDashboardController(KitchenDashboardService kitchenDashboardService) {
        this.kitchenDashboardService = kitchenDashboardService;
    }

    @Operation(summary = "Get today's summary for the kitchen manager's restaurant")
    @GetMapping("/summary")
    public ResponseEntity<KitchenDashboardSummaryResponse> getTodaySummary(
            Authentication authentication
    ) {
        Long userId = Long.valueOf(authentication.getName());

        return ResponseEntity.ok(
                kitchenDashboardService.getTodaySummary(userId)
        );
    }
}
