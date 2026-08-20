package com.talabaty.backend.service;

import com.talabaty.backend.dto.response.CustomerAdminResponse;

import java.util.List;

public interface AdminCustomerService {
    List<CustomerAdminResponse> searchCustomers(String search);
}