package com.talabaty.backend.mapper;

import com.talabaty.backend.dto.response.CategoryResponse;
import com.talabaty.backend.model.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryResponse toResponse(Category category);
    List<CategoryResponse> toResponseList(List<Category> categories);
}