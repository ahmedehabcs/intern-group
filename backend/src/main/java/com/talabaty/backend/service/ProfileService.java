package com.talabaty.backend.service;

import com.fasterxml.jackson.databind.JsonNode;

public interface ProfileService {
    Object getProfile(String userEmail);
    void updateProfile(String userEmail, JsonNode requestBody);
}
