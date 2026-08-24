package com.talabaty.backend.service.impl;

import lombok.RequiredArgsConstructor;

import com.talabaty.backend.dto.request.CategoryRequest;
import com.talabaty.backend.dto.response.AdminCategoryResponse;
import com.talabaty.backend.dto.response.CategoryResponse;
import com.talabaty.backend.mapper.CategoryMapper;
import com.talabaty.backend.model.Category;
import com.talabaty.backend.repository.CategoryRepository;
import com.talabaty.backend.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;


    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponse> browseCategories() {
        List<Category> categories = categoryRepository
                .findDistinctByIsActiveTrueAndRestaurants_IsActiveTrueOrderByNameAsc();

        return categoryMapper.toResponseList(categories);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AdminCategoryResponse> getAllCategories() {
        List<Category> categories = categoryRepository.findAllByOrderByNameAsc();
        return categoryMapper.toAdminResponseList(categories);
    }

    @Override
    @Transactional
    public CategoryResponse createCategory(CategoryRequest request) {
        String categoryName = request.getName().trim();
        validateUniqueName(categoryName, null);

        Category category = categoryMapper.toEntity(request);
        category.setName(categoryName);
        category.setActive(true);

        return categoryMapper.toResponse(categoryRepository.save(category));
    }

    @Override
    @Transactional
    public CategoryResponse updateCategory(Long id, CategoryRequest request) {
        Category category = findCategory(id);
        String categoryName = request.getName().trim();
        validateUniqueName(categoryName, id);

        categoryMapper.updateEntity(request, category);
        category.setName(categoryName);

        return categoryMapper.toResponse(categoryRepository.save(category));
    }

    @Override
    @Transactional
    public void deleteCategory(Long id) {
        Category category = findCategory(id);

        // Keep restaurant links while removing the category from active results.
        category.setActive(false);
        categoryRepository.save(category);
    }

    private Category findCategory(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Category not found"
                ));
    }

    private void validateUniqueName(String name, Long categoryId) {
        boolean nameExists = categoryId == null
                ? categoryRepository.existsByNameIgnoreCase(name)
                : categoryRepository.existsByNameIgnoreCaseAndIdNot(name, categoryId);

        if (nameExists) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Category name already exists"
            );
        }
    }
}
