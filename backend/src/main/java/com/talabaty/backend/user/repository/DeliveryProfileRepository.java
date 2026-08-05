package com.talabaty.backend.user.repository;
import com.talabaty.backend.user.entity.DeliveryProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import com.talabaty.backend.user.entity.User;

public interface DeliveryProfileRepository extends JpaRepository<DeliveryProfile, Long> {
}