package com.biblioteca.Entity;

import java.math.BigDecimal;
import java.time.LocalDate;

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
@Table(name = "prestamos")
@NoArgsConstructor
@AllArgsConstructor
public class prestamos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_prestamos;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
	private usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "id_libros", referencedColumnName = "id_libros")
	private libros libros;
	
	
	private String fecha_prestamo;
	private String fecha_devolucion;
	private String fecha_real;
	
	private BigDecimal multa;
	
	@ManyToOne
	@JoinColumn(name = "id_estado", referencedColumnName = "id_estado")
	private estadoLibro estadoLibro;
	
	private String comentarios;

	public Integer getId_prestamos() {
		return id_prestamos;
	}

	public void setId_prestamos(Integer id_prestamos) {
		this.id_prestamos = id_prestamos;
	}

	public usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(usuario usuario) {
		this.usuario = usuario;
	}

	public libros getLibros() {
		return libros;
	}

	public void setLibros(libros libros) {
		this.libros = libros;
	}

	public String getFecha_prestamo() {
		return fecha_prestamo;
	}

	public void setFecha_prestamo(String fecha_prestamo) {
		this.fecha_prestamo = fecha_prestamo;
	}

	public String getFecha_devolucion() {
		return fecha_devolucion;
	}

	public void setFecha_devolucion(String fecha_devolucion) {
		this.fecha_devolucion = fecha_devolucion;
	}

	public String getFecha_real() {
		return fecha_real;
	}

	public void setFecha_real(String fecha_real) {
		this.fecha_real = fecha_real;
	}

	public BigDecimal getMulta() {
		return multa;
	}

	public void setMulta(BigDecimal multa) {
		this.multa = multa;
	}

	public estadoLibro getEstadoLibro() {
		return estadoLibro;
	}

	public void setEstadoLibro(estadoLibro estadoLibro) {
		this.estadoLibro = estadoLibro;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	
	
	
}
