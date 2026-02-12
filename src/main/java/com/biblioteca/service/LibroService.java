package com.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.Entity.libros;
import com.biblioteca.repository.ILibrosRepository;

@Service
public class LibroService {
	
	@Autowired
	private ILibrosRepository LibrosRepo;

	public List<libros> listarLibros() {
		return LibrosRepo.findAll();
	}

	public libros obtenerLibrosPorId(int id) {
		return LibrosRepo.findById(id).orElse(null);
	}

	public libros agregarLibros(libros agregar) {
		return LibrosRepo.save(agregar);
	}

	public void eliminarLibros(int id_libros) {
		LibrosRepo.deleteById(id_libros);
	}

	public libros editarLibros(int id_libros, libros nuevo) {
		libros editLibros = LibrosRepo.findById(id_libros)
				.orElseThrow(() -> new RuntimeException("Libro no encontrado"));

		editLibros.setTitulo(nuevo.getTitulo());
		editLibros.setAnio(nuevo.getAnio());
		editLibros.setIsbn(nuevo.getIsbn());
		editLibros.setEditorial(nuevo.getEditorial());
		editLibros.setAutores(nuevo.getAutores());
		editLibros.setCategoria(nuevo.getCategoria());
		editLibros.setEstadoLibro(nuevo.getEstadoLibro());

		return LibrosRepo.save(editLibros);
	}

}