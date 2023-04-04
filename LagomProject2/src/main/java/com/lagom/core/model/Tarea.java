package com.lagom.core.model;


import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "tareas")
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false, length = 255)
    private String descripcion;

    @Column(nullable = false)
    private int valorPuntos;
    
    @Column(name = "fecha_creacion", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private Date fechaCreacion;
    
    
    public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getValorPuntos() {
		return valorPuntos;
	}

	public void setValorPuntos(int valorPuntos) {
		this.valorPuntos = valorPuntos;
	}

	public Tarea(String nombre, String descripcion, int valorPuntos) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.valorPuntos = valorPuntos;
		this.fechaCreacion = new Date();
	}
	
	 @PrePersist
    protected void onCreate() {
        fechaCreacion = new Date();
    }

	
	public Tarea() {}
    
}

