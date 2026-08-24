package com.talabaty.backend.service.impl;


import com.talabaty.backend.dto.response.RiderAdminResponse;
import com.talabaty.backend.model.ApprovalStatus;
import com.talabaty.backend.model.DeliveryProfile;
import com.talabaty.backend.repository.DeliveryProfileRepository;
import com.talabaty.backend.service.AdminRiderService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminRiderServiceImpl implements AdminRiderService {

    private final DeliveryProfileRepository deliveryProfileRepository;

    public AdminRiderServiceImpl(DeliveryProfileRepository deliveryProfileRepository) {
        this.deliveryProfileRepository = deliveryProfileRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<RiderAdminResponse> getPendingRiders() {
        return deliveryProfileRepository.findByApprovalStatus(ApprovalStatus.PENDING)
                .stream()
                .map(this::toAdminResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public RiderAdminResponse approveRider(Long riderId) {
        DeliveryProfile rider = deliveryProfileRepository.findById(riderId)
                .orElseThrow(() -> new EntityNotFoundException("Rider not found"));
        rider.setApprovalStatus(ApprovalStatus.APPROVED);
        deliveryProfileRepository.save(rider);
        return toAdminResponse(rider);
    }

    @Override
    @Transactional
    public RiderAdminResponse rejectRider(Long riderId) {
        DeliveryProfile rider = deliveryProfileRepository.findById(riderId)
                .orElseThrow(() -> new EntityNotFoundException("Rider not found"));
        rider.setApprovalStatus(ApprovalStatus.REJECTED);
        deliveryProfileRepository.save(rider);
        return toAdminResponse(rider);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RiderAdminResponse> searchRiders(String search) {
        List<DeliveryProfile> riders = (search == null || search.isBlank())
                ? deliveryProfileRepository.findByApprovalStatusOrderByNameAsc(ApprovalStatus.APPROVED)
                : deliveryProfileRepository.findByApprovalStatusAndNameContainingIgnoreCaseOrderByNameAsc(ApprovalStatus.APPROVED, search);

        return riders.stream()
                .map(this::toAdminResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public RiderAdminResponse deactivateRider(Long riderId) {
        DeliveryProfile rider = deliveryProfileRepository.findById(riderId)
                .orElseThrow(() -> new EntityNotFoundException("Rider not found"));

        rider.setActive(false);
        rider.setOnline(false); // force offline too — can't stay online while deactivated
        deliveryProfileRepository.save(rider);
        return toAdminResponse(rider);
    }

    private RiderAdminResponse toAdminResponse(DeliveryProfile rider) {
        return new RiderAdminResponse(
                rider.getId(),
                rider.getName(),
                rider.getPhoneNumber(),
                rider.getVehicleType(),
                rider.getLicenseNumber(),
                rider.getNationalId(),
                rider.getApprovalStatus().name(),
                rider.getOnline(),
                rider.getActive()
        );
    }
}
