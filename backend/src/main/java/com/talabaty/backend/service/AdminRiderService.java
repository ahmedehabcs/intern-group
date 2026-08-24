package com.talabaty.backend.service;

import com.talabaty.backend.dto.response.RiderAdminResponse;

import java.util.List;

public interface AdminRiderService {
    List<RiderAdminResponse> getPendingRiders();
    RiderAdminResponse approveRider(Long riderId);
    RiderAdminResponse rejectRider(Long riderId);
    List<RiderAdminResponse> searchRiders(String search);
    RiderAdminResponse deactivateRider(Long riderId);
}