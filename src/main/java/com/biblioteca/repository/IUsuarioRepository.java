package com.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biblioteca.Entity.usuario;

@Repository
public interface IUsuarioRepository extends JpaRepository<usuario, Integer> {

}
