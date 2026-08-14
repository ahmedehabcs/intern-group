package com.talabaty.backend.mapper;

import com.talabaty.backend.dto.response.MenuItemSearchResponse;
import com.talabaty.backend.dto.response.RestaurantResponse;
import com.talabaty.backend.dto.response.SearchResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SearchMapper {

    @Mapping(target = "restaurants", source = "restaurants")
    @Mapping(target = "menuItems", source = "menuItems")
    SearchResponse toResponse(
            List<RestaurantResponse> restaurants,
            List<MenuItemSearchResponse> menuItems
    );
}