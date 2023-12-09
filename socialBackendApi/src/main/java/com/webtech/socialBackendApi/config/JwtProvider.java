package com.webtech.socialBackendApi.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;


import javax.crypto.SecretKey;
import java.util.Date;

@Slf4j
public class JwtProvider {
    private static final SecretKey key = Keys.hmacShaKeyFor(JwtConstant.JWT_SECRET_KEY.getBytes());

    public static String generateToken(Authentication authentication) {
        try{
            return Jwts.builder()
                    .setIssuer("Webtechsolution").setIssuedAt(new Date())
                    .setExpiration(new Date(new Date().getTime()+86400000))
                    .claim("email", authentication.getName())
                    .signWith(key)
                    .compact();
        } catch (Throwable t) {
            log.error("Failure during static initialization", t);
            throw t;
        }

    }

    public static String getEmailFromJwtToken(String jwt) {
        //Bearer token
        jwt = jwt.substring(7);
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key).build().parseClaimsJws(jwt).getBody();

        return String.valueOf(claims.get("email"));
    }
}
