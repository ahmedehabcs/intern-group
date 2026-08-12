package com.talabaty.backend.security;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.nimbusds.jose.proc.SecurityContext;
import com.talabaty.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.time.Clock;
import java.time.Instant;
import java.util.Base64;

@Service
public class JwtService {

    private final JwtProperties jwtProperties;
    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;
    private final Clock clock;

    // @Autowired is required, not optional: Spring only infers a constructor
    // implicitly when the class declares exactly one. This class has two (the
    // package-private one below lets tests inject a fixed Clock), so without
    // this annotation Spring falls back to a no-arg constructor that does not
    // exist and startup fails with "No default constructor found".
    @Autowired
    public JwtService(JwtProperties jwtProperties) {
        this(jwtProperties, Clock.systemUTC());
    }

    // Package-private: test seam for a fixed Clock (see JwtSecurityTests).
    JwtService(JwtProperties jwtProperties, Clock clock) {
        this.jwtProperties = jwtProperties;
        this.clock = clock;

        byte[] secretBytes = Base64.getDecoder().decode(jwtProperties.getSecret());

        if (secretBytes.length < 32) {
            throw new IllegalStateException(
                    "security.jwt.secret must decode to at least 32 bytes"
            );
        }

        SecretKey secretKey = new SecretKeySpec(secretBytes, "HmacSHA256");

        this.jwtEncoder = new NimbusJwtEncoder(
                new ImmutableSecret<SecurityContext>(secretKey)
        );

        NimbusJwtDecoder decoder = NimbusJwtDecoder
                .withSecretKey(secretKey)
                .macAlgorithm(MacAlgorithm.HS256)
                .build();

        decoder.setJwtValidator(JwtValidators.createDefault());

        this.jwtDecoder = decoder;
    }

    public String generateAccessToken(User user) {
        Instant issuedAt = Instant.now(clock);
        Instant expiresAt = issuedAt.plusSeconds(
                jwtProperties.getAccessTokenExpirationSeconds()
        );

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuedAt(issuedAt)
                .expiresAt(expiresAt)
                .claim("userId", user.getId())
                .claim("email", user.getEmail())
                .claim("role", user.getRole().name())
                .build();

        JwsHeader header = JwsHeader.with(MacAlgorithm.HS256)
                .type("JWT")
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(header, claims))
                .getTokenValue();
    }

    public Jwt validateAccessToken(String token) {
        return jwtDecoder.decode(token);
    }

    public long getAccessTokenExpirationSeconds() {
        return jwtProperties.getAccessTokenExpirationSeconds();
    }
}
