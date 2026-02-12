package com.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biblioteca.Entity.categoria;

@Repository
public interface ICategoriaRepository extends JpaRepository<categoria, Integer> {

}
