package com.lagom.core.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lagom.core.model.UsuarioTareaDAO;
import com.lagom.core.service.TareaService;
import com.lagom.core.service.UserDetailsServiceImpl;
import com.lagom.core.service.UsuarioTareaService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private TareaService tarea;
	
	@Autowired
	private UsuarioTareaService usuarioTarea;
	
	@GetMapping("")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("indexUsuario");
		List<Object[]> tareasP =  usuarioTarea.tareasUsuarioPropias(UserDetailsServiceImpl.usuario.getId(), "En Progreso");
		List<UsuarioTareaDAO> tareasProceso = new ArrayList<>();
	    for (Object[] obj : tareasP) {
	    	UsuarioTareaDAO dto = new UsuarioTareaDAO();
	    	dto.setNombre((String) obj[0]);
	        dto.setDescripcion((String) obj[1]); 
	        dto.setValorPuntos((int) obj[2]);
	        dto.setFechaCreacion((Date) obj[3]);
	        dto.setIdTarea((int) obj[4]);
	        dto.setPuntosObtenidos((double) obj[5]);
	        dto.setFechaRealizacion((Date) obj[6]);
	        tareasProceso.add(dto);
	    }
	    
	    List<Object[]> tareasF =  usuarioTarea.tareasUsuarioPropias(UserDetailsServiceImpl.usuario.getId(), "Finalizada");
		List<UsuarioTareaDAO> tareasFinalizadas = new ArrayList<>();
	    for (Object[] obj : tareasF) {
	    	UsuarioTareaDAO dto = new UsuarioTareaDAO();
	    	dto.setNombre((String) obj[0]);
	        dto.setDescripcion((String) obj[1]); 
	        dto.setValorPuntos((int) obj[2]);
	        dto.setFechaCreacion((Date) obj[3]);
	        dto.setIdTarea((int) obj[4]);
	        dto.setPuntosObtenidos((double) obj[5]);
	        dto.setFechaRealizacion((Date) obj[6]);
	        tareasFinalizadas.add(dto);
	    }
	    
		model.addObject("tareasProgreso", tareasProceso);
		model.addObject("tareasFinalizadas", tareasFinalizadas);
		model.addObject("tareasSinUsuario", tarea.obtenerTareas());
		return model;
	}
	
	@PostMapping("/{id}")
	public  String actualizarTarea(@PathVariable("id") Long tareaId) {
		usuarioTarea.guardarUsuarioTarea(UserDetailsServiceImpl.usuario.getId(),tareaId);
		usuarioTarea.actualizarEstado("En Progreso", tareaId);
		return "redirect:/usuario";  
	}
	
	@PostMapping("/modificar/{id}")
	public  String modificarTarea(@PathVariable("id") Long tareaId, @RequestParam("estado") String estado) {

		if (estado.equals("Pendiente")) {
			usuarioTarea.eliminarUsuarioAsignado(tareaId);
		}
		else {
			usuarioTarea.actualizarEstado(estado, tareaId);
		}
		return "redirect:/usuario";  
	}
}
