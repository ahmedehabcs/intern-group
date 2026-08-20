package com.talabaty.backend.repository;

import com.talabaty.backend.model.KitchenOrderCancellation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KitchenOrderCancellationRepository
        extends JpaRepository<KitchenOrderCancellation, Long> {
}
