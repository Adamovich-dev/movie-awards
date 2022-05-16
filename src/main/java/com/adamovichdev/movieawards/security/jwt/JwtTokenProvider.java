package com.adamovichdev.movieawards.security.jwt;

import com.adamovichdev.movieawards.security.dto.CustomUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expirationDateInMs}")
    private long tokenValidityInSeconds;

    public String createToken(Authentication authentication) {

        Date validity = new Date(new Date().getTime() + this.tokenValidityInSeconds);
        Map<String, Object> claims = new HashMap<>();
        CustomUser principal = (CustomUser) authentication.getPrincipal();
        claims.put("userId", principal.getUserId());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(principal.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public Authentication getAuthentication(String token) {

        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();

        String principal = claims.getSubject();

        return new UsernamePasswordAuthenticationToken(principal, "", new ArrayList<>());
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(authToken);
            return true;
        } catch (SignatureException exception) {
//            LOGGER.error("SignatureException occurred in  JwtTokenProvider::validateToken. Invalid JWT signature : " + exception.getMessage());
            return false;
        }
    }
}
