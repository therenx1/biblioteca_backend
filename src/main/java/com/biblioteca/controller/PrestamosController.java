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

import com.biblioteca.Entity.prestamos;
import com.biblioteca.service.PrestamoService;

@RestController
@RequestMapping("api/prestamos")
@CrossOrigin(origins = {"http://localhost:4200", "https://bibliotecafrontend-production.up.railway.app"}, allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class PrestamosController {

	@Autowired
	private PrestamoService PServicio;

	@GetMapping
	public ResponseEntity<List<prestamos>> listarPrestamos() {
	    List<prestamos> prestamos = PServicio.listarPrestamos();
	    return ResponseEntity.ok(prestamos);
	}


	@GetMapping("{id}")
	public ResponseEntity<prestamos> obtenerPrestamosPorId(@PathVariable int id) {

		prestamos prestamo = PServicio.obtenerPrestamosPorId(id);

		if (prestamo == null) {
			return ResponseEntity.notFound().build();

		}

		return ResponseEntity.ok(prestamo);
	}

	@PostMapping("/grabar")
	public ResponseEntity<prestamos> agregarPrestamos(@RequestBody prestamos prestamo) {
		try {
			prestamos nuevo = PServicio.agregarPrestamos(prestamo);
			return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PutMapping("{id}")
	public ResponseEntity<prestamos> actualizarPrestamos(@PathVariable int id,
			@RequestBody prestamos prestamoActualizado) {

		try {
			prestamos prestamoEditado = PServicio.editarPrestamos(id, prestamoActualizado);
			return ResponseEntity.ok(prestamoEditado);

		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarPrestamos(@PathVariable int id) {
		try {
			prestamos prestamo = PServicio.obtenerPrestamosPorId(id);

			if (prestamo == null) {
				return ResponseEntity.notFound().build();
			}

			PServicio.eliminarPrestamos(id);
			return ResponseEntity.noContent().build();

		} catch (Exception e) {
			return ResponseEntity.status(500).build();
		}
	}

}