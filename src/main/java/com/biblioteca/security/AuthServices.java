package com.biblioteca.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.Entity.trabajadores;
import com.biblioteca.service.trabajadoresService;

@Service
public class AuthServices {

	
	  @Autowired
	    private trabajadoresService trabajadorService;

	    @Autowired
	    private JwtUtil jwtUtil;

	    public String login(String email, String password) {
	        trabajadores user = trabajadorService.login(email, password);

	        String rol = user.getRol().getRoles();

	        return jwtUtil.generarToken(email, rol);
	    }
}
