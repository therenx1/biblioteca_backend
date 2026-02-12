package com.biblioteca.security;

import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        System.out.println("URL solicitada: " + request.getRequestURI());
        System.out.println("Header Authorization: " + authHeader);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            System.out.println("Token extraído: " + token.substring(0, Math.min(20, token.length())) + "...");

            if (jwtUtil.tokenValido(token)) {
                Claims claims = jwtUtil.obtenerClaims(token);
                String username = claims.getSubject();
                String rol = claims.get("rol", String.class);

                System.out.println("Token válido");
                System.out.println("Usuario: " + username);
                System.out.println("Rol: " + rol);

      
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(
                                username,
                                null,
                                Collections.singleton(
                                    new SimpleGrantedAuthority(rol)
                                )
                        );

                SecurityContextHolder.getContext().setAuthentication(auth);
            } else {
                System.out.println("Token inválido");
            }
        } else {
            System.out.println("No hay token en la petición");
        }

        filterChain.doFilter(request, response);
    }
}
