package com.talabaty.backend.service.impl;

import com.talabaty.backend.dto.response.CategoryResponse;
import com.talabaty.backend.model.Category;
import com.talabaty.backend.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTests {

    @Mock
    private CategoryRepository categoryRepository;

    private CategoryServiceImpl categoryService;

    @BeforeEach
    void setUp() {
        categoryService = new CategoryServiceImpl(categoryRepository);
    }

    @Test
    void browseCategoriesReturnsAllActiveCuisineCategories() {
        Category chinese = category(1L, "Chinese", "Traditional Chinese cuisine");
        when(categoryRepository.findDistinctByIsActiveTrueAndRestaurants_IsActiveTrueOrderByNameAsc())
                .thenReturn(List.of(chinese));

        List<CategoryResponse> result = categoryService.browseCategories();

        assertEquals(1, result.size());
        assertEquals(1L, result.getFirst().getId());
        assertEquals("Chinese", result.getFirst().getName());
        assertEquals("Traditional Chinese cuisine", result.getFirst().getDescription());
        verify(categoryRepository).findDistinctByIsActiveTrueAndRestaurants_IsActiveTrueOrderByNameAsc();
    }

    private Category category(Long id, String name, String description) {
        Category category = new Category(name, description, true);
        category.setId(id);
        return category;
    }
}
