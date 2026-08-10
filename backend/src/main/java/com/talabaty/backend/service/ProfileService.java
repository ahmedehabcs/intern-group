package com.talabaty.backend.service;

import com.talabaty.backend.dto.request.UpdateProfileRequest;

public interface ProfileService {
    void updateProfile(String userEmail, UpdateProfileRequest request);
}
