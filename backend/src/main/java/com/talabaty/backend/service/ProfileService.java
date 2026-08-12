package com.talabaty.backend.service;

import tools.jackson.databind.JsonNode;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
public interface ProfileService {
    Object getProfile(Long userId);
    void updateProfile(Long userId, JsonNode requestBody);
  //  void updateProfile(String userEmail, JsonNode requestBody);
}
