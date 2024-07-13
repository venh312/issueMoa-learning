package com.issuemoa.learning.presentation.jwt;

import com.issuemoa.learning.domain.exception.UsersNotFoundException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TokenProvider {
    private final JwtProperties jwtProperties;

    public Claims getClaims(String token) {
        try {
            return Jwts.parserBuilder().setSigningKey(jwtProperties.getSecretKey())
                    .build()
                    .parseClaimsJws(token.replace("Bearer ", ""))
                    .getBody();
        }  catch (SignatureException e) {
            throw new UsersNotFoundException("Invalid token");
        }
    }

    public Long getUserId(String token) {
        if (StringUtils.isBlank(token)) return null;
        Claims claims = getClaims(token);
        return ((Number) claims.get("id")).longValue();
    }
}