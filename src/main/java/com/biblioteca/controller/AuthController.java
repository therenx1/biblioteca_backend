package com.biblioteca.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.Dto.LoginRequest;
import com.biblioteca.Entity.trabajadores;
import com.biblioteca.security.AuthServices;
import com.biblioteca.security.JwtUtil;
import com.biblioteca.service.trabajadoresService;

@RestController
@RequestMapping("api/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class AuthController {
    @Autowired
    private AuthServices authService;
    
    @Autowired
    private trabajadoresService trabajadorService;
    
	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            String token = authService.login(request.email(), request.password());

            trabajadores user = trabajadorService.login(request.email(), request.password());
            String rol = user.getRol().getRoles();

            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("rol", user.getRol().getRoles());
            response.put("email", user.getEmail());
            response.put("nombre", user.getNombre());
            response.put("apellido", user.getApellido());
            response.put("edad", user.getEdad());
            response.put("id_trabajador", user.getId_trabajador());

            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", e.getMessage()));
        }
    }
    
    @PostMapping("/crear-admin")
    public ResponseEntity<?> crearAdmin(@RequestBody trabajadores admin) {
        try {
            trabajadores nuevoAdmin = trabajadorService.crearAdmin(admin);
            
            Map<String, String> response = new HashMap<>();
            response.put("mensaje", "Administrador creado exitosamente");
            response.put("email", nuevoAdmin.getEmail());
            
            System.out.println("Admin creado: " + nuevoAdmin.getEmail());
            
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            System.out.println("Error al crear admin: " + e.getMessage());
            return ResponseEntity.badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }

}
