package com.issuemoa.learning.presentation.jwt;

import com.issuemoa.learning.domain.exception.UsersNotFoundException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;

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

    public String resolveToken(HttpServletRequest request) {
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(authorization) && authorization.startsWith("Bearer"))
            return authorization.substring(7);
        return "";
    }

    public Long getUserId(HttpServletRequest request) {
        String token = resolveToken(request);

        if (token.isEmpty())
            throw new UsersNotFoundException("존재하지 않는 사용자입니다.");

        Claims claims = getClaims(token);
        return ((Number) claims.get("id")).longValue();
    }
}