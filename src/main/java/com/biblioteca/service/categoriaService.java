package com.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.biblioteca.Entity.categoria;
import com.biblioteca.repository.ICategoriaRepository;

@Service
public class categoriaService {
	
	
	@Autowired
	private ICategoriaRepository categoriaRepository;
	
	public List<categoria> listarCategoria() {
		return categoriaRepository.findAll();
	}
	
	public categoria obtenerPorId(int id_categoria) {
		return categoriaRepository.findById(id_categoria).orElse(null);
	}
	
	public categoria agregarCategoria(categoria agregar) {
		return categoriaRepository.save(agregar);
	}
	
	public void eliminarCategoria(int id_categoria) {
		categoriaRepository.deleteById(id_categoria);
	}
	
	public categoria editarCategoria(int id_categoria, categoria nuevoDato) {
		categoria categoria = categoriaRepository.findById(id_categoria).orElseThrow(() -> new RuntimeException("categoria no encontrada"));
		
		categoria.setNombreCategoria(nuevoDato.getNombreCategoria());
		return categoriaRepository.save(categoria);
	}
	
}
