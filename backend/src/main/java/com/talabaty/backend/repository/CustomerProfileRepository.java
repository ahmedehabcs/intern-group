package com.talabaty.backend.repository;
import com.talabaty.backend.model.CustomerProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import com.talabaty.backend.model.User;

public interface CustomerProfileRepository extends JpaRepository<CustomerProfile, Long> {
}