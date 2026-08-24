package com.talabaty.backend.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        // Skip only public auth endpoints - secured endpoints need authentication
        return path.startsWith("/api/auth/signup") ||
               path.startsWith("/api/auth/login") ||
               path.startsWith("/api/auth/verify-otp") ||
               path.startsWith("/api/auth/resend-otp") ||
               path.startsWith("/api/auth/forgot-password") ||
               path.startsWith("/api/auth/reset-password") ||
               path.startsWith("/api/auth/verify-email-change") ||
               path.startsWith("/api/auth/verify-password-change") ||
               path.startsWith("/swagger-ui") ||
               path.startsWith("/v3/api-docs");
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        final String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        logger.debug("Processing request: {} with Authorization header: {}", 
            request.getServletPath(), 
            authorizationHeader != null ? "present" : "missing");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            logger.debug("No valid Bearer token found for {}", request.getServletPath());
            filterChain.doFilter(request, response);
            return;
        }

        final String token = authorizationHeader.substring(7);
        final Long userId;

        try {
            Jwt jwt = jwtService.validateAccessToken(token);
            Number userIdClaim = jwt.getClaim("userId");
            userId = userIdClaim != null ? userIdClaim.longValue() : null;
            logger.debug("JWT validated successfully. userId: {}", userId);
        } catch (JwtException e) {
            logger.error("JWT validation failed: {}", e.getMessage());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid or expired token");
            return;
        }

        if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            try {
               UserDetails userDetails = this.userDetailsService.loadUserByUsername(userId.toString());
               UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                       userDetails,
                       null,
                       userDetails.getAuthorities()
               );
               authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
               SecurityContextHolder.getContext().setAuthentication(authToken);
               logger.debug("Authentication set for user: {}", userId);
            } catch (Exception e) {
               logger.error("Failed to load user details for userId {}: {}", userId, e.getMessage());
               response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
               response.getWriter().write("User not found");
               return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
