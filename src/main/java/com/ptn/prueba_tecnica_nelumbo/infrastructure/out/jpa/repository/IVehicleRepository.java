package com.ptn.prueba_tecnica_nelumbo.infrastructure.out.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ptn.prueba_tecnica_nelumbo.infrastructure.out.jpa.entity.VehicleEntity;

public interface IVehicleRepository extends JpaRepository<VehicleEntity, Long> {

	VehicleEntity findByPlate(String plate);
	
	List<VehicleEntity> findAllByStatus(String status);

}