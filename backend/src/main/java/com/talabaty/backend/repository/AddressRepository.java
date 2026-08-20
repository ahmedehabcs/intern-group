package com.talabaty.backend.repository;

import com.talabaty.backend.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    Optional<Address> findByIdAndCustomerId(Long addressId, Long customerId);

    List<Address> findAllByCustomerIdOrderByIsDefaultDescIdAsc(Long customerId);

    Optional<Address> findByCustomerIdAndIsDefaultTrue(Long customerId);

    Optional<Address> findFirstByCustomerIdOrderByIdAsc(Long customerId);

    boolean existsByCustomerId(Long customerId);
}
