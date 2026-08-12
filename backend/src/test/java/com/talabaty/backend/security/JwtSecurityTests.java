package com.talabaty.backend.security;

import com.talabaty.backend.config.SecurityConfig;
import com.talabaty.backend.model.Role;
import com.talabaty.backend.model.User;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class JwtSecurityTests {

    private static final String SECRET = "coOqmi6GoA6vNrcHOCq6qcVOoZ2rBDatn57VYBRNxag=";

    @AfterEach
    void clearSecurityContext() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void generatedTokenValidatesExpectedClaims() {
        JwtService jwtService = jwtService(900);
        User user = persistedUser();

        Jwt jwt = jwtService.validateAccessToken(jwtService.generateAccessToken(user));

        assertEquals(user.getEmail(), jwt.getClaimAsString("email"));
        assertEquals(user.getRole().name(), jwt.getClaimAsString("role"));
        assertNotNull(jwt.getExpiresAt());
    }

    @Test
    void rejectsTamperedToken() {
        JwtService jwtService = jwtService(900);
        User user = persistedUser();
        String token = jwtService.generateAccessToken(user);
        char replacement = token.charAt(token.length() - 1) == 'A' ? 'B' : 'A';
        String tampered = token.substring(0, token.length() - 1) + replacement;

        assertThrows(JwtException.class, () -> jwtService.validateAccessToken(tampered));
    }

    @Test
    void rejectsExpiredToken() {
        Clock oldClock = Clock.fixed(Instant.now().minusSeconds(300), ZoneOffset.UTC);
        JwtService jwtService = jwtService(60, oldClock);
        User user = persistedUser();

        String token = jwtService.generateAccessToken(user);

        assertThrows(JwtException.class, () -> jwtService.validateAccessToken(token));
    }

    @Test
    void filterCreatesUserDetailsPrincipalForValidToken() throws Exception {
        JwtService jwtService = mock(JwtService.class);
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter(jwtService);
        Jwt jwt = Jwt.withTokenValue("token")
                .header("alg", "HS256")
                .claim("email", "driver@example.com")
                .claim("role", "DRIVER")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(900))
                .build();
        when(jwtService.validateAccessToken("token")).thenReturn(jwt);
        MockHttpServletRequest request = bearerRequest("token");

        filter.doFilter(request, new MockHttpServletResponse(), new MockFilterChain());

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = assertInstanceOf(UserDetails.class, principal);
        assertEquals("driver@example.com", userDetails.getUsername());
        assertEquals("ROLE_DRIVER", userDetails.getAuthorities().iterator().next().getAuthority());
    }

    @Test
    void invalidTokenReturnsUnauthorizedAndStopsAuthentication() throws Exception {
        JwtService jwtService = mock(JwtService.class);
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter(jwtService);
        when(jwtService.validateAccessToken("invalid")).thenThrow(new JwtException("bad token"));
        MockHttpServletResponse response = new MockHttpServletResponse();

        filter.doFilter(bearerRequest("invalid"), response, new MockFilterChain());

        assertEquals(HttpServletResponse.SC_UNAUTHORIZED, response.getStatus());
        assertEquals(null, SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    void missingAuthenticationEntryPointReturnsUnauthorized() throws Exception {
        SecurityConfig securityConfig = new SecurityConfig(null);
        MockHttpServletResponse response = new MockHttpServletResponse();

        securityConfig.authenticationEntryPoint().commence(
                new MockHttpServletRequest("GET", "/api/profile"),
                response,
                new InsufficientAuthenticationException("missing token")
        );

        assertEquals(HttpServletResponse.SC_UNAUTHORIZED, response.getStatus());
    }

    private JwtService jwtService(long expirationSeconds) {
        return jwtService(expirationSeconds, Clock.systemUTC());
    }

    private JwtService jwtService(long expirationSeconds, Clock clock) {
        JwtProperties properties = new JwtProperties();
        properties.setSecret(SECRET);
        properties.setAccessTokenExpirationSeconds(expirationSeconds);
        return new JwtService(properties, clock);
    }

    private User persistedUser() {
        User user = new User("customer@example.com", "password", Role.CUSTOMER);
        ReflectionTestUtils.setField(user, "id", 1L);
        return user;
    }

    private MockHttpServletRequest bearerRequest(String token) {
        MockHttpServletRequest request = new MockHttpServletRequest("GET", "/api/profile");
        request.addHeader("Authorization", "Bearer " + token);
        return request;
    }
}
