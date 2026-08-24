package com.talabaty.backend.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterResponse {
    private String message;

    public RegisterResponse() {}

    public RegisterResponse(String message) { this.message = message; }

}
