package com.talabaty.backend.repository;
import com.talabaty.backend.model.DeliveryProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import com.talabaty.backend.model.User;

public interface DeliveryProfileRepository extends JpaRepository<DeliveryProfile, Long> {
}