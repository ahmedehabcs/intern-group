package com.talabaty.backend.service;

import com.talabaty.backend.dto.request.CategoryStatusFilter;
import com.talabaty.backend.dto.response.AdminCategoryResponse;

import java.util.List;

public interface AdminCategoryService {

    List<AdminCategoryResponse> browseCategories(
            String search,
            CategoryStatusFilter status
    );
}