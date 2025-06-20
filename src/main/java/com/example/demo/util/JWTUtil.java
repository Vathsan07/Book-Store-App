package com.example.demo.util;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {

 private final String secretKey = "thisIsAReallyLongAndSecureJWTSecretKey01234567890";
 private final long validity = 1000 * 60 * 60; 

 public String generateToken(String username) {
     return Jwts.builder()
             .setSubject(username)
             .setIssuedAt(new Date())
             .setExpiration(new Date(System.currentTimeMillis() + validity))
             .signWith(SignatureAlgorithm.HS256, secretKey)
             .compact();
 }

 public String extractUsername(String token) {
     return Jwts.parser().setSigningKey(secretKey)
             .parseClaimsJws(token)
             .getBody().getSubject();
 }

 public boolean validateToken(String token) {
     try {
         extractUsername(token); // throws exception if invalid
         return true;
     } catch (Exception e) {
         return false;
     }
 }
}
