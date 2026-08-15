package com.talabaty.backend.controller;

import com.talabaty.backend.config.OpenApiConfig;
import com.talabaty.backend.dto.request.CategoryStatusFilter;
import com.talabaty.backend.dto.response.AdminCategoryResponse;
import com.talabaty.backend.service.AdminCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(
        name = "Admin Catalog",
        description = "Admin operations for catalog management"
)
@RestController
@RequestMapping("/api/admin/catalog/categories")
@SecurityRequirement(name = OpenApiConfig.SECURITY_SCHEME_NAME)
public class AdminCategoryController {

    private final AdminCategoryService adminCategoryService;

    public AdminCategoryController(
            AdminCategoryService adminCategoryService
    ) {
        this.adminCategoryService = adminCategoryService;
    }

    @Operation(
            summary = "Browse and search all categories",
            description = """
                    Browse all categories, including active, inactive, and
                    categories not assigned to restaurants. Optionally filter
                    by a partial category name and category status.
                    """
    )
    @GetMapping
    public ResponseEntity<List<AdminCategoryResponse>> browseCategories(
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "ALL")
            CategoryStatusFilter status
    ) {
        return ResponseEntity.ok(
                adminCategoryService.browseCategories(search, status)
        );
    }
}