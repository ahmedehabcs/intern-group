package com.talabaty.backend.controller;

import lombok.RequiredArgsConstructor;
import com.talabaty.backend.dto.request.AddressRequest;
import com.talabaty.backend.dto.response.AddressResponse;
import com.talabaty.backend.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@Tag(name = "Addresses", description = "Manage the authenticated customer's addresses")
@RestController
@RequestMapping("/api/addresses")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;


    @Operation(summary = "Get the authenticated customer's addresses")
    @GetMapping
    public ResponseEntity<List<AddressResponse>> getAddresses(Authentication authentication) {
        Long userId = getAuthenticatedUserId(authentication);
        return ResponseEntity.ok(addressService.getCustomerAddresses(userId));
    }

    @Operation(summary = "Get one address owned by the authenticated customer")
    @GetMapping("/{addressId}")
    public ResponseEntity<AddressResponse> getAddress(
            Authentication authentication,
            @PathVariable @Positive Long addressId
    ) {
        Long userId = getAuthenticatedUserId(authentication);
        return ResponseEntity.ok(addressService.getCustomerAddress(userId, addressId));
    }

    @Operation(summary = "Create a customer address")
    @PostMapping
    public ResponseEntity<AddressResponse> createAddress(
            Authentication authentication,
            @Valid @RequestBody AddressRequest request
    ) {
        Long userId = getAuthenticatedUserId(authentication);
        AddressResponse response = addressService.createAddress(userId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Update an address owned by the authenticated customer")
    @PutMapping("/{addressId}")
    public ResponseEntity<AddressResponse> updateAddress(
            Authentication authentication,
            @PathVariable @Positive Long addressId,
            @Valid @RequestBody AddressRequest request
    ) {
        Long userId = getAuthenticatedUserId(authentication);
        return ResponseEntity.ok(addressService.updateAddress(userId, addressId, request));
    }

    @Operation(summary = "Set an address as the customer's default address")
    @PatchMapping("/{addressId}/default")
    public ResponseEntity<AddressResponse> setDefaultAddress(
            Authentication authentication,
            @PathVariable @Positive Long addressId
    ) {
        Long userId = getAuthenticatedUserId(authentication);
        return ResponseEntity.ok(addressService.setDefaultAddress(userId, addressId));
    }

    @Operation(summary = "Delete an address owned by the authenticated customer")
    @DeleteMapping("/{addressId}")
    public ResponseEntity<Void> deleteAddress(
            Authentication authentication,
            @PathVariable @Positive Long addressId
    ) {
        Long userId = getAuthenticatedUserId(authentication);
        addressService.deleteAddress(userId, addressId);
        return ResponseEntity.noContent().build();
    }

    private Long getAuthenticatedUserId(Authentication authentication) {
        return Long.valueOf(authentication.getName());
    }
}
