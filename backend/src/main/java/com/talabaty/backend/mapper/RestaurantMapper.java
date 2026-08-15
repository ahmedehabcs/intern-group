package com.talabaty.backend.mapper;

import com.talabaty.backend.dto.response.MenuSectionResponse;
import com.talabaty.backend.dto.response.RestaurantDetailsResponse;
import com.talabaty.backend.dto.response.RestaurantResponse;
import com.talabaty.backend.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {

    @Mapping(target = "categories", source = "categoryNames")
    RestaurantResponse toResponse(
            Restaurant restaurant,
            List<String> categoryNames
    );

    @Mapping(target = "menuSections", source = "menuSectionResponses")
    RestaurantDetailsResponse toDetailsResponse(
            Restaurant restaurant,
            List<MenuSectionResponse> menuSectionResponses
    );
}