package com.talabaty.backend.service;

import com.talabaty.backend.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> browseCategories(String search);
}
