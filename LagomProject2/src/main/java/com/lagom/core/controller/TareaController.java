package com.lagom.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lagom.core.model.Tarea;
import com.lagom.core.service.TareaService;

@Controller
@RequestMapping("/tareas")
public class TareaController {

	@Autowired
	private TareaService tarea;
	
	private ModelAndView model;
	
	@GetMapping("/listar")
	public ModelAndView tareas() {
		model = new ModelAndView("tareas");
		model.addObject("tareas", tarea.obtenerTareas());
		return model;
	}
	
	@GetMapping("/new")
    public String newTarea(Model model) {
        model.addAttribute("tarea", new Tarea());
        return "tareas/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("tarea") Tarea task) {
    	tarea.guardarTarea(task);
        return "redirect:/tareas/list";
    }
	
	@GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        Tarea task = tarea.obtenerTareaPorId(id);
        model.addAttribute("tarea", task);
        return "tareas/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
    	tarea.eliminarTarea(id);
        return "redirect:/tareas/listar";
    }
	
}
