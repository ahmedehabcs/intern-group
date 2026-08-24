package com.talabaty.backend.dto.request;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Getter
@Setter
public class CustomerSignupRequest {

    @NotBlank(message = "Email address is required")
    @Email(message = "Invalid email format")
    @Pattern(
            regexp = "(?i)^[a-zA-Z0-9._%+-]+@(gmail\\.com|yahoo\\.com|hotmail\\.com|outlook\\.com)$",
            message = "Email must end with a valid domain (e.g., gmail.com, yahoo.com, hotmail.com, outlook.com)"
    )
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, and one number"
    )
    private String password;

    @NotBlank(message = "Name is required")
    private String name;

    // Getters and Setters







    @Override
    public String toString() {
        return "CustomerSignupRequest{" +
                "email='" + email + '\'' +
                ", password='[PROTECTED]'" +
                ", name='" + name + '\'' +
                '}';
    }
}
