package com.biblioteca.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.Entity.autores;
import com.biblioteca.repository.IAutoresRepository;

@Service
public class autoresService {
		
	@Autowired
	private IAutoresRepository autoresRepository;
	
	public List<autores> listarAutores() {
		return autoresRepository.findAll();
	}
	
	public autores obtenerPorId(int id) {
		return autoresRepository.findById(id).orElse(null);
	}
	
	public autores agregarAutores(autores agregar) {
		return autoresRepository.save(agregar);
	}
	
	public void eliminarAutores(int id_autores) {
		autoresRepository.deleteById(id_autores);
	}
	
	public autores editarAutores(int id_autores, autores nuevoDato) {
		autores autor = autoresRepository.findById(id_autores).orElseThrow(() -> new RuntimeException("Autor no encontrado"));
	
		autor.setNacionalidad(nuevoDato.getNacionalidad());
		autor.setNombreAutores(nuevoDato.getNombreAutores());
		return autoresRepository.save(autor);
	}

}
