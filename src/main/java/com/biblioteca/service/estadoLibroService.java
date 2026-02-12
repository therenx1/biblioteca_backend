package com.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.Entity.estadoLibro;
import com.biblioteca.repository.IEstadoLibroRepository;

@Service
public class estadoLibroService {
	
	@Autowired
	private IEstadoLibroRepository estadoLibroRepo;
	
	public List<estadoLibro> listarEstadoLibro() {
		return estadoLibroRepo.findAll();
	}
}
