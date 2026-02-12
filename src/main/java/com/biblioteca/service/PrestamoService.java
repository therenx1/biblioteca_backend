package com.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.Entity.estadoLibro;
import com.biblioteca.Entity.libros;
import com.biblioteca.Entity.prestamos;
import com.biblioteca.Entity.usuario;
import com.biblioteca.repository.IEstadoLibroRepository;
import com.biblioteca.repository.ILibrosRepository;
import com.biblioteca.repository.IPrestamosRepository;
import com.biblioteca.repository.IUsuarioRepository;

@Service
public class PrestamoService {
	
	@Autowired
	private IUsuarioRepository usuarioRepo;

	@Autowired
	private ILibrosRepository librosRepo;

	@Autowired
	private IEstadoLibroRepository estadoRepo;
	
	@Autowired
	private IPrestamosRepository PrestRepo;


	public List<prestamos> listarPrestamos() {
		return PrestRepo.findAll();
	}

	public prestamos obtenerPrestamosPorId(int id) {
		return PrestRepo.findById(id).orElse(null);
	}

	public prestamos agregarPrestamos(prestamos p) {

	    usuario u = usuarioRepo.findById(
	        p.getUsuario().getId_usuario()
	    ).orElseThrow(() -> new RuntimeException("Usuario no existe"));

	    libros l = librosRepo.findById(
	        p.getLibros().getId_libros()
	    ).orElseThrow(() -> new RuntimeException("Libro no existe"));

	    estadoLibro e = estadoRepo.findById(
	        p.getEstadoLibro().getId_estado()
	    ).orElseThrow(() -> new RuntimeException("Estado no existe"));

	    p.setUsuario(u);
	    p.setLibros(l);
	    p.setEstadoLibro(e);

	    return PrestRepo.save(p);
	}


	public void eliminarPrestamos(int id_prestamos) {
		PrestRepo.deleteById(id_prestamos);
	}

	public prestamos editarPrestamos(int id_prestamos, prestamos nuevo) {

		prestamos editPrestamo = PrestRepo.findById(id_prestamos)
				.orElseThrow(() -> new RuntimeException("Prestamo no encontrado"));

		editPrestamo.setUsuario(nuevo.getUsuario());
		editPrestamo.setLibros(nuevo.getLibros());
		editPrestamo.setFecha_prestamo(nuevo.getFecha_prestamo());
		editPrestamo.setFecha_devolucion(nuevo.getFecha_devolucion());
		editPrestamo.setFecha_real(nuevo.getFecha_real());
		editPrestamo.setMulta(nuevo.getMulta());
		editPrestamo.setEstadoLibro(nuevo.getEstadoLibro());
		editPrestamo.setComentarios(nuevo.getComentarios());

		return PrestRepo.save(editPrestamo);
	}

}