package com.talabaty.backend.controller;

import com.talabaty.backend.dto.response.CategoryResponse;
import com.talabaty.backend.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import com.talabaty.backend.config.OpenApiConfig;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
// Groups this endpoint in Swagger
@Tag(name = "Customer Categories", description = "Browse restaurant cuisines and categories")
@SecurityRequirement(name = OpenApiConfig.SECURITY_SCHEME_NAME)
@RestController
@RequestMapping("/api/customer/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Operation(summary = "Browse active restaurant categories") // Swagger description
    @GetMapping
    public ResponseEntity<List<CategoryResponse>> browseCategories(
            @RequestParam(required = false) String search // Optional search parameter
    ) {
        return ResponseEntity.ok(categoryService.browseCategories(search));
    }
}
