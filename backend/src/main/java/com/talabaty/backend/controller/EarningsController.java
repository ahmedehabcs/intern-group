package com.talabaty.backend.controller;


import com.talabaty.backend.dto.response.EarningsSummaryResponse;
import com.talabaty.backend.service.DeliveryOrderService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/delivery/earnings")
@PreAuthorize("hasRole('DRIVER')")
@SecurityRequirement(name = "bearerAuth")
public class EarningsController {

    private final DeliveryOrderService deliveryOrderService;

    public EarningsController(DeliveryOrderService deliveryOrderService) {
        this.deliveryOrderService = deliveryOrderService;
    }

    @GetMapping("/summary")
    public ResponseEntity<EarningsSummaryResponse> getEarningsSummary(
            @RequestParam(required = false, defaultValue = "today") String period,
            @RequestParam(required = false) LocalDate from,
            @RequestParam(required = false) LocalDate to,
            Authentication authentication
    ) {
        Long riderId = Long.parseLong(authentication.getName());
        LocalDate today = LocalDate.now();

        LocalDate resolvedFrom;
        LocalDate resolvedTo;

        if (from != null && to != null) {
            // Custom range mode takes priority if explicitly provided
            if (from.isAfter(to)) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "'from' date must not be after 'to' date"
                );
            }
            resolvedFrom = from;
            resolvedTo = to;
        } else {
            // Preset mode — defaults to "today" if period isn't sent at all
            resolvedTo = today;
            resolvedFrom = switch (period.toLowerCase()) {
                case "today" -> today;
                case "week" -> today.minusDays(6);
                case "month" -> today.minusDays(29);
                default -> throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Invalid period: use today, week, or month"
                );
            };
        }

        return ResponseEntity.ok(deliveryOrderService.getEarningsSummary(riderId, resolvedFrom, resolvedTo));
    }
}