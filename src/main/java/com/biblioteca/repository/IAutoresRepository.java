package com.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biblioteca.Entity.autores;

@Repository
public interface IAutoresRepository extends JpaRepository<autores, Integer> {

}
