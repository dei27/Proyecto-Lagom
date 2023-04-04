package com.lagom.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lagom.core.model.Rol;

public interface RolRepository extends JpaRepository<Rol, Long> {

}
