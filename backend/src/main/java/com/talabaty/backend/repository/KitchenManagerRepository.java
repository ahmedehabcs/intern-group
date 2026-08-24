package com.talabaty.backend.repository;

import com.talabaty.backend.model.KitchenManager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KitchenManagerRepository extends JpaRepository<KitchenManager, Long> {

    Optional<KitchenManager> findByUserId(Long userId);
}
