
package com.biblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.Entity.estadoLibro;
import com.biblioteca.service.estadoLibroService;


@RestController
@RequestMapping("/api/estadoLibro")
@CrossOrigin(origins = {"http://localhost:4200", "https://bibliotecafrontend-production.up.railway.app"}, allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class EstadoLibroController {

	
	@Autowired
	private estadoLibroService estadoLibroService;

	@GetMapping
	public ResponseEntity<List<estadoLibro>> listarEstadoLibro() {
		List<estadoLibro> estadoLibro = estadoLibroService.listarEstadoLibro();

		if (estadoLibro.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(estadoLibro);

	}
}
