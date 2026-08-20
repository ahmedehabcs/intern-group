package com.talabaty.backend.repository;

import com.talabaty.backend.model.CustomerProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerProfileRepository extends JpaRepository<CustomerProfile, Long> {
    // Search by name or email (case-insensitive). Pass null/blank search to get all customers.
    @Query("SELECT c FROM CustomerProfile c WHERE " +
            "(:search IS NULL OR :search = '' OR " +
            "LOWER(c.name) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(c.user.email) LIKE LOWER(CONCAT('%', :search, '%')))")
    List<CustomerProfile> searchCustomers(@Param("search") String search);
}
