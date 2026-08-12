package com.talabaty.backend.controller;

import com.talabaty.backend.dto.response.CategoryResponse;
import com.talabaty.backend.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Groups this endpoint in Swagger
@Tag(name = "Categories", description = "Browse restaurant cuisines and categories")
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Operation(
            summary = "Browse active restaurant categories",
            description = "Publicly browse active categories associated with active restaurants.",
            tags = "Categories"
    )
    @GetMapping
    public ResponseEntity<List<CategoryResponse>> browseCategories() {
        return ResponseEntity.ok(categoryService.browseCategories());
    }
}
