package com.talabaty.backend.service;

import com.talabaty.backend.dto.request.AddressRequest;
import com.talabaty.backend.dto.response.AddressResponse;

import java.util.List;

public interface AddressService {

    List<AddressResponse> getCustomerAddresses(Long userId);

    AddressResponse getCustomerAddress(Long userId, Long addressId);

    AddressResponse createAddress(Long userId, AddressRequest request);

    AddressResponse updateAddress(Long userId, Long addressId, AddressRequest request);

    AddressResponse setDefaultAddress(Long userId, Long addressId);

    void deleteAddress(Long userId, Long addressId);
}