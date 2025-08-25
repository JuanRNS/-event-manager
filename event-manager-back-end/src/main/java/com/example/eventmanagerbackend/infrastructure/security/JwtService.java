package com.example.eventmanagerbackend.infrastructure.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collectors;


@Service
public class JwtService {

    private final JwtEncoder jwtEncoder;
    public JwtService(JwtEncoder enconder) {
        this.jwtEncoder = enconder;
    }

    public String generateToken(Authentication authentication) {
        Instant now = Instant.now();
        long exp = 3600;

        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder()
                .issuer("event-manager-back-end")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(exp))
                .subject(authentication.getName())
                .claim("scope", scope)
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }




}
