package com.example.ecommers.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

/**
 * This utility class provides methods for generating, validating, and extracting JWT tokens.
 */
@Slf4j
@Component
public class JwtUtil {
    @Value("${jwt.secret.key}")
    private String secretKey;
    @Value("${jwt.time.expiration}")
    private String timeExpiration;

    /**
     * Generates an access token for the specified username.
     *
     * @param email The username for which to generate the token.
     * @return The generated access token.
     */

    //Generar token de acceso
    public String generateAccesToken(String email){
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+Long.parseLong(timeExpiration)))
                .signWith(getSigantureKey(), SignatureAlgorithm.HS256) //compatibilidad con el key encriptado autogenerado
                .compact();
    }
    /**
     * Obtains the signature key for token signing.
     *
     * @return The signature key for token signing.
     */
    //Obtener firma del token
    private Key getSigantureKey(){
        byte[]keyBytes= Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    /**
     * Validates the authenticity of a token.
     *
     * @param token The token to be validated.
     * @return True if the token is valid, false otherwise.
     */
    //Validar token de acceso
    public boolean isTokenValid(String token){
        try {
            //leyendo el token
            Jwts.parserBuilder()
                    .setSigningKey(getSigantureKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return true;
        }catch (Exception ex){
            log.error("token invalido,error: ".concat(ex.getMessage()));
            return false;
        }
    }
    //Obtener claims del token (informacion del cuerpo del token https://jwt.io/introduction)
    public Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSigantureKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    //Obtener un solo calim
    public <T> T getClaim (String token, Function<Claims,T>claimsFunction){
        Claims claims=extractAllClaims(token);
        return claimsFunction.apply(claims);
    }
    //Obtener username del token
    public String getUserNameToken(String token){
        return getClaim(token,Claims::getSubject);
    }

    /**
     * Generates a valid JWT token with the specified role.
     *
     * @param role The role to be included in the token's claims.
     * @return The generated valid JWT token.
     */
    //generar tokens JWT válidos

    public String generateValidToken(String role) {

        String token = Jwts.builder()
                .setSubject("user") // Sujeto del token
                .claim("role", role) // Roles o permisos del usuario
                .setIssuedAt(new Date(System.currentTimeMillis())) // Fecha de emisión del token
                .setExpiration(new Date(System.currentTimeMillis()+Long.parseLong(timeExpiration))) // Fecha de expiración del token
                .signWith(getSigantureKey(),SignatureAlgorithm.HS256 ) // Firma del token con clave secreta
                .compact();
        return token;
    }
    public String extractTokenFromAuthentication(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated() || !(authentication instanceof UsernamePasswordAuthenticationToken)) {
            return null;
        }

        UsernamePasswordAuthenticationToken authToken = (UsernamePasswordAuthenticationToken) authentication;
        Object credentials = authToken.getCredentials();

        if (credentials instanceof String) {
            return (String) credentials;
        }

        return null;
    }
}
