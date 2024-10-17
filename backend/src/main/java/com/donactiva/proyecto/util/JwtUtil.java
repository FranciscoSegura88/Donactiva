package com.donactiva.proyecto.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generarToken(String nombre, String correo, String contraseña){

        Map<String, Object> claims = new HashMap<>();
        claims.put("nombre", nombre);
        claims.put("correo", correo);
        claims.put("contraseña", contraseña);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(correo)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 4)) //Expira en 4 horas el token
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public Boolean validarToken(String token){

        try{
            Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token);

            return true;
        }catch (Exception e){
            
            return false;
        }
    }

    @SuppressWarnings("unused")
    private Claims extractAllClaims(String token){

        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}
