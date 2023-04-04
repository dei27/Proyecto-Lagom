package com.lagom.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lagom.core.model.UsuarioRol;

public interface UsarioRolRepository extends JpaRepository<UsuarioRol, Long> {

	@Query("SELECT r.nombre FROM UsuarioRol ur INNER JOIN ur.rol r INNER JOIN usuario u WHERE u.id = :idusuario")
	List<String> findNombresByUsuarioId(@Param("idusuario") Long id);
}
