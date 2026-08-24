package com.talabaty.backend.security;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.Positive;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Validated
@ConfigurationProperties(prefix = "security.login-rate-limit")
@Getter
@Setter
public class LoginRateLimitProperties {

    // Maximum failed logins allowed before blocking further attempts.
    @Positive
    private int maxFailedAttempts;

    // Number of seconds before failed attempts expire.
    @Positive
    private long windowSeconds;




}
