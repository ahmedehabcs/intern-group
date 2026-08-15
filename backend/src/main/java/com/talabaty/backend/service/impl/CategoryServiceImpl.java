package com.talabaty.backend.service.impl;

import com.talabaty.backend.dto.response.CategoryResponse;
import com.talabaty.backend.mapper.CategoryMapper;
import com.talabaty.backend.model.Category;
import com.talabaty.backend.repository.CategoryRepository;
import com.talabaty.backend.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(
            CategoryRepository categoryRepository,
            CategoryMapper categoryMapper
    ) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponse> browseCategories() {
        List<Category> categories = categoryRepository
                .findDistinctByIsActiveTrueAndRestaurants_IsActiveTrueOrderByNameAsc();

        return categoryMapper.toResponseList(categories);
    }
}