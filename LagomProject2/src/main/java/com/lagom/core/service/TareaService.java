package com.lagom.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lagom.core.model.Tarea;
import com.lagom.core.repository.TareaRepository;

@Service
public class TareaService {

	@Autowired
	private TareaRepository tareaRepository;
	
	public List<Tarea> obtenerTareas(){
		return tareaRepository.findTareasNotAssigned();
	}
	
	public List<Tarea> obtenerTareasProgreso(){
		return tareaRepository.findTareasProgreso();
	}
	
	public List<Tarea> obtenerTareasFinalizadas(){
		return tareaRepository.findTareasFinalizadas();
	}
	
//	public List<Tarea> obtenerAsignadasTareas(){
//		return tareaRepository.findTareasNotAssigned();
//	}
	
	public Tarea obtenerTareaPorId(Long id) {
        return tareaRepository.findById(id).orElse(null);
    }

    public Tarea guardarTarea(Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    public Tarea actualizarTarea(Long id, Tarea tareaDetalles) {
        Tarea tarea = tareaRepository.findById(id).orElse(null);

        tarea.setNombre(tareaDetalles.getNombre());
        tarea.setDescripcion(tareaDetalles.getDescripcion());
        tarea.setValorPuntos(tareaDetalles.getValorPuntos());

        Tarea tareaActualizada = tareaRepository.save(tarea);
        return tareaActualizada;
    }

    public void eliminarTarea(Long id) {
        Tarea tarea = tareaRepository.findById(id).orElse(null);

        tareaRepository.delete(tarea);
    }
}
