package com.issuemoa.learning.presentation.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

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

    public Long getUserId(String token) {
        if (StringUtils.isBlank(token)) return null;
        Claims claims = getClaims(token);
        return ((Number) claims.get("id")).longValue();
    }
}