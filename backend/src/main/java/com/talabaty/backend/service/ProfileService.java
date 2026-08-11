package com.talabaty.backend.service;

import com.talabaty.backend.dto.request.UpdateProfileRequest;
import com.talabaty.backend.dto.response.ProfileResponse;

public interface ProfileService {
    ProfileResponse getProfile(String userEmail);
    ProfileResponse updateProfile(String userEmail, UpdateProfileRequest request);
}
