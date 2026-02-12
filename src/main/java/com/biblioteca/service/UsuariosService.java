package com.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.Entity.usuario;
import com.biblioteca.repository.IUsuarioRepository;

@Service
public class UsuariosService {

	@Autowired
	private IUsuarioRepository UsuaRepo;

	public List<usuario> listarUsuarios() {
		return UsuaRepo.findAll();
	}

	public usuario obtenerUsuariosPorId(int id) {
		return UsuaRepo.findById(id).orElse(null);
	}

	public usuario agregarUsuarios(usuario agregar) {
		return UsuaRepo.save(agregar);
	}

	public void eliminarUsuarios(int id_usuarios) {
		UsuaRepo.deleteById(id_usuarios);
	}

	public usuario editarUsuarios(int id_usuarios, usuario nuevo) {
		usuario editUsua = UsuaRepo.findById(id_usuarios)
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

		editUsua.setDni(nuevo.getDni());
		editUsua.setCorreo(nuevo.getCorreo());
		editUsua.setTelefono(nuevo.getTelefono());
		editUsua.setDireccion(nuevo.getDireccion());
		editUsua.setGenero(nuevo.getGenero());

		return UsuaRepo.save(editUsua);
	}

}