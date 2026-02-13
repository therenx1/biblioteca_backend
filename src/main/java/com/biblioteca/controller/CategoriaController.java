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

import com.biblioteca.Entity.categoria;
import com.biblioteca.service.categoriaService;

@RestController
@RequestMapping("/api/categoria")
@CrossOrigin(origins = {"http://localhost:4200", "https://bibliotecafrontend-production.up.railway.app"}, allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class CategoriaController {

	@Autowired
	private categoriaService categoriaService;

	
	@GetMapping
	public ResponseEntity<List<categoria>> listarCategoria() {
		List<categoria> categoria = categoriaService.listarCategoria();

		if (categoria.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(categoria);

	}

	@GetMapping("/{id}")
	public ResponseEntity<categoria> obtenerPorId(@PathVariable int id) {
		categoria categoria = categoriaService.obtenerPorId(id);

		if (categoria == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(categoria);
	}

	@PostMapping("/grabar")
	public ResponseEntity<categoria> agregarCategoria(@RequestBody categoria categoria) {
		try {

			categoria nuevo = categoriaService.agregarCategoria(categoria);
			return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PutMapping("{id}")
	public ResponseEntity<categoria> editarCategoria(@PathVariable int id, @RequestBody categoria categoriaActualizada) {

		try {

			categoria editarCategoria = categoriaService.editarCategoria(id, categoriaActualizada);
			return ResponseEntity.ok(editarCategoria);
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarCategoria(@PathVariable int id) {
		try {
			categoria categoria = categoriaService.obtenerPorId(id);

			if (categoria == null) {
				return ResponseEntity.notFound().build();
			}

			categoriaService.eliminarCategoria(id);
			return ResponseEntity.noContent().build();

		} catch (Exception e) {
			return ResponseEntity.status(500).build();
		}
	}
	
}