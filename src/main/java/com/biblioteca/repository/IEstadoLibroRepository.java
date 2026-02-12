package com.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biblioteca.Entity.estadoLibro;

@Repository
public interface IEstadoLibroRepository extends JpaRepository<estadoLibro, Integer> {

}
