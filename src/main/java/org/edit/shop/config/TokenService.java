package org.edit.shop.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.UUID;

@Service
public class TokenService {

    private final static long EXPIRATION_TIME = 120000;
    private final static String SECRET = "misuperpassword123@#aasdfasdf123@!#";
    private final static SecretKey secretKey = Keys.hmacShaKeyFor(SECRET.getBytes());

    /**
     * Su unico proposito es ver si el token es valido
     */
    public void validateToken (String token) {
        Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parse(token);
    }


    /**
     * Creamos un token cuando se hace login
     */
    public String generate (String username) {

        Date now = new Date();
        Date exp = new Date (now.getTime() + EXPIRATION_TIME);
        return Jwts.builder()
                .id(UUID.randomUUID().toString())
                .subject(username)
                .issuedAt(now)
                .expiration(exp)
                .issuer("ecommerce")
                .signWith(secretKey)
                .compact();
    }

    /**
     * Para saber quien es el usuario a partir del token
     */
    public String getUserFromToken (String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

}
