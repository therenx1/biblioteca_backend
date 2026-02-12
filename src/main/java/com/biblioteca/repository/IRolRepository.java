package com.biblioteca.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biblioteca.Entity.rol;

@Repository
public interface IRolRepository extends JpaRepository<rol, Integer> {

	Optional<rol> findByRoles(String rol);
}
