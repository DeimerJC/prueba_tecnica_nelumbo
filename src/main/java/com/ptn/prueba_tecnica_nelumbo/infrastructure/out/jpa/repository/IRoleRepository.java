package com.ptn.prueba_tecnica_nelumbo.infrastructure.out.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ptn.prueba_tecnica_nelumbo.infrastructure.out.jpa.entity.RoleEntity;

public interface IRoleRepository extends JpaRepository<RoleEntity, Long> {

	RoleEntity findByRoleName(String Rolename);

}