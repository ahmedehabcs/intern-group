package com.talabaty.backend.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

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

    /**
     * Optional, unlike the driver payload where it is mandatory.
     * <p>
     * A customer can finish signing up without one and add it later through
     * PUT /api/profile, so requiring it here would reject registrations that
     * the signup form already treats as valid. It is stored when supplied -
     * previously the field did not exist at all, so a number typed into the
     * form was dropped by Jackson and the profile came back with
     * phoneNumber: null.
     */
    @Size(max = 20, message = "Phone number must not exceed 20 characters")
    private String phoneNumber;

    // Getters and Setters

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "CustomerSignupRequest{" +
                "email='" + email + '\'' +
                ", password='[PROTECTED]'" +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
