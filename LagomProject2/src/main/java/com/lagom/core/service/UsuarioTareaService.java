package com.lagom.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lagom.core.model.Tarea;
import com.lagom.core.model.Usuario;
import com.lagom.core.model.UsuarioTarea;
import com.lagom.core.repository.UsuarioTareaRepository;

@Service
public class UsuarioTareaService {

	@Autowired
	private UsuarioTareaRepository usuarioTareaRepo;
//
	public List<UsuarioTarea> encontrarUsuario(Usuario usuario) {
		return usuarioTareaRepo.findByUsuario(usuario);
	};
	
	public UsuarioTarea encontrarUsuarioById(Long id) {
		return usuarioTareaRepo.findById(id).orElseGet(null);
	};
//
	public UsuarioTarea encontrarTarea(Tarea tarea) {
		return usuarioTareaRepo.findByTarea(tarea);
	}
	
	
	public void guardarUsuarioTarea(Long usuarioId, Long tarea) {
		usuarioTareaRepo.insertarUsuariosRoles(usuarioId, tarea);
	}
	
	public void actualizarEstado(String estado, Long idTarea) {
		usuarioTareaRepo.actualizarEstado(estado, idTarea);
	}
	
	public List<Object[]> tareasUsuarioPropias(Long idUsuario,String estado){
		return usuarioTareaRepo.tareasUsuarioPropias(idUsuario, estado);
	}
	
	public void eliminarUsuarioAsignado(Long idTarea) {
		usuarioTareaRepo.eliminarUsuarioTarea(idTarea);
	}
//
//	public List<UsuarioTarea> encontrarPorFecha(LocalDate fechaInicio, LocalDate fechaFin){
//		return usuarioTareaRepo.findByFechaRealizacionBetween(fechaInicio, fechaFin);
//	}
}
