package com.talabaty.backend.auth.service;

import com.talabaty.backend.security.LoginRateLimitProperties;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class LoginRateLimitService {

    private final LoginRateLimitProperties properties;
    private final ConcurrentHashMap<String, AttemptWindow> attempts =
            new ConcurrentHashMap<>();

    public LoginRateLimitService(LoginRateLimitProperties properties) {
        this.properties = properties;
    }

    public void checkLoginAllowed(String email, String clientIp) {
        String key = createKey(email, clientIp);
        AttemptWindow attemptWindow = attempts.get(key);

        if (attemptWindow == null) {
            return;
        }

        if (Instant.now().isAfter(attemptWindow.expiresAt)) {
            attempts.remove(key, attemptWindow);
            return;
        }

        if (attemptWindow.failedAttempts >= properties.getMaxFailedAttempts()) {
            throw new ResponseStatusException(
                    HttpStatus.TOO_MANY_REQUESTS,
                    "Too many failed login attempts. Please try again later."
            );
        }
    }

    public void recordFailedAttempt(String email, String clientIp) {
        String key = createKey(email, clientIp);
        Instant now = Instant.now();

        attempts.compute(key, (ignored, existingWindow) -> {
            if (existingWindow == null || now.isAfter(existingWindow.expiresAt)) {
                // Starts a new failure window for this email and IP address.
                return new AttemptWindow(
                        1,
                        now.plusSeconds(properties.getWindowSeconds())
                );
            }

            return new AttemptWindow(
                    existingWindow.failedAttempts + 1,
                    existingWindow.expiresAt
            );
        });
    }

    public void clearFailedAttempts(String email, String clientIp) {
        // Successful login removes the stored failed-attempt count.
        attempts.remove(createKey(email, clientIp));
    }

    private String createKey(String email, String clientIp) {
        String normalizedEmail = email.trim().toLowerCase(Locale.ROOT);
        String safeClientIp = clientIp == null || clientIp.isBlank()
                ? "unknown"
                : clientIp;

        return normalizedEmail + "|" + safeClientIp;
    }

    private record AttemptWindow(int failedAttempts, Instant expiresAt) {
    }
}