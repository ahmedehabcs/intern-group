package com.talabaty.backend.dto.response;

import lombok.Getter;


@Getter
public class CustomerAdminResponse {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;

    public CustomerAdminResponse(Long id, String name, String email, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

}
