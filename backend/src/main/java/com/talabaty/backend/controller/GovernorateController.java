package com.talabaty.backend.controller;

import com.talabaty.backend.dto.response.GovernorateResponse;
import com.talabaty.backend.repository.GovernorateRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Groups this endpoint in Swagger
@Tag(name = "Governorates", description = "Browse governorates for address and restaurant forms")
@RestController
@RequestMapping("/api/governorates")
public class GovernorateController {

    private final GovernorateRepository governorateRepository;

    public GovernorateController(GovernorateRepository governorateRepository) {
        this.governorateRepository = governorateRepository;
    }

    /**
     * Public because the address form is reachable during checkout and the set
     * of governorates is reference data, not anything customer-specific.
     *
     * <p>Reads the repository directly: there is no rule to apply beyond a
     * sorted list, so a service layer here would only forward the call.
     */
    @Operation(
            summary = "List governorates",
            description = "Publicly list every governorate, ordered by name, for populating address "
                    + "and restaurant forms with the ids those endpoints expect.",
            tags = "Governorates"
    )
    @GetMapping
    public ResponseEntity<List<GovernorateResponse>> listGovernorates() {
        List<GovernorateResponse> governorates = governorateRepository
                .findAll(Sort.by(Sort.Direction.ASC, "name"))
                .stream()
                .map(governorate -> new GovernorateResponse(
                        governorate.getId(),
                        governorate.getName()
                ))
                .toList();

        return ResponseEntity.ok(governorates);
    }
}
