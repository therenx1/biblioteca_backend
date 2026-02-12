package com.biblioteca.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "libros")
@NoArgsConstructor
@AllArgsConstructor
public class libros {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_libros;
	private String titulo;
	private int anio;
	@Column(unique = true)
	private String isbn;
	private String editorial;
	
	@ManyToOne
	@JoinColumn(name = "id_autores", referencedColumnName = "id_autores")
	private autores autores;
	
	@ManyToOne
	@JoinColumn(name = "id_categoria", referencedColumnName = "id_categoria")
	private categoria categoria;
	
	@ManyToOne
	@JoinColumn(name = "id_estado", referencedColumnName = "id_estado")
	private estadoLibro estadoLibro;

	public Integer getId_libros() {
		return id_libros;
	}

	public void setId_libros(Integer id_libros) {
		this.id_libros = id_libros;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public autores getAutores() {
		return autores;
	}

	public void setAutores(autores autores) {
		this.autores = autores;
	}

	public categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(categoria categoria) {
		this.categoria = categoria;
	}

	public estadoLibro getEstadoLibro() {
		return estadoLibro;
	}

	public void setEstadoLibro(estadoLibro estadoLibro) {
		this.estadoLibro = estadoLibro;
	}
	
	
	
	
}
