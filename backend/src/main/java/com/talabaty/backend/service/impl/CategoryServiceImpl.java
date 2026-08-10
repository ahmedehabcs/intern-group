package com.talabaty.backend.service.impl;

import com.talabaty.backend.dto.response.CategoryResponse;
import com.talabaty.backend.model.Category;
import com.talabaty.backend.repository.CategoryRepository;
import com.talabaty.backend.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponse> browseCategories(String search) {
        // Normalize the search value and remove leading/trailing spaces.
        String normalizedSearch = search == null ? "" : search.trim();

        List<Category> categories;

        if (normalizedSearch.isEmpty()) {
            // Return all active categories that belong to active restaurants.
            categories = categoryRepository
                    .findDistinctByIsActiveTrueAndRestaurants_IsActiveTrueOrderByNameAsc();
        } else {
            // Search active categories by name.
            categories = categoryRepository
                    .findDistinctByIsActiveTrueAndRestaurants_IsActiveTrueAndNameContainingIgnoreCaseOrderByNameAsc(
                            normalizedSearch
                    );
        }
// Convert Category entities to CategoryResponse DTOs.
        List<CategoryResponse> responses = new ArrayList<>();

        for (Category category : categories) {
            CategoryResponse response = toResponse(category);
            responses.add(response);
        }

        return responses;
    }
    // Map Category entity fields to the response DTO.
    private CategoryResponse toResponse(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getDescription()
        );
    }
}
