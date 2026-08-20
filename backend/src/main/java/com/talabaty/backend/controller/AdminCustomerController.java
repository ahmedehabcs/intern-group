package com.talabaty.backend.controller;

import com.talabaty.backend.dto.response.CustomerAdminResponse;
import com.talabaty.backend.service.AdminCustomerService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/customers")
@PreAuthorize("hasRole('ADMIN')")
@SecurityRequirement(name = "bearerAuth")
public class AdminCustomerController {

    private final AdminCustomerService adminCustomerService;

    public AdminCustomerController(AdminCustomerService adminCustomerService) {
        this.adminCustomerService = adminCustomerService;
    }

    @GetMapping
    public ResponseEntity<List<CustomerAdminResponse>> searchCustomers(
            @RequestParam(required = false) String search
    ) {
        return ResponseEntity.ok(adminCustomerService.searchCustomers(search));
    }
}