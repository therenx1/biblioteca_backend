package com.biblioteca.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	private final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	// Duración del token: 1 hora
	private final long EXPIRATION_TIME = 1000 * 60 * 60;

	public String generarToken(String username, String rol) {
		return Jwts.builder().setSubject(username).claim("rol", rol).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).signWith(SECRET_KEY).compact();
	}

	public Claims obtenerClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody();
	}

	public String obtenerUsername(String token) {
		return obtenerClaims(token).getSubject();
	}

	public boolean tokenValido(String token) {
		try {
			obtenerClaims(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
