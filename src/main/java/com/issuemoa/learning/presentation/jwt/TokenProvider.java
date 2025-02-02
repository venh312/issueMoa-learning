package com.issuemoa.learning.presentation.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class TokenProvider {
    private final JwtProperties jwtProperties;

    public Claims getClaims(String token) {
        try {
            // JWT 파싱
            return Jwts.parserBuilder().setSigningKey(jwtProperties.getSecretKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (MalformedJwtException e) {
            log.error("[getClaims] MalformedJwtException Message : {}", e.getMessage());
        } catch (JwtException e) {
            log.error("[getClaims] JwtException Message : {}", e.getMessage());
        }
        return null;
    }

    public String resolveToken(String bearerToken) {
        return Optional.ofNullable(bearerToken)
                .filter(token -> token.startsWith("Bearer "))
                .map(token -> token.substring(7))
                .orElse("");
    }

    public Long getUserId(String token) {
        log.info("==> Authorization :: {}", token);

        String parseToken = resolveToken(token);

        if (parseToken.isEmpty()) return 0L;

        Claims claims = getClaims(parseToken);
        return ((Number) claims.get("id")).longValue();
    }
}