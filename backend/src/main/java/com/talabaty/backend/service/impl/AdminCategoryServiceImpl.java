package com.talabaty.backend.service.impl;

import com.talabaty.backend.dto.request.CategoryStatusFilter;
import com.talabaty.backend.dto.response.AdminCategoryResponse;
import com.talabaty.backend.mapper.CategoryMapper;
import com.talabaty.backend.model.Category;
import com.talabaty.backend.repository.CategoryRepository;
import com.talabaty.backend.service.AdminCategoryService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminCategoryServiceImpl implements AdminCategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public AdminCategoryServiceImpl(
            CategoryRepository categoryRepository,
            CategoryMapper categoryMapper
    ) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AdminCategoryResponse> browseCategories(
            String search,
            CategoryStatusFilter status
    ) {
        String normalizedSearch = normalizeSearch(search);
        Boolean activeFilter = toActiveFilter(status);

        List<Category> categories;

        if (normalizedSearch == null && activeFilter == null) {
            categories = categoryRepository.findAll(
                    Sort.by(Sort.Direction.ASC, "name")
            );
        } else if (normalizedSearch != null && activeFilter == null) {
            categories = categoryRepository
                    .findByNameContainingIgnoreCaseOrderByNameAsc(
                            normalizedSearch
                    );
        } else if (normalizedSearch == null) {
            categories = categoryRepository
                    .findByIsActiveOrderByNameAsc(activeFilter);
        } else {
            categories = categoryRepository
                    .findByNameContainingIgnoreCaseAndIsActiveOrderByNameAsc(
                            normalizedSearch,
                            activeFilter
                    );
        }

        return categoryMapper.toAdminResponseList(categories);
    }

    private String normalizeSearch(String search) {
        if (search == null || search.trim().isEmpty()) {
            return null;
        }

        return search.trim();
    }

    private Boolean toActiveFilter(CategoryStatusFilter status) {
        if (status == null || status == CategoryStatusFilter.ALL) {
            return null;
        }

        return status == CategoryStatusFilter.ACTIVE;
    }
}