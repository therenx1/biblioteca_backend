package com.biblioteca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.biblioteca.Entity.rol;
import com.biblioteca.Entity.trabajadores;
import com.biblioteca.repository.IRolRepository;
import com.biblioteca.repository.ITrabajadoresRepository;
import com.biblioteca.security.JwtUtil;

@Service
public class trabajadoresService {
	
	@Autowired
	private ITrabajadoresRepository trabajadorRepository;
	
	@Autowired
	private IRolRepository rolRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	
	public List<trabajadores> listarTrabajador() {
		return trabajadorRepository.findAll();
	}
	
	public trabajadores editarTrabajador(int id, trabajadores nuevo) {
		trabajadores existentes = trabajadorRepository.findById(id).orElseThrow(() -> new RuntimeException("Trabajador no encontrado"));
		
		existentes.setNombre(nuevo.getNombre());
		existentes.setApellido(nuevo.getApellido());
		existentes.setEmail(nuevo.getEmail());
		existentes.setEdad(nuevo.getEdad());
		return trabajadorRepository.save(existentes);
	}
	
	public void eliminarTrabajador(int id) {
	    trabajadores existe = trabajadorRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Trabajador no encontrado"));

	    trabajadorRepository.delete(existe);
	}

	
	public void cambiarPassword(int id, String nuevaPassword) {
		trabajadores cambiar = trabajadorRepository.findById(id).orElseThrow(() -> new RuntimeException("usuario no encontaddo"));
		
		cambiar.setPassword(passwordEncoder.encode(nuevaPassword));
		trabajadorRepository.save(cambiar);
	}
	
	public trabajadores obtenerPorId(int id_trabajador) {
		return trabajadorRepository.findById(id_trabajador).orElse(null);
	}
	
	public trabajadores getTrabajadoresLogueados(String token) {
	    String email = jwtUtil.obtenerUsername(token);
	    return trabajadorRepository.findByEmail(email)
	            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
	}
	
	public trabajadores crearAdmin(trabajadores nuevo) {
	    Optional<trabajadores> adminExistente = trabajadorRepository.findByRol_Roles("Administrador");
	    
	    if(adminExistente.isPresent()) {
	        throw new RuntimeException("Ya existe un administrador en el sistema. No se pueden crear más administradores.");
	    }
	    
	    Optional<trabajadores> emailExistente = trabajadorRepository.findByEmail(nuevo.getEmail());
	    if(emailExistente.isPresent()) {
	        throw new RuntimeException("El email ya está registrado en el sistema");
	    }
	    
	    rol adminRoles = rolRepository.findByRoles("Administrador")
	        .orElseThrow(() -> new RuntimeException("Rol Administrador no existe en la BD"));
	    
	    nuevo.setRol(adminRoles);
	    nuevo.setPassword(passwordEncoder.encode(nuevo.getPassword()));
	    
	    return trabajadorRepository.save(nuevo);
	}
	
	
	public trabajadores crearPorRol(trabajadores nuevo, String nombreRol) {
		rol roles = rolRepository.findByRoles(nombreRol).orElseThrow(() -> new RuntimeException("Rol no existente :" + nombreRol));
	
		nuevo.setRol(roles);
		nuevo.setPassword(passwordEncoder.encode(nuevo.getPassword()));
		return trabajadorRepository.save(nuevo);
	
	}
	
	
	public trabajadores login(String email, String password) {
		trabajadores encontrado = trabajadorRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Trabajador/Usuario no encontrado"));
		
		if(!passwordEncoder.matches(password, encontrado.getPassword())) {
			throw new RuntimeException("Contraseña incorrecta");
		}
		return encontrado;
	}
}
