package com.lagom.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lagom.core.model.Tarea;
import com.lagom.core.model.UsuarioTarea;
import com.lagom.core.service.TareaService;
import com.lagom.core.service.UsuarioTareaService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private TareaService tarea;
	
	private Tarea tareaModel;
	
	private UsuarioTarea usuarioTareaModel;
	
	@Autowired
	private UsuarioTareaService usuarioTareaService;
	
	@GetMapping("")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("indexAdmin");
		model.addObject("tareasPendientes", tarea.obtenerTareas());
		model.addObject("tareasEnProgreso", tarea.obtenerTareasProgreso());
		model.addObject("tareasFinalizadas", tarea.obtenerTareasFinalizadas());
		return model;
	}
	
	@PostMapping("/{id}")
    public String eliminar(@PathVariable("id") Long id) {
    	tarea.eliminarTarea(id);
        return "redirect:/admin";
    }
	
	@PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable("id") Long id, 	@RequestParam("nombre") String nombre,
												            @RequestParam("descripcion") String descripcion, 
												            @RequestParam("valorPuntos") int valorPuntos){
		tareaModel = tarea.obtenerTareaPorId(id);
		tareaModel.setNombre(nombre);
		tareaModel.setDescripcion(descripcion);
		tareaModel.setValorPuntos(valorPuntos);
    	tarea.guardarTarea(tareaModel);
        return "redirect:/admin";
    }
	
	@PostMapping("/calificar/{id}")
    public String calficar(@PathVariable("id") Long id, @RequestParam("opcion") int opcion,  @RequestParam("puntos") double puntos){
		tareaModel = tarea.obtenerTareaPorId(id);
		usuarioTareaModel = usuarioTareaService.encontrarTarea(tareaModel);
		
		switch (opcion) {
		case 1:
			usuarioTareaModel.setPuntosObtenidos(puntos);
			break;
		case 2:
			usuarioTareaModel.setPuntosObtenidos(puntos/2);
			break;
		default:
			usuarioTareaModel.setPuntosObtenidos(0);
			break;
		}
		
    	tarea.guardarTarea(tareaModel);
        return "redirect:/admin";
    }
	
	@GetMapping("/formTarea")
    public String newTarea() {
        return "formTarea";
    }
	
	@PostMapping("/crearTarea")
    public String newTarea(@RequestParam("nombre") String nombre, @RequestParam("descripcion")String descripcion,@RequestParam("valor")int puntos) {
        tareaModel = new Tarea(nombre, descripcion, puntos);
        tarea.guardarTarea(tareaModel);
        return "redirect:/admin";
    }
}
