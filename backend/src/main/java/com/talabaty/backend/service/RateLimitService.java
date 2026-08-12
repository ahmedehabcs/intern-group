package com.talabaty.backend.service;

import com.talabaty.backend.security.LoginRateLimitProperties;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RateLimitService {

    private final LoginRateLimitProperties properties;
    private final ConcurrentHashMap<String, AttemptWindow> attempts =
            new ConcurrentHashMap<>();

    public RateLimitService(LoginRateLimitProperties properties) {
        this.properties = properties;
    }

    public void checkAllowed(String action, String email, String clientIp) {
        String key = createKey(action, email, clientIp);
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
                    "Too many failed attempts for this action. Please try again later."
            );
        }
    }

    public void recordFailure(String action, String email, String clientIp) {
        String key = createKey(action, email, clientIp);
        Instant now = Instant.now();

        attempts.compute(key, (ignored, existingWindow) -> {
            if (existingWindow == null || now.isAfter(existingWindow.expiresAt)) {
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

    public void clearFailures(String action, String email, String clientIp) {
        attempts.remove(createKey(action, email, clientIp));
    }

    private String createKey(String action, String email, String clientIp) {
        String normalizedEmail = email.trim().toLowerCase(Locale.ROOT);
        String safeClientIp = clientIp == null || clientIp.isBlank()
                ? "unknown"
                : clientIp;

        return action + "|" + normalizedEmail + "|" + safeClientIp;
    }

    private record AttemptWindow(int failedAttempts, Instant expiresAt) {
    }
}
