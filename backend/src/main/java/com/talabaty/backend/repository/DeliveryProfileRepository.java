package com.talabaty.backend.repository;

import com.talabaty.backend.model.DeliveryProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryProfileRepository extends JpaRepository<DeliveryProfile, Long> {
}
