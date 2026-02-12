package com.biblioteca.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@Table(name = "autores")
@NoArgsConstructor
@AllArgsConstructor
public class autores {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_autores;
	
	@Column(name = "nombre_autores")
	private String nombreAutores;
	
	private String nacionalidad;

	public Integer getId_autores() {
		return id_autores;
	}

	public void setId_autores(Integer id_autores) {
		this.id_autores = id_autores;
	}

	public String getNombreAutores() {
		return nombreAutores;
	}

	public void setNombreAutores(String nombreAutores) {
		this.nombreAutores = nombreAutores;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	
	
	

}
