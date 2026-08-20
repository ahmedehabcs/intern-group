package com.talabaty.backend.service;

import com.talabaty.backend.dto.request.CategoryRequest;
import com.talabaty.backend.dto.response.AdminCategoryResponse;
import com.talabaty.backend.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> browseCategories();

    List<AdminCategoryResponse> getAllCategories();

    CategoryResponse createCategory(CategoryRequest request);

    CategoryResponse updateCategory(Long id, CategoryRequest request);

    void deleteCategory(Long id);
}
