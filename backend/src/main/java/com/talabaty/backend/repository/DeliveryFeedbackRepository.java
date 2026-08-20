package com.talabaty.backend.repository;

import com.talabaty.backend.model.DeliveryFeedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryFeedbackRepository
        extends JpaRepository<DeliveryFeedback, Long> {

    boolean existsByOrderId(Long orderId);

    List<DeliveryFeedback> findByRiderIdOrderByCreatedAtDesc(Long riderId);
}