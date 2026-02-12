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
@Table(name = "trabajadores")
@NoArgsConstructor
@AllArgsConstructor
public class trabajadores {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_trabajador;
	private String nombre;
	private String apellido;
	@Column(unique = true)
	private String email;
	private Integer edad;
	private String password;
	
	@ManyToOne
	@JoinColumn(name = "id_rol")
	private rol rol;

	public Integer getId_trabajador() {
		return id_trabajador;
	}

	public void setId_trabajador(Integer id_trabajador) {
		this.id_trabajador = id_trabajador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public rol getRol() {
		return rol;
	}

	public void setRol(rol rol) {
		this.rol = rol;
	}
	
	
	
}
