package com.p8labs.security.member.utils;

import com.p8labs.security.member.enums.AuthorityType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.*;

public class JwtUtil {
    private static final String SECRET = "your-256-bit-secret-your-256-bit-secret";
    private static final long EXPIRATION = 1000 * 60 * 60;
    private static final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    public static String generateToken(Long id, String memberId, List<String> roles) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", id);
        claims.put("roles", roles);

        return Jwts.builder()
                .setClaims(claims)                  // 커스텀 클레임 추가
                .setSubject(memberId)               // 표준 subject (사용자명 등)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public static Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public static Long extractEmail(String token) {
        return extractAllClaims(token).get("id", Long.class);
    }

    public static List<String> extractRole(String token) {
        Claims claims = extractAllClaims(token);
        Object rolesObject = claims.get("roles");

        if (rolesObject instanceof List<?>) {
            return ((List<?>) rolesObject).stream()
                    .filter(String.class::isInstance)
                    .map(String.class::cast)
                    .toList();
        }
        return Collections.emptyList();
    }

    public static String extractMemberId(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public static boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}
