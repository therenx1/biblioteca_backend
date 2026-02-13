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

import com.biblioteca.Entity.libros;
import com.biblioteca.service.LibroService;

@RestController
@RequestMapping("/api/libros")
@CrossOrigin(origins = {"http://localhost:4200", "https://bibliotecafrontend-production.up.railway.app"}, allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class LibrosController {

	@Autowired
	private LibroService libroServicio;

	@GetMapping
	public ResponseEntity<List<libros>> listarLibros() {
		List<libros> libro = libroServicio.listarLibros();

		if (libro.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(libro);

	}

	@GetMapping("/{id}")
	public ResponseEntity<libros> obtenerLibrosPorId(@PathVariable int id) {
		libros libro = libroServicio.obtenerLibrosPorId(id);

		if (libro == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(libro);
	}

	@PostMapping("/grabar")
	public ResponseEntity<libros> agregarUsuarios(@RequestBody libros libro) {
		try {

			libros nuevo = libroServicio.agregarLibros(libro);
			return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PutMapping("{id}")
	public ResponseEntity<libros> actualizarLibros(@PathVariable int id, @RequestBody libros libroActualizado) {

		try {

			libros libroEditado = libroServicio.editarLibros(id, libroActualizado);
			return ResponseEntity.ok(libroEditado);
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")

	public ResponseEntity<Void> eliminarLibros(@PathVariable int id) {
		try {
			libros libro = libroServicio.obtenerLibrosPorId(id);

			if (libro == null) {
				return ResponseEntity.notFound().build();
			}

			libroServicio.eliminarLibros(id);
			return ResponseEntity.noContent().build();

		} catch (Exception e) {
			return ResponseEntity.status(500).build();
		}
	}

}