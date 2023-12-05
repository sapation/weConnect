package com.webtech.socialBackendApi.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtProvider {
    private static SecretKey key = Keys.hmacShaKeyFor(JwtConstant.JWT_SECRET_KEY.getBytes());

    public static String generateToken(Authentication authentication) {
        return Jwts.builder()
                .setIssuer("Webtechsolution").setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+86400000))
                .claim("email", authentication.name())
                .compact();

    }

    public static String getEmailFromJwtToken(String jwt) {
        //Bearer token
        jwt = jwt.substring(7);
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key).build().parseClaimsJwt(jwt).getBody();

        return String.valueOf(claims.get("email"));
    }
}
