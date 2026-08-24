package com.talabaty.backend.controller;

import lombok.RequiredArgsConstructor;

import com.talabaty.backend.dto.response.RiderAdminResponse;
import com.talabaty.backend.service.AdminRiderService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/riders")
@PreAuthorize("hasRole('ADMIN')")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class AdminRiderController {

    private final AdminRiderService adminRiderService;


    @GetMapping
    public ResponseEntity<List<RiderAdminResponse>> searchRiders(
            @RequestParam(required = false) String search
    ) {
        return ResponseEntity.ok(adminRiderService.searchRiders(search));
    }

    @GetMapping("/pending")
    public ResponseEntity<List<RiderAdminResponse>> getPendingRiders() {
        return ResponseEntity.ok(adminRiderService.getPendingRiders());
    }

    @PutMapping("/{riderId}/approve")
    public ResponseEntity<RiderAdminResponse> approveRider(@PathVariable Long riderId) {
        return ResponseEntity.ok(adminRiderService.approveRider(riderId));
    }

    @PutMapping("/{riderId}/reject")
    public ResponseEntity<RiderAdminResponse> rejectRider(@PathVariable Long riderId) {
        return ResponseEntity.ok(adminRiderService.rejectRider(riderId));
    }

    @PutMapping("/{riderId}/deactivate")
    public ResponseEntity<RiderAdminResponse> deactivateRider(@PathVariable Long riderId) {
        return ResponseEntity.ok(adminRiderService.deactivateRider(riderId));
    }
}
