package com.talabaty.backend.controller;

import lombok.RequiredArgsConstructor;

import com.talabaty.backend.dto.request.UpdateStatusRequest;
import com.talabaty.backend.service.DeliveryOrderService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/delivery/profile")
@PreAuthorize("hasRole('DRIVER')")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class DeliveryProfileController {

    private final DeliveryOrderService deliveryOrderService;


    @PutMapping("/status")
    public ResponseEntity<Void> updateStatus(
            @RequestBody UpdateStatusRequest request,
            Authentication authentication
    ) {
        Long riderId = Long.parseLong(authentication.getName());
        deliveryOrderService.updateOnlineStatus(riderId, request.isOnline());
        return ResponseEntity.noContent().build();
    }
}
