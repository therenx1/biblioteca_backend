package com.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biblioteca.Entity.libros;

@Repository
public interface ILibrosRepository extends JpaRepository<libros, Integer> {

}
