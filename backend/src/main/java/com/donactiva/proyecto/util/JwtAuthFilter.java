package com.donactiva.proyecto.util;

import java.io.IOException;

import io.jsonwebtoken.Claims;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter{

    private JwtUtil jwtUtil;

    public JwtAuthFilter(JwtUtil jwtUtil){
        this.jwtUtil = jwtUtil;
    }

    @SuppressWarnings("null")
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) 
            throws ServletException, IOException{

        String authHeader = request.getHeader("Authorization");

        if(authHeader != null && authHeader.startsWith("Bearer ")){
            String token = authHeader.substring(7);
            Claims claims = jwtUtil.extractAllClaims(token);

            if(jwtUtil.validarToken(token)){
                SecurityContextHolder.getContext().setAuthentication(jwtUtil.getAuthentication(claims));
            }
        }

        filterChain.doFilter(request, response);
    }

}
