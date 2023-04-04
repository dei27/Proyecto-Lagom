package com.lagom.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lagom.core.model.Tarea;
import com.lagom.core.model.Usuario;
import com.lagom.core.model.UsuarioTarea;

import jakarta.transaction.Transactional;

public interface UsuarioTareaRepository extends JpaRepository<UsuarioTarea, Long> {

	List<UsuarioTarea> findByUsuario(Usuario usuario);
//  
	
    UsuarioTarea findByTarea(Tarea tarea);
    
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO usuarios_tareas (id_usuario, id_tarea) VALUES (:idUsuario, :idTarea)", nativeQuery = true)
    void insertarUsuariosRoles(@Param("idUsuario") Long idUsuario, @Param("idTarea") Long idTarea);
    
    @Transactional
    @Modifying
    @Query("UPDATE UsuarioTarea ut SET ut.estado = :estado WHERE ut.tarea.id = :idTarea")
    void actualizarEstado(@Param("estado") String estado, @Param("idTarea") Long idTarea);
//    
//    List<UsuarioTarea> findByFechaRealizacionBetween(LocalDate fechaInicio, LocalDate fechaFin);
    
    @Query(value = "SELECT t.nombre, t.descripcion, t.valor_puntos, t.fecha_creacion, ut.id_tarea, ut.puntos_obtenidos, ut.fecha_realizacion "
            + "FROM tareas t "
            + "INNER JOIN usuarios_tareas ut ON t.id = ut.id_tarea "
            + "WHERE ut.id_usuario = :idUsuario AND ut.estado = :estado order by t.nombre", nativeQuery = true)
    public List<Object[]> tareasUsuarioPropias(@Param("idUsuario") Long idUsuario, @Param("estado") String estado);
    
    
    @Transactional
    @Modifying
    @Query(value = "Delete from UsuarioTarea ut where ut.tarea.id = :idTarea")
    void eliminarUsuarioTarea(@Param("idTarea") Long idTarea);

}
