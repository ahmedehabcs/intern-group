package com.talabaty.backend.auth.service.impl;
import com.talabaty.backend.auth.service.LoginRateLimitService;
import com.talabaty.backend.auth.dto.request.LoginRequest;
import com.talabaty.backend.auth.dto.request.RegisterRequest;
import com.talabaty.backend.auth.dto.response.LoginResponse;
import com.talabaty.backend.auth.dto.response.RegisterResponse;
import com.talabaty.backend.auth.service.AuthService;
import com.talabaty.backend.security.JwtService;
import com.talabaty.backend.user.entity.User;
import com.talabaty.backend.user.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    // Tracks failed logins for each email-and-IP combination.
    private final LoginRateLimitService loginRateLimitService;
    public AuthServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            LoginRateLimitService loginRateLimitService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.loginRateLimitService = loginRateLimitService;
    }

    @Override
    public RegisterResponse register(RegisterRequest request) {
        // TODO: implement registration logic
        throw new UnsupportedOperationException("register not implemented");
    }

    @Override
    public LoginResponse login(LoginRequest request, String clientIp) {
        if (request == null
                || request.getEmail() == null
                || request.getEmail().isBlank()
                || request.getPassword() == null
                || request.getPassword().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Email and password are required"
            );
        }

        // Blocks the request if this email and IP exceeded the configured limit.
        loginRateLimitService.checkLoginAllowed(request.getEmail(), clientIp);

        User user = userRepository.findByEmail(request.getEmail())
                .orElse(null);

        if (user == null) {
            loginRateLimitService.recordFailedAttempt(request.getEmail(), clientIp);

            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Invalid email or password"
            );
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            loginRateLimitService.recordFailedAttempt(request.getEmail(), clientIp);

            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Invalid email or password"
            );
        }

        if (!user.isEmailVerified()) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Email is not verified"
            );
        }

        // A successful login clears earlier failures for this email and IP.
        loginRateLimitService.clearFailedAttempts(request.getEmail(), clientIp);

        String accessToken = jwtService.generateAccessToken(user);

        return new LoginResponse(
                user.getRole(),
                user.getEmail(),
                user.getId(),
                jwtService.getAccessTokenExpirationSeconds(),
                "Bearer",
                accessToken
        );
    }
    }
