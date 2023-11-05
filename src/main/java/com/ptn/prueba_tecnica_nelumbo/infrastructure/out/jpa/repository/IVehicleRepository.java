package com.ptn.prueba_tecnica_nelumbo.infrastructure.out.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ptn.prueba_tecnica_nelumbo.infrastructure.out.jpa.entity.VehicleEntity;

public interface IVehicleRepository extends JpaRepository<VehicleEntity, Long> {

	VehicleEntity findByPlate(String plate);
	
	List<VehicleEntity> findAllByStatus(String status);
	
	@Query("select v from VehicleEntity v where v.status='ENABLE' and NOT EXISTS (SELECT ph FROM ParkingHistoryEntity ph WHERE v.parkingEntity.id = ph.parkingEntity.id and v.id=ph.vehicleEntity.id)")
    List<VehicleEntity> vehiclesParkedFirstTime();
	
	@Query("SELECT v FROM VehicleEntity v WHERE v.plate LIKE %:plate_search% and v.status='ENABLE'")
	List<VehicleEntity> searchVehicles(@Param("plate_search") String plateSearch);

}