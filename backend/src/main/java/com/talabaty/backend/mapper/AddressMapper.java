package com.talabaty.backend.mapper;

import com.talabaty.backend.dto.request.AddressRequest;
import com.talabaty.backend.dto.response.AddressResponse;
import com.talabaty.backend.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    @Mapping(target = "governorateId", source = "governorate.id")
    @Mapping(target = "governorateName", source = "governorate.name")
    AddressResponse toResponse(Address address);

    List<AddressResponse> toResponseList(List<Address> addresses);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "governorate", ignore = true)
    @Mapping(target = "default", ignore = true)
    Address toEntity(AddressRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "governorate", ignore = true)
    @Mapping(target = "default", ignore = true)
    void updateEntity(AddressRequest request, @MappingTarget Address address);
}
