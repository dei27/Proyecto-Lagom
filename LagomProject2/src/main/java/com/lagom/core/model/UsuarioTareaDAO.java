package com.lagom.core.model;

import java.util.Date;

public class UsuarioTareaDAO {

	public String nombre;
	public String descripcion;
	private int valorPuntos;
	private Date fechaCreacion;
	private int idTarea;
	private double puntosObtenidos;
	private Date fechaRealizacion;
	
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
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
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
	public int getIdTarea() {
		return idTarea;
	}
	public void setIdTarea(int idTarea) {
		this.idTarea = idTarea;
	}
}
