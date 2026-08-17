package com.talabaty.backend.repository;

import com.talabaty.backend.model.OrderCancellationLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderCancellationLogRepository extends JpaRepository<OrderCancellationLog, Long> {
}