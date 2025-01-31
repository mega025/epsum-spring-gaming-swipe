package com.rinndp.gamingswipe.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;


/*
*
* Estructura de un token que va a ser generado
*
 */
@Component
public class JwtUtil {

    // secretKey = jwt.secret del archivo application.properties
    @Value("${jwt.secret}")
    private String secretKey;

    // Fecha de caducidad
    // Recomendado 30min - 1h. No más a no ser que sea personal
    @Value("${jwt.expiration}")
    private long expirationTime;

    private SecretKey getSigningKey () {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(this.secretKey));
    }

    /*
    * JWT -> Librería de seguridad de tokens
    * setSubject -> aquí añadimos los elementos que enviemos al token
    * setIssuedAt -> fecha de creación del token
    * setExpiration -> cuando caduca el token. Se selecciona la fecha actual en ms y se suma la fecha de expiración
    * signWith -> firma con la clave privada
    * compact -> para generar el token público
    */

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(getSigningKey())
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            JwtParser parser = Jwts.parser()
                    .setSigningKey(getSigningKey())
                    .build();
            parser.parseClaimsJws(token);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }


    public String extractEmail (String token) {
        JwtParser parser = Jwts.parser()
                .setSigningKey(getSigningKey())
                .build();
        Claims claims = parser.parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}
