package com.talabaty.backend.service.impl;

import com.talabaty.backend.dto.request.AddressRequest;
import com.talabaty.backend.dto.response.AddressResponse;
import com.talabaty.backend.mapper.AddressMapper;
import com.talabaty.backend.model.Address;
import com.talabaty.backend.model.CustomerProfile;
import com.talabaty.backend.model.Governorate;
import com.talabaty.backend.repository.AddressRepository;
import com.talabaty.backend.repository.CustomerProfileRepository;
import com.talabaty.backend.repository.GovernorateRepository;
import com.talabaty.backend.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final CustomerProfileRepository customerProfileRepository;
    private final GovernorateRepository governorateRepository;
    private final AddressMapper addressMapper;

    public AddressServiceImpl(
            AddressRepository addressRepository,
            CustomerProfileRepository customerProfileRepository,
            GovernorateRepository governorateRepository,
            AddressMapper addressMapper
    ) {
        this.addressRepository = addressRepository;
        this.customerProfileRepository = customerProfileRepository;
        this.governorateRepository = governorateRepository;
        this.addressMapper = addressMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AddressResponse> getCustomerAddresses(Long userId) {
        CustomerProfile customer = requireCustomer(userId);

        List<Address> addresses = addressRepository
                .findAllByCustomerIdOrderByIsDefaultDescIdAsc(customer.getId());

        return addressMapper.toResponseList(addresses);
    }

    @Override
    @Transactional(readOnly = true)
    public AddressResponse getCustomerAddress(Long userId, Long addressId) {
        CustomerProfile customer = requireCustomer(userId);
        Address address = requireOwnedAddress(addressId, customer.getId());

        return addressMapper.toResponse(address);
    }

    @Override
    @Transactional
    public AddressResponse createAddress(Long userId, AddressRequest request) {

        CustomerProfile customer = requireCustomer(userId);

        // Get governorate automatically from governorate name
        Governorate governorate =
                requireGovernorate(request.getGovernorate());

        Address address = addressMapper.toEntity(request);

        address.setCustomer(customer);
        address.setGovernorate(governorate);

        address.setDefault(
                !addressRepository.existsByCustomerId(customer.getId())
        );

        return addressMapper.toResponse(
                addressRepository.save(address)
        );
    }

    @Override
    @Transactional
    public AddressResponse updateAddress(
            Long userId,
            Long addressId,
            AddressRequest request
    ) {

        CustomerProfile customer = requireCustomer(userId);

        Address address =
                requireOwnedAddress(addressId, customer.getId());

        // Get governorate automatically from governorate name
        Governorate governorate =
                requireGovernorate(request.getGovernorate());

        addressMapper.updateEntity(request, address);

        address.setGovernorate(governorate);

        return addressMapper.toResponse(
                addressRepository.save(address)
        );
    }

    @Override
    @Transactional
    public AddressResponse setDefaultAddress(
            Long userId,
            Long addressId
    ) {

        CustomerProfile customer = requireCustomer(userId);

        Address newDefaultAddress =
                requireOwnedAddress(addressId, customer.getId());

        if (newDefaultAddress.isDefault()) {
            return addressMapper.toResponse(newDefaultAddress);
        }

        addressRepository.findByCustomerIdAndIsDefaultTrue(customer.getId())
                .ifPresent(currentDefaultAddress ->
                        currentDefaultAddress.setDefault(false)
                );

        addressRepository.flush();

        newDefaultAddress.setDefault(true);

        return addressMapper.toResponse(
                addressRepository.save(newDefaultAddress)
        );
    }

    @Override
    @Transactional
    public void deleteAddress(Long userId, Long addressId) {

        CustomerProfile customer = requireCustomer(userId);

        Address address =
                requireOwnedAddress(addressId, customer.getId());

        boolean deletedAddressWasDefault =
                address.isDefault();

        addressRepository.delete(address);

        addressRepository.flush();

        if (deletedAddressWasDefault) {

            addressRepository
                    .findFirstByCustomerIdOrderByIdAsc(customer.getId())
                    .ifPresent(nextDefaultAddress ->
                            nextDefaultAddress.setDefault(true)
                    );
        }
    }

    private CustomerProfile requireCustomer(Long userId) {

        return customerProfileRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.FORBIDDEN,
                        "Address operations are available only to customers"
                ));
    }

    private Address requireOwnedAddress(
            Long addressId,
            Long customerId
    ) {

        return addressRepository
                .findByIdAndCustomerId(addressId, customerId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Address not found"
                ));
    }

    private Governorate requireGovernorate(
            String governorateName
    ) {

        return governorateRepository
                .findByNameIgnoreCase(governorateName)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Governorate not found: " + governorateName
                ));
    }
}