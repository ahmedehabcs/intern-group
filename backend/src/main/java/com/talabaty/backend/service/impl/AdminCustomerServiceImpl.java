package com.talabaty.backend.service.impl;

import com.talabaty.backend.dto.response.CustomerAdminResponse;
import com.talabaty.backend.model.CustomerProfile;
import com.talabaty.backend.repository.CustomerProfileRepository;
import com.talabaty.backend.service.AdminCustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminCustomerServiceImpl implements AdminCustomerService {

    private final CustomerProfileRepository customerProfileRepository;

    public AdminCustomerServiceImpl(CustomerProfileRepository customerProfileRepository) {
        this.customerProfileRepository = customerProfileRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerAdminResponse> searchCustomers(String search) {
        return customerProfileRepository.searchCustomers(search)
                .stream()
                .map(this::toAdminResponse)
                .collect(Collectors.toList());
    }

    private CustomerAdminResponse toAdminResponse(CustomerProfile customer) {
        return new CustomerAdminResponse(
                customer.getId(),
                customer.getName(),
                customer.getUser().getEmail(),
                customer.getPhoneNumber()
        );
    }
}