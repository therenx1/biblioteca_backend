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

import com.biblioteca.Entity.autores;
import com.biblioteca.service.autoresService;

@RestController
@RequestMapping("/api/autores")
@CrossOrigin(origins = {"http://localhost:4200", "https://bibliotecafrontend-production.up.railway.app"}, allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class AutoresController {

	@Autowired
	private autoresService autoresService;

	
	@GetMapping
	public ResponseEntity<List<autores>> listarAutores() {
		List<autores> autor = autoresService.listarAutores();

		if (autor.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(autor);

	}

	@GetMapping("/{id}")
	public ResponseEntity<autores> obtenerPorId(@PathVariable int id) {
		autores autor = autoresService.obtenerPorId(id);

		if (autor == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(autor);
	}

	@PostMapping("/grabar")
	public ResponseEntity<autores> agregarAutores(@RequestBody autores autor) {
		try {

			autores nuevo = autoresService.agregarAutores(autor);
			return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PutMapping("{id}")
	public ResponseEntity<autores> editarAutores(@PathVariable int id, @RequestBody autores autorActualizado) {

		try {

			autores editarAutores = autoresService.editarAutores(id, autorActualizado);
			return ResponseEntity.ok(editarAutores);
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")

	public ResponseEntity<Void> eliminarAutores(@PathVariable int id) {
		try {
			autores autor = autoresService.obtenerPorId(id);

			if (autor == null) {
				return ResponseEntity.notFound().build();
			}

			autoresService.eliminarAutores(id);
			return ResponseEntity.noContent().build();

		} catch (Exception e) {
			return ResponseEntity.status(500).build();
		}
	}
}
