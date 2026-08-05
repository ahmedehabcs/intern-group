package com.talabaty.backend.user.repository;
import com.talabaty.backend.user.entity.CustomerProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import com.talabaty.backend.user.entity.User;

public interface CustomerProfileRepository extends JpaRepository<CustomerProfile, Long> {
}