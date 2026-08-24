package com.talabaty.backend.repository;

import com.talabaty.backend.model.ApprovalStatus;
import com.talabaty.backend.model.DeliveryProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryProfileRepository extends JpaRepository<DeliveryProfile, Long> {
    List<DeliveryProfile> findByNameContainingIgnoreCaseOrderByNameAsc(String name);
    List<DeliveryProfile> findAllByOrderByNameAsc();
    List<DeliveryProfile> findByApprovalStatus(ApprovalStatus approvalStatus);
    List<DeliveryProfile> findByApprovalStatusOrderByNameAsc(ApprovalStatus approvalStatus);
    List<DeliveryProfile> findByApprovalStatusAndNameContainingIgnoreCaseOrderByNameAsc(ApprovalStatus approvalStatus, String name);
    
}
