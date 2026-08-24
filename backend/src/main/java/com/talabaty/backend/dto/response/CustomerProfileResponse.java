package com.talabaty.backend.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerProfileResponse {
    private String email;
    private String name;
    private String phoneNumber;

    public CustomerProfileResponse(String email, String name, String phoneNumber) {
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    // Getters and Setters





}
