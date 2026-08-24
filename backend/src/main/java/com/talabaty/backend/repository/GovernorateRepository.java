package com.talabaty.backend.repository;

import com.talabaty.backend.model.Governorate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GovernorateRepository extends JpaRepository<Governorate, Long> {

    Optional<Governorate> findByNameIgnoreCase(String name);
}