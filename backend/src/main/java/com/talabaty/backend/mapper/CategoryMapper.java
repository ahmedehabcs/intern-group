package com.talabaty.backend.mapper;

import com.talabaty.backend.dto.request.CategoryRequest;
import com.talabaty.backend.dto.response.AdminCategoryResponse;
import com.talabaty.backend.dto.response.CategoryResponse;
import com.talabaty.backend.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryResponse toResponse(Category category);
    List<CategoryResponse> toResponseList(List<Category> categories);

    AdminCategoryResponse toAdminResponse(Category category);

    List<AdminCategoryResponse> toAdminResponseList(List<Category> categories);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "restaurants", ignore = true)
    Category toEntity(CategoryRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "restaurants", ignore = true)
    void updateEntity(CategoryRequest request, @MappingTarget Category category);
}
