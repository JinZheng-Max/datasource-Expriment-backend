package com.experiment.studentManagement.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Map;

public class JwtUtils {

    public static String generateToken(Map<String, Object> claims, String secretKey, long ttlMillis) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        Date exp = new Date(nowMillis + ttlMillis);
        Key key = hmacKey(secretKey);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public static Claims parseToken(String token, String secretKey) {
        Key key = hmacKey(secretKey);
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private static Key hmacKey(String secret) {
        byte[] raw = secret == null ? new byte[0] : secret.getBytes(StandardCharsets.UTF_8);
        // JJWT 要求 HS256 至少 256 bit（32 字节）。不足则对 secret 进行 SHA-256 派生，保证长度与安全性。
        if (raw.length < 32) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                raw = md.digest(raw);
            } catch (NoSuchAlgorithmException e) {
                throw new IllegalStateException("SHA-256 not available", e);
            }
        }
        return Keys.hmacShaKeyFor(raw);
    }
}
