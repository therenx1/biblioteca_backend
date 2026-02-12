package com.biblioteca.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biblioteca.Entity.trabajadores;

@Repository
public interface ITrabajadoresRepository extends JpaRepository<trabajadores, Integer> {
	Optional<trabajadores> findByEmail(String email);
	
	Optional<trabajadores> findByRol_Roles(String rol);
}
