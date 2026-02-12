package com.biblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.Entity.usuario;

import com.biblioteca.service.UsuariosService;


@RestController
@RequestMapping("api/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class UsuariosController {

	@Autowired
	private UsuariosService servicio;

	@GetMapping
	public ResponseEntity<List<usuario>> listarUsuarios() {
		List<usuario> usuarios = servicio.listarUsuarios();

		if (usuarios.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(usuarios);
	}

	@GetMapping("{id}")

	public ResponseEntity<usuario> obtenerUsuariosPorId(@PathVariable int id) {
		usuario usuarios = servicio.obtenerUsuariosPorId(id);

		if (usuarios == null) {

			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(usuarios);
	}

	@PostMapping("/grabar")
	public ResponseEntity<usuario> agregarUsuarios(@RequestBody usuario usuarios) {

		try {

			usuario nuevo = servicio.agregarUsuarios(usuarios);
			return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PutMapping("{id}")
	public ResponseEntity<usuario> actualizarUsuario(@PathVariable int id, @RequestBody usuario usuarioActualizado) {

		try {
			usuario usuarioEditado = servicio.editarUsuarios(id, usuarioActualizado);
			return ResponseEntity.ok(usuarioEditado);

		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarUsuarios(@PathVariable int id) {
		try {
			usuario usuarios = servicio.obtenerUsuariosPorId(id);

			if (usuarios == null) {
				return ResponseEntity.notFound().build();
			}

			servicio.eliminarUsuarios(id);
			return ResponseEntity.noContent().build();

		} catch (Exception e) {
			return ResponseEntity.status(500).build();
		}
	}

}