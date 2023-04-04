package com.lagom.core.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios_tareas")
public class UsuarioTarea {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tarea")
	public Tarea tarea;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario")
	public Usuario usuario;

	@Column(name = "puntos_obtenidos", nullable = false, columnDefinition = "DECIMAL(10,2) DEFAULT 0")
	private double puntosObtenidos;

	@Column(name = "fecha_realizacion", nullable = false, columnDefinition = "DATETIME DEFAULT NOW()")
	private Date fechaRealizacion;

	@Column(name = "estado", nullable = false, columnDefinition = "VARCHAR(25) DEFAULT 'En Progreso'")
	private String estado;
	
	public UsuarioTarea() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Tarea getTarea() {
		return tarea;
	}

	public void setTarea(Tarea tarea) {
		this.tarea = tarea;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public UsuarioTarea(Tarea tarea, Usuario usuario) {
		this.tarea = tarea;
		this.usuario = usuario;
	}

	public double getPuntosObtenidos() {
		return puntosObtenidos;
	}

	public void setPuntosObtenidos(double puntosObtenidos) {
		this.puntosObtenidos = puntosObtenidos;
	}

	public Date getFechaRealizacion() {
		return fechaRealizacion;
	}

	public void setFechaRealizacion(Date fechaRealizacion) {
		this.fechaRealizacion = fechaRealizacion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
