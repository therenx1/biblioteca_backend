package com.biblioteca.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.Entity.trabajadores;
import com.biblioteca.repository.ITrabajadoresRepository;
import com.biblioteca.security.JwtUtil;
import com.biblioteca.service.trabajadoresService;

@RestController
@RequestMapping("api/admin")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class AdminController {
	 
    @Autowired
    private trabajadoresService trabajadorService;
    
    @Autowired
    private ITrabajadoresRepository trabajadorRepository;
    
	@Autowired
	private JwtUtil jwtUtil;
    
    @GetMapping("/trabajadores")
    public ResponseEntity<List<trabajadores>> listarTrabajadores() {
    	List<trabajadores> lista = trabajadorService.listarTrabajador();
    	return ResponseEntity.ok(lista);
    }
    
    @PutMapping("/trabajadores/{id}")
    public ResponseEntity<?> editarTrabajador(@PathVariable int id, @RequestBody trabajadores datos) {
    	try {
			trabajadores actualizar = trabajadorService.editarTrabajador(id, datos);
			return ResponseEntity.ok(actualizar);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
    }

    @DeleteMapping("/trabajadores/{id}")
    public ResponseEntity<?> eliminarTrabajador(@PathVariable int id) {
    	try {
			trabajadorService.eliminarTrabajador(id);
			return ResponseEntity.ok("Trabajador eliminado correctamente");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
    }

    @PostMapping("/cambiar-password/{id}")
    public ResponseEntity<?> cambiarPassword(@PathVariable int id, @RequestBody Map<String, String> body) {
    	try {
    		String nueva = body.get("password");
    		trabajadorService.cambiarPassword(id, nueva);
    		return ResponseEntity.ok("Contraseña actualizada");
    	} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
    }
    
    @GetMapping("/me")
    public ResponseEntity<?> obtenerUsuarioLogueado(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no proporcionado");
        }

        String token = authHeader.substring(7);

        try {
            String email = jwtUtil.obtenerUsername(token);

            trabajadores usuario = trabajadorRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Token inválido o expirado: " + e.getMessage());
        }
    }
    

    @PostMapping("/crear-bibliotecario")
    public ResponseEntity<?> crearBibliotecario(@RequestBody trabajadores bibliotecario) {
        try {
            trabajadores nuevo = trabajadorService.crearPorRol(bibliotecario, "Bibliotecario");
            return ResponseEntity.ok("Bibliotecario creado exitosamente");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

   
    @PostMapping("/crear-auxiliar")
    public ResponseEntity<?> crearAuxiliar(@RequestBody trabajadores auxiliar) {
        try {
            trabajadores nuevo = trabajadorService.crearPorRol(auxiliar, "Auxiliar de Biblioteca");
            return ResponseEntity.ok("Auxiliar de biblioteca creado exitosamente");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
