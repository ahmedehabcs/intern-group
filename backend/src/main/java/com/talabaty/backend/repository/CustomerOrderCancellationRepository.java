package com.talabaty.backend.repository;

import com.talabaty.backend.model.CustomerOrderCancellation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerOrderCancellationRepository
        extends JpaRepository<CustomerOrderCancellation, Long> {
}