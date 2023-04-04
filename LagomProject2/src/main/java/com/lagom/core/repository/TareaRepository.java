package com.lagom.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lagom.core.model.Tarea;

public interface TareaRepository extends JpaRepository<Tarea, Long> {
	
	@Query("SELECT t FROM Tarea t WHERE t.id NOT IN (SELECT ut.tarea.id FROM UsuarioTarea ut) order by t.nombre")
	List<Tarea> findTareasNotAssigned();
	
	@Query("SELECT t FROM Tarea t INNER JOIN UsuarioTarea ut ON t.id = ut.tarea.id WHERE ut.estado = 'En Progreso' order by t.nombre")
	List<Tarea> findTareasProgreso();
	
	@Query("SELECT t  FROM Tarea t INNER JOIN UsuarioTarea ut ON t.id = ut.tarea.id WHERE ut.estado = 'Finalizada' order by t.nombre")
	List<Tarea> findTareasFinalizadas();
}
