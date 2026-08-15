package com.talabaty.backend.mapper;

import com.talabaty.backend.dto.response.AdminCategoryResponse;
import com.talabaty.backend.dto.response.CategoryResponse;
import com.talabaty.backend.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryResponse toResponse(Category category);

    List<CategoryResponse> toResponseList(List<Category> categories);


    //for admin
    @Mapping(source = "active", target = "isActive")
    AdminCategoryResponse toAdminResponse(Category category);

    List<AdminCategoryResponse> toAdminResponseList(
            List<Category> categories
    );
}